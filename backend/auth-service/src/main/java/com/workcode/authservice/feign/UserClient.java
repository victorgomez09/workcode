package com.workcode.authservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.workcode.authservice.dtos.CreateUserDto;
import com.workcode.authservice.dtos.UserDto;

@FeignClient(name = "users-service")
public interface UserClient {

    @PostMapping(value = "/users", consumes = "application/json")
    UserDto save(@RequestBody CreateUserDto user);
}
