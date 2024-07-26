package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.CognitoUser;
import com.exavalu.agentportal.model.Login;
import com.exavalu.agentportal.model.db.LoginUser;
import com.exavalu.agentportal.model.db.User;
import com.exavalu.agentportal.service.LoginService;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class LoginController {
	private static final Logger logger = LogManager.getLogger(EligibilityCheckController.class);
	@Autowired
	private LoginService loginService;
	@Value("${portalMode}")
	String portalMode;

	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@PostMapping("${securedString}" + "/login")
	public String validateLogin(@RequestBody String encryptedLoginDetails, HttpServletRequest request,
			HttpSession session) {
		logger.info("Entering LoginController validateLogin method");

		Login loginDetails = null;
		String decryptedLoginDetails = cryptoUtil.decryptData(encryptedLoginDetails);
		loginDetails = gson.fromJson(decryptedLoginDetails, Login.class);
		String role = "";
		if (portalMode.equalsIgnoreCase("DB")) {
			role = loginService.getRoleFromDB(loginDetails);
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting LoginController validateLogin method");
		return role;
	}

	@PostMapping("${securedString}" + "/loginUser")
	public String insertLoginUser(@RequestBody String encryptedUserDetails, HttpServletRequest request,
			HttpSession session) {
		logger.info("Entering LoginController insertLoginUser method");
		LoginUser loginUserDetails = null;
		Login loginDetails = null;
		String decryptedUserDetails = cryptoUtil.decryptData(encryptedUserDetails);
		loginUserDetails = gson.fromJson(decryptedUserDetails, LoginUser.class);
		String response = "";
		if (portalMode.equalsIgnoreCase("DB")) {
			response = loginService.insertLoginUserIntoDb(loginUserDetails);
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting LoginController insertLoginUser method");
		return response;
	}

	@PostMapping("${securedString}" + "/createUser")
	public String createNewUser(@RequestBody String encryptedUserDetails, HttpServletRequest request,
			HttpSession session) {
		logger.info("Entering LoginController createNewUser method");
		User userDetails = null;
		String decryptedUserDetails = cryptoUtil.decryptData(encryptedUserDetails);
		userDetails = gson.fromJson(decryptedUserDetails, User.class);
		String response = "";
		if (portalMode.equalsIgnoreCase("DB")) {
			response = loginService.createNewUser(userDetails);
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting LoginController createNewUser method");
		return response;
	}

	@PostMapping("${securedString}" + "/getUser")
	public ResponseEntity<List<User>> getUser(@RequestBody String encryptedUserDetails, HttpServletRequest request,
			HttpSession session) {
		logger.info("Entering LoginController getUser method");
		User userDetails = null;
		String decryptedUserDetails = cryptoUtil.decryptData(encryptedUserDetails);
		userDetails = gson.fromJson(decryptedUserDetails, User.class);
		List<User> response = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			response = loginService.getUser(userDetails);
		} else {
			// Customised implementation if portal mode switched to API
		}
		if (response == null) {
			logger.info("Exiting LoginController getUser method");
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		} else {
			logger.info("Exiting LoginController getUser method");
			return new ResponseEntity<List<User>>(response, HttpStatus.OK);
		}

	}

	@GetMapping("${securedString}" + "/getLoginUser")
	public List<LoginUser> geLogintUser(HttpServletRequest request, HttpSession session) {
		logger.info("Entering LoginController geLogintUser method");
		List<LoginUser> response = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			response = loginService.getLoginUser();
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting LoginController geLogintUser method");
		return response;
	}

	@PostMapping("${securedString}" + "/updateUser")
	public String updateUser(@RequestBody String encryptedUserDetails, HttpServletRequest request,
			HttpSession session) {
		logger.info("Entering LoginController updateUser method");
		String response = "";
		User userDetails = null;
		String decryptedUserDetails = cryptoUtil.decryptData(encryptedUserDetails);
		userDetails = gson.fromJson(decryptedUserDetails, User.class);
		if (portalMode.equalsIgnoreCase("DB")) {
			response = loginService.updateUser(userDetails);
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting LoginController updateUser method");
		return response;
	}

	@PostMapping("${securedString}" + "/deleteUser")
	public String deleteUser(@RequestBody User userDetails, HttpServletRequest request, HttpSession session) {
		logger.info("Entering LoginController deleteUser method");
		String response = "";
		if (portalMode.equalsIgnoreCase("DB")) {
			response = loginService.deleteUser(userDetails);
		} else {
			// Customised implementation if portal mode switched to API
		}
		logger.info("Exiting LoginController deleteUser method");
		return response;
	}
}
