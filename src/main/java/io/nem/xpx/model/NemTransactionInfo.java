package io.nem.xpx.model;

import java.io.Serializable;

public class NemTransactionInfo implements Serializable {
	
	private String nemHash;
	private String sender;
	private String receiver;
	private String payload;
	public String getNemHash() {
		return nemHash;
	}
	public void setNemHash(String nemHash) {
		this.nemHash = nemHash;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
	
	

}
