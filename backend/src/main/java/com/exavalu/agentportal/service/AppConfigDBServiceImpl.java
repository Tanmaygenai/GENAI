package com.exavalu.agentportal.service;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.error.FileNotFoundException;
import com.exavalu.agentportal.error.FileStorageException;
import com.exavalu.agentportal.model.AppConfig;
import com.exavalu.agentportal.model.ContentManagement;
import com.exavalu.agentportal.repository.AppConfigRepo;
@Service
@Qualifier("db")
public class AppConfigDBServiceImpl implements AppConfigService{
	private static final Logger logger = LogManager.getLogger(AppConfigDBServiceImpl.class);
	@Autowired
	private AppConfigRepo repo;
	
	public String storeFile(MultipartFile file, String key) {
		logger.info("Entering AppConfigDBServiceImpl storeFile method, with MultipartFile = " + file.getName());
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {

			AppConfig dbFile=repo.findByKeytype(key);
			if(dbFile==null) {
				AppConfig newFile = new AppConfig();
				newFile.setKeytype(key);
				newFile.setFilecontent(file.getBytes());
				repo.save(newFile);
			}else {
				dbFile.setFilecontent(file.getBytes());
				repo.save(dbFile);
			}
			
			
			return "Success";
		} catch (IOException ex) {
			logger.error("Inside AppConfigDBServiceImpl storeFile method: {}", ex.getMessage());
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
		
	}

	
	public AppConfig getObject(String keytype) {
		logger.info("Entering AppConfigDBServiceImpl getObject method");
		try {
			if(repo.findByKeytype(keytype)!=null) {
				return repo.findByKeytype(keytype);
			}
			else {
				return null;
			}
		}catch(Exception ex) {
			logger.error("Inside AppConfigDBServiceImpl getObject method: {}", ex.getMessage());
			throw new FileStorageException("Could not find object with key type: " + keytype + ". Please try again!", ex);
		}
										
	}


	@Override
	public String storeTheme(String value, String key) {
		
		logger.info("Entering AppConfigDBServiceImpl storeTheme method");

		try {

			AppConfig customTheme=repo.findByKeytype(key);
			if(customTheme==null) {
				AppConfig newTheme = new AppConfig();
				newTheme.setKeytype(key);
				newTheme.setFilecontent(value.getBytes());
				repo.save(newTheme);
			}else {
				customTheme.setFilecontent(value.getBytes());
				repo.save(customTheme);
			}
						
			return "Success";
		} catch (Exception ex) {
			logger.error("Inside AppConfigDBServiceImpl storeTheme method: {}", ex.getMessage());
			throw new FileStorageException("Could not store theme "  + ". Please try again!", ex);
		}
		
	}

}
