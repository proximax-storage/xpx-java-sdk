package io.nem.xpx.facade.model;

import java.io.Serializable;



/**
 * The Class MultisigUploadData.
 */
public class MultisigUploadResult implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The upload data. */
	private UploadResult uploadData;
	
	/** The secret key. */
	private String secretKey;


	/**
	 * Instantiates a new multisig upload data.
	 */
	public MultisigUploadResult() {
		this.uploadData = new UploadResult();
	}

	/**
	 * Gets the upload data.
	 *
	 * @return the upload data
	 */
	public UploadResult getUploadData() {
		return uploadData;
	}

	/**
	 * Sets the upload data.
	 *
	 * @param uploadData the new upload data
	 */
	public void setUploadData(UploadResult uploadData) {
		this.uploadData = uploadData;
	}

	/**
	 * Gets the secret key.
	 *
	 * @return the secret key
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * Sets the secret key.
	 *
	 * @param secretKey the new secret key
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
