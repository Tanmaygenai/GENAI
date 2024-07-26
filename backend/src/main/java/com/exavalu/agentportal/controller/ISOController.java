package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.FireLine;
import com.exavalu.agentportal.service.ISOService;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}")
public class ISOController {
	private static final Logger logger = LogManager.getLogger(ISOController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private ISOService ISOServiceDBService;
	@Autowired
	@Qualifier("carrier")
	private ISOService ISOServiceCarrierService;
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@PostMapping("${securedString}" + "/isofireline")
	public String getFireLineDetails(@RequestBody String encryptedFireLineDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering ISOController getFireLineDetails method");
		String responseMessage = null;
		FireLine fireLineDetails = null;
		String decryptedFireLineDetails = cryptoUtil.decryptData(encryptedFireLineDetails);
		fireLineDetails = gson.fromJson(decryptedFireLineDetails, FireLine.class);
		System.out.print(fireLineDetails);
		if (portalMode.equalsIgnoreCase("DB")) {
			responseMessage = ISOServiceDBService.getISOData(fireLineDetails);
		} else {
			responseMessage = ISOServiceCarrierService.getISOData(fireLineDetails);
		}
		logger.info("Exiting ISOController getFireLineDetails method");
		return responseMessage;
	}
}
