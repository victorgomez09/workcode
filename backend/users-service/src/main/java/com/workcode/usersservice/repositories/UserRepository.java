package com.workcode.usersservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workcode.usersservice.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {}
