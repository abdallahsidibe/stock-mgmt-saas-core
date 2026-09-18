package com.tech.saas.services.impl;

import com.tech.saas.services.TenantSeedService;
import com.tech.saas.utils.TenantCodeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantSeedServiceImpl implements TenantSeedService {

    private final DataSource dataSource;

    @Override
    public void seedDemoData(final String companyCode) {
        final String schemaName = TenantCodeValidator.toSafeSchemaName(companyCode);
        log.info("Seeding demo data into schema: '{}'", schemaName);

        final Resource seedResource = new ClassPathResource("db/seed/demo/seed_tenant_demo_data.sql");
        if (!seedResource.exists()) {
            log.warn("Demo seed script 'db/seed/demo/seed_tenant_demo_data.sql' not found. Skipping demo seed.");
            return;
        }

        try (final Connection connection = this.dataSource.getConnection()) {
            // Set search path to target schema
            try (final Statement statement = connection.createStatement()) {
                statement.execute(String.format("SET search_path TO \"%s\", public", schemaName));
            }

            ScriptUtils.executeSqlScript(connection, seedResource);

            log.info("Demo data seeded successfully into schema: '{}'", schemaName);

        } catch (final SQLException e) {
            log.error("Failed to seed demo data into schema: '{}'", schemaName, e);
            throw new RuntimeException("Failed to seed demo data for tenant '" + companyCode + "'", e);
        }
    }
}
