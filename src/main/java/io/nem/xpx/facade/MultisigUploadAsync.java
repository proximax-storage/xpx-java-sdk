package io.nem.xpx.facade;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import io.nem.ApiException;
import io.nem.xpx.builder.MultisigTransactionBuilder;
import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.MultisigUploadResult;
import io.nem.xpx.model.MultisigUploadBinaryParameter;
import io.nem.xpx.model.MultisigUploadDataParameter;
import io.nem.xpx.model.MultisigUploadFileParameter;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.service.remote.RemoteDataHashApi;
import io.nem.xpx.service.remote.RemoteUploadApi;
import io.nem.xpx.utils.CryptoUtils;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class MultisigUpload.
 */
public class MultisigUploadAsync extends MultisigUpload {

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public MultisigUploadAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
		super(peerConnection);
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}
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
	public CompletableFuture<MultisigUploadResult> uploadDataOnMultisigTransaction(MultisigUploadDataParameter parameters,
			ServiceAsyncCallback<MultisigUploadResult> callback) throws ApiException, NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, UploadException {

		CompletableFuture<MultisigUploadResult> multisigUploadDataAsync = CompletableFuture.supplyAsync(() -> {
			MultisigUploadResult multisigUploadData = null;
			try {
				multisigUploadData = handleMultisigDataUpload(parameters);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return multisigUploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});
		return multisigUploadDataAsync;

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
			ServiceAsyncCallback<MultisigUploadResult> callback) throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, UploadException {

		CompletableFuture<MultisigUploadResult> multisigUploadFileAsync = CompletableFuture.supplyAsync(() -> {
			MultisigUploadResult multisigUploadData = null;
			try {
				multisigUploadData = handleMultisigFileUpload(parameters);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return multisigUploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});
		return multisigUploadFileAsync;

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
			MultisigUploadBinaryParameter parameters, ServiceAsyncCallback<MultisigUploadResult> callback) throws IOException, ApiException,
			InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		CompletableFuture<MultisigUploadResult> multisigUploadBinaryAsync = CompletableFuture.supplyAsync(() -> {
			MultisigUploadResult multisigUploadData = null;
			try {
				multisigUploadData = handleMultisigBinaryUpload(parameters);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return multisigUploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});
		return multisigUploadBinaryAsync;


	}

}
