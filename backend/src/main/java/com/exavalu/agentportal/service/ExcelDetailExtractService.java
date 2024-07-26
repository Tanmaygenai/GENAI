package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelDetailExtractService {

	List uploadExcel(MultipartFile[] files);

}
