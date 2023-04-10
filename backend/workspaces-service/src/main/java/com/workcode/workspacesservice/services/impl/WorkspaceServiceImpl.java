package com.workcode.workspacesservice.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.command.InspectContainerCmd;
import com.workcode.workspacesservice.dtos.CreateWorkspaceDto;
import com.workcode.workspacesservice.dtos.UserDto;
import com.workcode.workspacesservice.feign.UsersClient;
import com.workcode.workspacesservice.models.Workspace;
import com.workcode.workspacesservice.repositories.WorkspaceRepository;
import com.workcode.workspacesservice.services.DockerEngineService;
import com.workcode.workspacesservice.services.WorkspaceService;
import com.workcode.workspacesservice.utils.DockerUtil;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UsersClient usersClient;

    @Autowired
    private DockerEngineService dockerEngineService;

    @Autowired
    private DockerUtil dockerUtil;

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
        UserDto user = usersClient.findByEmail(data.getUser());

        InspectContainerCmd container = dockerEngineService.buildContainer(data);
        List<String> logs = dockerUtil.getDockerLogs(container.getContainerId());
        for (String string : logs) {
            System.out.println("log: " + string);
        }
        // System.out.println("test1: " + container.exec().toString());
        System.out.println("test: " + container.exec().getNetworkSettings().toString());

        return null;
    }

}
