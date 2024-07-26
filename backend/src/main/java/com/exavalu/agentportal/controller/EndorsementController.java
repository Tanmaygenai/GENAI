package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.service.EndorsementService;
import com.exavalu.agentportal.service.QuickQuoteService;

@RestController
@RequestMapping("${apiPrefix}")
public class EndorsementController {
	private static final Logger logger = LogManager.getLogger(EligibilityCheckController.class);

	@Value("${portalMode}")
	String portalMode;
	
	@Value("${carrierMode}")
	String carrierMode;

	@Autowired
	@Qualifier("db")
	private QuickQuoteService endorsementDBService;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();
	
	@Autowired
	@Qualifier("soc")
	private EndorsementService endorsementSocService;

	public EndorsementController() {
		/*
		 * 
		 */
	}

	
	@PostMapping("${securedString}" + "/endorsements/{policyLocator}")
	public ResponseEntity<PolicyPremiumInfo> postEndorsementDetails(
			@RequestBody String encryptedQuickIndicationDetails, @PathVariable("policyLocator") String policyLocator) {
		logger.info("Entering EndorsementController postEndorsementDetails method");
		PolicyPremiumInfo endorsementDetails = null;
		QuickIndication quickIndicationDetails = null;
		String decryptedQuickIndicationDetails = cryptoUtil.decryptData(encryptedQuickIndicationDetails);
		quickIndicationDetails = gson.fromJson(decryptedQuickIndicationDetails, QuickIndication.class);
		endorsementDetails = endorsementSocService.createEndorsementDetails(policyLocator, quickIndicationDetails);

		if (endorsementDetails == null) {
			logger.info("Exiting EndorsementController getPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting EndorsementController getPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(endorsementDetails, HttpStatus.OK);
		}
	}
}
