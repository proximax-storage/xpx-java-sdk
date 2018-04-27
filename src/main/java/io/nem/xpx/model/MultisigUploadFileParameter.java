package io.nem.xpx.model;

import java.io.Serializable;



/**
 * The Class UploadFileParameter.
 */
public class MultisigUploadFileParameter extends UploadFileParameter implements Serializable {

	
	/** The multisig public key. */
	private String multisigPublicKey;

	/**
	 * Gets the multisig public key.
	 *
	 * @return the multisig public key
	 */
	public String getMultisigPublicKey() {
		return multisigPublicKey;
	}

	
	/**
	 * Sets the multisig public key.
	 *
	 * @param multisigPublicKey the new multisig public key
	 */
	public void setMultisigPublicKey(String multisigPublicKey) {
		this.multisigPublicKey = multisigPublicKey;
	}
	
	
}
