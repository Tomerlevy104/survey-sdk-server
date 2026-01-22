package com.surveysdk.server.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
public class AuthEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    // Store a strong hash (e.g., BCrypt).
    private String passwordHash;

    // Store only a hash of the API key.
    private String apiKeyHash;

    // Used only to identify the key in UI (e.g., "...A1B2").
    private String apiKeyLast4;

    @CreatedDate
    private Date createdDate;

    public AuthEntity() {}

    public AuthEntity(String email, String passwordHash, String apiKeyHash, String apiKeyLast4, Date createdDate) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.apiKeyHash = apiKeyHash;
        this.apiKeyLast4 = apiKeyLast4;
        this.createdDate = createdDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getApiKeyHash() { return apiKeyHash; }
    public void setApiKeyHash(String apiKeyHash) { this.apiKeyHash = apiKeyHash; }

    public String getApiKeyLast4() { return apiKeyLast4; }
    public void setApiKeyLast4(String apiKeyLast4) { this.apiKeyLast4 = apiKeyLast4; }

    public Date getCreatedDate() { return createdDate; }
    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
