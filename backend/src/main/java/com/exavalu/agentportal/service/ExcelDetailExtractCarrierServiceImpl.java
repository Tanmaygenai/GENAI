package com.exavalu.agentportal.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.error.FileStorageException;

@Service
@Qualifier("carrier")
public class ExcelDetailExtractCarrierServiceImpl implements ExcelDetailExtractService {
	
	static Logger logger = LogManager.getLogger(ExcelDetailExtractCarrierServiceImpl.class);

	@Override
	public List uploadExcel(MultipartFile[] files) {
		// TODO Auto-generated method stub
		return null;
	}
}
