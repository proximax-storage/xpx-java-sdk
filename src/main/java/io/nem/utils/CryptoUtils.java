package io.nem.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * The Class CryptoUtil.
 */
public class CryptoUtils {

	public static byte[] encrypt(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();
		return basicBinaryEncryptor.encrypt(binary, password);
	}

	public static String encryptToBase64String(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();
		return basicBinaryEncryptor.encryptToBase64String(binary, password);
	}

	public static byte[] decrypt(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();

		return basicBinaryEncryptor.decrypt(binary, password);
	}

	public static String decryptToBase64String(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();

		return basicBinaryEncryptor.decryptToBase64String(binary, password);
	}

	public static String decryptToBase64String(String cipherEncryptedText, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();

		return basicBinaryEncryptor.decryptToBase64String(cipherEncryptedText, password);
	}

}
