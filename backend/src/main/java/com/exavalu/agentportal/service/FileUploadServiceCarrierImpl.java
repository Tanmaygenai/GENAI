package com.exavalu.agentportal.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("carrier")
public class FileUploadServiceCarrierImpl implements FileUploadService {
	private static final Logger logger = LogManager.getLogger(FileUploadServiceCarrierImpl.class);

	@Override
	public String storePolicyFile(MultipartFile[] files, String policyNumber,String transactionIndicator,
			String documentType, String status) {
		logger.debug("Entering FileUploadServiceCarrierImpl storePolicyFile method, with MultipartFile = " + files.toString()
		+ " , " + "policyNumber = " + policyNumber);

		return null;
	}
}
