package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.upload.UploadException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class MultisigUploadAsync extends AbstractAsyncFacadeService {

	private MultisigUpload multisigUpload;

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public MultisigUploadAsync(PeerConnection peerConnection) {
		this.multisigUpload = new MultisigUpload(peerConnection);
	}

	/**
	 * Upload data on multisig transaction.
	 *
	 * @param parameters the parameters
	 * @param callback the callback
	 * @return the completable future
	 * @throws ApiException the api exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UploadException the upload exception
	 */
	public CompletableFuture<MultisigUploadResult> uploadDataOnMultisigTransaction(MultisigUploadTextDataParameter parameters,
			ServiceAsyncCallback<MultisigUploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return multisigUpload.uploadDataOnMultisigTransaction(parameters);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Upload file on multisig transaction.
	 *
	 * @param parameters the parameters
	 * @param callback the callback
	 * @return the completable future
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UploadException the upload exception
	 */
	public CompletableFuture<MultisigUploadResult> uploadFileOnMultisigTransaction(MultisigUploadFileParameter parameters,
			ServiceAsyncCallback<MultisigUploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return multisigUpload.uploadFileOnMultisigTransaction(parameters);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Upload binary on multisig transaction.
	 *
	 * @param parameters the parameters
	 * @param callback the callback
	 * @return the completable future
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UploadException the upload exception
	 */
	public CompletableFuture<MultisigUploadResult> uploadBinaryOnMultisigTransaction(
			MultisigUploadBinaryParameter parameters, ServiceAsyncCallback<MultisigUploadResult> callback) {

		return runAsync(
				() -> {
					try {
						return multisigUpload.uploadBinaryOnMultisigTransaction(parameters);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);

	}

}
