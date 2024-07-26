package com.exavalu.agentportal.util;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.exavalu.agentportal.config.APIHeaders;
import com.exavalu.agentportal.controller.CabinetController;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Configuration
public class CryptoUtil {

	private static final Logger logger = LogManager.getLogger(CryptoUtil.class);
	@Value("${security_cryptoKey}")
	String cryptoKey;
	
	String secretKeyValue = System.getProperty("secretKeys");
	
	private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String ALGORITHM = "AES";
	
	public void prepareSecreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String encryptResult(String strToEncrypt) {
        try {
            prepareSecreteKey(secretKeyValue);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public String decryptResult(String strToDecrypt) {
        try {
            prepareSecreteKey(secretKeyValue);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

	public String decryptData(String cipherText) {
		logger.debug("Entering CryptoUtil decryptData method, with cipherText = " + cipherText);
		String decryptedText = null;
		JSONObject jsonResponse = null;

		try {
			if (cipherText != null && !cipherText.isEmpty()) {
				cipherText = URLDecoder.decode(cipherText, "UTF-8");
				cipherText = cipherText.replace(" ", "+");
				byte[] cipherData = Base64.getDecoder().decode(cipherText);
				byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);
				String algorithm = "MD5";
				MessageDigest md5 = null;

				md5 = MessageDigest.getInstance(algorithm);

				final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData,
						cryptoKey.getBytes(StandardCharsets.UTF_8), md5);
				SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
				IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

				byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
				Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
				aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
				byte[] decryptedData = aesCBC.doFinal(encrypted);
				decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("Inside CryptoUtil decryptData method: {}", e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			logger.error("Inside CryptoUtil decryptData method: {}", e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error("Inside CryptoUtil decryptData method: {}", e.getMessage());
		} catch (IllegalBlockSizeException e) {
			logger.error("Inside CryptoUtil decryptData method: {}", e.getMessage());
		} catch (BadPaddingException e) {
			logger.error("Inside CryptoUtil decryptData method: {}", e.getMessage());
		} catch (InvalidKeyException e) {
			logger.error("Inside CryptoUtil decryptData method: {}", e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("Inside CryptoUtil decryptData method: {}", e.getMessage());
		}
		return decryptedText;
	}

	/**
	 * Generates a key and an initialization vector (IV) with the given salt and
	 * password.
	 * <p>
	 * This method is equivalent to OpenSSL's EVP_BytesToKey function (see
	 * https://github.com/openssl/openssl/blob/master/crypto/evp/evp_key.c). By
	 * default, OpenSSL uses a single iteration, MD5 as the algorithm and UTF-8
	 * encoded password data.
	 * </p>
	 * 
	 * @param keyLength  the length of the generated key (in bytes)
	 * @param ivLength   the length of the generated IV (in bytes)
	 * @param iterations the number of digestion rounds
	 * @param salt       the salt data (8 bytes of data or <code>null</code>)
	 * @param password   the password data (optional)
	 * @param md         the message digest algorithm to use
	 * @return an two-element array with the generated key and IV
	 */
	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password,
			MessageDigest md) {
		logger.debug("Entering CryptoUtil GenerateKeyAndIV method, with keyLength = " + keyLength + " , "
				+ "ivLength = " + ivLength + " , " + "iterations = " + iterations + " , " + "salt = " + salt + " , "
				+ "password = " + password + " , " + "MessageDigest = " + md.toString());
		int digestLength = md.getDigestLength();
		int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
		byte[] generatedData = new byte[requiredLength];
		int generatedLength = 0;

		try {
			md.reset();

			// Repeat process until sufficient data has been generated
			while (generatedLength < keyLength + ivLength) {

				// Digest data (last digest if available, password data, salt if available)
				if (generatedLength > 0)
					md.update(generatedData, generatedLength - digestLength, digestLength);
				md.update(password);
				if (salt != null)
					md.update(salt, 0, 8);
				md.digest(generatedData, generatedLength, digestLength);

				// additional rounds
				for (int i = 1; i < iterations; i++) {
					md.update(generatedData, generatedLength, digestLength);
					md.digest(generatedData, generatedLength, digestLength);
				}

				generatedLength += digestLength;
			}

			// Copy key and IV into separate byte arrays
			byte[][] result = new byte[2][];
			result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
			if (ivLength > 0)
				result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

			return result;

		} catch (DigestException e) {
			logger.error("Inside CryptoUtil GenerateKeyAndIV method: {}", e.getMessage());
			throw new IllegalArgumentException(e);

		} finally {
			// Clean out temporary data
			Arrays.fill(generatedData, (byte) 0);
		}
	}
}
