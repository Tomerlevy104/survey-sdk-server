package com.surveysdk.server.services;

import com.surveysdk.server.dtos.AuthRegisterRequestDTO;
import com.surveysdk.server.dtos.AuthLoginRequestDTO;
import com.surveysdk.server.dtos.AuthResponseDTO;
import com.surveysdk.server.entities.AuthEntity;
import com.surveysdk.server.repositories.AuthRepository;
import com.surveysdk.server.utils.auth.ApiKeyGenerator;
import com.surveysdk.server.utils.auth.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Register
    public AuthResponseDTO register(AuthRegisterRequestDTO registerRequest) {

        String email = normalizeEmail(registerRequest.getEmail());
        String password = registerRequest.getPassword();

        // Validation
        validateEmailAndPassword(email, password);

        // Check if user exist
        if (authRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        // Hash password
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        // Generate API Key (shown once)
        String apiKey = ApiKeyGenerator.generateApiKey();
        String apiKeyLast4 = ApiKeyGenerator.last4(apiKey);
        String apiKeyHash = BCrypt.hashpw(apiKey, BCrypt.gensalt());

        // Persist user
        AuthEntity user = new AuthEntity(
                email,
                passwordHash,
                apiKeyHash,
                apiKeyLast4,
                new Date()
        );

        authRepository.save(user);

        String jwt = JwtUtils.generateToken(user.getEmail(), user.getId());

        // Return response
        return new AuthResponseDTO(
                jwt,
                apiKey,
                apiKeyLast4
        );
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Login
    public AuthResponseDTO login(AuthLoginRequestDTO loginRequest) {

        String email = normalizeEmail(loginRequest.getEmail());
        String password = loginRequest.getPassword();

        // Validation
        validateEmailAndPassword(email, password);

        // Find user by email
        AuthEntity user = authRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        // Verify password (raw vs hash)
        boolean ok = BCrypt.checkpw(password, user.getPasswordHash());
        if (!ok) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String jwt = JwtUtils.generateToken(user.getEmail(), user.getId());
        // Return response
        return new AuthResponseDTO(
                jwt,
                null, // apiKey never returned on login
                user.getApiKeyLast4()
        );
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Normalize Email (cancel case-sensitive and delete spaces at the beginning or at the end of the Email)
    private String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Validation
    private void validateEmailAndPassword(String email, String password) {
        if (email == null || email.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        if (password == null || password.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password is required");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
}
