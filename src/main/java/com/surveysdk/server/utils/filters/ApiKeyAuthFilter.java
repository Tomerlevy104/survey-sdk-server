package com.surveysdk.server.utils.filters;

import com.surveysdk.server.entities.AuthEntity;
import com.surveysdk.server.repositories.AuthRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final AuthRepository authRepository;

    public ApiKeyAuthFilter(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String apiKey = request.getHeader("X-API-Key");

        if (apiKey == null || apiKey.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Find matching user by comparing bcrypt hash
        AuthEntity owner = authRepository.findAll()
                .stream()
                .filter(u -> BCrypt.checkpw(apiKey, u.getApiKeyHash()))
                .findFirst()
                .orElse(null);

        if (owner == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Put developerId in request
        request.setAttribute("developerId", owner.getId());

        filterChain.doFilter(request, response);
    }
}
