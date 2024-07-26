package com.exavalu.agentportal.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("carrier")
public class FileStorageCarrierServiceImpl implements CabinateFileStorageService {

	private static final Logger logger = LogManager.getLogger(FileStorageCarrierServiceImpl.class);

	public String storeFile(MultipartFile file, String description, String classification) {
		logger.debug("Entering FileStorageCarrierServiceImpl storeFile method, with MultipartFile = " + file.getName()
				+ " , " + "description = " + description + " , " + "classification = " + classification);
		return null;
	}

}
