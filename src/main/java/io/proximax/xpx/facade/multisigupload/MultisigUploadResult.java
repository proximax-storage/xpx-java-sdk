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

package io.proximax.xpx.facade.multisigupload;

import io.proximax.xpx.facade.upload.UploadResult;

import java.io.Serializable;





/**
 * The Class MultisigUploadData.
 */
public class MultisigUploadResult implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The upload data. */
	private final UploadResult uploadResult;
	
	/** The secret key. */
	private final String secretKey;

	/**
	 * Instantiate class.
	 *
	 * @param uploadData the upload data
	 * @param secretKey the secret key
	 */
	public MultisigUploadResult(final UploadResult uploadData, final String secretKey) {
		this.uploadResult = uploadData;
		this.secretKey = secretKey;
	}

	/**
	 * Gets the upload result.
	 *
	 * @return the upload result
	 */
	public UploadResult getUploadResult() {
		return uploadResult;
	}

	/**
	 * Gets the secret key.
	 *
	 * @return the secret key
	 */
	public String getSecretKey() {
		return secretKey;
	}

}
