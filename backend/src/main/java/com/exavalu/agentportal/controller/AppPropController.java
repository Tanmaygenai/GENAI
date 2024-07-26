package com.exavalu.agentportal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.model.AppConfig;
import com.exavalu.agentportal.service.AppConfigService;
import com.exavalu.agentportal.service.AppPropService;

import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("${apiPrefix}")
public class AppPropController {

	@Value("${portalMode}")
	String portalMode;
	private static final Logger logger = LogManager.getLogger(AppPropController.class);
	
	@Autowired
	@Qualifier("db")
	private AppPropService appPropDBService;
	
	@PostMapping("${securedString}" + "/applicationProperties")
	public ResponseEntity<String> updateCarrierMode(@RequestBody String carrierMode){
		
		logger.info("Entering AppPropController saveCarrierMode method");
		System.out.println(carrierMode);
		String responseData = null;
		String key="sourcesystem";
		responseData = appPropDBService.saveCarrierMode(carrierMode, key);
		logger.info("Exiting AppConfigController saveCarrierMode method");
		return new ResponseEntity<String>(responseData, HttpStatus.OK);
	}
}
