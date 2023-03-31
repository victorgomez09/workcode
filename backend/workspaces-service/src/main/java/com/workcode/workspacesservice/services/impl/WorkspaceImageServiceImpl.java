package com.workcode.workspacesservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.workcode.workspacesservice.models.WorkspaceImage;
import com.workcode.workspacesservice.repositories.WorkspaceImageRepository;
import com.workcode.workspacesservice.services.WorkspaceImageService;

public class WorkspaceImageServiceImpl implements WorkspaceImageService {

    @Autowired
    private WorkspaceImageRepository dockerImageRepository;

    @Override
    public List<WorkspaceImage> findAll() {
        return dockerImageRepository.findAll();
    }

    @Override
    public WorkspaceImage findById(Integer id) {
        return dockerImageRepository.findById(id).orElse(null);
    }

    @Override
    public WorkspaceImage findByName(String name) {
        return dockerImageRepository.findByName(name).orElse(null);
    }

    @Override
    public WorkspaceImage save(WorkspaceImage data) {
        return dockerImageRepository.save(data);
    }
    
}
