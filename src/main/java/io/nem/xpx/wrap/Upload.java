/*
 * 
 */
package io.nem.xpx.wrap;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

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
import io.nem.builder.BinaryTransferTransaction;
import io.nem.builder.BinaryTransferTransactionBuilder;
import io.nem.builder.MultisigTransactionBuilder;
import io.nem.utils.CryptoUtils;
import io.nem.utils.JsonUtils;
import io.nem.xpx.DataHashApi;
import io.nem.xpx.PublishAndAnnounceApi;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;
import io.nem.xpx.model.RequestAnnounceDataSignature;

/**
 * The Class Upload.
 */
public class Upload {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;

	/** The data hash api. */
	private DataHashApi dataHashApi;

	/** The publish and announce api. */
	private PublishAndAnnounceApi publishAndAnnounceApi;

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 */
	public Upload(PeerConnection peerConnection) {
		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		this.dataHashApi = new DataHashApi();
		this.publishAndAnnounceApi = new PublishAndAnnounceApi();
	}

	/**
	 * Upload data on multisig transaction.
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
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws InvalidKeyException
	 *             the invalid key exception
	 * @throws InvalidKeySpecException
	 *             the invalid key spec exception
	 * @throws NoSuchPaddingException
	 *             the no such padding exception
	 * @throws InvalidAlgorithmParameterException
	 *             the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException
	 *             the illegal block size exception
	 * @throws BadPaddingException
	 *             the bad padding exception
	 */
	public MultisigUploadData uploadDataOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, String data, String name, String keywords,
			String metaData)
			throws ApiException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			String keyRandom = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());

			encrypted = CryptoUtils.encrypt(data.getBytes(), keyRandom.toCharArray());
			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name, keywords, metaData);
			multisigUploadData.setSecretKey(keyRandom);
		} else { // PLAIN
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name, keywords, metaData);
		}

		// Announce The Signature.
		BinaryTransferTransaction transaction = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), MessageTypes.PLAIN).buildTransaction();

		// multisig builder.
		RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.otherTransaction(transaction).buildAndSendMultisigTransaction();

		// Return the NEM Txn Hash.
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		multisigUploadData.getUploadData().setDataMessage(response);
		multisigUploadData.getUploadData().setNemHash(publishedData);

		return multisigUploadData;

	}

	public MultisigUploadData uploadDataOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, String data, String name, String keywords,
			String metaData, Mosaic mosaic)
			throws ApiException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			String keyRandom = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());

			encrypted = CryptoUtils.encrypt(data.getBytes(), keyRandom.toCharArray());
			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name, keywords, metaData);
			multisigUploadData.setSecretKey(keyRandom);
		} else { // PLAIN
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name, keywords, metaData);
		}

		// Announce The Signature.
		BinaryTransferTransaction transaction = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.addMosaic(mosaic).message(JsonUtils.toJson(response), MessageTypes.PLAIN).buildTransaction();

		// multisig builder.
		RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.otherTransaction(transaction).buildAndSendMultisigTransaction();

		// Return the NEM Txn Hash.
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		multisigUploadData.getUploadData().setDataMessage(response);
		multisigUploadData.getUploadData().setNemHash(publishedData);

		return multisigUploadData;

	}

	public MultisigUploadData uploadDataOnMultisigTransaction(int messageType, String multisigPublicKey, String senderPrivateKey,
			String recipientPublicKey, String data, String name, String keywords, String metaData, Mosaic... mosaics)
			throws ApiException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			String keyRandom = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());

			encrypted = CryptoUtils.encrypt(data.getBytes(), keyRandom.toCharArray());
			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name, keywords, metaData);
			multisigUploadData.setSecretKey(keyRandom);
		} else { // PLAIN
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name, keywords, metaData);
		}

		// Announce The Signature.
		BinaryTransferTransaction transaction = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.addMosaics(mosaics).message(JsonUtils.toJson(response), MessageTypes.PLAIN).buildTransaction();

		// multisig builder.
		RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.otherTransaction(transaction).buildAndSendMultisigTransaction();

		// Return the NEM Txn Hash.
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		multisigUploadData.getUploadData().setDataMessage(response);
		multisigUploadData.getUploadData().setNemHash(publishedData);

		return multisigUploadData;

	}

	/**
	 * Upload file on multisig transaction.
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
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ApiException
	 *             the api exception
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 */
	public MultisigUploadData uploadFileOnMultisigTransaction(int messageType,  String multisigPublicKey, String senderPrivateKey,
			String recipientPublicKey, File file, String keywords, String metaData)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			String keyRandom = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());

			encrypted = CryptoUtils.encrypt(FileUtils.readFileToByteArray(file), keyRandom.toCharArray());
			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, file.getName(), keywords,
					metaData);
			multisigUploadData.setSecretKey(keyRandom);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
		}

		// Announce The Signature
		BinaryTransferTransaction transaction = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), MessageTypes.PLAIN).buildTransaction();

		RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.otherTransaction(transaction).buildAndSendMultisigTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		multisigUploadData.getUploadData().setDataMessage(response);
		multisigUploadData.getUploadData().setNemHash(publishedData);
		return multisigUploadData;

	}

	public MultisigUploadData uploadFileOnMultisigTransaction(int messageType, String multisigPublicKey,  String senderPrivateKey,
			String recipientPublicKey, File file, String keywords, String metaData, Mosaic mosaic)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			String keyRandom = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());

			encrypted = CryptoUtils.encrypt(FileUtils.readFileToByteArray(file), keyRandom.toCharArray());
			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, file.getName(), keywords,
					metaData);
			multisigUploadData.setSecretKey(keyRandom);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
		}

		// Announce The Signature
		BinaryTransferTransaction transaction = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.addMosaic(mosaic).message(JsonUtils.toJson(response), MessageTypes.PLAIN).buildTransaction();

		RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.otherTransaction(transaction).buildAndSendMultisigTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		multisigUploadData.getUploadData().setDataMessage(response);
		multisigUploadData.getUploadData().setNemHash(publishedData);
		return multisigUploadData;

	}

	public MultisigUploadData uploadFileOnMultisigTransaction(int messageType, String multisigPublicKey,String senderPrivateKey,
			String recipientPublicKey, File file, String keywords, String metaData, Mosaic... mosaics)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			String keyRandom = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());

			encrypted = CryptoUtils.encrypt(FileUtils.readFileToByteArray(file), keyRandom.toCharArray());
			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, file.getName(), keywords,
					metaData);
			multisigUploadData.setSecretKey(keyRandom);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
		}

		// Announce The Signature
		BinaryTransferTransaction transaction = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.addMosaics(mosaics).message(JsonUtils.toJson(response), MessageTypes.PLAIN).buildTransaction();

		RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.otherTransaction(transaction).buildAndSendMultisigTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		multisigUploadData.getUploadData().setDataMessage(response);
		multisigUploadData.getUploadData().setNemHash(publishedData);
		return multisigUploadData;

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
	 */
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
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
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
	 */
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
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
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
	 */
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
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
		} else { // PLAIN
			String data = HexEncoder.getString(FileUtils.readFileToByteArray(file));
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, file.getName(), keywords,
					metaData);
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
	 */
	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,
			String name, String keywords, String metaData) throws ApiException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
					new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine)).encrypt(data.getBytes());

			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name, keywords, metaData);
		} else { // PLAIN

			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name, keywords, metaData);
		}

		// Announce The Signature
		RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
				.message(JsonUtils.toJson(response), messageType)
				.buildAndSignTransaction();

		// Return the NEM Txn Hash
		String publishedData = publishAndAnnounceApi
				.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
		uploadData.setDataMessage(response);
		uploadData.setNemHash(publishedData);
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
	 */
	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,
			String name, String keywords, String metaData, Mosaic mosaic) throws ApiException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
					new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine)).encrypt(data.getBytes());

			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name, keywords, metaData);
		} else { // PLAIN

			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name, keywords, metaData);
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
	 */
	public UploadData uploadData(int messageType, String senderPrivateKey, String recipientPublicKey, String data,
			String name, String keywords, String metaData, Mosaic... mosaics) throws ApiException {
		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		BinaryTransactionEncryptedMessage response = null;
		if (messageType == MessageTypes.SECURE) {
			encrypted = engine.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderPrivateKey), engine),
					new KeyPair(PublicKey.fromHexString(recipientPublicKey), engine)).encrypt(data.getBytes());

			String encryptedData = HexEncoder.getString(encrypted);
			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(encryptedData, name, keywords, metaData);
		} else { // PLAIN

			response = dataHashApi.generateHashAndExposeDataToNetworkUsingPOST(data, name, keywords, metaData);
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