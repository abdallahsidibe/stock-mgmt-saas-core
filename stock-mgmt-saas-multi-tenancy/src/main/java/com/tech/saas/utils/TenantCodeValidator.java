package com.tech.saas.utils;

import com.tech.saas.exceptions.InvalidRequestException;
import java.util.regex.Pattern;

public class TenantCodeValidator {

    private static final Pattern TENANT_CODE_PATTERN = Pattern.compile("^[a-z0-9_]{3,30}$");

    /**
     * Validates that the company code is strictly safe against SQL injection and follows standard identifier rules.
     * Allowed: lowercase letters, numbers, and underscores, between 3 and 30 characters.
     */
    public static boolean isValidTenantCode(final String companyCode) {
        if (companyCode == null) {
            return false;
        }
        return TENANT_CODE_PATTERN.matcher(companyCode.toLowerCase()).matches();
    }

    /**
     * Validates company code and generates a safe PostgreSQL schema name.
     * Returns: tenant_<company_code>
     * Throws InvalidRequestException if companyCode is invalid.
     */
    public static String toSafeSchemaName(final String companyCode) {
        if (!isValidTenantCode(companyCode)) {
            throw new InvalidRequestException("Invalid tenant company code: '" + companyCode + "'. Must match regex [a-z0-9_]{3,30}");
        }
        return "tenant_" + companyCode.toLowerCase();
    }
}
