package com.dxs.auth.core.token ;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import com.dxs.auth.core.entity.RoleEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class KeyPairJwtTokenManager implements ITokenManager {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public KeyPairJwtTokenManager(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
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
                .signWith(this.privateKey)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        try {
            Claims claims = Jwts.parser().verifyWith(this.publicKey).build().parseSignedClaims(token).getPayload();
            return !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

}
