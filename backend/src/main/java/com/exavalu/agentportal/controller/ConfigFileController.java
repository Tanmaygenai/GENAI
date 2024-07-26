package com.exavalu.agentportal.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.service.ConfigDocumentService;

@RestController
@RequestMapping("${apiPrefix}")
public class ConfigFileController {
	private static final Logger logger = LogManager.getLogger(DashboardController.class);
	
	@Autowired
	private ConfigDocumentService configDocumentService;
	@Value("${portalMode}")
	String portalMode;
	
	@PostMapping("${securedString}" + "/postConfigDocument")
	public String uploadConfigDocument(@RequestParam("file") MultipartFile file,@RequestHeader(value="username") String username) {
		String result="";
		byte[] configFileBytes = null;
		try {
			configFileBytes = file.getBytes();
			if (portalMode.equalsIgnoreCase("DB")) {
	        	result = configDocumentService.uploadConfigDocument(configFileBytes);
	        }
	        else {
	        	// Customised implementation if portal mode switched to API
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping("${securedString}" + "/getConfigDocument")
	public String getDocument() {
		String result="";
        if (portalMode.equalsIgnoreCase("DB")) {
        	result = configDocumentService.getConfigDocument();
        }
        else {
        	// Customised implementation if portal mode switched to API
        }
        return result;
	}
	
	@GetMapping("${securedString}" + "/downloadConfigDocument")
	public ResponseEntity<Resource> downloadConfigDoc(@RequestHeader(value = "username") String username) {
		logger.debug("Entering downloadConfigDoc method, with userName = " + username);
		ResponseEntity<Resource> file = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			// result = cognitoUserService.signupUser(cognitoUserDetails);
			file = configDocumentService.downloadConfigDocument();
			System.out.print(file);
		} else {
			// Customised implementation if portal mode switched to API
		}
		return file;
	}

}
