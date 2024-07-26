package com.exavalu.agentportal.service;

import org.springframework.web.multipart.MultipartFile;

public interface CabinateFileStorageService {
    public String storeFile(MultipartFile file, String description, String classification);
}

