package com.workcode.usersservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workcode.usersservice.dtos.CreateUserDto;
import com.workcode.usersservice.dtos.UserDto;
import com.workcode.usersservice.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> result = userService.findAll();
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") Integer userId) {
        UserDto result = userService.findById(userId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable("userEmail") String userEmail) {
        UserDto result = userService.findByEmail(userEmail);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody CreateUserDto userData) {
        return ResponseEntity.ok(userService.save(userData));
    }

}
