package com.workcode.workspacesservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.workspacesservice.models.Workspace;
import com.workcode.workspacesservice.repositories.WorkspaceRepository;
import com.workcode.workspacesservice.services.WorkspaceService;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

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
    public Workspace save(Workspace data) {
        return workspaceRepository.save(data);
    }
    
}
