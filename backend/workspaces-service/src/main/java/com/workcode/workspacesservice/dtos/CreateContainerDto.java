package com.workcode.workspacesservice.dtos;

import com.workcode.workspacesservice.enums.EWorkspaceImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContainerDto {
    private String name;
    private EWorkspaceImage image;
}