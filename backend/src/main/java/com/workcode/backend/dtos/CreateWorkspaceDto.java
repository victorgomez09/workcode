package com.workcode.backend.dtos;

import com.workcode.backend.enums.EWorkspaceImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateWorkspaceDto {
    private String user;
    private String name;
    private EWorkspaceImage image;
    private int port;
    private String password;
}
