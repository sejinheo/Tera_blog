package com.Tera.blog.config.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    //HS256 방식은 대칭키(하나의 키로 서명 & 검증) 알고리즘이다.
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    //토큰 유효 시간
    private final long expirationTime = 1000 * 60 * 60;

    public String generateToken(String loginId) {
        return Jwts.builder()
                .setSubject(loginId)// 토큰 주제 (사용자 식별자)
                .setIssuedAt(new Date())// 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))// 만료 시간
                .signWith(key)// 비밀 키로 서명
                .compact();// 문자열(JWT)로 변환

    }
//토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }

    }

    public String getLoginId(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
