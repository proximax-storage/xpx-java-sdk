/*
 *
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class DownloadAsync extends AbstractAsyncFacadeService {

	private Download download;

	public DownloadAsync(PeerConnection peerConnection) {
		this.download = new Download(peerConnection);

	}

	public CompletableFuture<DownloadBinaryResult> downloadBinary(DownloadParameter downloadParameter, ServiceAsyncCallback<DownloadBinaryResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return download.downloadBinary(downloadParameter);
					} catch (DownloadException e) {
						throw new CompletionException(e);
					}
				}, downloadParameter, callback);
	}
	public CompletableFuture<DownloadTextDataResult> downloadTextData(DownloadParameter downloadParameter, ServiceAsyncCallback<DownloadTextDataResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return download.downloadTextData(downloadParameter);
					} catch (DownloadException e) {
						throw new CompletionException(e);
					}
				}, downloadParameter, callback);
	}
	public CompletableFuture<DownloadFileResult> downloadFile(DownloadParameter downloadParameter, ServiceAsyncCallback<DownloadFileResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return download.downloadFile(downloadParameter);
					} catch (DownloadException e) {
						throw new CompletionException(e);
					}
				}, downloadParameter, callback);
	}


}
