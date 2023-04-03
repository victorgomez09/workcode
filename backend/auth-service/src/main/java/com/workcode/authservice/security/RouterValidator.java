package com.workcode.authservice.security;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.workcode.authservice.dtos.RequestDto;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouterValidator {
    
    @Getter @ Setter
    private List<RequestDto> paths;

    public boolean isAdminPath(RequestDto data) {
        return paths.stream().anyMatch(p -> Pattern.matches(p.getUri(), data.getUri()) && p.getMethod().equals(data.getMethod()));
    }
}
