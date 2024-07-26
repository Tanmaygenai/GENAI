package com.exavalu.agentportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.service.FileUploadService;
import com.exavalu.agentportal.service.ExcelDetailExtractService;

@RestController
@RequestMapping("${apiPrefix}")
public class ExcelDetailExtractController {
	
	
	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private ExcelDetailExtractService excelDetailExtractDBService;

	@Autowired
	@Qualifier("carrier")
	private ExcelDetailExtractService excelDetailExtractCarrierService;
	
	private static final Logger logger = LogManager.getLogger(ExcelDetailExtractController.class);
	
	
	
	@PostMapping("${securedString}" + "/uploadExcel")
	public ResponseEntity<List> uploadExcel(@RequestParam("file") MultipartFile[] files)
			throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info("Entering DriverDetailExtractController uploadExcel method");
		List responseData = null;
 
		if (portalMode.equalsIgnoreCase("DB")) { 
			for (MultipartFile name : files) {
				logger.info("NAME: %s", name.getOriginalFilename());
			}
			responseData = excelDetailExtractDBService.uploadExcel(files);
		} else { 
			responseData = excelDetailExtractDBService.uploadExcel(files);
		}
		logger.info("Exiting DriverDetailExtractController upload method");
		logger.info("Total execution took {}ms", (System.currentTimeMillis() - startTime));
		return new ResponseEntity<List>(responseData, HttpStatus.OK);
	}

}
