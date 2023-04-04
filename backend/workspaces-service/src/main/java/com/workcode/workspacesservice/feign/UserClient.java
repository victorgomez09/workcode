package com.workcode.workspacesservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.workcode.workspacesservice.dtos.UserDto;

@FeignClient(name = "users-service")
public interface UserClient {

    @GetMapping(value = "/users/email/{userEmail}")
    UserDto me(@RequestParam("userEmail") String userEmail);
}
