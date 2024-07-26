
package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.QuoteList;
import com.exavalu.agentportal.model.db.LossNotice;
import com.exavalu.agentportal.model.LossNoticeList;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.service.OpenWorkService;
import com.exavalu.agentportal.service.OpenWorkSocService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class OpenWorkController {
	private static final Logger logger = LogManager.getLogger(OpenWorkController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@Autowired
	@Qualifier("db")
	private OpenWorkService openWorkDBService;

	@Autowired
	@Qualifier("carrier")
	private OpenWorkService openWorkCarrierService;
	
	@Autowired
	@Qualifier("db")
	private OpenWorkSocService openWorkSocDBService;

	@PostMapping("${securedString}" + "/openWorkDB")
	public ResponseEntity<List<QuoteList>> getOpenWorkDBItem(@RequestBody String role,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering OpenWorkController getOpenWorkDBItem method");
		List<QuoteList> openWorkDB = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			openWorkDB = openWorkDBService.getItems(username, role);
		} else {
			openWorkDB = openWorkCarrierService.getItems(username, role);
		}

		if (openWorkDB == null) {
			logger.info("Exiting OpenWorkController getOpenWorkDBItem method");
			return new ResponseEntity<List<QuoteList>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting OpenWorkController getOpenWorkDBItem method");
			return new ResponseEntity<List<QuoteList>>(openWorkDB, HttpStatus.OK);
		}

	}
	
	@PostMapping("${securedString}" + "/openWorkPolicyDB")
	public ResponseEntity<List<PolicyDetails>> getOpenWorkSocDBItem(@RequestBody String role,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering OpenWorkController getOpenWorkSocDBItem method");
		List<PolicyDetails> openWorkDB = null;
			openWorkDB = openWorkSocDBService.getItems(username, role);
		if (openWorkDB == null) {
			logger.info("Exiting OpenWorkController getOpenWorkSocDBItem method");
			return new ResponseEntity<List<PolicyDetails>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting OpenWorkController getOpenWorkSocDBItem method");
			return new ResponseEntity<List<PolicyDetails>>(openWorkDB, HttpStatus.OK);
		}

	}
	
	
	@PostMapping("${securedString}" + "/getLossNotice")
	public  ResponseEntity<List<LossNotice>> getLossNoticeDBItem(@RequestBody String role,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering OpenWorkController getLossNoticeDBItem method");
		List<LossNotice> lossNoticeDB = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			lossNoticeDB = openWorkDBService.getlossNoticeItems(username, role);
		} else {
			lossNoticeDB = openWorkCarrierService.getlossNoticeItems(username, role);
		}

		if (lossNoticeDB == null) {
			logger.info("Exiting OpenWorkController getLossNoticeDBItem method");
			return new ResponseEntity<List<LossNotice>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting OpenWorkController getLossNoticeDBItem method");
			return new ResponseEntity<List<LossNotice>>(lossNoticeDB, HttpStatus.OK);
		}
	}

	@GetMapping("${securedString}" + "/submitOpenWork")
	public String submitOpenWorkItem(@RequestParam(value = "appNumber") String encryptedAppNumber,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering OpenWorkController submitOpenWorkItem method");
		String appNumber = cryptoUtil.decryptData(encryptedAppNumber);
		String details = openWorkDBService.submitOpenWork(appNumber, username);
		logger.info("Exiting OpenWorkController submitOpenWorkItem method");
		return details;
	}

	@GetMapping("${securedString}" + "/deleteOpenWork")
	public String deleteOpenWorkItem(@RequestParam(value = "appNumber") String encryptedAppNumber,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering OpenWorkController deleteOpenWorkItem method");
		String appNumber = cryptoUtil.decryptData(encryptedAppNumber);
		String details = openWorkDBService.deleteOpenWork(appNumber, username);
		logger.info("Exiting OpenWorkController deleteOpenWorkItem method");
		return details;
	}
}
