package com.workcode.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workcode.backend.models.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
    List<Workspace> findByUserId(Integer userId);

    Optional<Workspace> findByName(String name);
}
