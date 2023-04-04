package com.workcode.workspacesservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.workspacesservice.dtos.CreateWorkspaceDto;
import com.workcode.workspacesservice.dtos.UserDto;
import com.workcode.workspacesservice.feign.AuthClient;
import com.workcode.workspacesservice.models.Workspace;
import com.workcode.workspacesservice.repositories.WorkspaceRepository;
import com.workcode.workspacesservice.services.WorkspaceService;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private AuthClient authClient;

    @Override
    public List<Workspace> findAllbyUserId(Integer userId) {
        return workspaceRepository.findByUserId(userId);
    }

    @Override
    public Workspace findByName(String name) {
        return workspaceRepository.findByName(name).orElse(null);
    }

    @Override
    public Workspace findById(Integer id) {
        return workspaceRepository.findById(id).orElse(null);
    }

    @Override
    public Workspace save(CreateWorkspaceDto data) {
        UserDto user = authClient.me();
        System.out.println("user: " + user.getEmail());
        return null;

        // Workspace workspace =
        // Workspace.builder().name(data.getName()).image(data.getImage().toString()).build();
        // return workspaceRepository.save(workspace);
    }

}
