package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.DRCExcessQuickIndication.ExcessQuickIndication;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.service.ExcessQuickIndicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}")
public class ExcessQuickIndicationController {
	private static final Logger logger = LogManager.getLogger(EligibilityCheckController.class);
	@Autowired
	private ExcessQuickIndicationService excessQuickIndicationService;

	public ExcessQuickIndicationController() {
		/*
		 * 
		 */
	}

	@PostMapping("${securedString}" + "/ExcessQuickIndication")
	public ResponseEntity<PolicyPremiumInfo> getPolicyById(
			@RequestBody ExcessQuickIndication excessQuickIndicationDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering ExcessQuickIndicationController getPolicyById method");
		PolicyPremiumInfo premiumDetails = excessQuickIndicationService
				.sendExcessQuickIndicationData(excessQuickIndicationDetails, username);
		if (premiumDetails == null) {
			logger.info("Exiting ExcessQuickIndicationController getPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting ExcessQuickIndicationController getPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(premiumDetails, HttpStatus.OK);
		}
	}
}
