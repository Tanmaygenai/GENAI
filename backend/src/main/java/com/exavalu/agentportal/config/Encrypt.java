package com.exavalu.agentportal.config;

import javax.persistence.AttributeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Encrypt implements AttributeConverter<String, String> {
	@Autowired
	DatabaseEncryptDecrypt encryptionUtil;
	private static final Logger logger = LogManager.getLogger(Encrypt.class);

	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			return encryptionUtil.encrypt(attribute);
		} catch (Exception e) {
			logger.error("Inside Encrypt convertToDatabaseColumn method: {}", e.getMessage());
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			return encryptionUtil.decrypt(dbData);
		} catch (Exception e) {
			logger.error("Inside Encrypt convertToEntityAttribute method: {}", e.getMessage());
			throw new IllegalStateException(e);
		}
	}
}
