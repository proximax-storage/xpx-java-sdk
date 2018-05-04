/*
 *
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.CryptoUtils;
import org.apache.commons.codec.binary.Base64;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;



/**
 * The Class Download.
 */
public class DownloadAsync extends Download {


	/**
	 * Instantiates a new download.
	 *
	 * @param peerConnection
	 *            the peer connection
	 */
	public DownloadAsync(PeerConnection peerConnection) {
		super(peerConnection);

	}

	/**
	 * Download plain.
	 *
	 * @param nemHash the nem hash
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<DownloadResult> downloadPlain(String nemHash, ServiceAsyncCallback<DownloadResult> callback) {

		CompletableFuture<DownloadResult> downloadPlainAsync = CompletableFuture.supplyAsync(() -> {

			try {
				TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
				TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
						ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));
				byte[] securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

				return new DownloadResult(resourceMessage, Base64.decodeBase64(securedResponse), MessageTypes.PLAIN);

			} catch (ApiException | IOException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
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
	public CompletableFuture<DownloadResult> downloadFile(final String nemHash, String transferType,
														  ServiceAsyncCallback<DownloadResult> callback)
			{

		CompletableFuture<DownloadResult> downloadFileAsync = CompletableFuture.supplyAsync(() -> {
			try {

				TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
				TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
						ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));

				byte[] securedResponse = downloadApi.downloadFileUsingGET(nemHash, transferType);

				return new DownloadResult(resourceMessage, securedResponse, MessageTypes.PLAIN);

			} catch (ApiException | IOException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
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

			try {

				TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
				TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
						ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));

                byte[] securedResponse = downloadApi.downloadBinaryUsingGET(nemHash, transferType);

                return new DownloadResult(resourceMessage, securedResponse, MessageTypes.PLAIN);

            } catch (ApiException | IOException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
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
				transaction = (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();

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

                        return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);

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

                        return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);
					}

				} else if (transaction.getMessage().getType() == 1) {

					ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
							ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

					securedResponse = downloadApi.downloadTextUsingGET(nemHash, transferType);

                    return new DownloadResult(resourceMessage, securedResponse, MessageTypes.PLAIN);
				}
			} catch (InterruptedException | ExecutionException | ApiException | IOException e) {
				throw new CompletionException(e);
			}

            return new DownloadResult(null, null, 0);
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
				transaction = (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();

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

                        return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);

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

                        return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);
					}

				} else if (transaction.getMessage().getType() == 1) {

					ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
							ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

					securedResponse = downloadApi.downloadTextUsingGET(nemHash, transferType);

                    return new DownloadResult(resourceMessage, securedResponse, MessageTypes.PLAIN);
				}
			} catch (InterruptedException | ExecutionException | ApiException | IOException e) {
				throw new CompletionException(e);
			}

			return new DownloadResult(null, null, 0);
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
			byte[] securedResponse = null;

			try {
				// Evauate the transaction.
				TransferTransaction transaction = (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();

				if (transaction.getSignature() != null) {
					if (messageType == MessageTypes.SECURE) {
						byte[] decrypted = null;

						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

						securedResponse = downloadApi.downloadBinaryUsingGET(nemHash, "");
						decrypted = CryptoUtils.decrypt(securedResponse, keySecret.toCharArray());
						// String encryptedData = HexEncoder.getString(decrypted);

                        return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);

                    } else {
						byte[] decrypted = null;
						ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
								ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));
						// String encryptedData = HexEncoder.getString(decrypted);

                        return new DownloadResult(resourceMessage, decrypted, MessageTypes.PLAIN);
					}
				}
				return new DownloadResult(null, null, 0);
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
