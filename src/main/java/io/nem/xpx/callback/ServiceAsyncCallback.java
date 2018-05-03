package io.nem.xpx.callback;


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
