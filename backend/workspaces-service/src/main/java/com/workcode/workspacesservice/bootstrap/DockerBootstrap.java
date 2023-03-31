package com.workcode.workspacesservice.bootstrap;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Volume;

// TODO: move to docker service
@Component
public class DockerBootstrap {

    @Autowired
    private DockerClient dockerClient;
    
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws URISyntaxException {
        String image = dockerClient.buildImageCmd()
            .withDockerfile(getFileFromResource("docker/coder-vscode-server/Dockerfile"))
            .withTags(Collections.singleton("vscode-test"))
            .exec(new BuildImageResultCallback())
            .awaitImageId();
        System.out.println("image: " + image);

        HostConfig hostConfig = HostConfig.newHostConfig().withPortBindings(PortBinding.parse("1209:8443"));
        CreateContainerResponse containerResponse = dockerClient
            .createContainerCmd("vscode-test")
            .withName("vscode-test-container")
            .withHostConfig(hostConfig)
            .withEnv(Arrays.asList("PUID=1000", "PGID=1000", "TZ=Etc/UTC", "PASSWORD=test"))
            .withVolumes(new Volume("/path/to/appdata/config:/config"))
            .exec();

        System.out.println("container: " + containerResponse.getId());
        dockerClient.startContainerCmd(containerResponse.getId()).exec();
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }

    private static void printFile(File file) {
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
