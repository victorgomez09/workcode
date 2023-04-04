package com.workcode.usersservice.services;

import java.util.List;

import com.workcode.usersservice.dtos.CreateUserDto;
import com.workcode.usersservice.dtos.UserDto;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(int id);

    UserDto findByEmail(String email);

    UserDto save(CreateUserDto user);
}
