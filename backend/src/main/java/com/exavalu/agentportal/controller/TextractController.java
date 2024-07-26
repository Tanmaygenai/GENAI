package com.exavalu.agentportal.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.service.FileUploadService;
import com.exavalu.agentportal.service.TextractService;

@RestController
@RequestMapping("${apiPrefix}")
public class TextractController {
	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private TextractService textractDBService;

	@Autowired
	@Qualifier("carrier")
	private TextractService textractCarrierService;
	
	private static final Logger logger = LogManager.getLogger(TextractController.class);
	
	@PostMapping("${securedString}" + "/uploadPdf")
	public ResponseEntity<List> uploadPdf(@RequestParam("file") MultipartFile[] files)
			throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info("Entering TextractController uploadPdf method");
		List responseData = null;
 
		if (portalMode.equalsIgnoreCase("DB")) { 
			for (MultipartFile name : files) {
				logger.info("NAME: %s", name.getOriginalFilename());
			}
			responseData = textractDBService.uploadPdf(files);
		} else { 
			responseData = textractCarrierService.uploadPdf(files);
		}
		logger.info("Exiting TextractController uploadPdf method");
		logger.info("Total execution took {}ms", (System.currentTimeMillis() - startTime));
		return new ResponseEntity<List>(responseData, HttpStatus.OK);
	}
}
