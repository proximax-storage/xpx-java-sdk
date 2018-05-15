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
 * The Class Download.
 */
public class DownloadAsync extends AbstractAsyncFacadeService {

	private Download download;

	public DownloadAsync(PeerConnection peerConnection) {
		this.download = new Download(peerConnection);

	}

	public CompletableFuture<DownloadResult> download(DownloadParameter downloadParameter, ServiceAsyncCallback<DownloadResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return download.download(downloadParameter);
					} catch (DownloadException e) {
						throw new CompletionException(e);
					}
				}, downloadParameter, callback);
	}


}
