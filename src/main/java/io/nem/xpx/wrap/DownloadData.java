package io.nem.xpx.wrap;

import java.io.Serializable;

import io.nem.xpx.model.BinaryTransactionEncryptedMessage;

public class DownloadData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BinaryTransactionEncryptedMessage dataMessage;
	private byte[] data;
	private int messageType;
	public BinaryTransactionEncryptedMessage getDataMessage() {
		return dataMessage;
	}
	public void setDataMessage(BinaryTransactionEncryptedMessage dataMessage) {
		this.dataMessage = dataMessage;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
