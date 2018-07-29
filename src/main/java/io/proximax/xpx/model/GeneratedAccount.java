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

package io.proximax.xpx.model;

import org.nem.core.crypto.KeyPair;
import org.nem.core.model.Account;






/**
 * The Class GeneratedAccount.
 */
public class GeneratedAccount {
	
	/** The key pair. */
	private KeyPair keyPair;
	
	/** The account. */
	private Account account;
	
	/** The encoded address. */
	private String encodedAddress;
	
	/** The encoded public key. */
	private String encodedPublicKey;
	
	/** The encoded private key. */
	private String encodedPrivateKey;
	
	/**
	 * Gets the encoded address.
	 *
	 * @return the encoded address
	 */
	public String getEncodedAddress() {
		return encodedAddress;
	}

	/**
	 * Sets the encoded address.
	 *
	 * @param encodedAddress the new encoded address
	 */
	public void setEncodedAddress(String encodedAddress) {
		this.encodedAddress = encodedAddress;
	}

	/**
	 * Gets the encoded public key.
	 *
	 * @return the encoded public key
	 */
	public String getEncodedPublicKey() {
		return encodedPublicKey;
	}

	/**
	 * Sets the encoded public key.
	 *
	 * @param encodedPublicKey the new encoded public key
	 */
	public void setEncodedPublicKey(String encodedPublicKey) {
		this.encodedPublicKey = encodedPublicKey;
	}

	/**
	 * Gets the encoded private key.
	 *
	 * @return the encoded private key
	 */
	public String getEncodedPrivateKey() {
		return encodedPrivateKey;
	}

	/**
	 * Sets the encoded private key.
	 *
	 * @param encodedPrivateKey the new encoded private key
	 */
	public void setEncodedPrivateKey(String encodedPrivateKey) {
		this.encodedPrivateKey = encodedPrivateKey;
	}

	/**
	 * Gets the key pair.
	 *
	 * @return the key pair
	 */
	public KeyPair getKeyPair() {
		return keyPair;
	}
	
	/**
	 * Sets the key pair.
	 *
	 * @param keyPair the new key pair
	 */
	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}
	
	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	
	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
