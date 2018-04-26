package io.nem.xpx.service.model;

import java.io.File;
import java.io.Serializable;
import org.nem.core.model.mosaic.Mosaic;


/**
 * The Class UploadFileParameter.
 */
public class MultisigUploadBinaryParameter extends UploadBinaryParameter implements Serializable {

	
	private String multisigPublicKey;

	public String getMultisigPublicKey() {
		return multisigPublicKey;
	}

	
	public void setMultisigPublicKey(String multisigPublicKey) {
		this.multisigPublicKey = multisigPublicKey;
	}
	
	
}
