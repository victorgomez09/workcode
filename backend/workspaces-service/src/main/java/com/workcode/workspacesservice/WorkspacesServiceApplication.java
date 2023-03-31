package com.workcode.workspacesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WorkspacesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkspacesServiceApplication.class, args);
	}

}
