package com.workcode.workspacesservice.bootstrap;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;

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

}
