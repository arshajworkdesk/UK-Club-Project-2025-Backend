package com.ukclub.gateway.config;

import com.ukclub.gateway.constants.GatewayConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * CORS Configuration
 * Allows Angular frontend to communicate with the gateway
 * Values are read from application.properties (from .env file)
 */
@Configuration
public class CorsConfig {

    @Value("${CORS_ALLOWED_ORIGINS}")
    private String allowedOrigins;

    @Value("${CORS_ALLOWED_METHODS}")
    private String allowedMethods;

    @Value("${CORS_ALLOWED_HEADERS}")
    private String allowedHeaders;

    @Value("${CORS_ALLOW_CREDENTIALS}")
    private boolean allowCredentials;

    @Value("${CORS_MAX_AGE}")
    private long maxAge;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        
        // Allowed origins - parsed from comma-separated string
        List<String> originsList = Arrays.asList(allowedOrigins.split(","));
        corsConfig.setAllowedOrigins(originsList);
        
        // Allowed HTTP methods - parsed from comma-separated string
        List<String> methodsList = Arrays.asList(allowedMethods.split(","));
        corsConfig.setAllowedMethods(methodsList);
        
        // Allowed headers - parsed from comma-separated string
        List<String> headersList = Arrays.asList(allowedHeaders.split(","));
        corsConfig.setAllowedHeaders(headersList);
        
        // Allow credentials (cookies, authorization headers)
        corsConfig.setAllowCredentials(allowCredentials);
        
        // Cache preflight response
        corsConfig.setMaxAge(maxAge);
        
        // Exposed headers (using constants for header names)
        corsConfig.setExposedHeaders(GatewayConstants.CORS_EXPOSED_HEADERS);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        
        return new CorsWebFilter(source);
    }
}

