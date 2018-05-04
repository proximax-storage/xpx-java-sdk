package io.nem.xpx.model;

import java.io.File;
import java.io.Serializable;



/**
 * The Class UploadFileParameter.
 */
public class UploadFileParameter extends DataParameter implements Serializable {

	/** The data. */
	private File data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public File getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data
	 *            the new data
	 */
	public void setData(File data) {
		this.data = data;
	}

}
