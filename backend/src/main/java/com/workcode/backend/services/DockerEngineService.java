package com.workcode.backend.services;

import java.util.List;

import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.workcode.backend.dtos.CreateWorkspaceDto;

public interface DockerEngineService {
    List<Container> findAllContainers();

    InspectContainerCmd findContainerById(String containerId);

    Container findContainerByName(String containerName);

    InspectContainerCmd buildContainer(CreateWorkspaceDto containerData);
}
