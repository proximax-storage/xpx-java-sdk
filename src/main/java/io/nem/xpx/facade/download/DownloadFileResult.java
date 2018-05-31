/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class DownloadFileResult extends DownloadResult {

	private DownloadFileResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		super(dataMessage, data, messageType);
	}

	public static DownloadFileResult fromDownloadResult(DownloadResult downloadResult) {
		return new DownloadFileResult(downloadResult.getDataMessage(),
				downloadResult.getData(),
				downloadResult.getMessageType());
	}

	public void saveToFile(File file) throws IOException  {
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(getData());
		}
	}

	public String getFileName() {
		return getDataMessage().name();
	}

	public String getContentType() {
		return getDataMessage().type();
	}

}
