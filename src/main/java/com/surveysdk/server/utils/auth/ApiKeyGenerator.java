package com.surveysdk.server.utils.auth;

import java.util.UUID;

/**
 * ApiKeyGenerator
 * Generates API keys for SDK usage.
 */
public class ApiKeyGenerator {

    private static final String PREFIX = "SSK_";

    private ApiKeyGenerator() {}

    public static String generateApiKey() {
        return PREFIX + UUID.randomUUID();
    }

    public static String last4(String apiKey) {
        if (apiKey == null || apiKey.length() < 4) return "";
        return apiKey.substring(apiKey.length() - 4);
    }
}
