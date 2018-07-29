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

/*
 *
 */
package io.proximax.xpx.facade.download;

import io.proximax.xpx.callback.ServiceAsyncCallback;
import io.proximax.xpx.facade.AbstractAsyncFacadeService;
import io.proximax.xpx.facade.connection.PeerConnection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;



/**
 * The Class DownloadAsync.
 */
public class DownloadAsync extends AbstractAsyncFacadeService {

	/** The download. */
	private Download download;

	/**
	 * Instantiates a new download async.
	 *
	 * @param peerConnection the peer connection
	 */
	public DownloadAsync(PeerConnection peerConnection) {
		this.download = new Download(peerConnection);

	}

	/**
	 * Download binary.
	 *
	 * @param downloadParameter the download parameter
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadBinaryResult> downloadBinary(DownloadParameter downloadParameter, ServiceAsyncCallback<DownloadBinaryResult> callback) {

		return runAsync(
				() -> {
					try {
						return download.downloadBinary(downloadParameter);
					} catch (DownloadException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}
	
	/**
	 * Download text data.
	 *
	 * @param downloadParameter the download parameter
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadTextDataResult> downloadTextData(DownloadParameter downloadParameter, ServiceAsyncCallback<DownloadTextDataResult> callback) {

		return runAsync(
				() -> {
					try {
						return download.downloadTextData(downloadParameter);
					} catch (DownloadException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}
	
	/**
	 * Download file.
	 *
	 * @param downloadParameter the download parameter
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadFileResult> downloadFile(DownloadParameter downloadParameter, ServiceAsyncCallback<DownloadFileResult> callback) {

		return runAsync(
				() -> {
					try {
						return download.downloadFile(downloadParameter);
					} catch (DownloadException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}


}
