package io.nem.xpx.callback;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import io.nem.ApiCallback;
import io.nem.xpx.exceptions.ApiException;





/**
 * The Class RemoteDataHashUploadCallback.
 */
public class RemoteDataHashUploadCallback implements ApiCallback<String> {
	
	/** The logger. */
	protected Logger LOGGER = Logger.getAnonymousLogger();
	
	/* (non-Javadoc)
	 * @see io.nem.ApiCallback#onFailure(io.nem.ApiException, int, java.util.Map)
	 */
	@Override
	public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		Logger.getAnonymousLogger().severe(e.getMessage());
		
	}

	/* (non-Javadoc)
	 * @see io.nem.ApiCallback#onSuccess(java.lang.Object, int, java.util.Map)
	 */
	@Override
	public void onSuccess(String result, int statusCode, Map<String, List<String>> responseHeaders) {
		Logger.getAnonymousLogger().info("onSuccess " + result);
		
	}

	/* (non-Javadoc)
	 * @see io.nem.ApiCallback#onUploadProgress(long, long, boolean)
	 */
	@Override
	public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
		Logger.getAnonymousLogger().info("onUploadProgress: " + done);
		
	}

	/* (non-Javadoc)
	 * @see io.nem.ApiCallback#onDownloadProgress(long, long, boolean)
	 */
	@Override
	public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
		Logger.getAnonymousLogger().info("onDownloadProgress: " + done);
		
	}


	
}
