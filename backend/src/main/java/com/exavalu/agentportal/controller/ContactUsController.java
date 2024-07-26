package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exavalu.agentportal.model.db.ContactUsDetails;
import com.exavalu.agentportal.service.ContactDBService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${apiPrefix}")
public class ContactUsController {
    private static final Logger logger = LogManager.getLogger(ContactUsController.class);

    @Value("${portalMode}")
    String portalMode;
    @Autowired
    private ContactDBService contactDBService;
    @Autowired
    private CryptoUtil cryptoUtil;
    Gson gson = new GsonBuilder().create();
    
    @GetMapping("${securedString}" + "/getCases")
    public ResponseEntity<List<ContactUsDetails>> getContactUsDetails(@RequestParam("role") String role,
            @RequestHeader(value = "username") String username) {
        logger.info("Entering ContactUsController getContactUsDetails method");
        List<ContactUsDetails> contactUsDetails = null;
        if (portalMode.equalsIgnoreCase("DB")) {
            contactUsDetails = contactDBService.getContactUsDetails(username, role);
        } else {
            // Customised implementation if portal mode switched to API
        }
        if (contactUsDetails == null) {
            logger.info("Exiting ContactUsController getContactUsDetails method");
            return new ResponseEntity<List<ContactUsDetails>>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Exiting ContactUsController getContactUsDetails method");
            return new ResponseEntity<List<ContactUsDetails>>(contactUsDetails, HttpStatus.OK);
        }

    }
    
    @PostMapping("${securedString}" + "/closeCaseByUser")
    public String closeCaseByUser(@RequestBody String encryptedContactUsDetails,
    		  @RequestHeader(value = "username") String username) {
    	 logger.info("Entering ContactUsController sendDetails method");
         String response = "";

         ContactUsDetails contactUsDetails = null;
         String decryptedContactUsDetails = cryptoUtil.decryptData(encryptedContactUsDetails);
         contactUsDetails = gson.fromJson(decryptedContactUsDetails, ContactUsDetails.class);
         if (portalMode.equalsIgnoreCase("DB")) {
             response = contactDBService.closeCaseByUser(username, contactUsDetails);
         } else {
             // Customised implementation if portal mode switched to API
         }
         logger.info("Exiting ContactUsController sendDetails method");
         return response;
    	
    }
    
    @PostMapping("${securedString}" + "/caseReopen")
    public String caseReopen(@RequestBody String encryptedContactUsDetails,
    		  @RequestHeader(value = "username") String username) {
    	 logger.info("Entering ContactUsController sendDetails method");
         String response = "";

         ContactUsDetails contactUsDetails = null;
         String decryptedContactUsDetails = cryptoUtil.decryptData(encryptedContactUsDetails);
         contactUsDetails = gson.fromJson(decryptedContactUsDetails, ContactUsDetails.class);
         if (portalMode.equalsIgnoreCase("DB")) {
             response = contactDBService.caseReopen(username, contactUsDetails);
         } else {
             // Customised implementation if portal mode switched to API
         }
         logger.info("Exiting ContactUsController sendDetails method");
         return response;
    }
    
    @GetMapping("${securedString}" + "/getCasesById")
    public ResponseEntity<List<ContactUsDetails>> getContactUsDetailsById(@RequestParam("caseId") String caseId,
            @RequestHeader(value = "username") String username) {
        logger.info("Entering ContactUsController getContactUsDetailsById method");
        List<ContactUsDetails> contactUsDetails = null;
        if (portalMode.equalsIgnoreCase("DB")) {
            contactUsDetails = contactDBService.getContactUdDetailsByCaseId(username, caseId);
        } else {
            // Customised implementation if portal mode switched to API
        }
        if (contactUsDetails == null) {
            logger.info("Exiting ContactUsController getContactUsDetails method");
            return new ResponseEntity<List<ContactUsDetails>>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Exiting ContactUsController getContactUsDetails method");
            return new ResponseEntity<List<ContactUsDetails>>(contactUsDetails, HttpStatus.OK);
        }

    }

    @PostMapping("${securedString}" + "/contactUs")
    public String sendDetails(@RequestBody String encryptedContactUsDetails,
            @RequestHeader(value = "username") String username) {
        logger.info("Entering ContactUsController sendDetails method");
        String response = "";

        ContactUsDetails contactUsDetails = null;
        String decryptedContactUsDetails = cryptoUtil.decryptData(encryptedContactUsDetails);
        contactUsDetails = gson.fromJson(decryptedContactUsDetails, ContactUsDetails.class);
        if (portalMode.equalsIgnoreCase("DB")) {
            response = contactDBService.sendDetails(contactUsDetails);
        } else {
            // Customised implementation if portal mode switched to API
        }
        logger.info("Exiting ContactUsController sendDetails method");
        return response;
    }

}