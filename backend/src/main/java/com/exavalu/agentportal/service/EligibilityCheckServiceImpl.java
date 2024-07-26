package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.EligibilityCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {
	@Autowired
	private RestTemplate restTemplate;
	private static final Logger logger = LogManager.getLogger(EligibilityCheckServiceImpl.class);

	@Override
	public String eligibilityCheck(EligibilityCheck eligibilityCheckDetails) {
		logger.debug("Entering EligibilityCheckServiceImpl eligibilityCheck method, with EligibilityCheck = "
				+ eligibilityCheckDetails.toString());
		return "Eligible";
	}
}
