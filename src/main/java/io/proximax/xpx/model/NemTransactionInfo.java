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

import java.io.Serializable;


/**
 * The Class NemTransactionInfo.
 */
public class NemTransactionInfo implements Serializable {
	
	/** The proximax hash. */
	private String nemHash;
	
	/** The sender. */
	private String sender;
	
	/** The receiver. */
	private String receiver;
	
	/** The payload. */
	private String payload;
	
	/**
	 * Gets the proximax hash.
	 *
	 * @return the proximax hash
	 */
	public String getNemHash() {
		return nemHash;
	}
	
	/**
	 * Sets the proximax hash.
	 *
	 * @param nemHash the new proximax hash
	 */
	public void setNemHash(String nemHash) {
		this.nemHash = nemHash;
	}
	
	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}
	
	/**
	 * Sets the sender.
	 *
	 * @param sender the new sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	/**
	 * Gets the receiver.
	 *
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}
	
	/**
	 * Sets the receiver.
	 *
	 * @param receiver the new receiver
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * Gets the payload.
	 *
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}
	
	/**
	 * Sets the payload.
	 *
	 * @param payload the new payload
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
	
	

}
