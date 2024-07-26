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

@RestController
@RequestMapping("${apiPrefix}")
public class AppConfigController {

	@Value("${portalMode}")
	String portalMode;
	private static final Logger logger = LogManager.getLogger(AppConfigController.class);
	
	@Autowired
	@Qualifier("db")
	private AppConfigService appConfigDBService;
	
	@Autowired
	@Qualifier("carrier")
	private AppConfigService appConfigCarrierService;
	
	@PostMapping("${securedString}" + "/uploadLogo")
	public ResponseEntity<String> uploadLogo(@RequestParam("file") MultipartFile file){
		
		logger.info("Entering AppConfigController uploadLogo method");
		String responseData = null;
		String key="brandlogo";
		if (portalMode.equalsIgnoreCase("DB")) {
			responseData = appConfigDBService.storeFile(file, key);
		} else {
			responseData = appConfigDBService.storeFile(file, key);
		}
		logger.info("Exiting AppConfigController uploadLogo method");
		return new ResponseEntity<String>(responseData, HttpStatus.OK);
	}

	//API to fetch logo
	@GetMapping("${securedString}" + "/getLogo/{keytype}")
	public ResponseEntity<AppConfig> getLogoObject(@PathVariable String keytype){
		
		logger.info("Entering AppConfigController getLogoObject method");
		AppConfig dbFile=null;
		AppConfig emptyFile = new AppConfig();
		
		if (portalMode.equalsIgnoreCase("DB")) {
			dbFile = appConfigDBService.getObject(keytype);
		} else {
			dbFile = appConfigDBService.getObject(keytype);
		}
		logger.info("Exiting AppConfigController getLogoObject method");
		if(dbFile==null) {
			return ResponseEntity.ok().body(emptyFile);
		}else {
			return ResponseEntity.ok().body(dbFile);
		}
	}

	//API to fetch theme
	@GetMapping("${securedString}" + "/getthemeconfig/{keytype}")
	public ResponseEntity getTheme(@PathVariable String keytype){
		
		logger.info("Entering AppConfigController getTheme method");
		AppConfig dbFile=null;
		AppConfig emptyFile = new AppConfig();
		
		if (portalMode.equalsIgnoreCase("DB")) {
			dbFile = appConfigDBService.getObject(keytype);
		} else {
			dbFile = appConfigDBService.getObject(keytype);
		}
		logger.info("Exiting AppConfigController getTheme method");
		if(dbFile==null) {
//			return new ResponseEntity("Key type not found",HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok().body(emptyFile);
		}else {
			return ResponseEntity.ok().body(new ByteArrayResource(dbFile.getFilecontent()));
		}	
	}
	
	@PostMapping("${securedString}" + "/storeTheme")
	public ResponseEntity<String> saveTheme(@RequestBody String theme){
		
		logger.info("Entering AppConfigController saveTheme method");
		
		String responseData = null;
		String key="customtheme";
		if (portalMode.equalsIgnoreCase("DB")) {
			responseData = appConfigDBService.storeTheme(theme, key);
		} else {
			responseData = appConfigDBService.storeTheme(theme, key);
		}
		logger.info("Exiting AppConfigController saveTheme method");
		return new ResponseEntity<String>(responseData, HttpStatus.OK);
	}
}
