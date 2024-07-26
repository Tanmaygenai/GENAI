package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.AppConfig;
import com.exavalu.agentportal.repository.AppConfigRepo;
import com.exavalu.agentportal.service.FileStorageDBServiceImpl;
import com.exavalu.agentportal.service.FileUploadService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("${apiPrefix}")
public class FileController {

	@Autowired
	private FileStorageDBServiceImpl fileStorageService;
	
	@Value("${portalMode}")
	String portalMode;
	
	/*
	 * @Value("${carrierMode}") String carrierMode;
	 */

	@Autowired
	@Qualifier("db")
	private FileUploadService fileUploadDBService;

//	@Autowired
//	@Qualifier("carrier")
//	private FileUploadService fileUploadCarrierService;
	
	@Autowired
	@Qualifier("gw")
	private FileUploadService fileUploadGWService;
	
	@Autowired
	private AppConfigRepo repo;
	

	private static final Logger logger = LogManager.getLogger(FileController.class);

	@PostMapping("${securedString}" + "/upload")
	public ResponseEntity<String> uploadData(@RequestParam("document") MultipartFile[] files,
			@RequestParam("transactionNumber") String quoteNumber,
			@RequestParam("transactionIndicator") String transactionIndicator,@RequestParam("documentType") String documentType,
			@RequestParam("status") String status,@RequestHeader(value = "username") String username)
			throws Exception {

		logger.info("Entering FileController uploadData method");
		String carrierMode = "";
		AppConfig carrierModeField = repo.findByKeytype("sourcesystem");
		if(carrierModeField != null) {
			carrierMode = carrierModeField.getPropCarrierMode();
		}
		String policyNumber= quoteNumber.replaceAll("\\D", "");
		String responseData = null;
		String description = null;
		if (carrierMode.equalsIgnoreCase("GW")) {
			responseData = fileUploadGWService.storePolicyFile(files,quoteNumber,transactionIndicator,documentType,status);
		} 
		else {
			responseData = fileUploadDBService.storePolicyFile(files,quoteNumber,transactionIndicator,documentType,status);
		}
		logger.info("Exiting FileController uploadData method");
		return new ResponseEntity<String>(responseData, HttpStatus.OK);
	}
}
