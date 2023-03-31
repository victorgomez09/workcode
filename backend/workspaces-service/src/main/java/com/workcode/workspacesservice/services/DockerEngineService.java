package com.workcode.workspacesservice.services;

import java.util.List;

import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.model.Container;

public interface DockerEngineService {
    List<Container> getAllContainers();
    BuildImageCmd build();
}
