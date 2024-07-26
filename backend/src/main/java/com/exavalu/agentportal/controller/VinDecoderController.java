package com.exavalu.agentportal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exavalu.agentportal.integrations.vindecoder.VinDecoderService;
import com.exavalu.agentportal.model.AppConfig;
import com.exavalu.agentportal.model.Vin;
import com.exavalu.agentportal.repository.AppConfigRepo;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@RestController
@RequestMapping("${apiPrefix}")
public class VinDecoderController {
	private static final Logger logger = LogManager.getLogger(VinDecoderController.class);

	@Value("${portalMode}")
	String portalMode;
	/*
	 * @Value("${carrierMode}") String carrierMode;
	 */
	@Value("${vinApiUrl}")
	String vinApiUrl;
	@Value("${vinApiKey}")
	String vinApiKey;
	
	@Autowired
	@Qualifier("db")
	private VinDecoderService vinDecoderDBService;
	@Autowired
	@Qualifier("carrier")
	private VinDecoderService vinDecoderCarrierService;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();
	
	@Autowired
	private AppConfigRepo repo;
	
	@PostMapping("${securedString}" + "/decodeVin")
	public ResponseEntity<Vin> getVinDetails(@RequestBody String encryptedVinDetails,
			@RequestHeader(value = "username") String username) {
		String carrierMode = "";
		AppConfig carrierModeField = repo.findByKeytype("sourcesystem");
		if(carrierModeField != null) {
			carrierMode = carrierModeField.getPropCarrierMode();
		}
		Vin responseMessage = null;
		Vin vinDetails = null;
		String decryptedDetails = cryptoUtil.decryptData(encryptedVinDetails);
		vinDetails = gson.fromJson(decryptedDetails, Vin.class);
		logger.debug("=== Start Calling in VinDecodeController getVinDetails() ===");
		if (portalMode.equalsIgnoreCase("DB")) {
			responseMessage = vinDecoderDBService.getVehicleInfo(vinDetails, vinApiUrl, vinApiKey);
		} else {
			responseMessage = vinDecoderCarrierService.getVehicleInfo(vinDetails, null, null);
		}
		logger.debug("=== Finished Calling in VinDecodeController getVinDetails() ===");
		if (responseMessage == null) {
			return new ResponseEntity<Vin>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Vin>(responseMessage, HttpStatus.OK);
		}

	}
}
