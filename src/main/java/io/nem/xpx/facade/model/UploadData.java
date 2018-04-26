/*
 * 
 */
package io.nem.xpx.facade.model;

import java.io.Serializable;

import io.nem.xpx.service.model.Resource;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;


/**
 * The Class UploadData.
 */
public class UploadData implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data message. */
	private ResourceHashMessage dataMessage;
	
	/** The nem hash. */
	private String nemHash;
	
	/**
	 * Gets the data message.
	 *
	 * @return the data message
	 */
	public ResourceHashMessage getDataMessage() {
		return dataMessage;
	}
	
	/**
	 * Sets the data message.
	 *
	 * @param dataMessage the new data message
	 */
	public void setDataMessage(ResourceHashMessage dataMessage) {
		this.dataMessage = dataMessage;
	}
	
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
	
	
	
}
