package io.nem.xpx;

import java.io.IOException;

import io.nem.ApiException;


/**
 * The Interface DownloadApiInterface.
 */
public interface DownloadApiInterface {
	
	/**
	 * Download stream using hash using POST.
	 *
	 * @param hash the hash
	 * @return the byte[]
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public byte[] downloadStreamUsingHashUsingPOST(String hash) throws ApiException,IOException;
}
