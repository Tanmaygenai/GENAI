package com.exavalu.agentportal.controller;

import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.exavalu.agentportal.model.CognitoUser;
import com.exavalu.agentportal.service.CognitoUserService;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@RestController
@RequestMapping("${apiPrefix}")
public class CognitoUserController {
	private static final Logger logger = LogManager.getLogger(CognitoUserController.class);
	@Autowired
	private CognitoUserService cognitoUserService;

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@GetMapping("${securedString}" + "/getCognitoUserList")
	public ResponseEntity<List<CognitoUser>> listAllUsers(@RequestHeader(value = "username") String username) {
		logger.info("Entering CognitoUserController listAllUsers method");
		List<CognitoUser> cognitoUser = new ArrayList<>();
		if (portalMode.equalsIgnoreCase("DB")) {
			cognitoUser = cognitoUserService.getUserList();
		} else {
			// Customised implementation if portal mode switched to API
		}
		if (cognitoUser == null) {
			logger.info("Exiting CognitoUserController listAllUsers method");
			return new ResponseEntity<List<CognitoUser>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting CognitoUserController listAllUsers method");
			return new ResponseEntity<List<CognitoUser>>(cognitoUser, HttpStatus.OK);
		}
	}

	@PostMapping("${securedString}" + "/deleteCognitoUser")
	public String deleteUserFromPool(@RequestBody String userName, @RequestHeader(value = "username") String username) {
		logger.info("Entering CognitoUserController deleteUserFromPool method");
		CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder().region(Region.US_EAST_2)
				.credentialsProvider(ProfileCredentialsProvider.create()).build();
		String result = "";
		if (portalMode.equalsIgnoreCase("DB")) {
			result = cognitoUserService.deleteUser(userName);
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting CognitoUserController deleteUserFromPool method");
		return result;
	}

	@PostMapping("${securedString}" + "/userSignup")
	public String createNewUser(@RequestBody String encryptedUserDetails,
			@RequestHeader(value = "username") String username) {
		logger.info("Entering CognitoUserController createNewUser method");
		String decryptedUserDetails = cryptoUtil.decryptData(encryptedUserDetails);
		CognitoUser cognitoUserDetails = null;
		cognitoUserDetails = gson.fromJson(decryptedUserDetails, CognitoUser.class);
		String result = "";
		if (portalMode.equalsIgnoreCase("DB")) {
			// result = cognitoUserService.signupUser(cognitoUserDetails);
			result = cognitoUserService.createAdminUser(cognitoUserDetails);
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting CognitoUserController createNewUser method");
		return result;
	}
}
