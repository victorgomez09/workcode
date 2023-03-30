package com.workcode.usersservice.services;

import java.util.List;

import com.workcode.usersservice.models.User;

public interface UserService {
    List<User> findAll();
    User findById(int id);
    User save(User user);
}
