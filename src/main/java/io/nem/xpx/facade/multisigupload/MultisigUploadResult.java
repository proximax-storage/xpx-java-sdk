package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.facade.upload.UploadResult;

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
