package io.madan.erpdemo.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

    private final String jwtSecret = "my-secret-key";

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret.getBytes())
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }
}
