package io.nem.xpx.model;

import java.io.Serializable;
import org.nem.core.model.mosaic.Mosaic;


/**
 * The Class UploadPathParameter.
 */
public class UploadPathParameter extends DataParameter implements Serializable {


	/** The path. */
	private String path;
	
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}
}
