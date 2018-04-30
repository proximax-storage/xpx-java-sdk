/*
 * 
 */
package io.nem.xpx.facade;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import io.nem.ApiException;
import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.UploadPathParameter;


/**
 * The Class Upload.
 */
public class UploadAsync extends Upload {

	/**
	 * Instantiates a new upload.
	 */
	public UploadAsync() {

	}

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public UploadAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
		super(peerConnection);

	}

	/**
	 * Upload file.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadData> uploadFile(UploadFileParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {
		CompletableFuture<UploadData> uploadFileAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handleFileUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return uploadData;
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
	public CompletableFuture<UploadData> uploadTextData(UploadDataParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {
		CompletableFuture<UploadData> uploadDataAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handleTextDataUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return uploadData;
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
	public CompletableFuture<UploadData> uploadBinary(UploadBinaryParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {

		CompletableFuture<UploadData> uploadBinaryAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handleBinaryUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return uploadData;
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
	public CompletableFuture<UploadData> uploadPath(UploadPathParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {

		CompletableFuture<UploadData> uploadPathAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handlePathUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException | PeerConnectionNotFoundException e) {
				throw new CompletionException(e);
			}
			return uploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return uploadPathAsync;

	}
}
