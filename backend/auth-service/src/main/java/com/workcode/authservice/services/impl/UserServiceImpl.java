package com.workcode.authservice.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workcode.authservice.dtos.CreateUserDto;
import com.workcode.authservice.dtos.LoginDto;
import com.workcode.authservice.dtos.TokenDto;
import com.workcode.authservice.models.User;
import com.workcode.authservice.repositories.UserRepository;
import com.workcode.authservice.security.JwtProvider;
import com.workcode.authservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {
   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public TokenDto save(CreateUserDto data) {
        Optional<User> user = userRepository.findByEmail(data.getEmail());
        if (!user.isPresent())
            return null;
        
        String password = passwordEncoder.encode(data.getPassword());
        userRepository.save(User.builder().email(data.getEmail()).password(password).build());

        return login(LoginDto.builder().email(data.getEmail()).password(data.getPassword()).build());
    }

    @Override
    public TokenDto login(LoginDto data) {
        Optional<User> user = userRepository.findByEmail(data.getEmail());
        if (!user.isPresent())
            return null;

        if (passwordEncoder.matches(data.getPassword(), user.get().getPassword()))
            return TokenDto.builder().token(jwtProvider.createToken(user.get())).build();

        return null;
    }

    @Override
    public TokenDto validate(String token) {
        if (!jwtProvider.validateToken(token))
            return null;

        String email = jwtProvider.getEmailFromToken(token);
        if (!userRepository.findByEmail(email).isPresent())
            return null;

        return TokenDto.builder().token(token).build();
    }
    
}
