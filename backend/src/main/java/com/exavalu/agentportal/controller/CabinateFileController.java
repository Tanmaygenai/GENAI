package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.service.CabinateFileStorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${apiPrefix}")
public class CabinateFileController {
	@Value("${portalMode}")
	String portalMode;
	private static final Logger logger = LogManager.getLogger(CabinateFileController.class);

	@Autowired
	@Qualifier("db")
	private CabinateFileStorageService cabinetFileStorageDBService;

	@Autowired
	@Qualifier("carrier")
	private CabinateFileStorageService cabinetFileStorageCarrierService;

	@PostMapping("${securedString}" + "/fileupload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("description") String description, @RequestParam("classification") String classification) {
		logger.info("Entering CabinateFileController uploadFile");
		String responseData = null;

		if (portalMode.equalsIgnoreCase("DB")) {
			responseData = cabinetFileStorageDBService.storeFile(file, description, classification);
		} else {
			responseData = cabinetFileStorageCarrierService.storeFile(file, description, classification);
		}
		logger.info("Exiting CabinateFileController uploadFile");
		return new ResponseEntity<String>(responseData, HttpStatus.OK);
	}

}
