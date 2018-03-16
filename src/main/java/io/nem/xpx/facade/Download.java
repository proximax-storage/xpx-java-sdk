/*
 * 
 */
package io.nem.xpx.facade;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
import org.nem.core.utils.HexEncoder;
import io.nem.ApiException;
import io.nem.xpx.DownloadApiInterface;
import io.nem.xpx.LocalDownloadApi;
import io.nem.xpx.RemoteDownloadApi;
import io.nem.xpx.TransactionApi;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DownloadData;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.utils.CryptoUtils;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class Download.
 */
public class Download {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The download api. */
	private DownloadApiInterface downloadApi;

	/** The engine. */
	private CryptoEngine engine;

	public Download() {}

	/**
	 * Instantiates a new download.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 */
	public Download(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

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

	/**
	 * Download public data.
	 *
	 * @param nemHash
	 *            the nem hash
	 * @return the download data
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 * @throws IOException
	 */
	public DownloadData downloadPublicFileOrData(String nemHash)
			throws InterruptedException, ExecutionException, ApiException, IOException {

		DownloadData downloadData = new DownloadData();
		byte[] securedResponse = null;
		BinaryTransactionEncryptedMessage binaryEncryptedData = new BinaryTransactionEncryptedMessage();

		TransferTransaction transaction = (TransferTransaction) TransactionApi.getTransaction(nemHash).getEntity();

		binaryEncryptedData = JsonUtils.fromJson(new String(transaction.getMessage().getDecodedPayload()),
				BinaryTransactionEncryptedMessage.class);

		securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

		downloadData.setData(securedResponse);
		downloadData.setDataMessage(binaryEncryptedData);
		downloadData.setMessageType(MessageTypes.PLAIN);
		return downloadData;

	}

	/**
	 * Download data.
	 *
	 * @param nemHash
	 *            the nem hash
	 * @param senderOrReceiverPrivateKey
	 *            the sender or receiver private key
	 * @param senderOrReceiverPublicKey
	 *            the sender or receiver public key
	 * @return the download data
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws IOException
	 */
	public DownloadData downloadFileOrData(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		DownloadData downloadData = new DownloadData();
		byte[] securedResponse = null;
		SecureMessage message;
		BinaryTransactionEncryptedMessage binaryEncryptedData = new BinaryTransactionEncryptedMessage();

		// get the addresses
		// private key address
		PrivateKey privateKey = PrivateKey.fromHexString(senderOrReceiverPrivateKey);
		KeyPair keyPair = new KeyPair(privateKey);
		String senderOrReceiverPrivateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		// Evauate the transaction.
		TransferTransaction transaction = (TransferTransaction) TransactionApi.getTransaction(nemHash).getEntity();

		if (transaction.getMessage().getType() == 2) {
			if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

				message = SecureMessage.fromEncodedPayload(
						new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
						new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
						transaction.getMessage().getEncodedPayload());

				binaryEncryptedData = JsonUtils.fromJson(new String(message.getDecodedPayload()),
						BinaryTransactionEncryptedMessage.class);

				securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

				byte[] decrypted = engine
						.createBlockCipher(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine),
								new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine))
						.decrypt(HexEncoder.getBytes(new String(securedResponse)));

				downloadData.setData(decrypted);
				downloadData.setDataMessage(binaryEncryptedData);
				downloadData.setMessageType(MessageTypes.SECURE);
				return downloadData;
			} else if (transaction.getRecipient().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

				message = SecureMessage.fromEncodedPayload(
						new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
						new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
						transaction.getMessage().getEncodedPayload());

				binaryEncryptedData = JsonUtils.fromJson(new String(message.getDecodedPayload()),
						BinaryTransactionEncryptedMessage.class);

				securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

				byte[] decrypted = engine
						.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine),
								new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine))
						.decrypt(HexEncoder.getBytes(new String(securedResponse)));

				downloadData.setData(decrypted);
				downloadData.setDataMessage(binaryEncryptedData);
				downloadData.setMessageType(MessageTypes.SECURE);
				return downloadData;

			}

		} else if (transaction.getMessage().getType() == 1) {

			binaryEncryptedData = JsonUtils.fromJson(new String(transaction.getMessage().getDecodedPayload()),
					BinaryTransactionEncryptedMessage.class);

			securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

			downloadData.setData(securedResponse);
			downloadData.setDataMessage(binaryEncryptedData);
			downloadData.setMessageType(MessageTypes.PLAIN);
			return downloadData;
		}

		return downloadData;
	}

	public DownloadData downloadMultisigFileOrData(int messageType, String nemHash, String keySecret)
			throws ApiException, InterruptedException, ExecutionException, IOException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		DownloadData downloadData = new DownloadData();
		byte[] securedResponse = null;
		BinaryTransactionEncryptedMessage binaryEncryptedData = new BinaryTransactionEncryptedMessage();

	

		// Evauate the transaction.
		TransferTransaction transaction = (TransferTransaction) TransactionApi.getTransaction(nemHash).getEntity();
		
		
		if(transaction.getSignature() != null) {
			if(messageType == MessageTypes.SECURE) {
				byte[] decrypted = null;
				binaryEncryptedData = JsonUtils.fromJson(new String(transaction.getMessage().getDecodedPayload()),
						BinaryTransactionEncryptedMessage.class);
				securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());
				decrypted = CryptoUtils.decrypt(securedResponse, keySecret.toCharArray());
				//String encryptedData = HexEncoder.getString(decrypted);
				downloadData.setData(decrypted);
				downloadData.setDataMessage(binaryEncryptedData);
				downloadData.setMessageType(MessageTypes.SECURE);
				return downloadData;
			} else {
				byte[] decrypted = null;
				binaryEncryptedData = JsonUtils.fromJson(new String(transaction.getMessage().getDecodedPayload()),
						BinaryTransactionEncryptedMessage.class);
				//String encryptedData = HexEncoder.getString(decrypted);
				downloadData.setData(decrypted);
				downloadData.setDataMessage(binaryEncryptedData);
				downloadData.setMessageType(MessageTypes.PLAIN);
				return downloadData;
			}
		}
		return downloadData;
	}
}
