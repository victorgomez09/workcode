package com.workcode.workspacesservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workcode.workspacesservice.models.WorkspaceImage;

public interface WorkspaceImageRepository extends JpaRepository<WorkspaceImage, Integer> {
    Optional<WorkspaceImage> findByName(String name);
}
