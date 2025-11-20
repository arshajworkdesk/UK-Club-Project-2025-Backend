package com.ukclub.gateway.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Gateway Constants
 * Centralized constants for API Gateway configuration
 */
public class GatewayConstants {



    // Public endpoints that don't require JWT authentication
    // Note: Member and contact endpoints removed until services are implemented
    // Uncomment in both GatewayConfig.java and here when services are ready:
    // "/api/membership/register", "/api/members", "/api/contact"
    public static final List<String> PUBLIC_ENDPOINTS = Arrays.asList(
            "/api/admin/login"
    );

    // JWT Authentication constants
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    // User info header names (passed to downstream services)
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String HEADER_USER_EMAIL = "X-User-Email";
    public static final String HEADER_USER_ROLE = "X-User-Role";
    
    // HTTP Headers
    public static final String CONTENT_TYPE_HEADER = "Content-Type";
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    // Exposed headers (header names only - values come from properties)
    public static final List<String> CORS_EXPOSED_HEADERS = Arrays.asList(
            AUTHORIZATION_HEADER,
            CONTENT_TYPE_HEADER
    );
    

    
    // Filter order (higher priority = lower number)
    public static final int JWT_FILTER_ORDER = -100;

    // Private constructor to prevent instantiation
    private GatewayConstants() {
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }
}

