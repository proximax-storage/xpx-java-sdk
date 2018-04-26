package io.nem.xpx.service.model;

import java.io.Serializable;


/**
 * The Class UploadFileParameter.
 */
public class MultisigUploadFileParameter extends UploadFileParameter implements Serializable {

	
	private String multisigPublicKey;

	public String getMultisigPublicKey() {
		return multisigPublicKey;
	}

	
	public void setMultisigPublicKey(String multisigPublicKey) {
		this.multisigPublicKey = multisigPublicKey;
	}
	
	
}
