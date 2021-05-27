package com.at.globalclasses;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAUtil {

 	private static String PUBLIC_KEY= "";
	private static String PRIVATE_KEY= "";

	public static PublicKey getPublicKey(String base64PublicKey) {
		PublicKey publicKey = null;
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	public static PrivateKey getPrivateKey(String base64PrivateKey) {
		PrivateKey privateKey = null;
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		try {
			privateKey = keyFactory.generatePrivate(keySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, 
	    InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
		return cipher.doFinal(data.getBytes());
	}

	public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, 
	    InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return new String(cipher.doFinal(data));
	}

	public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, 
	    BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
	}

	public static String encryptString(String stringToEncrypt) throws Exception{
		PUBLIC_KEY = QAUtils.getFromProperties("config.properties","PUBLIC_KEY");
	    String encryptedString = Base64.getEncoder().encodeToString(encrypt(stringToEncrypt,PUBLIC_KEY));
		return encryptedString;
	}

	public static void main(String[] args) throws Exception {
		PUBLIC_KEY = QAUtils.getFromProperties("config.properties","PUBLIC_KEY");
		PRIVATE_KEY = QAUtils.getFromProperties("config.properties","PRIVATE_KEY");
		try {
			String originalString = "4Gil3th0ught";
			System.out.println("originalString: " + originalString);
			
			String encryptedString = Base64.getEncoder().encodeToString(encrypt(originalString,PUBLIC_KEY));
			System.out.println("EncryptedString: " + encryptedString);
			
			String decryptedString = RSAUtil.decrypt(encryptedString, PRIVATE_KEY);
			System.out.println("DecryptedString: " + decryptedString);
			
			System.out.println("\n==========================================\n");
			
			// ORIGNAL
			// String encryptedStringFromJS = "e92M/K8sWnlSfY1z/cg/XnBKxQ7IJ4jT0ClmA7tcVYKxS28pk5KQ0R8PwTkIQXis9vXFU6KT6Mmj2AEISObmUdYfkBOwPL2XNToRQszwz8mUHd09ckAkameiAUhLToftBxd3zH/4r1UXdtlBg/gTTBqhwqar9l0fprN5+LXH+sFWUS6GNXGqjGNQcDRr8bPfCCkAyyxUMLw4wV+Evtjixrxj0mQNLoFSEr15W3bk7/lBxgcY6KICoT8hgutiJOpTWO5YuRLUyKMeouTwEvHAC9RXuy12fmBxhL5umuLujAbNLKZmDosen8AxDPIhZqqX/vsQJmUx6vKbIP4F1TuWVw==";
			// FE Team: GoF
			String encryptedStringFromJS = "NcG3MZdSTl9GSmbv9A4CqyvJS+En8bo4ixd/GNu+Ig+1fTy+1KwRUTO6Zmz+eDwXPYwQbjQs52i+U/iYaeKl7CxpDYkcX1VMyRz7rMIJQp7hZcuURHW3p/PfHym3eokeM8GoQBA33TcwK45+dc6oC99kLPXsk6ZLZNgb+rs7clD3JQb+P2cKBNlhmnzrYZq/UqnhXEjIk9yFbZrzd+WFwL1BhzwcsCkvUktLp6TofozmZR4xEtjd5uqtKIBsYi+eKls+7njqmYuVXEambiwnNNhzynIJia9XpeBJVRUs82pYTOH9WbBtivcNbMKYHx+M92+O7vAgWWJV9ibdScsx6g==";
			System.out.println("encryptedString From JS: " + encryptedStringFromJS);
			
			String decryptedStringFromJS = RSAUtil.decrypt(encryptedStringFromJS, PRIVATE_KEY);
			System.out.println("DecryptedString From JS: " + decryptedStringFromJS);
			
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			System.err.println(e.getMessage());
		}
	}
}
