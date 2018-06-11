/*
 *
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;

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
