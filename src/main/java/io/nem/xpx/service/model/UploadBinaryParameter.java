package io.nem.xpx.service.model;

import java.io.File;
import java.io.Serializable;
import org.nem.core.model.mosaic.Mosaic;


/**
 * The Class UploadFileParameter.
 */
public class UploadBinaryParameter extends DataParameter implements Serializable {

	
	/** The data. */
	private byte[] data;
	
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}
	
}
