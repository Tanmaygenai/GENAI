package com.exavalu.agentportal.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.exavalu.agentportal.model.db.AgentFeedback;
import com.exavalu.agentportal.service.AgentFeedbackService;

@RestController
@RequestMapping("${apiPrefix}")
public class AgentFeedbackController {

	private static final Logger logger = LogManager.getLogger(AgentFeedbackController.class);
	@Value("${portalMode}")
    String portalMode;
	@Autowired
	@Qualifier("db")
	private AgentFeedbackService agentFeedbackDBService;
	
	@Autowired
	@Qualifier("carrier")
	private AgentFeedbackService agentFeedbackCarrierService;
	
	@Autowired
	private CryptoUtil cryptoUtil;
    Gson gson = new GsonBuilder().create();
	
	 @PostMapping("${securedString}" + "/agentFeedback")
	 public String submitFeedback(@RequestBody String encryptedFeedback,
	            @RequestHeader(value = "username") String username) {
		 logger.info("Entering AgentFeedbackController submitFeedback method");
		 String response = "";
		 AgentFeedback agentFeedback=null;
		 String decryptedFeedback = cryptoUtil.decryptData(encryptedFeedback);
		 agentFeedback = gson.fromJson(decryptedFeedback, AgentFeedback.class);
	        if (portalMode.equalsIgnoreCase("DB")) {
	            response = agentFeedbackDBService.submitFeedback(agentFeedback);
	            logger.info("Exiting AgentFeedbackController submitFeedback method");
	        } else {
	        	response = agentFeedbackCarrierService.submitFeedback(agentFeedback);
	        	logger.info("Exiting AgentFeedbackController submitFeedback method");
	        }
					return response;
		 
	 }
	 
	 @GetMapping("${securedString}" + "/getFeedback")
	    public ResponseEntity<List<AgentFeedback>> getAgentFeedback( ) {
	        logger.info("Entering AgentFeedbackController getAgentFeedback method");
	        List<AgentFeedback> agentFeedback = null;
	        if (portalMode.equalsIgnoreCase("DB")) {
	        	agentFeedback = agentFeedbackDBService.getFeedback();
	        } else {
	            // Customised implementation if portal mode switched to API
	        }
	        if (agentFeedback == null) {
	            logger.info("Exiting AgentFeedbackController getAgentFeedback method");
	            return new ResponseEntity<List<AgentFeedback>>(HttpStatus.NOT_FOUND);
	        } else {
	            logger.info("Exiting AgentFeedbackController getAgentFeedback method");
	            return new ResponseEntity<List<AgentFeedback>>(agentFeedback, HttpStatus.OK);
	        }

	    }
}
