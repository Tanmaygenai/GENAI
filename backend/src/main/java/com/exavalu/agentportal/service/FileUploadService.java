package com.exavalu.agentportal.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
  
    String storePolicyFile(MultipartFile[] files,String policyNumber,String transactionIndicator,String documentType,String status);
}

