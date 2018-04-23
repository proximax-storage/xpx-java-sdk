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

import io.nem.api.ApiException;
import io.nem.xpx.LocalDownloadApi;
import io.nem.xpx.RemoteDownloadApi;
import io.nem.xpx.TransactionApi;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DownloadData;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.intf.DownloadApi;
import io.nem.xpx.model.DownloadCallback;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.CryptoUtils;

/**
 * The Class Download.
 */
public class DownloadAsync {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The download api. */
	private DownloadApi downloadApi;

	/** The engine. */
	private CryptoEngine engine;

	/**
	 * Instantiates a new download.
	 */
	public DownloadAsync() {
	}

	/**
	 * Instantiates a new download.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public DownloadAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.downloadApi = new RemoteDownloadApi();
		} else {
			this.downloadApi = new LocalDownloadApi();
		}

		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();

	}

	public CompletableFuture<DownloadData> downloadPlain(String nemHash, DownloadCallback callback)
			 {

		CompletableFuture<DownloadData> downloadPlainAsync = CompletableFuture.supplyAsync(() -> {
			DownloadData downloadData = new DownloadData();
			byte[] securedResponse = null;

			try {

				TransactionMetaDataPair transactionMetaDataPair = TransactionApi.getTransaction(nemHash);
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

	public CompletableFuture<DownloadData> downloadFile(String nemHash, String transferType, DownloadCallback callback)
			{

		CompletableFuture<DownloadData> downloadFileAsync = CompletableFuture.supplyAsync(() -> {
			DownloadData downloadData = new DownloadData();
			byte[] securedResponse = null;

			try {

				TransactionMetaDataPair transactionMetaDataPair = TransactionApi.getTransaction(nemHash);
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

	public CompletableFuture<DownloadData> downloadBinary(String nemHash, String transferType,
			DownloadCallback callback) {

		CompletableFuture<DownloadData> downloadBinaryAsync = CompletableFuture.supplyAsync(() -> {

			DownloadData downloadData = new DownloadData();
			byte[] securedResponse = null;

			try {

				TransactionMetaDataPair transactionMetaDataPair = TransactionApi.getTransaction(nemHash);
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

	public CompletableFuture<DownloadData> downloadSecureBinaryOrFile(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey, DownloadCallback callback)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		return downloadSecureBinaryOrFile(nemHash, "bytes", senderOrReceiverPrivateKey, senderOrReceiverPublicKey,
				callback);
	}

	public CompletableFuture<DownloadData> downloadSecureTextData(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey, DownloadCallback callback)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		return downloadSecureTextData(nemHash, "bytes", senderOrReceiverPrivateKey, senderOrReceiverPublicKey,
				callback);
	}

	public CompletableFuture<DownloadData> downloadSecureBinaryOrFile(String nemHash, String transferType,
			String senderOrReceiverPrivateKey, String senderOrReceiverPublicKey, DownloadCallback callback) {

		CompletableFuture<DownloadData> downloadBinaryOrFileAsync = CompletableFuture.supplyAsync(() -> {

			DownloadData downloadData = new DownloadData();
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
				transaction = (TransferTransaction) TransactionApi.getTransaction(nemHash).getEntity();

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

	public CompletableFuture<DownloadData> downloadSecureTextData(String nemHash, String transferType,
			String senderOrReceiverPrivateKey, String senderOrReceiverPublicKey, DownloadCallback callback) {

		CompletableFuture<DownloadData> downloadSecureTextDataAsync = CompletableFuture.supplyAsync(() -> {
			DownloadData downloadData = new DownloadData();
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
				transaction = (TransferTransaction) TransactionApi.getTransaction(nemHash).getEntity();

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

	public CompletableFuture<DownloadData> downloadMultisigFileOrData(int messageType, String nemHash, String keySecret, DownloadCallback callback) {
		
		CompletableFuture<DownloadData> downloadPlainAsync = CompletableFuture.supplyAsync(() -> {
			DownloadData downloadData = new DownloadData();
			byte[] securedResponse = null;

			try {


				// Evauate the transaction.
				TransferTransaction transaction = (TransferTransaction) TransactionApi.getTransaction(nemHash).getEntity();

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
