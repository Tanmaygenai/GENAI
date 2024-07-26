package com.exavalu.agentportal.service;

import java.io.*;
import java.nio.file.*;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Service
public class ConfigDocumentServiceImpl implements ConfigDocumentService {
	private static final Logger logger = LogManager.getLogger(ConfigDocumentServiceImpl.class);

	@Value("${awsS3BucketName}")
	String awsS3BucketName;

	@Value("${awsRegion}")
	String awsRegion;

	@Value("${bucketKey}")
	String bucketKey;

	@Value("${awsAccessKeyId}")
	String awsAccessKeyId;

	@Value("${awsSecretAccessKey}")
	String awsSecretAccessKey;
	
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	public String uploadConfigDocument(byte[] configFileBytes) {
		logger.debug("Entering ConfigDocumentServiceImpl uploadConfigDocument method, with configFileBytes = "
				+ configFileBytes.toString());
		String message = "";
		try {
	        File tempFile = File.createTempFile("temp", ".xlsx");
	        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
	        fileOutputStream.write(configFileBytes);
	        fileOutputStream.close();

	        // Convert the sheets to CSV and save them in the given directory
	        String outputDirectory = "../frontend/src/dummydata";
	        message = checkValidFile(tempFile.getAbsolutePath(), outputDirectory);
	        System.out.print("message>>>>>"+message);
	        if(message == "Valid File") {
	        	try {
	        		String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
	        		String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
	    			AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
	    			S3Client s3Client = S3Client.builder().region(Region.of(awsRegion))
	    					.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

	    			PutObjectRequest request = PutObjectRequest.builder().bucket(awsS3BucketName).key(bucketKey).build();

	    			s3Client.putObject(request, RequestBody.fromBytes(configFileBytes));
	    			
	    			message = "Object uploaded successfully.";
	    		} catch (AmazonServiceException e) {
	    			logger.error("Inside ConfigDocumentServiceImpl uploadConfigDocument method: {}", e.getMessage());
	    		} catch (SdkClientException e) {
	    			logger.error("Inside ConfigDocumentServiceImpl uploadConfigDocument method: {}", e.getMessage());
	    		}
	        }
	        // Delete the temporary file
	        tempFile.delete();
	    } catch (IOException e) {
	        message = "\"Error in getConfigDocument(): {}\", e.getMessage()";
	        logger.error("Error in getConfigDocument(): {}", e.getMessage());
	    }
		
		return message;
	}
	
	public String checkValidFile(String filePath, String outputDirectory) {
		String message = "";
		int c = 0;
        try (Workbook workbook = new XSSFWorkbook(filePath)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            	Sheet sheet = workbook.getSheetAt(sheetIndex);
            	if(!sheet.getSheetName().equals("commercialAuto") && !sheet.getSheetName().equals("commercialProperty") 
            		    && !sheet.getSheetName().equals("miscellaneous") && !sheet.getSheetName().equals("excessLiability")
            		    && !sheet.getSheetName().equals("admin") && !sheet.getSheetName().equals("lossNotice")) {
	                 c = c + 1;
	                 break;
            	}
            }
            if(c == 0) {
        		message = "Valid File";
        	} else {
        		message = "Config document is not valid, please use the sample format!";
        	}
        } catch (IOException e) {
            e.printStackTrace();
            message = "Some error!";
        }
        return message;
    }

	
	public String convertSheetsToCsv(String filePath, String outputDirectory) {
		String message = "";
		int c = 0;
        try (Workbook workbook = new XSSFWorkbook(filePath)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            	Sheet sheet = workbook.getSheetAt(sheetIndex);
            	if(sheet.getSheetName().equals("commercialAuto") || sheet.getSheetName().equals("commercialProperty") 
            		    || sheet.getSheetName().equals("miscellaneous") || sheet.getSheetName().equals("excessLiability")
            		    || sheet.getSheetName().equals("admin") || sheet.getSheetName().equals("lossNotice")) {
	                String csvFileName = outputDirectory + "/" + sheet.getSheetName() + ".csv";
	                convertSheetToCsv(sheet, csvFileName);         
            	} else {
            		 c = c+1;
            	}
            }
            if(c == 0) {
        		message = "Files created and saved successfully";
        	} else {
        		message = "Config document is not valid, please use the sample format!";
        	}
        } catch (IOException e) {
            e.printStackTrace();
            message = "Some error!";
        }
        return message;
    }

    private void convertSheetToCsv(Sheet sheet, String csvFileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(csvFileName);
             FileWriter fileWriter = new FileWriter(csvFileName);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {

        	for (Row row : sheet) {
                StringBuilder rowData = new StringBuilder();
                for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
                    Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String cellValue = "";
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        cellValue = cell.getStringCellValue();
                        if (cellValue.contains(",")) {
                            // Wrap the cell value in double quotes
                           cellValue = "\"" + cellValue.toString() + "\"";
                      }
                    }
                    rowData.append(cellValue);
                    if (cellIndex < row.getLastCellNum() - 1) {
                        rowData.append(",");
                    }
                }
                fileWriter.write(rowData.toString());
                fileWriter.write(System.lineSeparator());
            }
        }
    }


	public String getConfigDocument() {
		String message = "";
		try {
			String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
			String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
			AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
			S3Client s3Client = S3Client.builder().region(Region.of(awsRegion))
					.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

			GetObjectRequest request = GetObjectRequest.builder().key(bucketKey).bucket(awsS3BucketName).build();

			ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(request);
			byte[] data = objectBytes.asByteArray();

			try {
		        File tempFile = File.createTempFile("temp", ".xlsx");
		        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
		        fileOutputStream.write(data);
		        fileOutputStream.close();

		        // Convert the sheets to CSV and save them in the given directory
		        String outputDirectory = "../frontend/src/dummydata";
		        message = convertSheetsToCsv(tempFile.getAbsolutePath(), outputDirectory);

		        // Delete the temporary file
		        tempFile.delete();
		    } catch (IOException e) {
		        message = "\"Error in getConfigDocument(): {}\", e.getMessage()";
		        logger.error("Error in getConfigDocument(): {}", e.getMessage());
		    }

		} catch (AmazonServiceException e) {
			message = "\"Inside ConfigDocumentServiceImpl getConfigDocument method: {}\", e.getMessage()";
			logger.error("Inside ConfigDocumentServiceImpl getConfigDocument method: {}", e.getMessage());
		} catch (SdkClientException e) {
			message = "\"Inside ConfigDocumentServiceImpl getConfigDocument method: {}\", e.getMessage()";
			logger.error("Inside ConfigDocumentServiceImpl getConfigDocument method: {}", e.getMessage());
		}

		return message;

	}
	
	public ResponseEntity<Resource> downloadConfigDocument() {
        try {
        	String awsSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
        	String awsAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
        	AwsBasicCredentials awsCreds = AwsBasicCredentials.create(awsAccessKeyIdValue, awsSecretAccessKeyValue);
			S3Client s3Client = S3Client.builder().region(Region.of(awsRegion))
					.credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

			GetObjectRequest request = GetObjectRequest.builder().key(bucketKey).bucket(awsS3BucketName).build();

			ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(request);
			byte[] data = objectBytes.asByteArray();

			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            InputStreamResource resource = new InputStreamResource(inputStream);

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "/attachment; filename=\"" + bucketKey + "\"")
                    .body(resource);
        } catch (SdkClientException e) {
            // Handle exceptions and return an appropriate response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
}
