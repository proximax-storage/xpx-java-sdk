/*
 * 
 */
package io.nem.xpx.facade;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import io.nem.ApiException;
import io.nem.xpx.callback.DownloadCallback;
import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DownloadResult;
import io.nem.xpx.facade.model.UploadResult;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.DownloadApi;
import io.nem.xpx.service.local.LocalDownloadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.service.remote.RemoteDownloadApi;
import io.nem.xpx.utils.CryptoUtils;


/**
 * The Class Download.
 */
public class DownloadAsync extends Download {


	/**
	 * Instantiates a new download.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public DownloadAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
		super(peerConnection);

	}

	/**
	 * Download plain.
	 *
	 * @param nemHash the nem hash
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadResult> downloadPlain(String nemHash, ServiceAsyncCallback<DownloadResult> callback)
			 {

		CompletableFuture<DownloadResult> downloadPlainAsync = CompletableFuture.supplyAsync(() -> {
			DownloadResult downloadData = new DownloadResult();
			byte[] securedResponse = null;

			try {

				TransactionMetaDataPair transactionMetaDataPair = NemTransactionApi.getTransaction(nemHash);
				TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
						ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));
				securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());
				downloadData.setData(Base64.decodeBase64(securedResponse));
				downloadData.setDataMessage(resourceMessage);
				downloadData.setMessageType(MessageTypes.PLAIN);
			} catch (ApiException | IOException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}

			return downloadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return downloadPlainAsync;

	}

	/**
	 * Download file.
	 *
	 * @param nemHash the nem hash
	 * @param transferType the transfer type
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadResult> downloadFile(String nemHash, String transferType, ServiceAsyncCallback<DownloadResult> callback)
			{

		CompletableFuture<DownloadResult> downloadFileAsync = CompletableFuture.supplyAsync(() -> {
			DownloadResult downloadData = new DownloadResult();
			byte[] securedResponse = null;

			try {

				TransactionMetaDataPair transactionMetaDataPair = NemTransactionApi.getTransaction(nemHash);
				TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
						ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));

				securedResponse = downloadApi.downloadFileUsingGET(nemHash, transferType);
				downloadData.setData(securedResponse);
				downloadData.setDataMessage(resourceMessage);
				downloadData.setMessageType(MessageTypes.PLAIN);
			} catch (ApiException | IOException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}

			return downloadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return downloadFileAsync;

	}

	/**
	 * Download binary.
	 *
	 * @param nemHash the nem hash
	 * @param transferType the transfer type
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadResult> downloadBinary(String nemHash, String transferType,
			ServiceAsyncCallback<DownloadResult> callback) {

		CompletableFuture<DownloadResult> downloadBinaryAsync = CompletableFuture.supplyAsync(() -> {

			DownloadResult downloadData = new DownloadResult();
			byte[] securedResponse = null;

			try {

				TransactionMetaDataPair transactionMetaDataPair = NemTransactionApi.getTransaction(nemHash);
				TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
						ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));

				securedResponse = downloadApi.downloadBinaryUsingGET(nemHash, transferType);
				downloadData.setData(securedResponse);
				downloadData.setDataMessage(resourceMessage);
				downloadData.setMessageType(MessageTypes.PLAIN);
			} catch (ApiException | IOException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}

			return downloadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return downloadBinaryAsync;

	}

	/**
	 * Download secure binary or file.
	 *
	 * @param nemHash the nem hash
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @param callback the callback
	 * @return the completable future
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CompletableFuture<DownloadResult> downloadSecureBinaryOrFile(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey, ServiceAsyncCallback<DownloadResult> callback)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		return downloadSecureBinaryOrFile(nemHash, "bytes", senderOrReceiverPrivateKey, senderOrReceiverPublicKey,
				callback);
	}

	/**
	 * Download secure text data.
	 *
	 * @param nemHash the nem hash
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @param callback the callback
	 * @return the completable future
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public CompletableFuture<DownloadResult> downloadSecureTextData(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey, ServiceAsyncCallback<DownloadResult> callback)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		return downloadSecureTextData(nemHash, "bytes", senderOrReceiverPrivateKey, senderOrReceiverPublicKey,
				callback);
	}

	/**
	 * Download secure binary or file.
	 *
	 * @param nemHash the nem hash
	 * @param transferType the transfer type
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadResult> downloadSecureBinaryOrFile(String nemHash, String transferType,
			String senderOrReceiverPrivateKey, String senderOrReceiverPublicKey, ServiceAsyncCallback<DownloadResult> callback) {

		CompletableFuture<DownloadResult> downloadBinaryOrFileAsync = CompletableFuture.supplyAsync(() -> {

			DownloadResult downloadData = new DownloadResult();
			byte[] securedResponse = null;
			SecureMessage message;
			// BinaryTransactionEncryptedMessage binaryEncryptedData = new
			// BinaryTransactionEncryptedMessage();

			// get the addresses
			// private key address
			PrivateKey privateKey = PrivateKey.fromHexString(senderOrReceiverPrivateKey);
			KeyPair keyPair = new KeyPair(privateKey);
			String senderOrReceiverPrivateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

			// Evaluate the transaction.
			TransferTransaction transaction;
			try {
				transaction = (TransferTransaction) NemTransactionApi.getTransaction(nemHash).getEntity();

				if (transaction.getMessage().getType() == 2) {
					if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

						message = SecureMessage.fromEncodedPayload(
								new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
								new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
								transaction.getMessage().getEncodedPayload());

						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));
						securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

						byte[] decrypted = engine
								.createBlockCipher(
										new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine),
										new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine))
								.decrypt(securedResponse);

						downloadData.setData(decrypted);
						downloadData.setDataMessage(resourceMessage);
						downloadData.setMessageType(MessageTypes.SECURE);
						return downloadData;
					} else if (transaction.getRecipient().getAddress().getEncoded()
							.equals(senderOrReceiverPrivateKeyAddress)) {

						message = SecureMessage.fromEncodedPayload(
								new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
								new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
								transaction.getMessage().getEncodedPayload());

						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));

						securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

						byte[] decrypted = engine
								.createBlockCipher(
										new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine),
										new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine))
								.decrypt(securedResponse);
						// byte[] decoded = Base64.decodeBase64(decrypted);
						downloadData.setData(decrypted);
						downloadData.setDataMessage(resourceMessage);
						downloadData.setMessageType(MessageTypes.SECURE);
						return downloadData;

					}

				} else if (transaction.getMessage().getType() == 1) {

					ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
							ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

					securedResponse = downloadApi.downloadTextUsingGET(nemHash, transferType);

					downloadData.setData(securedResponse);
					downloadData.setDataMessage(resourceMessage);
					downloadData.setMessageType(MessageTypes.PLAIN);
					return downloadData;
				}
			} catch (InterruptedException | ExecutionException | ApiException | IOException e) {
				throw new CompletionException(e);
			}

			return downloadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return downloadBinaryOrFileAsync;

	}

	/**
	 * Download secure text data.
	 *
	 * @param nemHash the nem hash
	 * @param transferType the transfer type
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadResult> downloadSecureTextData(String nemHash, String transferType,
			String senderOrReceiverPrivateKey, String senderOrReceiverPublicKey, ServiceAsyncCallback<DownloadResult> callback) {

		CompletableFuture<DownloadResult> downloadSecureTextDataAsync = CompletableFuture.supplyAsync(() -> {
			DownloadResult downloadData = new DownloadResult();
			byte[] securedResponse = null;
			SecureMessage message;
			// get the addresses
			// private key address
			PrivateKey privateKey = PrivateKey.fromHexString(senderOrReceiverPrivateKey);
			KeyPair keyPair = new KeyPair(privateKey);
			String senderOrReceiverPrivateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

			// Evaluate the transaction.
			TransferTransaction transaction;
			try {
				transaction = (TransferTransaction) NemTransactionApi.getTransaction(nemHash).getEntity();

				if (transaction.getMessage().getType() == 2) {
					if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

						message = SecureMessage.fromEncodedPayload(
								new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
								new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
								transaction.getMessage().getEncodedPayload());

						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));
						securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

						byte[] decrypted = engine
								.createBlockCipher(
										new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine),
										new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine))
								.decrypt(Base64.decodeBase64(securedResponse));

						downloadData.setData(decrypted);
						downloadData.setDataMessage(resourceMessage);
						downloadData.setMessageType(MessageTypes.SECURE);
						return downloadData;
					} else if (transaction.getRecipient().getAddress().getEncoded()
							.equals(senderOrReceiverPrivateKeyAddress)) {

						message = SecureMessage.fromEncodedPayload(
								new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
								new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
								transaction.getMessage().getEncodedPayload());

						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));

						securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

						byte[] decrypted = engine
								.createBlockCipher(
										new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine),
										new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine))
								.decrypt(Base64.decodeBase64(securedResponse));
						downloadData.setData(decrypted);
						downloadData.setDataMessage(resourceMessage);
						downloadData.setMessageType(MessageTypes.SECURE);
						return downloadData;

					}

				} else if (transaction.getMessage().getType() == 1) {

					ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
							ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

					securedResponse = downloadApi.downloadTextUsingGET(nemHash, transferType);

					downloadData.setData(securedResponse);
					downloadData.setDataMessage(resourceMessage);
					downloadData.setMessageType(MessageTypes.PLAIN);
					return downloadData;
				}
			} catch (InterruptedException | ExecutionException | ApiException | IOException e) {
				throw new CompletionException(e);
			}

			return downloadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return downloadSecureTextDataAsync;

	}

	/**
	 * Download multisig file or data.
	 *
	 * @param messageType the message type
	 * @param nemHash the nem hash
	 * @param keySecret the key secret
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadResult> downloadMultisigFileOrData(int messageType, String nemHash, String keySecret, ServiceAsyncCallback<DownloadResult> callback) {
		
		CompletableFuture<DownloadResult> downloadPlainAsync = CompletableFuture.supplyAsync(() -> {
			DownloadResult downloadData = new DownloadResult();
			byte[] securedResponse = null;

			try {


				// Evauate the transaction.
				TransferTransaction transaction = (TransferTransaction) NemTransactionApi.getTransaction(nemHash).getEntity();

				if (transaction.getSignature() != null) {
					if (messageType == MessageTypes.SECURE) {
						byte[] decrypted = null;

						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

						securedResponse = downloadApi.downloadBinaryUsingGET(nemHash, "");
						decrypted = CryptoUtils.decrypt(securedResponse, keySecret.toCharArray());
						// String encryptedData = HexEncoder.getString(decrypted);
						downloadData.setData(decrypted);
						downloadData.setDataMessage(resourceMessage);
						downloadData.setMessageType(MessageTypes.SECURE);
						return downloadData;
					} else {
						byte[] decrypted = null;
						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));
						// String encryptedData = HexEncoder.getString(decrypted);
						downloadData.setData(decrypted);
						downloadData.setDataMessage(resourceMessage);
						downloadData.setMessageType(MessageTypes.PLAIN);
						return downloadData;
					}
				}
				return downloadData;
			} catch (ApiException | IOException | InterruptedException | ExecutionException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | InvalidKeySpecException | NoSuchAlgorithmException | NoSuchPaddingException e) {
				throw new CompletionException(e);
			}

		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return downloadPlainAsync;
		
		
	}
}
