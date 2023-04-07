package com.workcode.authservice.services;

import com.workcode.authservice.dtos.CreateUserDto;
import com.workcode.authservice.dtos.LoginDto;
import com.workcode.authservice.dtos.TokenDto;

public interface AuthService {
    TokenDto save(CreateUserDto data);

    TokenDto login(LoginDto data);

    TokenDto validate(String token);

}
