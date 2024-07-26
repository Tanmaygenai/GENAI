package com.exavalu.agentportal.service;

import com.exavalu.agentportal.model.ContentManagement;

import java.util.List;

public interface CabinetService {
    List<String> getCabinet(String username);
    List<ContentManagement> getFileDetails(String username, String classification);
    ContentManagement getFile(String fileId);
	List<ContentManagement> getAllFileDetails(String username);
}
