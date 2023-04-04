package com.workcode.workspacesservice.services.impl;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Volume;
import com.workcode.workspacesservice.dtos.CreateWorkspaceDto;
import com.workcode.workspacesservice.services.DockerEngineService;

@Service
public class DockerEngineServiceImpl implements DockerEngineService {
    
    @Autowired
    private DockerClient dockerClient;
    
    @Override
    public List<Container> findAllContainers() {
        return dockerClient.listContainersCmd().exec();
    }

    @Override
    public BuildImageCmd build() {
        return dockerClient.buildImageCmd(new File(""));
    }

    @Override
    public InspectContainerCmd findContainerById(String containerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findContainerById'");
    }

    @Override
    public InspectContainerCmd buildContainer(CreateWorkspaceDto containerData) {
        HostConfig hostConfig = HostConfig.newHostConfig().withPortBindings(PortBinding.parse("1209:8443"));
        CreateContainerResponse containerResponse = dockerClient
            .createContainerCmd("vscode-test")
            .withName("vscode-test-container")
            .withHostConfig(hostConfig)
            .withEnv(Arrays.asList("PUID=1000", "PGID=1000", "TZ=Etc/UTC", "PASSWORD=test"))
            .withVolumes(new Volume("/path/to/appdata/config:/config"))
        .exec();

        dockerClient.startContainerCmd(containerResponse.getId()).exec();
        return dockerClient.inspectContainerCmd(containerResponse.getId());
    }
    
}
