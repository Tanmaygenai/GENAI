package com.exavalu.agentportal.service;
import com.exavalu.agentportal.model.CognitoUser;
import java.util.List;

public interface CognitoUserService {
	
	List<CognitoUser> getUserList();
	String deleteUser(String username);
	//String signupUser(CognitoUser cognitoUserDetails);
	String createAdminUser(CognitoUser cognitoUserDetails);
	
}
