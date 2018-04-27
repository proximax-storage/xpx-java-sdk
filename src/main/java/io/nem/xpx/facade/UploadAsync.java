/*
 * 
 */
package io.nem.xpx.facade;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Future;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import io.nem.ApiException;
import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.UploadPathParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.TransactionAndAnnounceApi;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.local.LocalUploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.service.remote.RemoteUploadApi;


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
	public Future<UploadData> uploadFile(UploadFileParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {
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
	public Future<UploadData> uploadTextData(UploadDataParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {
		CompletableFuture<UploadData> uploadDataAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handleTextDataUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return uploadData;
		}).thenApply(n -> {
			// call the callback?
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
	public Future<UploadData> uploadBinary(UploadBinaryParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {

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
	public Future<UploadData> uploadPath(UploadPathParameter uploadParameter, ServiceAsyncCallback<UploadData> callback) {

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
