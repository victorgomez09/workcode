package com.workcode.backend.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.workcode.backend.dtos.CreateWorkspaceDto;
import com.workcode.backend.dtos.UserDto;
import com.workcode.backend.dtos.WorkspaceDto;
import com.workcode.backend.models.Port;
import com.workcode.backend.models.Workspace;
import com.workcode.backend.repositories.WorkspaceRepository;
import com.workcode.backend.services.DockerEngineService;
import com.workcode.backend.services.UserService;
import com.workcode.backend.services.WorkspaceService;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DockerEngineService dockerEngineService;

    @Override
    public List<WorkspaceDto> findAllbyUserId(Integer userId) {
        List<WorkspaceDto> result = new ArrayList<>();
        List<Workspace> workspaces = workspaceRepository.findByUserId(userId);
        workspaces.stream().forEach(w -> {
            Container c = dockerEngineService.findContainerByName(w.getName());
            result.add(WorkspaceDto.builder().id(w.getId()).name(w.getName()).image(w.getImage())
                    .ip(c.getNetworkSettings().getNetworks().get("bridge").getIpAddress())
                    .port(Arrays.asList(c.getPorts()).get(0).getPublicPort())
                    .build());
        });

        return result;
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
        UserDto user = userService.findByEmail(data.getUser());

        InspectContainerCmd container = dockerEngineService.buildContainer(data);
        List<Port> ports = new ArrayList<>();
        container.exec().getNetworkSettings().getPorts().getBindings().forEach((key, value) -> {
            ports.add(Port.builder().targetPort(key.getPort())
                    .exposedPort(Integer.valueOf(Arrays.asList(value).get(0).getHostPortSpec()))
                    .build());
        });

        Workspace workspace = Workspace.builder().name(data.getName()).image(data.getImage().name())
                .userId(user.getId()).ports(ports)
                .build();

        return workspaceRepository.save(workspace);
    }

}
