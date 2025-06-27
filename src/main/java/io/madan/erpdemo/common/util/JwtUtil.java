package io.madan.erpdemo.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

    private final String jwtSecret = "a-string-secret-at-least-256-bits-long";

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }
}
