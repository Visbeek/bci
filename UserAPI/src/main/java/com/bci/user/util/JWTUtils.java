package com.bci.user.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JWTUtils {

    private JWTUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String SECRET = "quierotrabajarenbci";
    private static final long JWT_VALID_TIME = 3600000;

    public static String generateJWT(String email) {
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_VALID_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
    }
}
