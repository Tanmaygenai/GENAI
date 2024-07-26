package com.exavalu.agentportal.controller;

import com.exavalu.agentportal.model.ContentManagement;
import com.exavalu.agentportal.service.CabinetService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}")
public class CabinetController {
	private static final Logger logger = LogManager.getLogger(CabinetController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private CabinetService cabinetDBService;

	@Autowired
	@Qualifier("carrier")
	private CabinetService cabinetCarrierService;

	public CabinetController() {
		/*
		 * 
		 */
	}

	@GetMapping("${securedString}" + "/cabinet")
	public ResponseEntity<List<String>> getCabinetDetails(@RequestHeader(value = "username") String username) {
		logger.info("Entering CabinetController getCabinetDetails method");
		List<String> details = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			details = cabinetDBService.getCabinet(username);
			if (details == null) {
				logger.info("Exiting CabinetController getCabinetDetails method");
				return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting CabinetController getCabinetDetails method");
				return new ResponseEntity<List<String>>(details, HttpStatus.OK);
			}
		} else {
			details = cabinetCarrierService.getCabinet(username);
			if (details == null) {
				logger.info("Exiting CabinetController getCabinetDetails method");
				return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting CabinetController getCabinetDetails method");
				return new ResponseEntity<List<String>>(details, HttpStatus.OK);
			}
		}

	}

	@GetMapping("${securedString}" + "/cabinateFileDetails/{classification}")
	public ResponseEntity<List<ContentManagement>> getCabinetFileDetails(
			@RequestHeader(value = "username") String username, @PathVariable String classification) {
		logger.info("Entering CabinetController getCabinetFileDetails method");

		List<ContentManagement> details = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			details = cabinetDBService.getFileDetails(username, classification);

			if (details == null) {
				logger.info("Exiting CabinetController getCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting CabinetController getCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(details, HttpStatus.OK);
			}
		} else {
			details = cabinetCarrierService.getFileDetails(username, classification);
			if (details == null) {
				logger.info("Exiting CabinetController getCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting CabinetController getCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(details, HttpStatus.OK);
			}

		}
	}
	
	@GetMapping("${securedString}" + "/allCabinateFileDetails")
	public ResponseEntity<List<ContentManagement>> getAllCabinetFileDetails(
			@RequestHeader(value = "username") String username) {
		logger.info("Entering CabinetController getAllCabinetFileDetails method");

		List<ContentManagement> details = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			details = cabinetDBService.getAllFileDetails(username);

			if (details == null) {
				logger.info("Exiting CabinetController getAllCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting CabinetController getAllCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(details, HttpStatus.OK);
			}
		} else {
			details = cabinetCarrierService.getAllFileDetails(username);
			if (details == null) {
				logger.info("Exiting CabinetController getAllCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(HttpStatus.NOT_FOUND);
			} else {
				logger.info("Exiting CabinetController getAllCabinetFileDetails method");
				return new ResponseEntity<List<ContentManagement>>(details, HttpStatus.OK);
			}

		}
	}


	@GetMapping("${securedString}" + "/filedownload/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		logger.info("Entering CabinetController downloadFile method");
		ContentManagement dbFile = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			dbFile = cabinetDBService.getFile(fileId);
		} else {
			dbFile = cabinetCarrierService.getFile(fileId);
		}
		logger.info("Exiting CabinetController downloadFile method");
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "/attachment; filename=\"" + dbFile.getName() + "\"")
				.body(new ByteArrayResource(dbFile.getFilecontent()));
	}

}
