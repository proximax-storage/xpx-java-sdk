package io.nem.xpx.callback;

import io.nem.xpx.facade.download.DownloadResult;




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
