package com.workcode.workspacesservice.services.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.model.Container;
import com.workcode.workspacesservice.services.DockerEngineService;

@Service
public class DockerEngineServiceImpl implements DockerEngineService {
    
    @Autowired
    private DockerClient dockerClient;
    
    @Override
    public List<Container> getAllContainers() {
        return dockerClient.listContainersCmd().exec();
    }

    @Override
    public BuildImageCmd build() {
        return dockerClient.buildImageCmd(new File(""));
    }
    
}
