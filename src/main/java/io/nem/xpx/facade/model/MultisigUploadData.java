package io.nem.xpx.facade.model;

import java.io.Serializable;

import io.nem.xpx.model.BinaryTransactionEncryptedMessage;


/**
 * The Class MultisigUploadData.
 */
public class MultisigUploadData implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The upload data. */
	private UploadData uploadData;
	
	/** The secret key. */
	private String secretKey;


	/**
	 * Instantiates a new multisig upload data.
	 */
	public MultisigUploadData() {
		this.uploadData = new UploadData();
	}

	/**
	 * Gets the upload data.
	 *
	 * @return the upload data
	 */
	public UploadData getUploadData() {
		return uploadData;
	}

	/**
	 * Sets the upload data.
	 *
	 * @param uploadData the new upload data
	 */
	public void setUploadData(UploadData uploadData) {
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
