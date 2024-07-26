package com.exavalu.agentportal.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.exavalu.agentportal.model.db.Quote;
import com.exavalu.agentportal.service.DataDownloadService;
import com.exavalu.agentportal.util.CryptoUtil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("${apiPrefix}")
public class DataDownloadController {
	private static final Logger logger = LogManager.getLogger(DataDownloadController.class);
	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private DataDownloadService dataDownloadService;

	@Autowired
	private CryptoUtil cryptoUtil;

	@Autowired
	@Qualifier("carrier")
	private DataDownloadService dataDownloadCarrierService;

	public DataDownloadController() {
		/*
		 * 
		 */
	}

	@GetMapping("${securedString}" + "/datadownload")
	public ResponseEntity<List<Quote>> getDataDownloadDetails(
			@RequestParam(value = "systemId", required = true) String systemId) {
		logger.info("Entering DataDownloadController getDataDownloadDetails method"+ systemId);
		systemId = cryptoUtil.decryptData(systemId);
		List<Quote> details = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			details = dataDownloadService.getDataDownloadDetails(systemId);
		} else {
			details = dataDownloadCarrierService.getDataDownloadDetails(systemId);
		}
		if (dataDownloadService == null) {
			logger.info("Exiting DataDownloadController getDataDownloadDetails method");
			return new ResponseEntity<List<Quote>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting DataDownloadController getDataDownloadDetails method");
			return new ResponseEntity(details, HttpStatus.OK);
		}
	}
}
