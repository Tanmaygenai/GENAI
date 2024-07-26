package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.EligibilityCheck;
import com.exavalu.agentportal.service.EligibilityCheckService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}")
public class EligibilityCheckController {
	private static final Logger logger = LogManager.getLogger(EligibilityCheckController.class);
	@Autowired
	private EligibilityCheckService eligibilityCheckService;

	@PostMapping("${securedString}" + "/checkEligibility")
	public String checkEligibility(@RequestBody EligibilityCheck eligibilityCheckDetails) {
		logger.info("Entering EligibilityCheckController checkEligibility method");
		String status = eligibilityCheckService.eligibilityCheck(eligibilityCheckDetails);
		logger.info("Exiting EligibilityCheckController checkEligibility method");
		return status;
	}
}
