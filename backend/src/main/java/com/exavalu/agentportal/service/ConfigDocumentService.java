package com.exavalu.agentportal.service;


import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ConfigDocumentService {
	String getConfigDocument();
	String uploadConfigDocument(byte[] configFileBytes);
	ResponseEntity<Resource> downloadConfigDocument();
}
