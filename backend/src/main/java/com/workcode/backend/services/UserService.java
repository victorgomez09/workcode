package com.workcode.backend.services;

import java.util.List;

import com.workcode.backend.dtos.CreateUserDto;
import com.workcode.backend.dtos.UserDto;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(int id);

    UserDto findByEmail(String email);

    UserDto save(CreateUserDto user);
}
