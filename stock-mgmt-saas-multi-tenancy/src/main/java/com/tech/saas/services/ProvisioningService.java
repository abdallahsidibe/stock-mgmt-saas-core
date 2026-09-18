package com.tech.saas.services;

import com.tech.saas.entities.Tenant;

public interface ProvisioningService {

    void provisionTenant(final Tenant tenant);
}
