package com.avis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnAvisApplication {
	private static final Logger logger = LoggerFactory.getLogger(AnAvisApplication.class);
	public static void main(String[] args) {
		
		logger.warn("pluto");
		SpringApplication.run(AnAvisApplication.class, args);
	}

}
