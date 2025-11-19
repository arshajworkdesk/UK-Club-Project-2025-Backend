package com.ukclub.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gateway Route Configuration
 * Defines routes to backend services
 * Service URLs are read from .env file via application.properties
 */
@Configuration
public class GatewayConfig {

    @Value("${AUTH_SERVICE_URL}")
    private String authServiceUrl;

    @Value("${MEMBER_SERVICE_URL}")
    private String memberServiceUrl;

    @Value("${CONTACT_SERVICE_URL}")
    private String contactServiceUrl;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Public Routes (No JWT Required)
                
                // Admin Login - Public
                .route("admin-login", r -> r
                        .path("/api/admin/login")
                        .uri(authServiceUrl))
                
                // Member Registration - Public
                .route("member-register", r -> r
                        .path("/api/membership/register")
                        .uri(memberServiceUrl))
                
                // Get Approved Members - Public
                .route("members-public", r -> r
                        .path("/api/members")
                        .uri(memberServiceUrl))
                
                // Contact Form - Public
                .route("contact", r -> r
                        .path("/api/contact")
                        .uri(contactServiceUrl))
                
                .build();
    }
}

