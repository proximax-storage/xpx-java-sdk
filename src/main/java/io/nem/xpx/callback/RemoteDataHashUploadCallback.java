package io.nem.xpx.callback;

import java.util.List;
import java.util.Map;

import io.nem.ApiCallback;
import io.nem.ApiException;
import io.nem.ProgressResponseBody.ProgressListener;

public class RemoteDataHashUploadCallback implements ApiCallback<String> {

	@Override
	public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(String result, int statusCode, Map<String, List<String>> responseHeaders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
		// TODO Auto-generated method stub
		
	}


	
}
