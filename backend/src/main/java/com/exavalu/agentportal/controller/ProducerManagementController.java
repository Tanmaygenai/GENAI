package com.exavalu.agentportal.controller;

import java.util.List;
import com.exavalu.agentportal.util.CryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.exavalu.agentportal.model.ProducerManagement;
import com.exavalu.agentportal.model.db.ContactUsDetails;
import com.exavalu.agentportal.service.ProducerManagementService;

@RestController
@RequestMapping("${apiPrefix}")
public class ProducerManagementController {
	private static final Logger logger = LogManager.getLogger(ProducerManagementController.class);

	@Value("${portalMode}")
	String portalMode;

	@Autowired
	@Qualifier("db")
	private ProducerManagementService producerService;
	@Autowired
	private CryptoUtil cryptoUtil;
	Gson gson = new GsonBuilder().create();

	@Autowired
	@Qualifier("carrier")
	private ProducerManagementService producerCarrierService;

	@GetMapping("${securedString}" + "/getProducerInfo")
	public List<ProducerManagement> getProducerInfo(
			@RequestParam(value = "producerType", required = false) String producerType,
			@RequestParam(value = "producerCode", required = false) String producerCode, HttpServletRequest request,
			HttpSession session) {
		logger.info("Entering ProducerManagementController getProducerInfo method");
		List<ProducerManagement> producerInfo = null;
		if (portalMode.equalsIgnoreCase("DB")) {
			producerInfo = producerService.getProducerInfo(producerType, producerCode);
		} else {
			producerInfo = producerCarrierService.getProducerInfo(producerType, producerCode);
		}
		logger.info("Exiting ProducerManagementController getProducerInfo method");
		return producerInfo;
	}

	@PostMapping("${securedString}" + "/createProducer")
	public String createNewProducer(@RequestBody String encryptedProducerEncrypted, HttpServletRequest request,
			HttpSession session) {
		logger.info("Entering ProducerManagementController createNewProducer method");
		String producerInfo = null;
		ProducerManagement newProducer = null;
		String decryptedProducerDetails = cryptoUtil.decryptData(encryptedProducerEncrypted);
		newProducer = gson.fromJson(decryptedProducerDetails, ProducerManagement.class);
		if (portalMode.equalsIgnoreCase("DB")) {
			producerInfo = producerService.createNewProducer(newProducer);
		} else {
			producerInfo = producerCarrierService.createNewProducer(newProducer);
		}
		logger.info("Exiting ProducerManagementController createNewProducer method");
		return producerInfo;
	}
}
