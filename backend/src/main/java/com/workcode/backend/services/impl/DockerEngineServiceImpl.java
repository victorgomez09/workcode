package com.workcode.backend.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Volume;
import com.workcode.backend.dtos.CreateWorkspaceDto;
import com.workcode.backend.services.DockerEngineService;

@Service
public class DockerEngineServiceImpl implements DockerEngineService {

    @Autowired
    private DockerClient dockerClient;

    @Override
    public List<Container> findAllContainers() {
        return dockerClient.listContainersCmd().exec();
    }

    @Override
    public InspectContainerCmd findContainerById(String containerId) {
        return dockerClient.inspectContainerCmd(containerId);
    }

    @Override
    public Container findContainerByName(String containerName) {
        return dockerClient.listContainersCmd().withNameFilter(Arrays.asList(containerName)).exec().get(0);
    }

    @Override
    public InspectContainerCmd buildContainer(CreateWorkspaceDto containerData) {
        System.out.println("PASSWORD=" + containerData.getPassword());
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withPortBindings(PortBinding.parse(containerData.getPort() + ":8443"));
        CreateContainerResponse containerResponse = dockerClient
                .createContainerCmd("vscode-test")
                .withName(containerData.getName())
                .withHostConfig(hostConfig)
                .withEnv(Arrays.asList("PUID=1000", "PGID=1000", "TZ=Etc/UTC",
                        "PASSWORD=" + containerData.getPassword()))
                .withVolumes(new Volume("~/.config:/config"))
                .exec();

        dockerClient.startContainerCmd(containerResponse.getId()).exec();
        return dockerClient.inspectContainerCmd(containerResponse.getId());
    }

}
