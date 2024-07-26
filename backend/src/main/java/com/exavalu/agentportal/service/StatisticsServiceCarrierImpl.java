package com.exavalu.agentportal.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exavalu.agentportal.model.UserStats;

@Service
@Qualifier("carrier")
public class StatisticsServiceCarrierImpl implements StatisticsService {
	private static final Logger logger = LogManager.getLogger(StatisticsServiceCarrierImpl.class);

	@Override
	public List<UserStats> getUserStats() {
		logger.debug("Entering StatisticsServiceCarrierImpl getUserStats method");
		// TODO Auto-generated method stub
		return null;
	}

}
