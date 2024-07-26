package com.exavalu.agentportal.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.Task;
import com.exavalu.agentportal.repository.TaskRepo;

@Service
@Qualifier("db")
public class TaskServiceDBImpl implements TaskService {
	private static final Logger logger = LogManager.getLogger(TaskServiceDBImpl.class);
	@Autowired
	private TaskRepo repository;

	@Override
	public List<Task> getTasks(String agentEmail) {
		logger.debug("Entering TaskServiceDBImpl getTasks method");
		// TODO Auto-generated method stub
		return repository.findByAgentEmail(agentEmail);
	}
}
