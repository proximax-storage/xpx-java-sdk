/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;


public class DownloadBinaryResult extends DownloadResult {

	private DownloadBinaryResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		super(dataMessage, data, messageType);
	}

	public static DownloadBinaryResult fromDownloadResult(DownloadResult downloadResult) {
		return new DownloadBinaryResult(downloadResult.getDataMessage(),
				downloadResult.getData(),
				downloadResult.getMessageType());

	}
}
