package com.exavalu.agentportal.service;

import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.model.AppConfig;

public interface AppPropService {
	public String saveCarrierMode(String carrierMode, String key);
}
