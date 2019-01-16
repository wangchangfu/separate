package com.mapscience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectGeneratorApplication {
	private final static Logger logger = LoggerFactory.getLogger(ProjectGeneratorApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ProjectGeneratorApplication.class, args);
		logger.info("ProjectGeneratorApplication is success!");
	}

}

