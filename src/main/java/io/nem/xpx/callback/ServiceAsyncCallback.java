package io.nem.xpx.callback;

import io.nem.xpx.facade.model.UploadData;

@FunctionalInterface
public interface ServiceAsyncCallback<T> {
	
	public void process(T uploadData);
}
