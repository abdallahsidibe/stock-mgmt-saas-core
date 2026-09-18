package com.tech.saas.services;

import java.util.List;

public interface TenantMigrationService {

    /**
     * Migrates a single tenant schema.
     */
    TenantMigrationResult migrateTenant(final String companyCode);

    /**
     * Migrates all ACTIVE tenant schemas in parallel with specified concurrency.
     */
    TenantBatchMigrationResult migrateAllTenants(final int concurrency);

    record TenantMigrationResult(
            String companyCode,
            String schemaName,
            boolean success,
            int migrationsExecuted,
            long durationMs,
            String errorMessage
    ) {}

    record TenantBatchMigrationResult(
            int totalTenants,
            int successfulTenants,
            int failedTenants,
            long totalDurationMs,
            List<TenantMigrationResult> results
    ) {}
}
