package com.exavalu.agentportal.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface ApprovalDocumentService {
	String getApprovalConfigDocument();
	String uploadApprovalConfigDocument(byte[] configFileBytes);
	ResponseEntity<Resource> downloadApprovalConfigDocument();
}
