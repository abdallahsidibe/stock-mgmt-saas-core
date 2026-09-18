package com.tech.saas.services.impl;

import com.tech.saas.common.PageResponse;
import com.tech.saas.entities.Tenant;
import com.tech.saas.entities.TenantStatus;
import com.tech.saas.entities.User;
import com.tech.saas.entities.UserRole;
import com.tech.saas.exceptions.DuplicateResourceException;
import com.tech.saas.exceptions.InvalidRequestException;
import com.tech.saas.exceptions.TenantProvisioningException;
import com.tech.saas.mappers.TenantMapper;
import com.tech.saas.repositories.TenantRepository;
import com.tech.saas.repositories.UserRepository;
import com.tech.saas.requests.RegisterTenantRequest;
import com.tech.saas.responses.TenantResponse;
import com.tech.saas.services.ProvisioningService;
import com.tech.saas.services.TenantService;
import com.tech.saas.utils.TenantCodeValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProvisioningService provisioningService;

    @Override
    @Transactional
    public void registerTenant(final RegisterTenantRequest request) {
        // Validate company code format to prevent invalid characters early
        if (!TenantCodeValidator.isValidTenantCode(request.getCompanyCode())) {
            throw new InvalidRequestException("Invalid company code format: '" + request.getCompanyCode() + "'. Allowed: [a-z0-9_]{3,30}");
        }

        // check if the tenant already exists by company code
        if (this.tenantRepository.existsByCompanyCode(request.getCompanyCode())) {
            throw new DuplicateResourceException("Tenant with company code '" + request.getCompanyCode() + "' already exists");
        }

        // check if email already exists
        if (this.tenantRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Tenant email '" + request.getEmail() + "' already exists");
        }

        // create tenant entity
        final Tenant tenant = this.tenantMapper.toEntity(request);
        tenant.setAdminPassword(this.passwordEncoder.encode(request.getAdminPassword()));
        tenant.setStatus(TenantStatus.PENDING);

        this.tenantRepository.save(tenant);
        log.info("Registered new tenant '{}' with status PENDING", tenant.getCompanyCode());
    }

    @Override
    public void approveTenant(final String tenantId) {
        // 1. Fetch tenant and validate state
        final Tenant tenant = this.tenantRepository.findById(tenantId)
                .orElseThrow(() -> new EntityNotFoundException("Tenant with ID '" + tenantId + "' does not exist"));

        if (tenant.getStatus() != TenantStatus.PENDING && tenant.getStatus() != TenantStatus.FAILED) {
            throw new InvalidRequestException("Tenant cannot be approved from status: " + tenant.getStatus());
        }

        // 2. Mark tenant as PROVISIONING
        tenant.setStatus(TenantStatus.PROVISIONING);
        tenant.setFailureReason(null);
        this.tenantRepository.save(tenant);
        log.info("Tenant '{}' status set to PROVISIONING", tenant.getCompanyCode());

        try {
            // 3. Provision PostgreSQL schema & run Flyway migrations
            this.provisioningService.provisionTenant(tenant);

            // 4. Create initial admin user in public.users
            createInitialAdminUser(tenant);

            // 5. Mark tenant as ACTIVE
            tenant.setStatus(TenantStatus.ACTIVE);
            tenant.setFailureReason(null);
            this.tenantRepository.save(tenant);
            log.info("Tenant '{}' successfully provisioned and activated", tenant.getCompanyCode());

        } catch (final Exception e) {
            log.error("Failed to approve tenant '{}': {}", tenant.getCompanyCode(), e.getMessage(), e);
            markTenantAsFailed(tenant, e.getMessage());
            throw new TenantProvisioningException("Failed to approve tenant: " + e.getMessage());
        }
    }

    @Override
    public void activateTenant(final String tenantId) {
        final Tenant tenant = this.tenantRepository.findById(tenantId)
                .orElseThrow(() -> new EntityNotFoundException("Tenant does not exist"));

        if (tenant.getStatus() != TenantStatus.PENDING && tenant.getStatus() != TenantStatus.INACTIVE && tenant.getStatus() != TenantStatus.SUSPENDED) {
            throw new InvalidRequestException("Tenant cannot be activated from status: " + tenant.getStatus());
        }

        tenant.setStatus(TenantStatus.ACTIVE);
        tenant.setFailureReason(null);
        this.tenantRepository.save(tenant);
        log.info("Activated tenant '{}'", tenant.getCompanyCode());
    }

    @Override
    public void deactivateTenant(final String tenantId) {
        final Tenant tenant = this.tenantRepository.findById(tenantId)
                .orElseThrow(() -> new EntityNotFoundException("Tenant does not exist"));

        if (tenant.getStatus() != TenantStatus.ACTIVE) {
            throw new InvalidRequestException("Tenant is not active");
        }

        tenant.setStatus(TenantStatus.INACTIVE);
        this.tenantRepository.save(tenant);
        log.info("Deactivated tenant '{}'", tenant.getCompanyCode());
    }

    @Override
    public void suspendTenant(final String tenantId) {
        final Tenant tenant = this.tenantRepository.findById(tenantId)
                .orElseThrow(() -> new EntityNotFoundException("Tenant does not exist"));

        if (tenant.getStatus() != TenantStatus.ACTIVE) {
            throw new InvalidRequestException("Tenant is not active");
        }

        tenant.setStatus(TenantStatus.SUSPENDED);
        this.tenantRepository.save(tenant);
        log.info("Suspended tenant '{}'", tenant.getCompanyCode());
    }

    @Override
    public PageResponse<TenantResponse> findAll(final int page, final int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        final Page<Tenant> tenants = this.tenantRepository.findAll(pageRequest);
        final Page<TenantResponse> tenantResponses = tenants.map(this.tenantMapper::toResponse);
        return PageResponse.of(tenantResponses);
    }

    private void markTenantAsFailed(final Tenant tenant, final String reason) {
        try {
            tenant.setStatus(TenantStatus.FAILED);
            tenant.setFailureReason(reason);
            this.tenantRepository.save(tenant);
        } catch (final Exception ex) {
            log.error("Failed to set status FAILED for tenant '{}'", tenant.getCompanyCode(), ex);
        }
    }

    private void createInitialAdminUser(final Tenant tenant) {
        // check if the user already exists
        if (this.userRepository.existsByUsername(tenant.getAdminUsername())) {
            log.info("Initial admin user '{}' already exists for tenant '{}'", tenant.getAdminUsername(), tenant.getCompanyCode());
            return;
        }

        final User adminUser = User.builder()
                .username(tenant.getAdminUsername())
                .email(tenant.getAdminEmail())
                .firstName(extractFirstName(tenant.getAdminFullName()))
                .lastName(extractLastName(tenant.getAdminFullName()))
                .password(tenant.getAdminPassword())
                .role(UserRole.ROLE_COMPANY_ADMIN)
                .tenant(tenant)
                .enabled(true)
                .build();
        this.userRepository.save(adminUser);
        log.info("Created initial admin user '{}' for tenant '{}'", tenant.getAdminUsername(), tenant.getId());
    }

    private String extractFirstName(final String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "Admin";
        }
        return fullName.trim().split("\\s+")[0];
    }

    private String extractLastName(final String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "User";
        }
        final String[] parts = fullName.trim().split("\\s+");
        return parts.length > 1 ? parts[1] : parts[0];
    }
}
