package com.workcode.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.backend.dtos.CreateUserDto;
import com.workcode.backend.dtos.UserDto;
import com.workcode.backend.models.User;
import com.workcode.backend.repositories.UserRepository;
import com.workcode.backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(u -> UserDto.builder().id(u.getId()).name(u.getName()).email(u.getEmail()).build())
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            return null;

        return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            return null;

        return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
    }

    @Override
    public UserDto save(CreateUserDto data) {
        User user = User.builder().name(data.getName()).email(data.getEmail()).build();
        user = userRepository.save(user);

        return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
    }
}