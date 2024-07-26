package com.exavalu.agentportal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.exavalu.agentportal.config.APIHeaders;

@SpringBootApplication
@EnableScheduling
public class AgentPortalApplication {

	private static final Logger logger = LogManager.getLogger(AgentPortalApplication.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(AgentPortalApplication.class, args);
		} catch (Exception e) {
			logger.error("Inside AgentPortalApplication main method: {}", e.getMessage());
		}
	}

}
