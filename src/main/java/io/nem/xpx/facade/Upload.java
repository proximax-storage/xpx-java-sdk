/*
 * 
 */
package io.nem.xpx.facade;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import org.nem.core.utils.HexEncoder;
import io.nem.ApiException;
import io.nem.xpx.DataHashApiInterface;
import io.nem.xpx.LocalDataHashApi;
import io.nem.xpx.RemoteDataHashApi;
import io.nem.xpx.TransactionAndAnnounceApi;
import io.nem.xpx.builder.BinaryTransferTransactionBuilder;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class Upload.
 */
public class Upload {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;

	/** The data hash api. */
	private DataHashApiInterface dataHashApi;

	/** The publish and announce api. */
	private TransactionAndAnnounceApi transactionAndAnnounceApi;

	private boolean isLocalPeerConnection = false;

	public Upload() {
	}

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 */
	public Upload(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.dataHashApi = new RemoteDataHashApi();
		} else {
			this.isLocalPeerConnection = true;
			this.dataHashApi = new LocalDataHashApi();
		}

		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		this.transactionAndAnnounceApi = new TransactionAndAnnounceApi();
	}

	/**
	 * Upload file.
	 *
	 * @param messageType
	 *            the message type
	 * @param senderPrivateKey
	 *            the sender private key
	 * @param recipientPublicKey
	 *            the recipient public key
	 * @param file
	 *            the file
	 * @param keywords
	 *            the keywords
	 * @param metaData
	 *            the meta data
	 * @param mosaics
	 *            the mosaics
	 * @return the upload data
	 * @throws ApiException
	 *             the api exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException
	 * @throws UploadException
	 */
	public UploadData uploadFile(UploadFileParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		UploadData uploadData = handleFileUpload(uploadParameter);

		return uploadData;

	}

	/**
	 * Upload data.
	 *
	 * @param messageType
	 *            the message type
	 * @param senderPrivateKey
	 *            the sender private key
	 * @param recipientPublicKey
	 *            the recipient public key
	 * @param data
	 *            the data
	 * @param name
	 *            the name
	 * @param keywords
	 *            the keywords
	 * @param metaData
	 *            the meta data
	 * @param mosaics
	 *            the mosaics
	 * @return the upload data
	 * @throws ApiException
	 *             the api exception
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws UploadException
	 */
	public UploadData uploadData(UploadDataParameter uploadParameter)
			throws UploadException, IOException, ApiException {

		UploadData uploadData = handleDataUpload(uploadParameter);

		return uploadData;
	}

	private UploadData handleDataUpload(UploadDataParameter uploadParameter)
			throws IOException, ApiException, UploadException {

		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		try {
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()), engine),
								new KeyPair(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()), engine))
						.encrypt(uploadParameter.getData().getBytes());

				String encryptedData = HexEncoder.getString(encrypted);
				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData,
						uploadParameter.getName(), uploadParameter.getKeywords(), uploadParameter.getMetaData());
			} else { // PLAIN

				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(uploadParameter.getData(),
						uploadParameter.getName(), uploadParameter.getKeywords(), uploadParameter.getMetaData());
			}

			String publishedData = "";
			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = BinaryTransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message(JsonUtils.toJson(response), uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildSignAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message(JsonUtils.toJson(response), uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

			uploadData.setDataMessage(response);
			uploadData.setNemHash(publishedData);
		} catch (Exception e) {
			dataHashApi.cleanupPinnedContentUsingPOST(response.getHash());
			throw new UploadException(e);
		}
		return uploadData;
	}

	private UploadData handleFileUpload(UploadFileParameter uploadParameter)
			throws UploadException, IOException, ApiException {

		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		try {
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()), engine),
								new KeyPair(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()), engine))
						.encrypt(FileUtils.readFileToByteArray(uploadParameter.getData()));

				String data = HexEncoder.getString(encrypted);
				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data,
						uploadParameter.getData().getName(), uploadParameter.getKeywords(),
						uploadParameter.getMetaData());
			} else { // PLAIN
				String data = HexEncoder.getString(FileUtils.readFileToByteArray(uploadParameter.getData()));
				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data,
						uploadParameter.getData().getName(), uploadParameter.getKeywords(),
						uploadParameter.getMetaData());
			}

			String publishedData = "";
			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = BinaryTransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message(JsonUtils.toJson(response), uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildSignAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message(JsonUtils.toJson(response), uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}
			uploadData.setDataMessage(response);
			uploadData.setNemHash(publishedData);
		} catch (Exception e) {
			dataHashApi.cleanupPinnedContentUsingPOST(response.getHash());
			throw new UploadException(e);
		}
		return uploadData;
	}

}
