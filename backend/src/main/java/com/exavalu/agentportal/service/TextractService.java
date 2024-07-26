package com.exavalu.agentportal.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface TextractService {

	List uploadPdf(MultipartFile[] files);
}
