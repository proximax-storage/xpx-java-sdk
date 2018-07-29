/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.proximax.xpx.adapters.cipher.BinaryPBKDF2CipherEncryption;





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
		BinaryPBKDF2CipherEncryption basicBinaryEncryptor = new BinaryPBKDF2CipherEncryption();
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
		BinaryPBKDF2CipherEncryption basicBinaryEncryptor = new BinaryPBKDF2CipherEncryption();
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
		BinaryPBKDF2CipherEncryption basicBinaryEncryptor = new BinaryPBKDF2CipherEncryption();

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
		BinaryPBKDF2CipherEncryption basicBinaryEncryptor = new BinaryPBKDF2CipherEncryption();

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
		BinaryPBKDF2CipherEncryption basicBinaryEncryptor = new BinaryPBKDF2CipherEncryption();

		return basicBinaryEncryptor.decryptToBase64String(cipherEncryptedText, password);
	}

}
