/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;

import java.io.Serializable;





/**
 * The Class DownloadResult.
 */
public class DownloadResult implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data message. */
	private final ResourceHashMessage dataMessage;
	
	/** The data. */
	private final byte[] data;
	
	/** The message type. */
	private final NemMessageType messageType;

    /**
     * Instantiate class.
     *
     * @param dataMessage the data message
     * @param data the data
     * @param messageType the message type
     */
	public DownloadResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		this.dataMessage = dataMessage;
		this.data = data;
		this.messageType = messageType;
	}

	/**
	 * Gets the data message.
	 *
	 * @return the data message
	 */
	public ResourceHashMessage getDataMessage() {
		return dataMessage;
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
	 * Gets the message type.
	 *
	 * @return the message type
	 */
	public NemMessageType getMessageType() {
		return messageType;
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
