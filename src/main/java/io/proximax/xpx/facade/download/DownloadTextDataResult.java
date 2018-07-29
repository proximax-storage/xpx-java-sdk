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
