package com.exavalu.agentportal.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.exavalu.agentportal.model.CognitoUser;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;
import java.security.*;

@Service
public class CognitoUserServiceImpl implements CognitoUserService {
	private static final Logger logger = LogManager.getLogger(CognitoUserServiceImpl.class);
	@Value("${userPoolId}")
	String userPoolId;

	@Value("${clientIdForPool}")
	String clientId;

	@Value("${awsAccessKeyId}")
	String awsAccessKeyId;

	@Value("${awsSecretAccessKey}")
	String awsSecretAccessKey;

	@Value("${awsRegion}")
	String awsRegion;
	
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	public List<CognitoUser> getUserList() {
		logger.debug("Entering CognitoUserServiceImpl getUserList method");
		String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
		String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
		CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
				.region(Region.of(awsRegion)).credentialsProvider(StaticCredentialsProvider.create(awsCreds))
				.build();

		List<CognitoUser> cogUser = new ArrayList<>();
		try {
			String userPoolIdValue = cryptoUtil.decryptResult(userPoolId);
			ListUsersRequest usersRequest = ListUsersRequest.builder().userPoolId(userPoolIdValue).build();
			ListUsersResponse res = cognitoClient.listUsers(usersRequest);
			for (UserType userType : res.users()) {
				CognitoUser userDetails = new CognitoUser();
				userDetails.setUserName(userType.username());
				userDetails.setUserStatus(userType.userStatusAsString());
				userDetails.setEmail(getAttributeValue(userType, "email"));
				userDetails.setPhone(getAttributeValue(userType, "phone_number"));
				userDetails.setPassword(getAttributeValue(userType, "password"));
				userDetails.setRole(getAttributeValue(userType, "custom:role"));
				userDetails.setOrganization(getAttributeValue(userType, "custom:organization"));
				userDetails.setAgencyCd(getAttributeValue(userType, "custom:agencyCd"));
				userDetails.setGroupCd(getAttributeValue(userType, "custom:groupCd"));
				userDetails.setStatus(getAttributeValue(userType, "custom:status"));
				cogUser.add(userDetails);
			}
		} catch (CognitoIdentityProviderException e) {
			logger.error("Inside CognitoUserServiceImpl getUserList method: {}", e.awsErrorDetails().errorMessage());
		}
		return cogUser;
	}

	public String deleteUser(String userName) {
		logger.debug("Entering CognitoUserServiceImpl deleteUser method, with userName = " + userName);
		String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
		String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
		CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
				.region(Region.of(awsRegion)).credentialsProvider(StaticCredentialsProvider.create(awsCreds))
				.build();
		String result = "";
		try {
			String userPoolIdValue = cryptoUtil.decryptResult(userPoolId);
			AdminDeleteUserRequest request = AdminDeleteUserRequest.builder().userPoolId(userPoolIdValue).username(userName)
					.build();
			AdminDeleteUserResponse response = cognitoClient.adminDeleteUser(request);
			if (response.toString().equals("AdminDeleteUserResponse()")) {
				result = "Success";
			}
		} catch (CognitoIdentityProviderException e) {
			logger.error("Inside CognitoUserServiceImpl deleteUser method: {}", e.awsErrorDetails().errorMessage());
			result = e.awsErrorDetails().errorMessage();
		}
		return result;

	}

	public String signupUser(CognitoUser cognitoUserDetails) {
		logger.debug("Entering CognitoUserServiceImpl signupUser method, with CognitoUser = "
				+ cognitoUserDetails.toString());
		String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
		String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
		CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
				.region(Region.of(awsRegion)).credentialsProvider(StaticCredentialsProvider.create(awsCreds))
				.build();
		String result = "";
		String userName = cognitoUserDetails.getUserName();
		String password = cognitoUserDetails.getPassword();
		String email = cognitoUserDetails.getEmail();
		String phone = cognitoUserDetails.getPhone();
		String role = cognitoUserDetails.getRole();
		String organization = cognitoUserDetails.getOrganization();
		String groupCd = cognitoUserDetails.getGroupCd();
		String agencyCd = cognitoUserDetails.getAgencyCd();
		String status = cognitoUserDetails.getStatus();
		String newPhone = phone.replaceAll("-", "");
		AttributeType userAttr1 = AttributeType.builder().name("email").value(email).build();
		AttributeType userAttr2 = AttributeType.builder().name("phone_number").value(newPhone).build();

		AttributeType userAttr3 = AttributeType.builder().name("custom:role").value(role).build();

		AttributeType userAttr4 = AttributeType.builder().name("custom:groupCd").value(groupCd).build();

		AttributeType userAttr5 = AttributeType.builder().name("custom:agencyCd").value(agencyCd).build();
		
		AttributeType userAttr6 = AttributeType.builder().name("custom:organization").value(organization).build();
		AttributeType userAttr7 = AttributeType.builder().name("custom:status").value(status).build();

		List<AttributeType> userAttrsList = new ArrayList<>();
		userAttrsList.add(userAttr1);
		userAttrsList.add(userAttr2);
		userAttrsList.add(userAttr3);
		userAttrsList.add(userAttr4);
		userAttrsList.add(userAttr5);
		userAttrsList.add(userAttr6);
		userAttrsList.add(userAttr7);
		try {
			String clientIdValue = cryptoUtil.decryptResult(clientId);
			SignUpRequest signUpRequest = SignUpRequest.builder().userAttributes(userAttrsList).username(userName)
					.clientId(clientIdValue).password(password).build();

			cognitoClient.signUp(signUpRequest);
			result = "Success";
		} catch (CognitoIdentityProviderException e) {
			logger.error("Inside CognitoUserServiceImpl signupUser method: {}", e.awsErrorDetails().errorMessage());
			result = e.awsErrorDetails().errorMessage();
		}

		return result;
	}

	public static String generateRandomPassword(int len) {
		String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
		String numberChars = "0123456789";
		String specialChars = "!@#$%^&*()";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		String chars = uppercaseChars + lowercaseChars + numberChars + specialChars;

		// add at least one uppercase letter
		int randomIndex = random.nextInt(uppercaseChars.length());
		sb.append(uppercaseChars.charAt(randomIndex));

		// add at least one lowercase letter
		randomIndex = random.nextInt(lowercaseChars.length());
		sb.append(lowercaseChars.charAt(randomIndex));

		// add at least one number
		randomIndex = random.nextInt(numberChars.length());
		sb.append(numberChars.charAt(randomIndex));

		// add at least one special character
		randomIndex = random.nextInt(specialChars.length());
		sb.append(specialChars.charAt(randomIndex));

		// add the remaining characters
		int remainingLen = len - 4;
		for (int i = 0; i < remainingLen; i++) {
			randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		// shuffle the characters to make the order random
		String shuffledString = new String(sb.toString().toCharArray());
		Collections.shuffle(Arrays.asList(shuffledString));
		return shuffledString;
	}

	private String getAttributeValue(UserType userType, String attributeName) {
		for (AttributeType attribute : userType.attributes()) {
			if (attributeName.equals(attribute.name())) {
				return attribute.value();
			}
		}
		return null;
	}

	public String createAdminUser(CognitoUser cognitoUserDetails) {
		logger.debug("Entering CognitoUserServiceImpl createAdminUser method, with CognitoUser = "
				+ cognitoUserDetails.toString());
		String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
		String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
		CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
				.region(Region.of(awsRegion)).credentialsProvider(StaticCredentialsProvider.create(awsCreds))
				.build();

		String result = "";
		String userName = cognitoUserDetails.getUserName();
		String email = cognitoUserDetails.getEmail();
		String phone = cognitoUserDetails.getPhone();
		String role = cognitoUserDetails.getRole();
		String organization = cognitoUserDetails.getOrganization();
		String groupCd = cognitoUserDetails.getGroupCd();
		String agencyCd = cognitoUserDetails.getAgencyCd();
		String status = cognitoUserDetails.getStatus();
		String newPhone = phone.replaceAll("-", "");
		int len = 8;
		String temppassword = generateRandomPassword(len) + "5!";
		try {
			String userPoolIdValue = cryptoUtil.decryptResult(userPoolId);
			AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder().userPoolId(userPoolIdValue)
					.username(userName).temporaryPassword(temppassword)
					.userAttributes(AttributeType.builder().name("email").value(email).build(),
							AttributeType.builder().name("phone_number").value(newPhone).build(),
							AttributeType.builder().name("custom:role").value(role).build(),
							AttributeType.builder().name("custom:organization").value(organization).build(),
							AttributeType.builder().name("custom:groupCd").value(groupCd).build(),
							AttributeType.builder().name("custom:agencyCd").value(agencyCd).build(),
							AttributeType.builder().name("custom:status").value(status).build())
					.desiredDeliveryMediums(DeliveryMediumType.EMAIL).build();
			AdminCreateUserResponse createUserResult = cognitoClient.adminCreateUser(createUserRequest);
			if (createUserResult.user() != null) {
				result = "Success";
			} else {
				result = "User creation failed.";
			}
		} catch (CognitoIdentityProviderException e) {
			logger.error("Inside CognitoUserServiceImpl createAdminUser method: {}",
					e.awsErrorDetails().errorMessage());
			result = e.awsErrorDetails().errorMessage();
		}
		return result;
	}

}
