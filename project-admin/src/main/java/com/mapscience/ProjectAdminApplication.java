package com.mapscience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectAdminApplication {
	private final static Logger logger = LoggerFactory.getLogger(ProjectAdminApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProjectAdminApplication.class, args);
		logger.info("ProjectAdminApplication is success!");
	}

}

