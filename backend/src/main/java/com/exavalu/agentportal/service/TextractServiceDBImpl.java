package com.exavalu.agentportal.service;

import com.exavalu.agentportal.error.FileStorageException;
import com.exavalu.agentportal.model.DocumentContainer;
import com.exavalu.agentportal.repository.PolicyFileRepo;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.SystemPropertyCredentialsProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

@Service
@Qualifier("db")
public class TextractServiceDBImpl implements TextractService {
	static Logger logger = LogManager.getLogger(TextractServiceDBImpl.class);
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();
	@Value("${awsRegion}")
	String awsRegion;
	@Value("${awsAccessKeyId}")
	String awsAccessKeyId;
	@Value("${awsSecretAccessKey}")
	String awsSecretAccessKey;
	@Value("${destinationDir}")
	String destinationDir;
	@Value("${acordSheetPath}")
	String acordSheetPath;
    @Autowired
    
    public static Map<String, String> getRelationships(Map<String, Block> blockMap, Map<String, Block> keyMap,Map<String, Block> valueMap) {
        Map<String, String> result = new LinkedHashMap<>();
        for(Map.Entry<String, Block> itr : keyMap.entrySet()) {
            Block valueBlock = findValue(itr.getValue(), valueMap);
            String key = getText(itr.getValue(), blockMap);
            String value = getText(valueBlock, blockMap);
            result.put(key, value);
        }

        return result;
    }

    public static Block findValue(Block keyBlock, Map<String, Block> valueMap) {
        Block b = null;
        for(Relationship relationship : keyBlock.relationships()) {
            if(relationship.type().toString().equals("VALUE")) {
                for(String id : relationship.ids()) {
                    b = valueMap.get(id);
                }
            }
        }
        return b;
    }

    public static String getText(Block result, Map<String, Block> blockMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Relationship relationship : result.relationships()) {
            if(relationship.type().toString().equals("CHILD")) {
                for(String id : relationship.ids()) {
                    Block b = blockMap.get(id);
                    if(b.blockTypeAsString().equals("WORD")) {
                        stringBuilder.append(b.text()).append(" ");
                    }
                 }
            }
        }
        return stringBuilder.toString();
    }

    public List uploadPdf(MultipartFile[] files) {
    	logger.debug("Entering FileUploadServiceDBImpl storePolicyFile method, with MultipartFile = " + files.toString());
       
        String fileName = null;
        Map[] text=new Map[0];
        List fields=new ArrayList();
       
            
        try {
            for(MultipartFile file:files){
            	logger.info("NAME: {}", file.getOriginalFilename());
                fileName  = StringUtils.cleanPath(file.getOriginalFilename());
                 byte[] multipartFile = file.getBytes();
                 File file1 =  File.createTempFile(destinationDir+fileName,"");
                 try (OutputStream os = new FileOutputStream(file1)) {
                     os.write(multipartFile);
                 }
                 
                 PDDocument document = PDDocument.load(file1);
                 PDFRenderer pdfRenderer = new PDFRenderer(document);
                 text=new Map[document.getNumberOfPages()];
                 for (int page = 0; page < document.getNumberOfPages(); ++page) { 
                	 long startTime = System.currentTimeMillis();
                	 File outPutFile =  File.createTempFile(destinationDir + fileName +"_"+ (page+1) ,".png");
                     BufferedImage bim = pdfRenderer.renderImageWithDPI(
                       page, 600, ImageType.RGB);
                     ImageIO.write(bim, "png", outPutFile);
                     File convFile = new File( file.getOriginalFilename() );
                     FileOutputStream fos = new FileOutputStream( convFile );
                     fos.write( file.getBytes() );
                     fos.close();
                     
                     InputStream inputStream = new FileInputStream(outPutFile);
                     SdkBytes imageBytes; 

                     
                     imageBytes = SdkBytes.fromInputStream(inputStream);
                       Document doc = Document.builder().bytes(imageBytes).build();
                       List<FeatureType> list = new ArrayList<>();
                       list.add(FeatureType.FORMS);
                       String textractSecretAccessKeyValue = cryptoUtil.decryptResult(awsSecretAccessKey);
               		   String textractAccessKeyIdValue = cryptoUtil.decryptResult(awsAccessKeyId);
                       AwsBasicCredentials textractCreds = AwsBasicCredentials.create(textractAccessKeyIdValue, textractSecretAccessKeyValue);
                       TextractClient textractClient = TextractClient.builder().region(Region.of(awsRegion)).credentialsProvider(StaticCredentialsProvider.create(textractCreds)).build();
                       AnalyzeDocumentRequest request = AnalyzeDocumentRequest.builder().featureTypes(list).document(doc).build();
                       
                       AnalyzeDocumentResponse response = textractClient.analyzeDocument(request);
                       List<Block> blocks = response.blocks();

                       Map<String, Block> blockMap = new LinkedHashMap<>();
                       Map<String, Block> keyMap = new LinkedHashMap<>();
                       Map<String, Block> valueMap = new LinkedHashMap<>();
               	
                       for (Block b : blocks) {
                           String block_id = b.id();
                           blockMap.put(block_id, b);
                           if(b.blockTypeAsString().equals("KEY_VALUE_SET")) {
                               for(EntityType entityType : b.entityTypes()) {
                                   if(entityType.toString().equals("KEY")) {
                                       keyMap.put(block_id, b);
                                   } else {
                                       valueMap.put(block_id, b);
                                   }
                               }
                           }
                       } 
                           text[page]=getRelationships(blockMap, keyMap, valueMap);
                           textractClient.close();
                           outPutFile.delete();
                           file1.delete();
                           logger.info("Time taken in page {} - {}ms",page+1, (System.currentTimeMillis() - startTime));
                 }
                 
                 document.close();
                 for(int i=0;i<text.length;i++) {
              		for (Object obj : text[i].entrySet()) {            		 
              			 fields.add(obj);
           		}
              		}
      
            }
//            long startTime = System.currentTimeMillis();
//            File fileNew = new File(acordSheetPath);   
//            FileInputStream fis = new FileInputStream(fileNew);
//            XSSFWorkbook wb = new XSSFWorkbook(fis);   
//            XSSFSheet sheet = wb.getSheetAt(0);       
//            Iterator<Row> itr = sheet.iterator();
//            String word="";
//            while (itr.hasNext())                 
//            {  
//            Row row = itr.next();  
//            Iterator<Cell> cellIterator = row.cellIterator();   
//            while (cellIterator.hasNext())   
//            {  
//            Cell cell = cellIterator.next();  
//            switch (cell.getCellType())               
//            {  
//            case STRING:  
//            	word=cell.getStringCellValue() ;     	
//            	for(int i=0;i<text.length;i++) {
//            		for (Object obj : text[i].entrySet()) {
//            		 if(obj.toString().contains(word))  {
//            			 fields.add(obj);
//            		 }
//            		}
//            		}
//            break;    
//            default:  
//            }  
//            }  
//            logger.info("");  
//            }  
            
//            logger.info("Time taken to map values {}ms", (System.currentTimeMillis() - startTime));
            return fields;
 
        } catch (IOException ex) {
        	logger.error("Inside TextractServiceDBImpl uploadPdf method: {}", ex.getMessage());
            throw new FileStorageException("Could not upload file " + fileName + ". Please try again!", ex);
        }
    }
    

}




