package com.exavalu.agentportal.carrier.socotra;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SocotraResponseMapper {
	public ResponseEntity<String> createPolicy(String authToken, String requestBody, String endPoint) {
	    String endpointUrl = endPoint + "policy";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer " + authToken);

	    RestTemplate restTemplate = new RestTemplate(); 
	    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
	    //ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class, headers);
	    ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.POST, entity, String.class);
	    return response;
	}
	
	public ResponseEntity<String> createPolicyHolder(String authToken, String requestBody, String endPoint) {
	    String endpointUrl = endPoint + "policyholder/create";
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer " + authToken);

	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
	    //ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class, headers);
	    ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.POST, entity, String.class);
	    return response;
	}
	
	public ResponseEntity<String> authenticate(String requestBody, String endPoint) {
		String endpointUrl = endPoint + "account/authenticate";
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    RestTemplate restTemplate = new RestTemplate(); 
	    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
	    //ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class, headers);
	    ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.POST, entity, String.class);
	    return response;
		
	}
	public ResponseEntity<String> createEndorsements(String authToken, String requestBody, String endPoint, String policyLocator) {
		String endpointUrl = endPoint + "policies/" + policyLocator + "/endorsements";
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer " + authToken);

	    RestTemplate restTemplate = new RestTemplate(); 
	    HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
	    //ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class, headers);
	    ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.POST, entity, String.class);
	    return response;
		
	}
	public ResponseEntity<String> getPolicyDetailsResponse(String authToken, String endPoint, String policyLocator) {
		String endpointUrl = endPoint + "policy/" + policyLocator;
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer " + authToken);

	    RestTemplate restTemplate = new RestTemplate(); 
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	    //ResponseEntity<String> response = restTemplate.postForEntity(endpointUrl, requestBody, String.class, headers);
	    ResponseEntity<String> response = restTemplate.exchange(endpointUrl, HttpMethod.GET, entity, String.class);
	    return response;
		
	}
}
