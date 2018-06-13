/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;

import java.nio.charset.Charset;



/**
 * The Class DownloadTextDataResult.
 */
public class DownloadTextDataResult extends DownloadResult {

	/**
	 * Instantiates a new download text data result.
	 *
	 * @param dataMessage the data message
	 * @param data the data
	 * @param messageType the message type
	 */
	private DownloadTextDataResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		super(dataMessage, data, messageType);
	}

	/**
	 * From download result.
	 *
	 * @param downloadResult the download result
	 * @return the download text data result
	 */
	public static DownloadTextDataResult fromDownloadResult(DownloadResult downloadResult) {
		return new DownloadTextDataResult(downloadResult.getDataMessage(),
				downloadResult.getData(),
				downloadResult.getMessageType());

	}

	/**
	 * Gets the string.
	 *
	 * @param encoding the encoding
	 * @return the string
	 */
	public String getString(String encoding) {
		return new String(getData(), Charset.forName(encoding));
	}

	/**
	 * Gets the string.
	 *
	 * @return the string
	 */
	public String getString() {
		return new String(getData(), Charset.forName("UTF-8"));
	}

}
