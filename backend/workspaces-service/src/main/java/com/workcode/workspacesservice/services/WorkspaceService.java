package com.workcode.workspacesservice.services;

import java.util.List;

import com.workcode.workspacesservice.dtos.CreateWorkspaceDto;
import com.workcode.workspacesservice.dtos.WorkspaceDto;
import com.workcode.workspacesservice.models.Workspace;

public interface WorkspaceService {
    List<WorkspaceDto> findAllbyUserId(Integer userId);

    Workspace findByName(String name);

    Workspace findById(Integer id);

    Workspace save(CreateWorkspaceDto data);
}
