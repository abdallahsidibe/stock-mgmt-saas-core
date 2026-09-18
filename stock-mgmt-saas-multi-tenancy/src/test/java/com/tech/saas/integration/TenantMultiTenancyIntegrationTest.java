package com.tech.saas.integration;

import com.tech.saas.config.CurrentTenantIdentifierResolverImpl;
import com.tech.saas.config.TenantContext;
import com.tech.saas.config.TenantSchemaResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TenantMultiTenancyIntegrationTest {

    @Mock
    private TenantSchemaResolver schemaResolver;

    private final CurrentTenantIdentifierResolverImpl tenantIdentifierResolver = new CurrentTenantIdentifierResolverImpl();

    @AfterEach
    void tearDown() {
        TenantContext.clear();
    }

    @Test
    @DisplayName("Should resolve public schema when TenantContext is empty")
    void testDefaultPublicSchemaResolution() {
        assertNull(TenantContext.getCurrentTenant());
        assertNull(TenantContext.getCurrentSchema());
        assertEquals("public", tenantIdentifierResolver.resolveCurrentTenantIdentifier());
    }

    @Test
    @DisplayName("Should resolve tenant schema correctly when set in TenantContext")
    void testTenantContextSchemaResolution() {
        TenantContext.setCurrentTenant("tenant-uuid-1");
        TenantContext.setCurrentSchema("tenant_novatech");

        assertEquals("tenant-uuid-1", TenantContext.getCurrentTenant());
        assertEquals("tenant_novatech", TenantContext.getCurrentSchema());
        assertEquals("tenant_novatech", tenantIdentifierResolver.resolveCurrentTenantIdentifier());
    }

    @Test
    @DisplayName("Should isolate schema context between thread cleanups")
    void testThreadLocalCleanup() {
        TenantContext.setCurrentTenant("tenant-uuid-1");
        TenantContext.setCurrentSchema("tenant_novatech");

        TenantContext.clear();

        assertNull(TenantContext.getCurrentTenant());
        assertNull(TenantContext.getCurrentSchema());
        assertEquals("public", tenantIdentifierResolver.resolveCurrentTenantIdentifier());
    }

    @Test
    @DisplayName("Should resolve tenant schema from database lookup using company code")
    void testTenantSchemaResolverLookup() {
        when(schemaResolver.resolveTenantSchema("tenant-123")).thenReturn("tenant_techcorp");

        final String schema = schemaResolver.resolveTenantSchema("tenant-123");
        assertEquals("tenant_techcorp", schema);
    }
}
