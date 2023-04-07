package com.workcode.workspacesservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.workcode.workspacesservice.dtos.UserDto;

@FeignClient(name = "users-service")
public interface UsersClient {

    @GetMapping("/email/{userEmail}")
    public UserDto findByEmail(@PathVariable("userEmail") String userEmail);
}
