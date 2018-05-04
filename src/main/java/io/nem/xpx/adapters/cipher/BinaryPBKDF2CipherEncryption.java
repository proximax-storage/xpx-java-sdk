package io.nem.xpx.adapters.cipher;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;




/**
 * The Class BinaryPBKDF2Cipher.
 */
public class BinaryPBKDF2CipherEncryption extends SymmetricKeyEncryption  {

	/** The Constant CONST_ALGO_PBKDF2. */
	private static final String CONST_ALGO_PBKDF2 = "PBKDF2WithHmacSHA256";

	/** The Constant SALT. */
	private static final byte[] SALT = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35,
			(byte) 0xE3, (byte) 0x03 };

	/** The Constant FIXED_NONCE. */
	private static final byte[] FIXED_NONCE = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56,
			(byte) 0x35, (byte) 0xE3, (byte) 0x03 };

	
	/**
	 * Encrypt.
	 *
	 * @param binary the binary
	 * @param password the password
	 * @return the byte[]
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 */
	public byte[] encrypt(byte[] binary, char[] password)
			throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		// DERIVE key (from password and salt)
		SecretKeyFactory factory = SecretKeyFactory.getInstance(CONST_ALGO_PBKDF2);
		KeySpec keyspec = new PBEKeySpec(password, SALT, 65536, 128);
		SecretKey tmp = factory.generateSecret(keyspec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		// ENCRYPTION
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(16 * 8, FIXED_NONCE);
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);

		byte[] byteCipher = cipher.doFinal(binary);
		return byteCipher;
	}
	
	/**
	 * Encrypt to base 64 string.
	 *
	 * @param binary the binary
	 * @param password the password
	 * @return the string
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 */
	public String encryptToBase64String(byte[] binary, char[] password)
			throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		// DERIVE key (from password and salt)
		SecretKeyFactory factory = SecretKeyFactory.getInstance(CONST_ALGO_PBKDF2);
		KeySpec keyspec = new PBEKeySpec(password, SALT, 65536, 128);
		SecretKey tmp = factory.generateSecret(keyspec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		// ENCRYPTION
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(16 * 8, FIXED_NONCE);
		cipher.init(Cipher.ENCRYPT_MODE, key, spec);

		byte[] byteCipher = cipher.doFinal(binary);
		String cipherText = new String(Base64.getEncoder().encode(byteCipher));
		return cipherText;
	}

	/**
	 * Decrypt.
	 *
	 * @param encodedCipherText the encoded cipher text
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
	public byte[] decrypt(String encodedCipherText, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {

		// DERIVE key (from password and salt)
		SecretKeyFactory factory = SecretKeyFactory.getInstance(CONST_ALGO_PBKDF2);
		KeySpec keyspec = new PBEKeySpec(password, SALT, 65536, 128);
		SecretKey tmp = factory.generateSecret(keyspec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(16 * 8, FIXED_NONCE);
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		byte[] decryptedCipher = cipher.doFinal(Base64.getDecoder().decode(encodedCipherText));
		return decryptedCipher;
	}
	
	/**
	 * Decrypt to base 64 string.
	 *
	 * @param encodedCipherText the encoded cipher text
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
	public String decryptToBase64String(String encodedCipherText, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {

		// DERIVE key (from password and salt)
		SecretKeyFactory factory = SecretKeyFactory.getInstance(CONST_ALGO_PBKDF2);
		KeySpec keyspec = new PBEKeySpec(password, SALT, 65536, 128);
		SecretKey tmp = factory.generateSecret(keyspec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(16 * 8, FIXED_NONCE);
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		byte[] decryptedCipher = cipher.doFinal(Base64.getDecoder().decode(encodedCipherText));
		String decryptedCipherText = new String(decryptedCipher);
		return decryptedCipherText;
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
	public byte[] decrypt(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {

		// DERIVE key (from password and salt)
		SecretKeyFactory factory = SecretKeyFactory.getInstance(CONST_ALGO_PBKDF2);
		KeySpec keyspec = new PBEKeySpec(password, SALT, 65536, 128);
		SecretKey tmp = factory.generateSecret(keyspec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(16 * 8, FIXED_NONCE);
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		byte[] decryptedCipher = cipher.doFinal(binary);
		return decryptedCipher;
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
	public String decryptToBase64String(byte[] binary, char[] password)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {

		// DERIVE key (from password and salt)
		SecretKeyFactory factory = SecretKeyFactory.getInstance(CONST_ALGO_PBKDF2);
		KeySpec keyspec = new PBEKeySpec(password, SALT, 65536, 128);
		SecretKey tmp = factory.generateSecret(keyspec);
		SecretKey key = new SecretKeySpec(tmp.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(16 * 8, FIXED_NONCE);
		cipher.init(Cipher.DECRYPT_MODE, key, spec);
		byte[] decryptedCipher = cipher.doFinal(binary);
		String decryptedCipherText = new String(decryptedCipher);
		return decryptedCipherText;
	}

}
