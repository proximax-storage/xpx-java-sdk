package io.nem.xpx.callback;

import io.nem.xpx.facade.model.DownloadResult;
import io.nem.xpx.facade.model.UploadResult;


/**
 * The Interface DownloadCallback.
 */
public interface DownloadCallback {
	
	/**
	 * Process.
	 *
	 * @param downloadData the download data
	 */
	public void process(DownloadResult downloadData);
}
