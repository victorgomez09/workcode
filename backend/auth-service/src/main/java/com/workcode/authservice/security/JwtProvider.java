package com.workcode.authservice.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.workcode.authservice.dtos.RequestDto;
import com.workcode.authservice.models.User;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private RouterValidator routerValidator;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 36000);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        
        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(exp).signWith(key, SignatureAlgorithm.ES512).compact();
    }

    public boolean validateToken(String token, RequestDto data) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (JwtException e) {
            return false;
        }

        if (!isAdmin(token) && routerValidator.isAdminPath(data))
            return true;
        
            return false;
    }

    public String getEmailFromToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return "Bad token";
        }
    }

    public boolean isAdmin(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role").equals("admin");
        } catch (Exception e) {
            return false;
        }
    }
}
