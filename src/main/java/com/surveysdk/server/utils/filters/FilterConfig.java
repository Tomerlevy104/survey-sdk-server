package com.surveysdk.server.utils.filters;

import com.surveysdk.server.repositories.AuthRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FilterConfig
 * Registers JWT filter for protected endpoints.
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilter() {
        // Create a registration bean for the JWT authentication filter
        FilterRegistrationBean<JwtAuthFilter> registration = new FilterRegistrationBean<>();

        // Attach the JwtAuthFilter instance that will validate JWT tokens
        registration.setFilter(new JwtAuthFilter());

        // Apply the filter to all requests starting with "/api/"
        // (e.g. /api/auth, /api/v1/surveys, etc.)
        registration.addUrlPatterns("/api/admin/*");
        // Set filter execution order (lower value = higher priority)
        // Useful if more filters are added in the future
        registration.setOrder(1);

        // Return the configured filter registration
        return registration;
    }
    //////////////////////////////////////////////////////////////////////////////////////
    @Bean
    public FilterRegistrationBean<ApiKeyAuthFilter> apiKeyAuthFilter(AuthRepository authRepository) {
        FilterRegistrationBean<ApiKeyAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new ApiKeyAuthFilter(authRepository));
        registration.addUrlPatterns("/api/sdk/*");
        registration.setOrder(2);
        return registration;
    }
    //////////////////////////////////////////////////////////////////////////////////////
}