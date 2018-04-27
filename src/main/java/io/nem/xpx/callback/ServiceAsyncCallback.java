package io.nem.xpx.callback;

import io.nem.xpx.facade.model.UploadData;


/**
 * The Interface ServiceAsyncCallback.
 *
 * @param <T> the generic type
 */
@FunctionalInterface
public interface ServiceAsyncCallback<T> {
	
	/**
	 * Process.
	 *
	 * @param uploadData the upload data
	 */
	public void process(T uploadData);
}
