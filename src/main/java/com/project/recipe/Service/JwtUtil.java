package com.project.recipe.Service;

import com.project.recipe.Dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    private final long expirationMillis = 1000L * 60 * 60 * 24 * 7;
    @Value("${jwt.secret}")
    private String secret;

    private Key secretKey;

    private Key getSecretKey() {
        if (secretKey == null) {
            secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
        }
        return secretKey;
    }

    public String generateToken(UserDto userDto){
        return createToken(userDto.getName());
    }
    private String createToken(String name){
        return Jwts.builder().subject(name).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+ expirationMillis)).signWith(getSecretKey()).compact();
    }
}
