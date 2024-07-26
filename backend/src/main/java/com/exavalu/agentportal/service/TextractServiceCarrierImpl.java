package com.exavalu.agentportal.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
@Qualifier("carrier")
public class TextractServiceCarrierImpl implements TextractService{
	private static final Logger logger = LogManager.getLogger(TextractServiceCarrierImpl.class);

	@Override
	public List uploadPdf(MultipartFile[] files) {
		logger.debug("Entering FileUploadServiceCarrierImpl storePolicyFile method, with MultipartFile = " + files.toString());
		return null;
	}

}
