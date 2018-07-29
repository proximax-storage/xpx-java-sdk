/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.callback;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import io.proximax.ApiCallback;
import io.proximax.xpx.exceptions.ApiException;





/**
 * The Class RemoteDataHashUploadCallback.
 */
public class RemoteDataHashUploadCallback implements ApiCallback<String> {
	
	/** The logger. */
	protected Logger LOGGER = Logger.getAnonymousLogger();
	
	/* (non-Javadoc)
	 * @see io.proximax.ApiCallback#onFailure(io.proximax.ApiException, int, java.util.Map)
	 */
	@Override
	public void onFailure(ApiException e, int statusCode, Map<String, List<String>> responseHeaders) {
		Logger.getAnonymousLogger().severe(e.getMessage());
		
	}

	/* (non-Javadoc)
	 * @see io.proximax.ApiCallback#onSuccess(java.lang.Object, int, java.util.Map)
	 */
	@Override
	public void onSuccess(String result, int statusCode, Map<String, List<String>> responseHeaders) {
		Logger.getAnonymousLogger().info("onSuccess " + result);
		
	}

	/* (non-Javadoc)
	 * @see io.proximax.ApiCallback#onUploadProgress(long, long, boolean)
	 */
	@Override
	public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
		Logger.getAnonymousLogger().info("onUploadProgress: " + done);
		
	}

	/* (non-Javadoc)
	 * @see io.proximax.ApiCallback#onDownloadProgress(long, long, boolean)
	 */
	@Override
	public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
		Logger.getAnonymousLogger().info("onDownloadProgress: " + done);
		
	}


	
}
