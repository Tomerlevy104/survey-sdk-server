package com.surveysdk.server.controllers;

import com.surveysdk.server.dtos.AuthLoginRequestDTO;
import com.surveysdk.server.dtos.AuthRegisterRequestDTO;
import com.surveysdk.server.dtos.AuthResponseDTO;
import com.surveysdk.server.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register new user
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponseDTO register(
            @RequestBody AuthRegisterRequestDTO request
    ) {
        return authService.register(request);
    }

    // Login existing user
    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody AuthLoginRequestDTO request
    ) {
        return authService.login(request);
    }
}
