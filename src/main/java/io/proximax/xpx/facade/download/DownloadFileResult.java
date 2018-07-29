/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * 
 */
package io.proximax.xpx.facade.download;

import io.proximax.xpx.model.NemMessageType;
import io.proximax.xpx.service.model.buffers.ResourceHashMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;



/**
 * The Class DownloadFileResult.
 */
public class DownloadFileResult extends DownloadResult {

	/**
	 * Instantiates a new download file result.
	 *
	 * @param dataMessage the data message
	 * @param data the data
	 * @param messageType the message type
	 */
	private DownloadFileResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		super(dataMessage, data, messageType);
	}

	/**
	 * From download result.
	 *
	 * @param downloadResult the download result
	 * @return the download file result
	 */
	public static DownloadFileResult fromDownloadResult(DownloadResult downloadResult) {
		return new DownloadFileResult(downloadResult.getDataMessage(),
				downloadResult.getData(),
				downloadResult.getMessageType());
	}

	/**
	 * Save to file.
	 *
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveToFile(File file) throws IOException  {
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(getData());
		}
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return getDataMessage() != null ? getDataMessage().name() : null;
	}

	/**
	 * Gets the content type.
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return getDataMessage() != null ? getDataMessage().type() : null;
	}

}
