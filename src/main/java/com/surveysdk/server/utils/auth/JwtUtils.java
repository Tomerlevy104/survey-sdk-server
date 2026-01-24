package com.surveysdk.server.utils.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

/**
 * JwtUtils
 * Utility class for generating and validating JWT tokens.
 */
public class JwtUtils {

    // TODO: remove from here and put it in application.properties file
    private static final String SECRET =
            "CHANGE_THIS_SECRET_TO_A_LONG_RANDOM_STRING_AT_LEAST_32_CHARS";

    // Token validity - 1 hour
    private static final long EXPIRATION_MS = 60 * 60 * 1000;

    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    private JwtUtils() {}

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Generate JWT for a user (identified by email).
    public static String generateToken(String email, String developerId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION_MS);

        return Jwts.builder()
                .setSubject(email)
                .claim("developerId", developerId)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    // Validate token and extract email (subject).
    public static String validateAndGetEmail(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null; // invalid or expired token
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
    // Validate token and extract developer Id (subject).
    public static String validateAndGetDeveloperId(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.get("developerId", String.class);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
}
