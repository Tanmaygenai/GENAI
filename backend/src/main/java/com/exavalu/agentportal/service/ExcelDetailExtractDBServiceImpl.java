package com.exavalu.agentportal.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exavalu.agentportal.error.FileStorageException;

@Service
@Qualifier("db")
public class ExcelDetailExtractDBServiceImpl implements ExcelDetailExtractService {
	
	static Logger logger = LogManager.getLogger(ExcelDetailExtractDBServiceImpl.class);
	
	
	public List<Map<String, String>> uploadExcel(MultipartFile[] files) {
		
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    List<Map<String, String>> fieldsList = new ArrayList<>();

	    try {
	        for (MultipartFile file : files) {
	            logger.info("Processing file: {}", file.getOriginalFilename());
	            
	            Workbook workbook = new XSSFWorkbook(file.getInputStream());

	            Sheet sheet = workbook.getSheetAt(0);
	            Row headerRow = sheet.getRow(0);
	            int columnCount = headerRow.getPhysicalNumberOfCells();
	            String[] header = new String[columnCount];
	            for (int i = 0; i < columnCount; i++) {
	                header[i] = headerRow.getCell(i).getStringCellValue();
	            }
	            
	            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	                Row dataRow = sheet.getRow(rowIndex);

	                Map<String, String> fieldMap = new LinkedHashMap<>();

	                for (int i = 0; i < columnCount; i++) {
	                    Cell cell = dataRow.getCell(i);
	                    if (cell != null) {
	                        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
	                           Date date = cell.getDateCellValue();
	                           String formattedDate = outputDateFormat.format(date);
	                           fieldMap.put(header[i], formattedDate);
	                        } else {
	                            fieldMap.put(header[i], cell.toString());
	                        }
	                    }
	                }

	                fieldsList.add(fieldMap);
	            }
	            workbook.close();
	        }
	        System.out.println(fieldsList);
	        return fieldsList;
	    } catch (IOException ex) {
	        logger.error("Error during Excel file processing: {}", ex.getMessage());
	        throw new FileStorageException("Could not process Excel file. Please try again!", ex);
	    }
	}
}

