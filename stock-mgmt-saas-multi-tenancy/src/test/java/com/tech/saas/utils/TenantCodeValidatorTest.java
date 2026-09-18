package com.tech.saas.utils;

import com.tech.saas.exceptions.InvalidRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TenantCodeValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"novatech", "techcorp", "acme_123", "tenant_a", "company1"})
    @DisplayName("Should accept valid tenant company codes")
    void testValidTenantCodes(final String code) {
        assertTrue(TenantCodeValidator.isValidTenantCode(code));
        assertEquals("tenant_" + code.toLowerCase(), TenantCodeValidator.toSafeSchemaName(code));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "no",                             // too short (< 3 chars)
            "this_company_name_is_way_too_long_for_schema_name_validation", // too long (> 30 chars)
            "nova; DROP TABLE tenants; --",   // SQL injection attempt
            "nova tech",                      // contains space
            "nova-tech",                      // contains hyphen
            "nova'tech",                      // contains single quote
            "nova\"tech"                      // contains double quote
    })
    @DisplayName("Should reject invalid or unsafe tenant codes")
    void testInvalidTenantCodes(final String code) {
        assertFalse(TenantCodeValidator.isValidTenantCode(code));
        assertThrows(InvalidRequestException.class, () -> TenantCodeValidator.toSafeSchemaName(code));
    }
}
