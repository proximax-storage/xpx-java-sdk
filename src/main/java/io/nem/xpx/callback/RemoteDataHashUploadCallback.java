package io.nem.xpx.callback;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import io.nem.ApiCallback;
import io.nem.ApiException;

public class RemoteDataHashUploadCallback implements ApiCallback<String> {
	protected Logger LOGGER = Logger.getAnonymousLogger();
	@Override
	public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		Logger.getAnonymousLogger().severe(e.getMessage());
		
	}

	@Override
	public void onSuccess(String result, int statusCode, Map<String, List<String>> responseHeaders) {
		Logger.getAnonymousLogger().info("onSuccess " + result);
		
	}

	@Override
	public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
		Logger.getAnonymousLogger().info("onUploadProgress: " + done);
		
	}

	@Override
	public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
		Logger.getAnonymousLogger().info("onDownloadProgress: " + done);
		
	}


	
}
