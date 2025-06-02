package com.project.recipe.Service;

import com.project.recipe.Dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtUtil {
    private final long expirationMillis = 1000L * 60 * 60 * 24 * 7;
    @Value("${jwt.secret}")
    private String secret;

    private Key secretKey;

    @PostConstruct
    public void initKey() {
        secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }


    private Key getSecretKey() {
        return secretKey;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject(); // subject = username
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration(); // exp = expiration
    }

    private boolean isExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDto userDto){
        return createToken(userDto.getEmail());
    }

    private String createToken(String email){
        return Jwts.builder().subject(email).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+ expirationMillis)).signWith(getSecretKey()).compact();
    }

    public boolean verifyToken(String jwt, UserDetails userDetails){
        String email = extractEmail(jwt);
        return (email.equals(userDetails.getUsername()) && !isExpired(jwt));

    }
}
