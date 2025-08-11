package com.praxium.prepengine.util;

import com.praxium.prepengine.entity.User;
import com.praxium.prepengine.security.UserDetail;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Log4j2
@Service
@Getter
@Setter
public final class JwtUtil {
    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.expiration}")
    private long expiration;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractEmail(String token) {
        Claims claims = extractClaims(token);
        return claims.get("email", String.class);
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token).getBody();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // Token is expired
            throw new RuntimeException("Token expired", e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("Unsupported JWT", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Malformed JWT", e);
        } catch (SignatureException e) {
            throw new RuntimeException("Invalid signature", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Illegal argument token", e);
        }
    }

    public User getLoggedInUser() {
        try {
            UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (ObjectUtils.isEmpty(userDetail)) {
                return null;
            }
            return userDetail.getUser();
        } catch (Exception e) {
            log.error("JwtServiceImpl :: getLoggedInUser", e);
            return null;
        }
    }

    public boolean isTokenValid(String jwt, UserDetail userDetails) {
        final String username = extractEmail(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }
}

