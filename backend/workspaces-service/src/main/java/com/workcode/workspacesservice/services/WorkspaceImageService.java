package com.workcode.workspacesservice.services;

import java.util.List;

import com.workcode.workspacesservice.models.WorkspaceImage;

public interface WorkspaceImageService {
    List<WorkspaceImage> findAll();
    WorkspaceImage findById(Integer id);
    WorkspaceImage findByName(String name);
    WorkspaceImage save(WorkspaceImage data);
}
