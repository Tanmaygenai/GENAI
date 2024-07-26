package com.exavalu.agentportal.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.Task;

@Service
@Qualifier("carrier")
public class TaskServiceCarrierImpl implements TaskService {
	private static final Logger logger = LogManager.getLogger(TaskServiceCarrierImpl.class);

	@Override
	public List<Task> getTasks(String agentEmail) {
		logger.debug("Entering TaskServiceCarrierImpl getTasks method");
		// TODO Auto-generated method stub
		return null;
	}
}
