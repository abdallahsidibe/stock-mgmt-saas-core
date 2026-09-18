package com.tech.saas.services;

import com.tech.saas.entities.Tenant;
import com.tech.saas.entities.TenantStatus;
import com.tech.saas.exceptions.DuplicateResourceException;
import com.tech.saas.exceptions.InvalidRequestException;
import com.tech.saas.exceptions.TenantProvisioningException;
import com.tech.saas.mappers.TenantMapper;
import com.tech.saas.repositories.TenantRepository;
import com.tech.saas.repositories.UserRepository;
import com.tech.saas.requests.RegisterTenantRequest;
import com.tech.saas.services.impl.TenantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TenantServiceTest {

    @Mock
    private TenantRepository tenantRepository;
    @Mock
    private TenantMapper tenantMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProvisioningService provisioningService;

    @InjectMocks
    private TenantServiceImpl tenantService;

    private Tenant pendingTenant;

    @BeforeEach
    void setUp() {
        pendingTenant = Tenant.builder()
                .id("tenant-1")
                .companyName("Novatech")
                .companyCode("novatech")
                .email("info@novatech.com")
                .status(TenantStatus.PENDING)
                .adminFullName("John Doe")
                .adminEmail("admin@novatech.com")
                .adminUsername("admin_novatech")
                .adminPassword("encodedPassword")
                .build();
    }

    @Test
    @DisplayName("Should successfully register tenant with PENDING status")
    void testRegisterTenantSuccess() {
        final RegisterTenantRequest request = RegisterTenantRequest.builder()
                .companyName("Novatech")
                .companyCode("novatech")
                .email("info@novatech.com")
                .adminFullName("John Doe")
                .adminEmail("admin@novatech.com")
                .adminUsername("admin_novatech")
                .adminPassword("password123")
                .build();

        when(tenantRepository.existsByCompanyCode("novatech")).thenReturn(false);
        when(tenantRepository.existsByEmail("info@novatech.com")).thenReturn(false);
        when(tenantMapper.toEntity(request)).thenReturn(pendingTenant);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");

        assertDoesNotThrow(() -> tenantService.registerTenant(request));

        verify(tenantRepository).save(pendingTenant);
        assertEquals(TenantStatus.PENDING, pendingTenant.getStatus());
    }

    @Test
    @DisplayName("Should reject tenant registration with invalid company code format")
    void testRegisterTenantInvalidCode() {
        final RegisterTenantRequest request = RegisterTenantRequest.builder()
                .companyCode("invalid code!")
                .build();

        assertThrows(InvalidRequestException.class, () -> tenantService.registerTenant(request));
        verify(tenantRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should approve tenant, run provisioning, and set status ACTIVE")
    void testApproveTenantSuccess() {
        when(tenantRepository.findById("tenant-1")).thenReturn(Optional.of(pendingTenant));
        when(userRepository.existsByUsername("admin_novatech")).thenReturn(false);

        assertDoesNotThrow(() -> tenantService.approveTenant("tenant-1"));

        verify(provisioningService).provisionTenant(pendingTenant);
        verify(userRepository).save(any());
        assertEquals(TenantStatus.ACTIVE, pendingTenant.getStatus());
        assertNull(pendingTenant.getFailureReason());
    }

    @Test
    @DisplayName("Should mark tenant as FAILED and record reason when provisioning fails")
    void testApproveTenantProvisioningFailure() {
        when(tenantRepository.findById("tenant-1")).thenReturn(Optional.of(pendingTenant));
        doThrow(new RuntimeException("PostgreSQL schema connection timeout"))
                .when(provisioningService).provisionTenant(pendingTenant);

        assertThrows(TenantProvisioningException.class, () -> tenantService.approveTenant("tenant-1"));

        assertEquals(TenantStatus.FAILED, pendingTenant.getStatus());
        assertNotNull(pendingTenant.getFailureReason());
        assertTrue(pendingTenant.getFailureReason().contains("PostgreSQL schema connection timeout"));
    }

    @Test
    @DisplayName("Should suspend active tenant")
    void testSuspendTenant() {
        pendingTenant.setStatus(TenantStatus.ACTIVE);
        when(tenantRepository.findById("tenant-1")).thenReturn(Optional.of(pendingTenant));

        tenantService.suspendTenant("tenant-1");

        assertEquals(TenantStatus.SUSPENDED, pendingTenant.getStatus());
    }

    @Test
    @DisplayName("Should throw error when deactivating a non-active tenant")
    void testDeactivateNonActiveTenant() {
        pendingTenant.setStatus(TenantStatus.PENDING);
        when(tenantRepository.findById("tenant-1")).thenReturn(Optional.of(pendingTenant));

        final InvalidRequestException ex = assertThrows(InvalidRequestException.class, () -> tenantService.deactivateTenant("tenant-1"));
        assertEquals("Tenant is not active", ex.getMessage());
    }
}
