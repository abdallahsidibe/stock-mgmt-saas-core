package com.tech.saas.services;

import com.tech.saas.entities.Tenant;
import com.tech.saas.entities.TenantStatus;
import com.tech.saas.repositories.TenantRepository;
import com.tech.saas.services.impl.TenantMigrationServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectProvider;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TenantMigrationServiceTest {

    @Mock
    private TenantRepository tenantRepository;
    @Mock
    private DataSource dataSource;
    @Mock
    private ObjectProvider meterRegistryProvider;

    @Spy
    @InjectMocks
    private TenantMigrationServiceImpl tenantMigrationService;

    @Test
    @DisplayName("Should return empty batch result when no ACTIVE tenants exist")
    void testMigrateAllTenantsEmpty() {
        when(tenantRepository.findAll()).thenReturn(List.of());

        final TenantMigrationService.TenantBatchMigrationResult result = tenantMigrationService.migrateAllTenants(4);

        assertEquals(0, result.totalTenants());
        assertEquals(0, result.successfulTenants());
        assertEquals(0, result.failedTenants());
    }

    @Test
    @DisplayName("Should filter active tenants and attempt batch migration")
    void testMigrateAllActiveTenants() {
        final Tenant tenant1 = Tenant.builder().companyCode("novatech").status(TenantStatus.ACTIVE).build();
        final Tenant tenant2 = Tenant.builder().companyCode("techcorp").status(TenantStatus.ACTIVE).build();
        final Tenant tenant3 = Tenant.builder().companyCode("pending_co").status(TenantStatus.PENDING).build();

        when(tenantRepository.findAll()).thenReturn(List.of(tenant1, tenant2, tenant3));
        doReturn(new TenantMigrationService.TenantMigrationResult("novatech", "tenant_novatech", true, 2, 50, null))
                .when(tenantMigrationService).migrateTenant("novatech");
        doReturn(new TenantMigrationService.TenantMigrationResult("techcorp", "tenant_techcorp", true, 2, 45, null))
                .when(tenantMigrationService).migrateTenant("techcorp");

        final TenantMigrationService.TenantBatchMigrationResult result = tenantMigrationService.migrateAllTenants(2);

        assertEquals(2, result.totalTenants());
        assertEquals(2, result.successfulTenants());
        assertEquals(0, result.failedTenants());
        verify(tenantMigrationService, times(1)).migrateTenant("novatech");
        verify(tenantMigrationService, times(1)).migrateTenant("techcorp");
        verify(tenantMigrationService, never()).migrateTenant("pending_co");
    }
}
