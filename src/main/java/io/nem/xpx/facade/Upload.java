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
import io.nem.xpx.PublishAndAnnounceApi;
import io.nem.xpx.RemoteDataHashApi;
import io.nem.xpx.builder.BinaryTransferTransactionBuilder;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadException;
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
	private PublishAndAnnounceApi publishAndAnnounceApi;

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
		this.publishAndAnnounceApi = new PublishAndAnnounceApi();
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
	 * @return the upload data
	 * @throws ApiException
	 *             the api exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException
	 * @throws UploadException
	 */
	public UploadData uploadFile(int messageType, String senderPrivateKey, String recipientPublicKey, File file,
			String keywords, String metaData) throws ApiException, IOException, UploadException {

		UploadData uploadData = handleFileUpload(messageType, senderPrivateKey, recipientPublicKey, file, keywords,
				metaData, new Mosaic[0]);

		return uploadData;

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
	 * @param mosaic
	 *            the mosaic
	 * @return the upload data
	 * @throws ApiException
	 *             the api exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException
	 * @throws UploadException
	 */
	public UploadData uploadFile(int messageType, String senderPrivateKey, String recipientPublicKey, File file,
			String keywords, String metaData, Mosaic mosaic) throws ApiException, IOException, UploadException {

		UploadData uploadData = handleFileUpload(messageType, senderPrivateKey, recipientPublicKey, file, keywords,
				metaData, mosaic);

		return uploadData;

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
	public UploadData uploadFile(int messageType, String senderPrivateKey, String recipientPublicKey, File file,
			String keywords, String metaData, Mosaic... mosaics) throws UploadException, IOException, ApiException {
		UploadData uploadData = handleFileUpload(messageType, senderPrivateKey, recipientPublicKey, file, keywords,
				metaData, mosaics);

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
	 * @return the upload data
	 * @throws ApiException
	 *             the api exception
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws UploadException
	 */
	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,
			String name, String keywords, String metaData) throws UploadException, IOException, ApiException {

		UploadData uploadData = handleDataUpload(messageType, senderPrivateKey, recipientPublicKey, data, name,
				keywords, metaData, new Mosaic[0]);

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
	 * @param mosaic
	 *            the mosaic
	 * @return the upload data
	 * @throws ApiException
	 *             the api exception
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws UploadException
	 */
	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,
			String name, String keywords, String metaData, Mosaic mosaic)
			throws UploadException, IOException, ApiException {

		UploadData uploadData = handleDataUpload(messageType, senderPrivateKey, recipientPublicKey, data, name,
				keywords, metaData, mosaic);

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
	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,
			String name, String keywords, String metaData, Mosaic... mosaics)
			throws UploadException, IOException, ApiException {

		UploadData uploadData = handleDataUpload(messageType, senderPrivateKey, recipientPublicKey, data, name,
				keywords, metaData, mosaics);

		return uploadData;
	}

	private UploadData handleDataUpload(int messageType, String senderPrivateKey, String recipientPublicKey,
			String data, String name, String keywords, String metaData, Mosaic... mosaics)
			throws IOException, ApiException, UploadException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		try {
			if (messageType == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
								new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine))
						.encrypt(data.getBytes());

				String encryptedData = HexEncoder.getString(encrypted);
				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name, keywords,
						metaData);
			} else { // PLAIN

				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name, keywords, metaData);
			}

			String publishedData = "";
			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = BinaryTransferTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.amount(Amount.fromNem(1l)).message(JsonUtils.toJson(response), messageType).addMosaics(mosaics)
						.buildSignAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.amount(Amount.fromNem(1l)).message(JsonUtils.toJson(response), messageType).addMosaics(mosaics)
						.buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = publishAndAnnounceApi
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

	private UploadData handleFileUpload(int messageType, String senderPrivateKey, String recipientPublicKey, File file,
			String keywords, String metaData, Mosaic... mosaics) throws UploadException, IOException, ApiException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		try {
			if (messageType == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
								new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine))
						.encrypt(FileUtils.readFileToByteArray(file));

				String data = HexEncoder.getString(encrypted);
				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
						metaData);
			} else { // PLAIN
				String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
				response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
						metaData);
			}

			String publishedData = "";
			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = BinaryTransferTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.amount(Amount.fromNem(1l)).message(JsonUtils.toJson(response), messageType).addMosaics(mosaics)
						.buildSignAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.amount(Amount.fromNem(1l)).message(JsonUtils.toJson(response), messageType).addMosaics(mosaics)
						.buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = publishAndAnnounceApi
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
