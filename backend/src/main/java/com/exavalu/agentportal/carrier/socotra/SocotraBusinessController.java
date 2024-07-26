package com.exavalu.agentportal.carrier.socotra;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.exavalu.agentportal.model.MappingEntry;
import com.exavalu.agentportal.model.PolicyDetails;
import com.exavalu.agentportal.model.XmlMappingConfig;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Driver;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.InsuranceLine;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.InsuredInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.PolicyInfo;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.Risk;
import com.exavalu.agentportal.repository.PolicyDetailsRepo;
import com.exavalu.agentportal.repository.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import springfox.documentation.spring.web.json.Json;

@RestController
@RequestMapping("${apiPrefix}")
public class SocotraBusinessController {
	public String createPolicyWorkFlow(String socotraEndPoint, String socotraUsername, String socotraPassword,
			String socotraHostName, QuickIndication quickIndicationDetails) {
		SocotraRequestMapper socotraRequestMapper = new SocotraRequestMapper();
		SocotraResponseMapper socotraResponseMapper = new SocotraResponseMapper();
		String authenticateRequestBody = socotraRequestMapper.buildAuthenticateRequestBody(socotraUsername,
				socotraPassword, socotraHostName);
		JsonObject resultJson = new JsonObject();
		if (authenticateRequestBody != null && authenticateRequestBody.contains("null")) {
			resultJson.addProperty("message", authenticateRequestBody);
			return resultJson.toString();
		}
		ResponseEntity<String> authenticateResponse = socotraResponseMapper.authenticate(authenticateRequestBody,
				socotraEndPoint);
		if (authenticateResponse == null || authenticateResponse.getBody() == null) {
			resultJson.addProperty("message", "Authentication response is null or undefined.");
			return resultJson.toString();
		}

		String responseBody = authenticateResponse.getBody();
		JsonNode jsonNode = null;
		try {
			jsonNode = new ObjectMapper().readTree(responseBody);
		} catch (JsonProcessingException e) {
			resultJson.addProperty("message", e.getMessage());
			e.printStackTrace();
		}

		if (jsonNode == null || jsonNode.get("authorizationToken") == null) {
			resultJson.addProperty("message",
					"Authorization token is null or undefined in the authentication response.");
			return resultJson.toString();
		}

		String authToken = jsonNode.get("authorizationToken").asText();
		String policyHolderRequestBody = socotraRequestMapper.buildPolicyHolderRequestBody(quickIndicationDetails);

		if (policyHolderRequestBody != null && policyHolderRequestBody.contains("null")) {
			resultJson.addProperty("message", policyHolderRequestBody);
			return resultJson.toString();
		}
		ResponseEntity<String> policyHolderResponse = socotraResponseMapper.createPolicyHolder(authToken,
				policyHolderRequestBody, socotraEndPoint);
		if (policyHolderResponse == null || policyHolderResponse.getBody() == null) {
			resultJson.addProperty("message", "Policy holder response is null or undefined.");
			return resultJson.toString();
		}
		String policyHolderResponseBody = policyHolderResponse.getBody();
		JsonNode jsonPolicyHolderNode = null;
		try {
			jsonPolicyHolderNode = new ObjectMapper().readTree(policyHolderResponseBody);
		} catch (JsonProcessingException e) {
			resultJson.addProperty("message", e.getMessage());
			e.printStackTrace();
		}

		if (jsonPolicyHolderNode == null || jsonPolicyHolderNode.get("locator") == null) {
			resultJson.addProperty("message", "Locator is null or undefined in the policy holder response.");
			return resultJson.toString();
		}

		String locator = jsonPolicyHolderNode.get("locator").asText();
		String policyRequestBody = socotraRequestMapper.buildPolicyRequestBody(locator, quickIndicationDetails);
		if (policyRequestBody != null && policyRequestBody.contains("null")) {
			resultJson.addProperty("message", policyRequestBody);
			return resultJson.toString();
		}
		ResponseEntity<String> policyResponse = socotraResponseMapper.createPolicy(authToken, policyRequestBody,
				socotraEndPoint);

		if (policyResponse == null || policyResponse.getBody() == null) {
			resultJson.addProperty("message", "Policy response is null or undefined.");
			return resultJson.toString();
		}

		String policyResponseBody = policyResponse.getBody();
		JsonNode jsonPolicyNode = null;

		try {
			jsonPolicyNode = new ObjectMapper().readTree(policyResponseBody);
		} catch (JsonProcessingException e) {
			resultJson.addProperty("message", e.getMessage());
			e.printStackTrace();
		}
		if (jsonPolicyNode == null || jsonPolicyNode.get("locator") == null) {
			resultJson.addProperty("message", "Locator is null or undefined in the policy response.");
			return resultJson.toString();
		}
		if (jsonPolicyNode == null || jsonPolicyNode.get("characteristics").get(0).get("grossPremium") == null) {
			resultJson.addProperty("message", "Gross Premium is null or undefined in the policy response.");
			return resultJson.toString();
		}
		resultJson.addProperty("locator", jsonPolicyNode.get("locator").asText());
		resultJson.addProperty("fullTermAmount",
				jsonPolicyNode.get("characteristics").get(0).get("grossPremium").asInt());
		resultJson.addProperty("policyData", jsonPolicyNode.toString());
		return resultJson.toString();
	}

	public String createEndorsementWorkFlow(String socotraEndPoint, String socotraUsername, String socotraPassword,
			String socotraHostName, QuickIndication quickIndicationDetails, String policyLocator) {
		SocotraRequestMapper socotraRequestMapper = new SocotraRequestMapper();
		SocotraResponseMapper socotraResponseMapper = new SocotraResponseMapper();
		String authenticateRequestBody = socotraRequestMapper.buildAuthenticateRequestBody(socotraUsername,
				socotraPassword, socotraHostName);
		JsonObject resultJson = new JsonObject();
		if (authenticateRequestBody != null && authenticateRequestBody.contains("null")) {
			resultJson.addProperty("message", authenticateRequestBody);
			return resultJson.toString();
		}
		ResponseEntity<String> authenticateResponse = socotraResponseMapper.authenticate(authenticateRequestBody,
				socotraEndPoint);
		if (authenticateResponse == null || authenticateResponse.getBody() == null) {
			resultJson.addProperty("message", "Authentication response is null or undefined.");
			return resultJson.toString();
		}

		String responseBody = authenticateResponse.getBody();
		JsonNode jsonNode = null;
		try {
			jsonNode = new ObjectMapper().readTree(responseBody);
		} catch (JsonProcessingException e) {
			resultJson.addProperty("message", e.getMessage());
			e.printStackTrace();
		}

		if (jsonNode == null || jsonNode.get("authorizationToken") == null) {
			resultJson.addProperty("message",
					"Authorization token is null or undefined in the authentication response.");
			return resultJson.toString();
		}

		String authToken = jsonNode.get("authorizationToken").asText();

		// get policy details from socotra start

		ResponseEntity<String> getPolicyDetailsResponse = socotraResponseMapper.getPolicyDetailsResponse(authToken,
				socotraEndPoint, policyLocator);

		if (getPolicyDetailsResponse == null || getPolicyDetailsResponse.getBody() == null) {
			resultJson.addProperty("message", "getPolicyDetails response is null or undefined.");
			return resultJson.toString();
		}

		String policyDetailsBody = getPolicyDetailsResponse.getBody();
		JsonNode jsongetPolicyNode = null;
		try {
			jsongetPolicyNode = new ObjectMapper().readTree(policyDetailsBody);
			/*
			 * JsonNode policyDetails1 = jsongetPolicyNode.get("exposures"); JsonNode
			 * exposuresArray = policyDetails1; List<JsonNode> filteredNodes = new
			 * ArrayList<>();
			 * 
			 * for (JsonNode exposureNode : exposuresArray) { JsonNode nameNode =
			 * exposureNode.get("name"); if (nameNode != null &&
			 * "Drivers".equals(nameNode.asText())) {
			 * 
			 * filteredNodes.add(exposureNode); } } for (JsonNode driverNode :
			 * filteredNodes) { System.out.println(driverNode); }
			 */

		} catch (JsonProcessingException e) {
			resultJson.addProperty("message", e.getMessage());
			e.printStackTrace();
		}

		// get policy details from socotra end

		String createEndorsementsRequestBody = socotraRequestMapper.buildEndorsementRequestBody(quickIndicationDetails,
				policyLocator, jsongetPolicyNode);
		if (createEndorsementsRequestBody != null && createEndorsementsRequestBody.contains("null")) {
			resultJson.addProperty("message", createEndorsementsRequestBody);
			return resultJson.toString();
		}
		ResponseEntity<String> createEndorsementsResponse = socotraResponseMapper.createEndorsements(authToken,
				createEndorsementsRequestBody, socotraEndPoint, policyLocator);
		if (createEndorsementsResponse == null || createEndorsementsResponse.getBody() == null) {
			resultJson.addProperty("message", "Authentication response is null or undefined.");
			return resultJson.toString();
		}

		String responseEndorsememntBody = createEndorsementsResponse.getBody();
		JsonNode jsonEndorsementNode = null;
		try {
			jsonEndorsementNode = new ObjectMapper().readTree(responseEndorsememntBody);
		} catch (JsonProcessingException e) {
			resultJson.addProperty("message", e.getMessage());
			e.printStackTrace();
		}

		if (jsonEndorsementNode.get("locator") != null) {
			String responseEndorsememntLocator = jsonEndorsementNode.get("locator").asText();
			//resultJson.addProperty("message", "Endorsement issued successfully:" + responseEndorsememntLocator);
			resultJson.addProperty("message", "Endorsement issued successfully");
			resultJson.addProperty("locator", responseEndorsememntLocator);
			return resultJson.toString();
		}

		return resultJson.toString();
	}
}
