package com.exavalu.agentportal.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exavalu.agentportal.model.Task;
import com.exavalu.agentportal.service.TaskService;

@RestController
@RequestMapping("${apiPrefix}")
public class TaskController {
	private static final Logger logger = LogManager.getLogger(DashboardController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private TaskService taskDBService;

	@Autowired
	@Qualifier("carrier")
	private TaskService taskCarrierService;

	@GetMapping("${securedString}" + "/getTask")
	public ResponseEntity<List<Task>> getTask(@RequestHeader(value = "agentEmail") String agentEmail) {
		logger.info("Entering TaskController getTask method");
		List<Task> tasks = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			tasks = taskDBService.getTasks(agentEmail);
		} else {
			tasks = taskCarrierService.getTasks(agentEmail);
		}
		System.out.println(tasks);
		if (tasks == null || tasks.size() == 0) {
			logger.info("Exiting TaskController getTask method");
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		} else {
			logger.info("Exiting TaskController getTask method");
			return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
		}

	}
}
