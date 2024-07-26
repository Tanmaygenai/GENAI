package com.exavalu.agentportal.service;

import com.exavalu.agentportal.error.FileStorageException;
import com.exavalu.agentportal.model.ContentManagement;
import com.exavalu.agentportal.repository.FileRepo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Qualifier("db")
public class FileStorageDBServiceImpl implements CabinateFileStorageService {

	private static final Logger logger = LogManager.getLogger(FileStorageDBServiceImpl.class);
	@Autowired
	private FileRepo fileRepo;

	public String storeFile(MultipartFile file, String description, String classification) {
		logger.debug("Entering FileStorageDBServiceImpl storeFile method, with MultipartFile = " + file.getName() + " , "
				+ "description = " + description + " , " + "classification = " + classification);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			ContentManagement dbFile = new ContentManagement(fileName, file.getContentType(), file.getBytes(),
					description, classification);
			fileRepo.save(dbFile);
			return "Success";
		} catch (IOException ex) {
			logger.error("Inside FileStorageDBServiceImpl storeFile method: {}", ex.getMessage());
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

}
