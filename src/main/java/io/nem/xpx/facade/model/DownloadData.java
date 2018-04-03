/*
 * 
 */
package io.nem.xpx.facade.model;

import java.io.Serializable;

import io.nem.xpx.model.BinaryTransactionEncryptedMessage;


/**
 * The Class DownloadData.
 */
public class DownloadData implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data message. */
	private BinaryTransactionEncryptedMessage dataMessage;
	
	/** The data. */
	private byte[] data;
	
	/** The message type. */
	private int messageType;
	
	/**
	 * Gets the data message.
	 *
	 * @return the data message
	 */
	public BinaryTransactionEncryptedMessage getDataMessage() {
		return dataMessage;
	}
	
	/**
	 * Sets the data message.
	 *
	 * @param dataMessage the new data message
	 */
	public void setDataMessage(BinaryTransactionEncryptedMessage dataMessage) {
		this.dataMessage = dataMessage;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	
	/**
	 * Gets the message type.
	 *
	 * @return the message type
	 */
	public int getMessageType() {
		return messageType;
	}
	
	/**
	 * Sets the message type.
	 *
	 * @param messageType the new message type
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
