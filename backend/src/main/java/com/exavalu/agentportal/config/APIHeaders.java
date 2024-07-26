package com.exavalu.agentportal.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Configuration
public class APIHeaders implements CommandLineRunner {

	@Value("${secretManagerName}")
	String secretManagerName;
	@Value("${awsRegion}")
	String awsRegion;
	@Value("${awsAccessKeyId}")
	String awsAccessKeyId;
	@Value("${awsSecretAccessKey}")
	String awsSecretAccessKey;
	@Value("${apiHeaderStoreLocation}")
	String apiHeaderStoreLocation;
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	private static final Logger logger = LogManager.getLogger(APIHeaders.class);

	@Override
	public void run(String... args) throws Exception {

		logger.debug("Entering APIHeaders Config run method");
		String secretName = secretManagerName;
		Region region = Region.of(awsRegion); 

		String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
		String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
		AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
		// Create a Secrets Manager client
		SecretsManagerClient client = SecretsManagerClient.builder().region(region)
				.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();
		GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder().secretId(secretName).build();
		GetSecretValueResponse getSecretValueResponse;

		try {
			getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
		} catch (Exception e) {
			logger.error("ERROR: {}", e.getMessage());
			throw e;
		}

		String secret = getSecretValueResponse.secretString();
		JSONObject json = new JSONObject(secret);
		Iterator<String> keys = json.keys();
		
		try {
			FileWriter myWriter = new FileWriter(apiHeaderStoreLocation);
			BufferedWriter bw = new BufferedWriter(myWriter);
			bw.write("GENERATE_SOURCEMAP=false");
			bw.newLine();
			while (keys.hasNext()) {
				String keyValue = (String) keys.next();
				String valueString = json.getString(keyValue);
				bw.write(keyValue + "=" + valueString);
				bw.newLine();
			}
			bw.close();
			logger.debug("Exiting APIHeaders Config run method");
		} catch (IOException e) {
			logger.error("Inside APIHeaders Config run method: {}", e.getMessage());
			e.printStackTrace();
		}
	}
}