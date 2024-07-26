package com.exavalu.agentportal.service;

import com.exavalu.agentportal.error.FileStorageException;
import com.exavalu.agentportal.model.DocumentContainer;
import com.exavalu.agentportal.repository.PolicyFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
@Qualifier("db")
public class FileUploadServiceDBImpl implements FileUploadService {
	static Logger logger = LogManager.getLogger(FileUploadServiceDBImpl.class);
	@Autowired
	private PolicyFileRepo policyFileRepo;

	public String storePolicyFile(MultipartFile[] files,String policyNumber,String transactionIndicator,String documentType,String s) {
		String description=null;
		logger.debug("Entering FileUploadServiceDBImpl storePolicyFile method, with MultipartFile = " + files.toString()
				+ " , " + "description = " + description + " , " + "policyNumber = " + policyNumber);
		String fileName = null;
		try {
			for (MultipartFile file : files) {
				logger.info("NAME: {}", file.getOriginalFilename());
				fileName = StringUtils.cleanPath(file.getOriginalFilename());
				DocumentContainer dbFile = new DocumentContainer(fileName, file.getContentType(), file.getBytes(),
						description, policyNumber);
				policyFileRepo.save(dbFile);
			}
			return "Success";

		} catch (IOException ex) {
			logger.error("Inside FileUploadServiceDBImpl storePolicyFile method: {}", ex.getMessage());
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
}
