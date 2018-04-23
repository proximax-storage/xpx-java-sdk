package io.nem.xpx.model;

import io.nem.xpx.facade.model.UploadData;

@FunctionalInterface
public interface UploadCallback {
	
	public void process(UploadData uploadData);
}
