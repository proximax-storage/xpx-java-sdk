package io.nem.xpx.facade.model;

import java.io.Serializable;

import io.nem.xpx.model.BinaryTransactionEncryptedMessage;

public class MultisigUploadData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UploadData uploadData;
	private String secretKey;


	public MultisigUploadData() {
		this.uploadData = new UploadData();
	}

	public UploadData getUploadData() {
		return uploadData;
	}

	public void setUploadData(UploadData uploadData) {
		this.uploadData = uploadData;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

}
