package io.nem.xpx.utils;

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

	/**
	 * Encrypt.
	 *
	 * @param binary the binary
	 * @param password the password
	 * @return the byte[]
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 */
	public static byte[] encrypt(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();
		return basicBinaryEncryptor.encrypt(binary, password);
	}

	/**
	 * Encrypt to base 64 string.
	 *
	 * @param binary the binary
	 * @param password the password
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 */
	public static String encryptToBase64String(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();
		return basicBinaryEncryptor.encryptToBase64String(binary, password);
	}

	/**
	 * Decrypt.
	 *
	 * @param binary the binary
	 * @param password the password
	 * @return the byte[]
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public static byte[] decrypt(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();

		return basicBinaryEncryptor.decrypt(binary, password);
	}

	/**
	 * Decrypt to base 64 string.
	 *
	 * @param binary the binary
	 * @param password the password
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public static String decryptToBase64String(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();

		return basicBinaryEncryptor.decryptToBase64String(binary, password);
	}

	/**
	 * Decrypt to base 64 string.
	 *
	 * @param cipherEncryptedText the cipher encrypted text
	 * @param password the password
	 * @return the string
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public static String decryptToBase64String(String cipherEncryptedText, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		BinaryPBKDF2Cipher basicBinaryEncryptor = new BinaryPBKDF2Cipher();

		return basicBinaryEncryptor.decryptToBase64String(cipherEncryptedText, password);
	}

}
