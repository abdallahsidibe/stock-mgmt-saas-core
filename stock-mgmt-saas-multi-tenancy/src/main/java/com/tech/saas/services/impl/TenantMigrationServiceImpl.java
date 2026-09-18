package com.tech.saas.services.impl;

import com.tech.saas.entities.Tenant;
import com.tech.saas.entities.TenantStatus;
import com.tech.saas.repositories.TenantRepository;
import com.tech.saas.services.TenantMigrationService;
import com.tech.saas.utils.TenantCodeValidator;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantMigrationServiceImpl implements TenantMigrationService {

    private final TenantRepository tenantRepository;
    private final DataSource dataSource;
    private final ObjectProvider<MeterRegistry> meterRegistryProvider;

    @Override
    public TenantMigrationResult migrateTenant(final String companyCode) {
        final String schemaName = TenantCodeValidator.toSafeSchemaName(companyCode);
        final long startTime = System.currentTimeMillis();

        try {
            log.info("Starting Flyway migration for tenant schema: '{}'", schemaName);
            final Flyway tenantFlyway = Flyway.configure()
                    .dataSource(this.dataSource)
                    .schemas(schemaName)
                    .defaultSchema(schemaName)
                    .locations("classpath:db/migration/tenant")
                    .baselineOnMigrate(true)
                    .table("flyway_schema_history")
                    .validateOnMigrate(true)
                    .cleanDisabled(true)
                    .load();

            final int executed = tenantFlyway.migrate().migrationsExecuted;
            final long durationMs = System.currentTimeMillis() - startTime;
            log.info("Successfully migrated tenant schema: '{}' in {} ms. Executed migrations: {}", schemaName, durationMs, executed);

            recordMigrationMetrics(companyCode, true, durationMs);
            return new TenantMigrationResult(companyCode, schemaName, true, executed, durationMs, null);

        } catch (final Exception e) {
            final long durationMs = System.currentTimeMillis() - startTime;
            log.error("Failed to migrate tenant schema: '{}' after {} ms", schemaName, durationMs, e);
            recordMigrationMetrics(companyCode, false, durationMs);
            return new TenantMigrationResult(companyCode, schemaName, false, 0, durationMs, e.getMessage());
        }
    }

    @Override
    public TenantBatchMigrationResult migrateAllTenants(final int concurrency) {
        final long batchStartTime = System.currentTimeMillis();
        final List<Tenant> activeTenants = this.tenantRepository.findAll().stream()
                .filter(t -> t.getStatus() == TenantStatus.ACTIVE)
                .toList();

        final int totalTenants = activeTenants.size();
        log.info("Batch tenant migration started for {} active tenants with concurrency level {}", totalTenants, concurrency);

        if (totalTenants == 0) {
            return new TenantBatchMigrationResult(0, 0, 0, 0, List.of());
        }

        final int poolSize = Math.min(Math.max(1, concurrency), 20);
        final ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

        try {
            final List<CompletableFuture<TenantMigrationResult>> futures = activeTenants.stream()
                    .map(tenant -> CompletableFuture.supplyAsync(
                            () -> migrateTenant(tenant.getCompanyCode()),
                            executorService
                    ))
                    .toList();

            final List<TenantMigrationResult> results = futures.stream()
                    .map(CompletableFuture::join)
                    .toList();

            final int successful = (int) results.stream().filter(TenantMigrationResult::success).count();
            final int failed = totalTenants - successful;
            final long totalDurationMs = System.currentTimeMillis() - batchStartTime;

            log.info("Completed batch tenant migration in {} ms. Total: {}, Successful: {}, Failed: {}",
                    totalDurationMs, totalTenants, successful, failed);

            return new TenantBatchMigrationResult(totalTenants, successful, failed, totalDurationMs, results);

        } finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (final InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void recordMigrationMetrics(final String companyCode, final boolean success, final long durationMs) {
        final MeterRegistry meterRegistry = this.meterRegistryProvider.getIfAvailable();
        if (meterRegistry != null) {
            final String status = success ? "success" : "failure";
            meterRegistry.counter("tenant.migration.result", "status", status).increment();
            meterRegistry.timer("tenant.migration.duration", "status", status).record(durationMs, TimeUnit.MILLISECONDS);
        }
    }
}
