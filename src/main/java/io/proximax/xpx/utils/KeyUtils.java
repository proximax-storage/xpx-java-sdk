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

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;





/**
 * The Class KeyUtils.
 */
public class KeyUtils {

	/**
	 * get address from private key.
	 *
	 * @param privateKeyString the private key string
	 * @return the address from private key
	 */
	public static String getAddressFromPrivateKey(String privateKeyString) {
		PrivateKey privateKey = PrivateKey.fromHexString(privateKeyString);
		KeyPair keyPair = new KeyPair(privateKey);
		return Address.fromPublicKey(keyPair.getPublicKey()).toString();
	}
	
	/**
	 * get public key from private key.
	 *
	 * @param privateKeyString the private key string
	 * @return the public from private key
	 */
	public static String getPublicFromPrivateKey(String privateKeyString) {
		PrivateKey privateKey = PrivateKey.fromHexString(privateKeyString);
		KeyPair keyPair = new KeyPair(privateKey);
		return keyPair.getPublicKey().toString();
	}
	
	/**
	 * get address from public key.
	 *
	 * @param publicKeyString the public key string
	 * @return the address from public key
	 */
	public static String getAddressFromPublicKey(String publicKeyString) {
		PublicKey publicKey = PublicKey.fromHexString(publicKeyString);
		Address address = Address.fromPublicKey(publicKey);
		return address.toString();
	}

	
}
