package io.nem.xpx.model;

import java.io.Serializable;


/**
 * The Class NemTransactionInfo.
 */
public class NemTransactionInfo implements Serializable {
	
	/** The nem hash. */
	private String nemHash;
	
	/** The sender. */
	private String sender;
	
	/** The receiver. */
	private String receiver;
	
	/** The payload. */
	private String payload;
	
	/**
	 * Gets the nem hash.
	 *
	 * @return the nem hash
	 */
	public String getNemHash() {
		return nemHash;
	}
	
	/**
	 * Sets the nem hash.
	 *
	 * @param nemHash the new nem hash
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
