/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;

import java.io.Serializable;


/**
 * The Class DownloadData.
 */
public class DownloadBinaryResult implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data message. */
	private final ResourceHashMessage dataMessage;

	/** The data. */
	private final byte[] data;

	public DownloadBinaryResult(ResourceHashMessage dataMessage, byte[] data) {
		this.dataMessage = dataMessage;
		this.data = data;
	}

	public ResourceHashMessage getDataMessage() {
		return dataMessage;
	}

	public byte[] getData() {
		return data;
	}
}
