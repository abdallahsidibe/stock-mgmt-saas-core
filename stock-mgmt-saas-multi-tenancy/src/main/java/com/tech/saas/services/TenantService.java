package com.tech.saas.services;

import com.tech.saas.common.PageResponse;
import com.tech.saas.requests.RegisterTenantRequest;
import com.tech.saas.responses.TenantResponse;

public interface TenantService {

    void registerTenant(final RegisterTenantRequest request);

    void approveTenant(final String tenantId);

    void activateTenant(final String tenantId);

    void deactivateTenant(final String tenantId);

    void suspendTenant(final String tenantId);

    PageResponse<TenantResponse> findAll(final int page, final int size);
}
