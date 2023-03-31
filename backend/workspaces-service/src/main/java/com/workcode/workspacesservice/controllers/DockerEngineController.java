package com.workcode.workspacesservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.dockerjava.api.model.Container;
import com.workcode.workspacesservice.services.DockerEngineService;

@RestController
@RequestMapping("/docker")
public class DockerEngineController {
    
    @Autowired
    private DockerEngineService dockerService;

    @GetMapping
    public ResponseEntity<List<Container>> index() {
        return ResponseEntity.ok(dockerService.findAllContainers());
    }
}
