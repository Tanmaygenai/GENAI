package com.exavalu.agentportal.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.exavalu.agentportal.carrier.guidewire.GuidewireBusinessController;
import com.exavalu.agentportal.service.FileUploadService;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
@Qualifier("gw")
public class fileUploadGWServiceImpl implements FileUploadService{
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${AttachmentGWURL}")
	String guidewireUploadDocURL;
	
	@Value("${guidewire.auth}")
	String auth;
	
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	private static final Logger logger = LogManager.getLogger(fileUploadGWServiceImpl.class);
	
	@Override
	public String storePolicyFile(MultipartFile[] files, String policyNumber,String transactionIndicator,
			String documentType, String status) {
		// TODO Auto-generated method stub
		
		GuidewireBusinessController service = new GuidewireBusinessController();
		String authValue = cryptoUtil.decryptResult(auth);
		String responseBody= service.storePolicyFile(files,policyNumber,transactionIndicator,documentType,status,guidewireUploadDocURL,authValue);
		return responseBody;
	}

}
