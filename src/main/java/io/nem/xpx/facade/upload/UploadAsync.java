/*
 * 
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;


/**
 * The Class Upload.
 */
public class UploadAsync extends AbstractAsyncFacadeService {

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
	public CompletableFuture<UploadResult> uploadTextData(UploadTextDataParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

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

	public CompletableFuture<UploadResult> uploadFilesAsZip(UploadFilesAsZipParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return upload.uploadFilesAsZip(parameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, uploadParameter, callback);
	}

	public CompletableFuture<MultiFileUploadResult> uploadMultipleFiles(UploadMultipleFilesParameter uploadParameter, ServiceAsyncCallback<MultiFileUploadResult> callback) {

		return runAsync(
				parameter -> {
					try {
						return upload.uploadMultipleFiles(parameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, uploadParameter, callback);
	}
}
