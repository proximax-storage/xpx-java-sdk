package io.nem.xpx.callback;

import io.nem.xpx.facade.model.DownloadData;
import io.nem.xpx.facade.model.UploadData;


/**
 * The Interface DownloadCallback.
 */
public interface DownloadCallback {
	
	/**
	 * Process.
	 *
	 * @param downloadData the download data
	 */
	public void process(DownloadData downloadData);
}
