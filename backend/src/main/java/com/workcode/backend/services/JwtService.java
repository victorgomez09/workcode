package com.workcode.backend.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String jwtToken);

    boolean isTokenValid(String jwtToken, UserDetails userDetails);

    String generateToken(UserDetails user);

    String generateRefreshToken(UserDetails user);
}
