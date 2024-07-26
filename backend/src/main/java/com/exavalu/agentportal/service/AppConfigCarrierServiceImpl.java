package com.exavalu.agentportal.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.model.AppConfig;
@Service
@Qualifier("carrier")
public class AppConfigCarrierServiceImpl implements AppConfigService{

	@Override
	public String storeFile(MultipartFile file, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppConfig getObject(String keytype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String storeTheme(String value, String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
