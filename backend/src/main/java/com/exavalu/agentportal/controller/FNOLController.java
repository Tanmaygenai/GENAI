package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.FNOL;
import com.exavalu.agentportal.service.FNOLService;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("${apiPrefix}")
public class FNOLController {

	public FNOLController() {
		/*
		 * 
		 */
	}

	@Autowired
	@Qualifier("db")
	private FNOLService fnolDBService;

	@Autowired
	@Qualifier("carrier")
	private FNOLService fnolCarrierService;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@Value("${portalMode}")
	String portalMode;

	static Logger logger = LogManager.getLogger(FNOLController.class);

	@PostMapping("${securedString}" + "/FNOL")
	public String generateLossNotice(@RequestBody String encryptedFnolDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering FNOLController generateLossNotice method");
		String lossNoticeNumber = "";
		FNOL fnolDetails = null;
		String decryptedFnolDetails = cryptoUtil.decryptData(encryptedFnolDetails);
		fnolDetails = gson.fromJson(decryptedFnolDetails, FNOL.class);

		if (portalMode.equalsIgnoreCase("DB")) {
			lossNoticeNumber = fnolDBService.generateLossNoticeNumber(fnolDetails,username,decryptedFnolDetails);
		} else {
			lossNoticeNumber = fnolCarrierService.generateLossNoticeNumber(fnolDetails,username,decryptedFnolDetails);
		}
		logger.info("Exiting FNOLController generateLossNotice method");
		return lossNoticeNumber;
	}

	@PostMapping("${securedString}" + "/lncount")
	public Long getLossNoticeCount(@RequestBody String role, @RequestHeader(value = "username") String username) {
		logger.info("Entering FNOLController getLossNoticeCount method");
		Long lossNoticeCount;
		if (portalMode.equalsIgnoreCase("DB")) {
			lossNoticeCount = fnolDBService.getLNCount(username, role);
		} else {
			lossNoticeCount = fnolCarrierService.getLNCount(username, role);
		}
		logger.info("Exiting FNOLController getLossNoticeCount method");
		return lossNoticeCount;
	}
}