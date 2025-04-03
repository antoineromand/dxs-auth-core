package com.dxs.auth.core.external;

import com.dxs.auth.core.entity.RoleEnum;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public TokenManager(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

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

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(this.publicKey).build().parse(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}
