package com.workcode.workspacesservice.services;

import java.util.List;

import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.workcode.workspacesservice.dtos.CreateContainerDto;

public interface DockerEngineService {
    List<Container> findAllContainers();
    InspectContainerCmd findContainerById(String containerId);
    InspectContainerCmd buildContainer(CreateContainerDto containerData);
    BuildImageCmd build();
}
