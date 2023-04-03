package com.workcode.authservice.services;

import com.workcode.authservice.dtos.CreateUserDto;
import com.workcode.authservice.dtos.LoginDto;
import com.workcode.authservice.dtos.RequestDto;
import com.workcode.authservice.dtos.TokenDto;

public interface UserService {
    TokenDto save(CreateUserDto data);
    TokenDto login(LoginDto data);
    TokenDto validate(String token, RequestDto data);
}
