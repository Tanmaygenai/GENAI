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
public class AppPropDBServiceImpl implements AppPropService{
	private static final Logger logger = LogManager.getLogger(AppPropDBServiceImpl.class);
	@Autowired
	private AppConfigRepo repo;
	
	public String saveCarrierMode(String carrierMode, String key) {
		logger.info("Entering AppPropDBServiceImpl saveCarrierMode method");		
		try {
			AppConfig carrierModeField =repo.findByKeytype(key);
			if(carrierModeField==null) {
				AppConfig newcarrierMode = new AppConfig();
				newcarrierMode.setKeytype(key);
				newcarrierMode.setPropCarrierMode(carrierMode);
				repo.save(newcarrierMode);
			}else {
				carrierModeField.setPropCarrierMode(carrierMode);
				//String s = carrierModeField.getPropCarrierMode();
				repo.save(carrierModeField);
			}
			return "Success";
		}catch(Exception ex) {
			logger.error("Inside AppConfigDBServiceImpl saveCarrierMode method: {}", ex.getMessage());
			throw new FileStorageException("Could not store file Please try again!", ex);
		}
	}
}
