/*
 * 
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;



/**
 * The Class Upload.
 */
public class UploadAsync extends AbstractAsyncFacadeService {

	/** The upload. */
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
				() -> {
					try {
						return upload.uploadFile(uploadParameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, callback);
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
				() -> {
					try {
						return upload.uploadTextData(uploadParameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, callback);
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
				() -> {
					try {
						return upload.uploadBinary(uploadParameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, callback);
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
				() -> {
					try {
						return upload.uploadPath(uploadParameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Upload files as zip.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<UploadResult> uploadFilesAsZip(UploadFilesAsZipParameter uploadParameter, ServiceAsyncCallback<UploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return upload.uploadFilesAsZip(uploadParameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Upload multiple files.
	 *
	 * @param uploadParameter the upload parameter
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<MultiFileUploadResult> uploadMultipleFiles(UploadMultipleFilesParameter uploadParameter, ServiceAsyncCallback<MultiFileUploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return upload.uploadMultipleFiles(uploadParameter);
					} catch (UploadException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}
}
