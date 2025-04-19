package com.example.Clubs.config.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Getter
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.refresh-secret-key}")
    private String refreshSecretKey;
    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;
    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private Key accessKey;
    private Key refreshKey;

    public JwtUtil() {

    }

    @PostConstruct
    public void init() {
        // Initialize the keys once properties are injected
        this.accessKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.refreshKey = Keys.hmacShaKeyFor(refreshSecretKey.getBytes());
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("tokenType", "access") // Access Token 표시
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Refresh Token 생성 (tokenType = "refresh")
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .claim("tokenType", "refresh") // Refresh Token 표시
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Access Token에서 username 추출
    public String getUsernameFromAccessToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(accessKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Refresh Token에서 username 추출
    public String getUsernameFromRefreshToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(refreshKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Access Token 유효성 검사
    public boolean validateAccessToken(String token) {
        return validateToken(token, "access");
    }

    // Refresh Token 유효성 검사
    public boolean validateRefreshToken(String token) {
        return validateToken(token, "refresh");
    }

    public boolean validateToken(String token, String tokenType) {
        try {
            Key key = "access".equals(tokenType) ? accessKey : refreshKey;
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 토큰 유형 확인 (잘못된 유형이면 false 반환)
            String validTokenType = claims.get("tokenType", String.class);
            if ((!"access".equals(validTokenType)) && (!"refresh".equals(validTokenType))) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // HTTP 요청에서 Bearer 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}