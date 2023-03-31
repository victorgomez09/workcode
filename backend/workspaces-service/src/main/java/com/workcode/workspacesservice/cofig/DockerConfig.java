package com.workcode.workspacesservice.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

@Configuration
public class DockerConfig {
    
    @Bean
    public DockerClient generateDockerClient() {
        return DockerClientBuilder.getInstance().build();
    }
}
