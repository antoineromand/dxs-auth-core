package com.dxs.auth.core.token;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import com.dxs.auth.core.entity.RoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class SecretJwtTokenManager implements ITokenManager {

    private final String jwtSecret;

    public SecretJwtTokenManager(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @Override
    public String generateToken(UUID id, RoleEnum role, long expiration) {
        Instant now = Instant.now();
        return Jwts
                .builder()
                .subject(id.toString())
                .claim("role", role.toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expiration)))
                .signWith(this.getSigningKey())
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(this.getSigningKey()).build().parseSignedClaims(token).getPayload();
            return !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
