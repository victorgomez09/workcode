package com.workcode.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workcode.authservice.dtos.CreateUserDto;
import com.workcode.authservice.dtos.LoginDto;
import com.workcode.authservice.dtos.TokenDto;
import com.workcode.authservice.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto data) {
        TokenDto token = authService.login(data);
        if (token == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody CreateUserDto data) {
        TokenDto token = authService.save(data);
        if (token == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam("token") String token) {
        TokenDto result = authService.validate(token);
        if (result == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(result);
    }

}
