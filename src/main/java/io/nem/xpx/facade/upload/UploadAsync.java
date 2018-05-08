/*
 * 
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.model.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;


/**
 * The Class Upload.
 */
public class UploadAsync {

	private Upload upload;


	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 */
	public UploadAsync(PeerConnection peerConnection) {
		this.upload = new Upload(peerConnection);

	}

	/**
	 * Upload file.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadFile(UploadFileParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return upload.uploadFile(parameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, uploadParameter, callback);
	}

	/**
	 * Upload text data.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadTextData(UploadDataParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return upload.uploadTextData(parameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, uploadParameter, callback);
	}

	/**
	 * Upload binary.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadBinary(UploadBinaryParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return upload.uploadBinary(parameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, uploadParameter, callback);
	}

	/**
	 * Upload path.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the future
	 */
	public CompletableFuture<UploadResult> uploadPath(UploadPathParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {
		return runAsync(
				parameter -> {
					try {
						return upload.uploadPath(parameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, uploadParameter, callback);
	}

	public CompletableFuture<UploadResult> uploadFilesAsZip(UploadMultiFilesParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return upload.uploadFilesAsZip(parameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, uploadParameter, callback);
	}

	private <T> CompletableFuture<UploadResult> runAsync(final Function<T, UploadResult> uploadFunction, final T uploadParameter,
														 final ServiceAsyncCallback<UploadResult> callback) {
		return CompletableFuture
				.supplyAsync(() -> uploadFunction.apply(uploadParameter))
				.thenApply(uploadResult -> {
					callback.process(uploadResult);
					return uploadResult;
				});
	}

}
