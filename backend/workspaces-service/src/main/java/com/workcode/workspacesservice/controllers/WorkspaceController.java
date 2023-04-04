package com.workcode.workspacesservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workcode.workspacesservice.dtos.CreateWorkspaceDto;
import com.workcode.workspacesservice.models.Workspace;
import com.workcode.workspacesservice.services.WorkspaceService;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Workspace>> findAllbyUserId(@PathVariable("userId") Integer userId) {
        List<Workspace> result = workspaceService.findAllbyUserId(userId);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<Workspace> findById(@PathVariable("workspaceId") Integer workspaceId) {
        Workspace result = workspaceService.findById(workspaceId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/name/{workspaceName}")
    public ResponseEntity<Workspace> findById(@PathVariable("workspaceName") String workspaceName) {
        Workspace result = workspaceService.findByName(workspaceName);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Workspace> save(@RequestBody CreateWorkspaceDto workspaceData) {
        return ResponseEntity.ok(workspaceService.save(workspaceData));
    }

}
