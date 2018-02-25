package io.nem.xpx.wrap;

import java.io.File;
import java.io.IOException;
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
import org.nem.core.utils.HexEncoder;
import io.nem.ApiException;
import io.nem.builder.BinaryTransferTransactionBuilder;
import io.nem.utils.JsonUtils;
import io.nem.xpx.DataHashApi;
import io.nem.xpx.PublishAndAnnounceApi;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;
import io.nem.xpx.model.RequestAnnounceDataSignature;

public class Upload {

	private PeerConnection peerConnection;
	private CryptoEngine engine;
	private DataHashApi dataHashApi;
	private PublishAndAnnounceApi publishAndAnnounceApi;

	public Upload(PeerConnection peerConnection) {
		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		this.dataHashApi = new DataHashApi();
		this.publishAndAnnounceApi = new PublishAndAnnounceApi();
	}

	public UploadData uploadFile(int messageType, String senderPrivateKey, String recipientPublicKey, File file,
			String keywords, String metaData) throws ApiException, IOException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			
			encrypted = engine
					.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
							new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine))
					.encrypt(FileUtils.readFileToByteArray(file));
			
			String data = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(),keywords, metaData);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(),keywords, metaData);
		}

		// Announce The Signature
		RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), messageType).buildAndSignTransaction();
		
		
		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		uploadData.setDataMessage(response);
		uploadData.setNemHash(publishedData);
		return uploadData;

	}

	public UploadData uploadFile(int messageType, String senderPrivateKey, String recipientPublicKey, File file,
			String keywords, String metaData, Mosaic mosaic) throws ApiException, IOException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine
					.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
							new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine))
					.encrypt(FileUtils.readFileToByteArray(file));

			String data = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(),keywords, metaData);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(),keywords, metaData);
		}

		// Announce The Signature
		RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), messageType).addMosaic(mosaic).buildAndSignTransaction();
		
		
		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		uploadData.setDataMessage(response);
		uploadData.setNemHash(publishedData);
		return uploadData;

	}

	public UploadData uploadFile(int messageType, String senderPrivateKey, String recipientPublicKey, File file,
			String keywords, String metaData, Mosaic... mosaics) throws ApiException, IOException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine
					.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
							new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine))
					.encrypt(FileUtils.readFileToByteArray(file));

			String data = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(),keywords, metaData);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(),keywords, metaData);
		}

		// Announce The Signature
		RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), messageType).addMosaics(mosaics).buildAndSignTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		uploadData.setDataMessage(response);
		uploadData.setNemHash(publishedData);
		return uploadData;

	}

	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data, String name,
			String keywords, String metaData) throws ApiException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
					new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine)).encrypt(data.getBytes());

			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name,keywords, metaData);
		} else { // PLAIN

			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name,keywords, metaData);
		}

		// Announce The Signature
		RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), messageType).buildAndSignTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		uploadData.setDataMessage(response);
		uploadData.setNemHash(publishedData);
		return uploadData;

	}

	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,String name,
			String keywords, String metaData, Mosaic mosaic) throws ApiException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
					new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine)).encrypt(data.getBytes());

			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name,keywords, metaData);
		} else { // PLAIN

			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name,keywords, metaData);
		}

		// Announce The Signature
		RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), messageType).addMosaic(mosaic).buildAndSignTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		uploadData.setDataMessage(response);
		uploadData.setNemHash(publishedData);
		return uploadData;

	}

	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,String name,
			String keywords, String metaData, Mosaic... mosaics) throws ApiException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
					new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine)).encrypt(data.getBytes());

			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name,keywords, metaData);
		} else { // PLAIN

			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name,keywords, metaData);
		}

		// Announce The Signature
		RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), messageType).addMosaics(mosaics).buildAndSignTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		uploadData.setDataMessage(response);
		uploadData.setNemHash(publishedData);
		return uploadData;

	}

}
