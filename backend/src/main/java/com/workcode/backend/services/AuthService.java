package com.workcode.backend.services;

import java.io.IOException;

import com.workcode.backend.dtos.AuthResponseDto;
import com.workcode.backend.dtos.CreateUserDto;
import com.workcode.backend.dtos.SignInDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    AuthResponseDto register(CreateUserDto data);

    AuthResponseDto authenticate(SignInDto data);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
