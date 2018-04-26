package io.nem.xpx.callback;

import io.nem.xpx.facade.model.DownloadData;
import io.nem.xpx.facade.model.UploadData;

public interface DownloadCallback {
	
	public void process(DownloadData downloadData);
}
