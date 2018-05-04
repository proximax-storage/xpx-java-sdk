package io.nem.xpx.model;

import java.io.Serializable;
import org.nem.core.model.mosaic.Mosaic;




/**
 * The Class UploadDataParameter.
 */
public class UploadDataParameter extends DataParameter implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The data. */
	private String data;
	
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(String data) {
		this.data = data;
	}
	
	
}
