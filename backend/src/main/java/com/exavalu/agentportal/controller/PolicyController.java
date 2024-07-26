package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.AppConfig;
import com.exavalu.agentportal.model.DocumentContainer;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyPremiumInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.repository.AppConfigRepo;
import com.exavalu.agentportal.service.PolicyGWServiceImpl;
import com.exavalu.agentportal.service.PolicyService;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("${apiPrefix}")
public class PolicyController {
	private static final Logger logger = LogManager.getLogger(EligibilityCheckController.class);

	public PolicyController() {
		/*
		 * 
		 */
	}

	@Value("${portalMode}")
	String portalMode;

	/*
	 * @Value("${carrierMode}") String carrierMode;
	 */

	@Autowired
	@Qualifier("db")
	private PolicyService policyDBService;

	@Autowired
	@Qualifier("gw")
	private PolicyGWServiceImpl policyGWService;

	@Autowired
	private CryptoUtil cryptoUtil;

	@Autowired
	@Qualifier("carrier")
	private PolicyService policyCarrierService;

	Gson gson = new GsonBuilder().create();
	
	@Autowired
	private AppConfigRepo repo;

	@PostMapping("${securedString}" + "/policyDB")
	public ResponseEntity<QuoteList> getPolicyByIdFromDB(@RequestBody String id,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering PolicyController getPolicyByIdFromDB method");
		String decryptId = cryptoUtil.decryptData(id);
		QuoteList quoteList;
		if (portalMode.equalsIgnoreCase("DB")) {
			quoteList = policyDBService.getPolicyFromDB(decryptId, username);

		} else {
			quoteList = policyCarrierService.getPolicyFromDB(decryptId, username);

		}
		if (quoteList == null) {
			logger.info("Exiting PolicyController getPolicyByIdFromDB method");
			return new ResponseEntity<QuoteList>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting PolicyController getPolicyByIdFromDB method");
			return new ResponseEntity<QuoteList>(quoteList, HttpStatus.OK);
		}
	}

//to get policy document details

	@GetMapping("${securedString}" + "/policyDownload/{quoteNumber}")
	public ResponseEntity<List<Quote>> getPolicyFileDetails(@RequestHeader(value = "username") String username,
			@PathVariable("quoteNumber") String quoteNumber) {
		logger.info("Entering PolicyController getPolicyFileDetails method");
		quoteNumber = cryptoUtil.decryptData(quoteNumber);
		List<Quote> details = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			details = policyDBService.getPolicyFileDetails(username, quoteNumber);
			if (details == null) {
				logger.info("Exiting PolicyController getPolicyFileDetails method");
				return new ResponseEntity<List<Quote>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting PolicyController getPolicyFileDetails method");
				return new ResponseEntity<List<Quote>>(details, HttpStatus.OK);
			}
		} else {
			details = policyCarrierService.getPolicyFileDetails(username, quoteNumber);
			if (details == null) {
				logger.info("Exiting PolicyController getPolicyFileDetails method");
				return new ResponseEntity<List<Quote>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting PolicyController getPolicyFileDetails method");
				return new ResponseEntity<List<Quote>>(details, HttpStatus.OK);
			}
		}
//end to get policy document details

	}

	// policy download
	@GetMapping("${securedString}" + "/policyfiledownload/{fileId}")
	public ResponseEntity<Resource> downloadPolicyFile(@PathVariable String fileId) {

		logger.info("Entering PolicyController downloadPolicyFile method");
		DocumentContainer dbFile = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			dbFile = policyDBService.getPolicyFile(fileId);
		} else {
			dbFile = policyCarrierService.getPolicyFile(fileId);
		}
		logger.info("Exiting PolicyController downloadPolicyFile method");
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "/attachment; filename=\"" + dbFile.getName() + "\"")
				.body(new ByteArrayResource(dbFile.getFilecontent()));
	}
	// end for policy download

	@PostMapping("${securedString}" + "/createPolicy")
	public String createPolicy(@RequestBody String quoteData, @RequestHeader(value = "username") String username) {
		logger.info("Entering PolicyController createPolicy method");
		QuickIndication quickIndicationDetails = new QuickIndication();
		String decryptData = cryptoUtil.decryptData(quoteData);
		quickIndicationDetails = gson.fromJson(decryptData, QuickIndication.class);
		String policyNumber;
		String carrierMode = "";
		AppConfig carrierModeField = repo.findByKeytype("sourcesystem");
		if(carrierModeField != null) {
			carrierMode = carrierModeField.getPropCarrierMode();
		}
		if ((carrierMode.equalsIgnoreCase("GW"))) {
			policyNumber = policyGWService.createPolicy(quickIndicationDetails, username);
		} else if (portalMode.equalsIgnoreCase("DB")) {
			policyNumber = policyDBService.createPolicy(quickIndicationDetails, username);
		} else {
			System.out.println("Carrier");
			policyNumber = policyCarrierService.createPolicy(quickIndicationDetails, username);
		}
		if (policyNumber == null) {
			logger.info("Exiting PolicyController createPolicy method");
			return "";
		} else {
			logger.info("Exiting PolicyController createPolicy method");
			return policyNumber;
		}
	}
}
