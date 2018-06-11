/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;



/**
 * The Class DownloadBinaryResult.
 */
public class DownloadBinaryResult extends DownloadResult {

	/**
	 * Instantiates a new download binary result.
	 *
	 * @param dataMessage the data message
	 * @param data the data
	 * @param messageType the message type
	 */
	private DownloadBinaryResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		super(dataMessage, data, messageType);
	}

	/**
	 * From download result.
	 *
	 * @param downloadResult the download result
	 * @return the download binary result
	 */
	public static DownloadBinaryResult fromDownloadResult(DownloadResult downloadResult) {
		return new DownloadBinaryResult(downloadResult.getDataMessage(),
				downloadResult.getData(),
				downloadResult.getMessageType());

	}
}
