package com.tech.saas.services;

public interface TenantSeedService {

    /**
     * Seeds demo data (categories, products, stock movements) into the target tenant schema.
     */
    void seedDemoData(final String companyCode);
}
