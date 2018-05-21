/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;


public class DownloadFileResult extends DownloadResult {

	private DownloadFileResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		super(dataMessage, data, messageType);
	}

	public static DownloadFileResult fromDownloadResult(DownloadResult downloadResult) {
		return new DownloadFileResult(downloadResult.getDataMessage(),
				downloadResult.getData(),
				downloadResult.getMessageType());

	}
}
