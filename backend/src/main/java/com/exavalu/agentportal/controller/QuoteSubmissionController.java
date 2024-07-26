package com.exavalu.agentportal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exavalu.agentportal.model.AppConfig;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.repository.AppConfigRepo;
import com.exavalu.agentportal.service.QuickQuoteService;
import com.exavalu.agentportal.service.QuoteSubmissionService;

@RestController
@RequestMapping("${apiPrefix}")
public class QuoteSubmissionController {
	private static final Logger logger = LogManager.getLogger(QuoteSubmissionController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private QuoteSubmissionService quoteSubmissionDBService;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@Autowired
	@Qualifier("carrier")
	private QuoteSubmissionService quoteSubmissionCarrierService;
	
	@Autowired
	@Qualifier("gw")
	private QuoteSubmissionService quoteSubmissionGWService;

	public QuoteSubmissionController() {
		/*
		 * 
		 */
	}

	@Autowired
	private AppConfigRepo repo;
	@PostMapping("${securedString}" + "/QuoteSubmission")
	public String submitQuote(@RequestBody String encryptedQuickIndicationDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering QuoteSubmissionController submitQuote method");
		String carrierMode = "";
		AppConfig carrierModeField = repo.findByKeytype("sourcesystem");
		if(carrierModeField != null) {
			carrierMode = carrierModeField.getPropCarrierMode();
		}
		QuickIndication quickIndicationDetails = null;
		String decryptedQuickIndicationDetails = cryptoUtil.decryptData(encryptedQuickIndicationDetails);
		quickIndicationDetails = gson.fromJson(decryptedQuickIndicationDetails, QuickIndication.class);
		String quoteNumber = null;
		if (carrierMode.equalsIgnoreCase("DB")) {
			quoteNumber = quoteSubmissionDBService.submitQuote(quickIndicationDetails);
		} else if (carrierMode.equalsIgnoreCase("GW")){
			quoteNumber = quoteSubmissionGWService.submitQuote(quickIndicationDetails);
		}else {
			quoteNumber = quoteSubmissionCarrierService.submitQuote(quickIndicationDetails);
		}
		logger.info("Exiting QuoteSubmissionController submitQuote method");
		return quoteNumber;
	}
	
	@PostMapping("${securedString}" + "/PropertyQuoteSubmission")
	public String submitPropertyQuote(@RequestBody String encryptedQuickIndicationDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering QuoteSubmissionController submitPropertyQuote method");
		QuickIndication quickIndicationDetails = null;
		String decryptedQuickIndicationDetails = cryptoUtil.decryptData(encryptedQuickIndicationDetails);
		quickIndicationDetails = gson.fromJson(decryptedQuickIndicationDetails, QuickIndication.class);
		String quoteNumber = null;
		quoteNumber = quoteSubmissionDBService.submitQuote(quickIndicationDetails);
		
		logger.info("Exiting QuoteSubmissionController submitPropertyQuote method");
		return quoteNumber;
	}
}
