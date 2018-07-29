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

import java.io.Serializable;





/**
 * The Class DownloadResult.
 */
public class DownloadResult implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data message. */
	private final ResourceHashMessage dataMessage;
	
	/** The data. */
	private final byte[] data;
	
	/** The message type. */
	private final NemMessageType messageType;

    /**
     * Instantiate class.
     *
     * @param dataMessage the data message
     * @param data the data
     * @param messageType the message type
     */
	public DownloadResult(final ResourceHashMessage dataMessage, final byte[] data, final NemMessageType messageType) {
		this.dataMessage = dataMessage;
		this.data = data;
		this.messageType = messageType;
	}

	/**
	 * Gets the data message.
	 *
	 * @return the data message
	 */
	public ResourceHashMessage getDataMessage() {
		return dataMessage;
	}
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	
	/**
	 * Gets the message type.
	 *
	 * @return the message type
	 */
	public NemMessageType getMessageType() {
		return messageType;
	}
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
