package com.tech.saas.services.impl;

import com.tech.saas.entities.Tenant;
import com.tech.saas.exceptions.TenantProvisioningException;
import com.tech.saas.services.ProvisioningService;
import com.tech.saas.services.TenantSeedService;
import com.tech.saas.utils.TenantCodeValidator;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProvisioningServiceImpl implements ProvisioningService {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;
    private final ObjectProvider<MeterRegistry> meterRegistryProvider;
    private final ObjectProvider<TenantSeedService> tenantSeedServiceProvider;

    @Value("${app.seed-demo-data-on-provision:true}")
    private boolean seedDemoDataOnProvision;

    @Override
    public void provisionTenant(final Tenant tenant) {
        final String schemaName = TenantCodeValidator.toSafeSchemaName(tenant.getCompanyCode());
        final long startTime = System.currentTimeMillis();

        log.info("Tenant provisioning started for company: '{}' (schema: '{}')", tenant.getCompanyName(), schemaName);

        try {
            // 1. Create the Postgres schema securely
            createSchema(schemaName);
            log.info("Schema created successfully: '{}'", schemaName);

            // 2. Run Flyway migrations for this tenant schema
            runTenantMigrations(schemaName);
            log.info("Tenant migrations completed successfully for schema: '{}'", schemaName);

            // 3. Seed demo data if configured (dev/demo environments)
            if (this.seedDemoDataOnProvision) {
                final TenantSeedService seedService = this.tenantSeedServiceProvider.getIfAvailable();
                if (seedService != null) {
                    log.info("Auto-seeding demo data for tenant schema: '{}'", schemaName);
                    seedService.seedDemoData(tenant.getCompanyCode());
                }
            }

            // Record success metric if Micrometer is configured
            recordMetrics(true, System.currentTimeMillis() - startTime);

        } catch (final Exception e) {
            final long duration = System.currentTimeMillis() - startTime;
            recordMetrics(false, duration);
            log.error("Failed to provision tenant: '{}' (schema: '{}') after {} ms", tenant.getCompanyName(), schemaName, duration, e);

            // Rollback: attempt schema cleanup
            try {
                dropSchema(schemaName);
                log.info("Schema rollback completed for '{}'", schemaName);
            } catch (final Exception exp) {
                log.error("Failed to rollback schema creation for tenant: '{}'", tenant.getCompanyName(), exp);
            }

            throw new TenantProvisioningException("Failed to provision tenant '" + tenant.getCompanyName() + "': " + e.getMessage());
        }
    }

    private void createSchema(final String schemaName) {
        // Enforce sanitized schemaName with double quotes for PostgreSQL identifier safety
        final String sql = String.format("CREATE SCHEMA IF NOT EXISTS \"%s\"", schemaName);
        this.jdbcTemplate.execute(sql);
    }

    private void dropSchema(final String schemaName) {
        final String sql = String.format("DROP SCHEMA IF EXISTS \"%s\" CASCADE", schemaName);
        this.jdbcTemplate.execute(sql);
    }

    private void runTenantMigrations(final String schemaName) {
        log.info("Running Flyway migrations for tenant schema: '{}'", schemaName);
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

        final int migrationCount = tenantFlyway.migrate().migrationsExecuted;
        log.info("Flyway migration completed for schema: '{}'. Migrations executed: {}", schemaName, migrationCount);
    }

    private void recordMetrics(final boolean success, final long durationMs) {
        final MeterRegistry meterRegistry = this.meterRegistryProvider.getIfAvailable();
        if (meterRegistry != null) {
            final String status = success ? "success" : "failure";
            meterRegistry.counter("tenant.provisioning.result", "status", status).increment();
            meterRegistry.timer("tenant.provisioning.duration", "status", status).record(durationMs, TimeUnit.MILLISECONDS);
        }
    }
}
