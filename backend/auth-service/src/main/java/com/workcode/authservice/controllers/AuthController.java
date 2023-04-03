package com.workcode.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workcode.authservice.dtos.CreateUserDto;
import com.workcode.authservice.dtos.LoginDto;
import com.workcode.authservice.dtos.RequestDto;
import com.workcode.authservice.dtos.TokenDto;
import com.workcode.authservice.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto data) {
        TokenDto token = userService.login(data);
        if (token == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenDto> register(@RequestBody CreateUserDto data) {
        TokenDto token = userService.save(data);
        if (token == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam("token") String token, @RequestBody RequestDto data) {
        TokenDto result = userService.validate(token, data);
        if (result == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(result);
    }
}
