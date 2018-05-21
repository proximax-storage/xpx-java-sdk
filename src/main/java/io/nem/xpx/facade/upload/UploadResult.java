/*
 * 
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;

import java.io.Serializable;




/**
 * The Class UploadData.
 */
public class UploadResult implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data message. */
	private final ResourceHashMessage dataMessage;
	
	/** The nem hash. */
	private final String nemHash;

    /**
     * Instantiate class.
     *
     * @param dataMessage the data message
     * @param nemHash the nem hash
     */
	public UploadResult(final ResourceHashMessage dataMessage, final String nemHash) {
		this.dataMessage = dataMessage;
		this.nemHash = nemHash;
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
	 * Gets the nem hash.
	 *
	 * @return the nem hash
	 */
	public String getNemHash() {
		return nemHash;
	}
}
