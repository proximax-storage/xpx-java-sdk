package io.nem.xpx.wrap;

import java.io.Serializable;

import io.nem.xpx.model.BinaryTransactionEncryptedMessage;

public class UploadData implements Serializable{

	private static final long serialVersionUID = 1L;
	private BinaryTransactionEncryptedMessage dataMessage;
	private String nemHash;
	public BinaryTransactionEncryptedMessage getDataMessage() {
		return dataMessage;
	}
	public void setDataMessage(BinaryTransactionEncryptedMessage dataMessage) {
		this.dataMessage = dataMessage;
	}
	public String getNemHash() {
		return nemHash;
	}
	public void setNemHash(String nemHash) {
		this.nemHash = nemHash;
	}
	
	
	
}
