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

import com.exavalu.agentportal.model.AppConfig;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.repository.AppConfigRepo;
import com.exavalu.agentportal.service.QuickQuoteService;

@RestController
@RequestMapping("${apiPrefix}")
public class QuickQuoteController {
	private static final Logger logger = LogManager.getLogger(EligibilityCheckController.class);

	@Value("${portalMode}")
	String portalMode;
	
	/*
	 * @Value("${carrierMode}") String carrierMode;
	 */

	@Autowired
	@Qualifier("db")
	private QuickQuoteService quickQuoteDBService;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@Autowired
	@Qualifier("gw")
	private QuickQuoteService quickQuoteGwService;
	
	@Autowired
	@Qualifier("soc")
	private QuickQuoteService quickQuoteSocService;

	public QuickQuoteController() {
		/*
		 * 
		 */
	}
	
	@Autowired
	private AppConfigRepo repo;

	@PostMapping("${securedString}" + "/QuickIndicationSaveForLater")
	public ResponseEntity<Quote> getPolicySaveforLaterById(@RequestBody String encryptedQuickIndicationDetails,
			@RequestHeader(value = "username") String username,@RequestHeader(value = "insuredName") String insuredName,@RequestHeader(value = "state") String state,
			@RequestHeader(value = "effectiveDt") String effectiveDt,@RequestHeader(value = "product") String product,@RequestHeader(value = "tempId") int tempId ) {
		logger.info("Entering QuickQuoteController getPolicySaveforLaterById method");
		Quote quoteDetails = null;
		QuickIndication quickIndicationDetails = null;
		String decryptedQuickIndicationDetails = cryptoUtil.decryptData(encryptedQuickIndicationDetails);
		quoteDetails = quickQuoteDBService.getQuickQuotePremiumSaveForLater(decryptedQuickIndicationDetails, username, insuredName, state,effectiveDt,product,tempId );
		
			return new ResponseEntity<Quote>(quoteDetails, HttpStatus.OK);
	}

	@PostMapping("${securedString}" + "/QuickIndication")
	public ResponseEntity<PolicyPremiumInfo> getPolicyById(@RequestBody String encryptedQuickIndicationDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering QuickQuoteController getPolicyById method");
		String carrierMode = "";
		AppConfig carrierModeField = repo.findByKeytype("sourcesystem");
		if(carrierModeField != null) {
			carrierMode = carrierModeField.getPropCarrierMode();
		}
		PolicyPremiumInfo premiumDetails = null;
		QuickIndication quickIndicationDetails = null;
		String decryptedQuickIndicationDetails = cryptoUtil.decryptData(encryptedQuickIndicationDetails);
		quickIndicationDetails = gson.fromJson(decryptedQuickIndicationDetails, QuickIndication.class);
		if (carrierMode.equalsIgnoreCase("GW")) {
			premiumDetails = quickQuoteGwService.getQuickQuotePremium(quickIndicationDetails, username);
		} else if (carrierMode.equalsIgnoreCase("SOC")) {
			premiumDetails = quickQuoteSocService.getQuickQuotePremium(quickIndicationDetails, username);
		} else {
			premiumDetails = quickQuoteDBService.getQuickQuotePremium(quickIndicationDetails, username);
		}

		if (premiumDetails == null) {
			logger.info("Exiting QuickQuoteController getPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting QuickQuoteController getPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(premiumDetails, HttpStatus.OK);
		}
	}

	
	
	@PostMapping("${securedString}" + "/QuickIndicationProperty")
	public ResponseEntity<PolicyPremiumInfo> getPropertyPolicyById(@RequestBody String encryptedQuickIndicationDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering QuickQuoteController getPropertyPolicyById method");
		PolicyPremiumInfo premiumDetails = null;
		QuickIndication quickIndicationDetails = null;
		String decryptedQuickIndicationDetails = cryptoUtil.decryptData(encryptedQuickIndicationDetails);
		quickIndicationDetails = gson.fromJson(decryptedQuickIndicationDetails, QuickIndication.class);
		premiumDetails = quickQuoteDBService.getQuickQuotePremium(quickIndicationDetails, username);
		if (premiumDetails == null) {
			logger.info("Exiting QuickQuoteController getPropertyPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting QuickQuoteController getPropertyPolicyById method");
			return new ResponseEntity<PolicyPremiumInfo>(premiumDetails, HttpStatus.OK);
		}
	}
}
