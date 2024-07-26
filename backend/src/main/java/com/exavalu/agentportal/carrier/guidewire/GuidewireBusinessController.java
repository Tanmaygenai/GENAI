package com.exavalu.agentportal.carrier.guidewire;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Base64;

import javax.annotation.PostConstruct;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.exavalu.agentportal.model.MappingEntry;
import com.exavalu.agentportal.model.XmlMappingConfig;
import com.exavalu.agentportal.model.DRCExcessQuickIndication.QuickIndication;
import com.exavalu.agentportal.service.QuickQuoteServiceGwImpl;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.exavalu.agentportal.error.FileStorageException;

public class GuidewireBusinessController {

	private static final Logger logger = LogManager.getLogger(GuidewireBusinessController.class);

	public String getQuickQuotePremium(QuickIndication quickIndicationDetails, String accountCreateGWURL,
			String newSubmissionGWURL, String auth) {
		logger.info("Entering GuidewireBusinessController getQuickQuotePremium method");

		GuidewireRequestMapper requestMapper = new GuidewireRequestMapper();
		GuidewireResponseMapper responseMapper = new GuidewireResponseMapper();
		String formatStr = null;
		try {
			formatStr = requestMapper.convertJsonToXml(quickIndicationDetails);
			String accNumber = requestMapper.getAccountNumber(quickIndicationDetails, accountCreateGWURL, auth);
			String res = requestMapper.transformXml(formatStr);
			String premiumDetails = requestMapper.newSubmission(res, accNumber, quickIndicationDetails,
					newSubmissionGWURL, auth);

			logger.info("Exiting GuidewireBusinessController getQuickQuotePremium method");
			return premiumDetails;
		} catch (Exception e) {
			logger.error("Inside GuidewireBusinessController getQuickQuotePremium method: {}", e.getMessage());
		}
		return "";
	}

	public String createPolicy(String quoteId, String issueSubmissionGWURL, String auth) {
		logger.info("Entering GuidewireBusinessController createPolicy method");

		GuidewireRequestMapper requestMapper = new GuidewireRequestMapper();
		GuidewireResponseMapper responseMapper = new GuidewireResponseMapper();
		String formatStr = null;
		try {
			String policyNumber = requestMapper.issueNewSubmission(quoteId, issueSubmissionGWURL, auth);

			logger.info("Exiting GuidewireBusinessController createPolicy method with policyNumber : " + policyNumber);
			return policyNumber;
		} catch (Exception e) {
			logger.error("Inside GuidewireBusinessController createPolicy method: {}", e.getMessage());
		}
		return "";
	}

	public String storePolicyFile(MultipartFile[] files, String policyNumber, String transactionIndicator,
			String documentType, String status, String guidewireUploadDocURL, String auth) {

		RestTemplate restTemplate = new RestTemplate();
		LinkedMultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();

		for (MultipartFile file : files) {
			try {
				ByteArrayResource fileResource = new ByteArrayResource(file.getBytes()) {
					@Override
					public String getFilename() {
						return file.getOriginalFilename();
					}
				};
				bodyMap.add("document", fileResource);
			} catch (IOException e) {
				logger.error("Inside GuidewireBusinessController storePolicyFile method: {}", e.getMessage());
			}
		}
		bodyMap.add("transactionNumber", policyNumber);
//      bodyMap.add("fileType", fileType);
		bodyMap.add("transactionIndicator", transactionIndicator);
		bodyMap.add("documentType", documentType);
		bodyMap.add("status", status);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		RequestEntity<LinkedMultiValueMap<String, Object>> requestEntity = RequestEntity.post(guidewireUploadDocURL)
				.headers(headers).body(bodyMap);

		try {
			// Send the API request using RestTemplate
			ResponseEntity<String> response = restTemplate.exchange(guidewireUploadDocURL, HttpMethod.POST,
					requestEntity, String.class);
			return response.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}