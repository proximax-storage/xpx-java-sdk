/*
 * 
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.model.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;



/**
 * The Class Upload.
 */
public class UploadAsync extends Upload {


	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 */
	public UploadAsync(PeerConnection peerConnection) {
		super(peerConnection);

	}

	/**
	 * Upload file.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadFile(UploadFileParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {
		CompletableFuture<UploadResult> uploadFileAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return handleFileUpload(uploadParameter);
			} catch (UploadException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});
		return uploadFileAsync;
	}

	/**
	 * Upload text data.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadTextData(UploadDataParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {
		CompletableFuture<UploadResult> uploadDataAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return handleTextDataUpload(uploadParameter);
			} catch (UploadException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			callback.process(n);
			return n;
		});
		return uploadDataAsync;
	}

	/**
	 * Upload binary.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadBinary(UploadBinaryParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		CompletableFuture<UploadResult> uploadBinaryAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return handleBinaryUpload(uploadParameter);
			} catch (UploadException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return uploadBinaryAsync;
	}

	/**
	 * Upload path.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadPath(UploadPathParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		CompletableFuture<UploadResult> uploadPathAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return handlePathUpload(uploadParameter);
			} catch (UploadException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return uploadPathAsync;

	}
}
