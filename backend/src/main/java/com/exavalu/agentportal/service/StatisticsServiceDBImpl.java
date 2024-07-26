package com.exavalu.agentportal.service;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.exavalu.agentportal.model.UserStats;
import com.exavalu.agentportal.repository.StatisticsRepo;

@Service
@Qualifier("db")
public class StatisticsServiceDBImpl implements StatisticsService {

	private static final Logger logger = LogManager.getLogger(StatisticsServiceDBImpl.class);
	@Autowired
	private StatisticsRepo repository;

	@Override
	public List<UserStats> getUserStats() {
		logger.debug("Entering StatisticsServiceDBImpl getUserStats method");
		// TODO Auto-generated method stub
		return repository.findAll();
//		return null;
	}
}
