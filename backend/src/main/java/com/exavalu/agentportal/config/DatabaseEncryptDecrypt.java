package com.exavalu.agentportal.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class DatabaseEncryptDecrypt {

	@Value("${key-db-encrypt}")
	private String key;
	@Value("${initVector}")
	private String initVector;
	@Value("${algo}")
	private String algo;

	private static final Logger logger = LogManager.getLogger(DatabaseEncryptDecrypt.class);

	public String encrypt(String value) {
		try {
			logger.debug("Entering DatabaseEncryptDecrypt encrypt method");
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(algo);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

			byte[] encrypted = cipher.doFinal(value.getBytes());
			logger.debug("Exiting DatabaseEncryptDecrypt encrypt method");
			return Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex) {
			logger.error("Inside DatabaseEncryptDecrypt encrypt method: {}", ex.getMessage());
		}
		return null;
	}

	public String decrypt(String encrypted) {
		try {
			logger.debug("Entering DatabaseEncryptDecrypt decrypt method");
			IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(algo);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted.getBytes()));
			logger.debug("Exiting DatabaseEncryptDecrypt decrypt method");
			return new String(original);
		} catch (Exception ex) {
			logger.error("Inside DatabaseEncryptDecrypt decrypt method: {}", ex.getMessage());
		}
		return null;
	}
}
