package io.nem.xpx.service.model;

import java.io.Serializable;

public class MultisigUploadDataParameter extends UploadDataParameter implements Serializable {
	
	private String multisigPublicKey;

	public String getMultisigPublicKey() {
		return multisigPublicKey;
	}

	
	public void setMultisigPublicKey(String multisigPublicKey) {
		this.multisigPublicKey = multisigPublicKey;
	}
	
	
}
