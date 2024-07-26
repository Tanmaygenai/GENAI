package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.exavalu.agentportal.model.Task;

@Repository
public interface TaskService {
	List<Task> getTasks(String agentEmail);
}
