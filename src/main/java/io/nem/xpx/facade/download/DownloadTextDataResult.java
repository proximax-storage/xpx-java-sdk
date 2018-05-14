/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.service.model.buffers.ResourceHashMessage;

import java.io.Serializable;


/**
 * The Class DownloadData.
 */
public class DownloadTextDataResult implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data message. */
	private final ResourceHashMessage dataMessage;

	/** The data. */
	private final String data;

	public DownloadTextDataResult(ResourceHashMessage dataMessage, String data) {
		this.dataMessage = dataMessage;
		this.data = data;
	}

	public ResourceHashMessage getDataMessage() {
		return dataMessage;
	}

	public String getData() {
		return data;
	}
}
