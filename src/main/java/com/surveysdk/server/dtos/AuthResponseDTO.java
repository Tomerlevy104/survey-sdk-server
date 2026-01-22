package com.surveysdk.server.dtos;

/**
 * AuthResponseDTO
 * Response returned after successful login or registration.
 */
public class AuthResponseDTO {

    private String jwt;
    private String apiKey;   // Returned only on first creation / regeneration
    private String apiKeyLast4;  // To show

    public AuthResponseDTO() {}

    public AuthResponseDTO(String jwt, String apiKey, String apiKeyLast4) {
        this.jwt = jwt;
        this.apiKey = apiKey;
        this.apiKeyLast4 = apiKeyLast4;
    }

    public String getJwt() { return jwt; }
    public void setJwt(String jwt) { this.jwt = jwt; }

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public String getApiKeyLast4() { return apiKeyLast4; }
    public void setApiKeyLast4(String apiKeyLast4) { this.apiKeyLast4 = apiKeyLast4; }
}
