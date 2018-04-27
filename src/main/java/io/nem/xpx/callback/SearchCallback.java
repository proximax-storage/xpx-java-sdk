package io.nem.xpx.callback;

import io.nem.xpx.facade.model.UploadData;


/**
 * The Interface SearchCallback.
 */
public interface SearchCallback {
	
	/**
	 * Process.
	 *
	 * @param searchResult the search result
	 */
	public void process(String searchResult);
}
