package com.workcode.backend.services;

import java.util.List;

import com.workcode.backend.dtos.CreateWorkspaceDto;
import com.workcode.backend.dtos.WorkspaceDto;
import com.workcode.backend.models.Workspace;

public interface WorkspaceService {
    List<WorkspaceDto> findAllbyUserId(Integer userId);

    Workspace findByName(String name);

    Workspace findById(Integer id);

    Workspace save(CreateWorkspaceDto data);
}
