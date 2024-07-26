package com.exavalu.agentportal.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.exavalu.agentportal.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {
	List<Task> findByAgentEmail(String agentEmail);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("update TaskEntity set quote_number = :quoteNumber, task_description = :taskDescription WHERE indication_id = :indication_id ")
	int updateTaskDescription(String quoteNumber, String taskDescription, String indication_id);
}
