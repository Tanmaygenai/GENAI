package com.exavalu.agentportal.service;

import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.model.AppConfig;

public interface AppConfigService {
	public String storeFile(MultipartFile file, String key);
	AppConfig getObject(String keytype);
	public String storeTheme(String value, String key);
}
