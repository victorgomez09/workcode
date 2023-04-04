package com.workcode.workspacesservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.workcode.workspacesservice.dtos.UserDto;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @GetMapping(value = "/auth/me")
    UserDto me();
}
