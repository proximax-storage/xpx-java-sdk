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
package io.proximax.xpx.facade.upload;

import io.proximax.xpx.service.model.buffers.ResourceHashMessage;

import java.io.Serializable;





/**
 * The Class UploadData.
 */
public class UploadResult implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The data message. */
	private final ResourceHashMessage dataMessage;
	
	/** The proximax hash. */
	private final String nemHash;

    /**
     * Instantiate class.
     *
     * @param dataMessage the data message
     * @param nemHash the proximax hash
     */
	public UploadResult(final ResourceHashMessage dataMessage, final String nemHash) {
		this.dataMessage = dataMessage;
		this.nemHash = nemHash;
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
	 * Gets the proximax hash.
	 *
	 * @return the proximax hash
	 */
	public String getNemHash() {
		return nemHash;
	}

	public String getIpfsHash() {
		return dataMessage.hash();
	}
}
