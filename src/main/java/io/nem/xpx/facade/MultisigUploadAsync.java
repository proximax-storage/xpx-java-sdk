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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

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
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import org.nem.core.utils.HexEncoder;
import io.nem.api.ApiException;
import io.nem.xpx.LocalDataHashApi;
import io.nem.xpx.LocalUploadApi;
import io.nem.xpx.RemoteDataHashApi;
import io.nem.xpx.RemoteUploadApi;
import io.nem.xpx.TransactionAndAnnounceApi;
import io.nem.xpx.builder.MultisigTransactionBuilder;
import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.MultisigUploadData;
import io.nem.xpx.intf.UploadApi;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.CryptoUtils;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class MultisigUpload.
 */
public class MultisigUploadAsync {
	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;

	/** The data hash api. */
	private UploadApi uploadApi;

	/** The publish and announce api. */
	private TransactionAndAnnounceApi transactionAndAnnounceApi;

	/** The is local peer connection. */
	private boolean isLocalPeerConnection = false;

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection            the peer connection
	 * @throws PeerConnectionNotFoundException the peer connection not found exception
	 */
	public MultisigUploadAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.uploadApi = new RemoteUploadApi();
		} else {
			this.isLocalPeerConnection = true;
			this.uploadApi = new LocalUploadApi();
		}

		this.engine = CryptoEngines.ed25519Engine();
		this.transactionAndAnnounceApi = new TransactionAndAnnounceApi();
	}

	/**
	 * Upload data on multisig transaction.
	 *
	 * @param messageType            the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey            the sender private key
	 * @param recipientPublicKey            the recipient public key
	 * @param data            the data
	 * @param name            the name
	 * @param keywords            the keywords
	 * @param metaData            the meta data
	 * @return the upload data
	 * @throws ApiException             the api exception
	 * @throws NoSuchAlgorithmException             the no such algorithm exception
	 * @throws InvalidKeyException             the invalid key exception
	 * @throws InvalidKeySpecException             the invalid key spec exception
	 * @throws NoSuchPaddingException             the no such padding exception
	 * @throws InvalidAlgorithmParameterException             the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException             the illegal block size exception
	 * @throws BadPaddingException             the bad padding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadData uploadDataOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, String data, String name, String keywords,
			String metaData) throws ApiException, NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigDataUpload(messageType, multisigPublicKey,
				senderPrivateKey, recipientPublicKey, data, name, keywords, metaData, new Mosaic[0]);
		return multisigUploadData;
	}

	/**
	 * Upload data on multisig transaction.
	 *
	 * @param messageType the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param data the data
	 * @param name the name
	 * @param keywords the keywords
	 * @param metaData the meta data
	 * @param mosaic the mosaic
	 * @return the multisig upload data
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
	public MultisigUploadData uploadDataOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, String data, String name, String keywords,
			String metaData, Mosaic mosaic) throws ApiException, NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigDataUpload(messageType, multisigPublicKey,
				senderPrivateKey, recipientPublicKey, data, name, keywords, metaData, mosaic);
		return multisigUploadData;

	}

	/**
	 * Upload data on multisig transaction.
	 *
	 * @param messageType the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param data the data
	 * @param name the name
	 * @param keywords the keywords
	 * @param metaData the meta data
	 * @param mosaics the mosaics
	 * @return the multisig upload data
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
	public MultisigUploadData uploadDataOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, String data, String name, String keywords,
			String metaData, Mosaic... mosaics) throws ApiException, NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigDataUpload(messageType, multisigPublicKey,
				senderPrivateKey, recipientPublicKey, data, name, keywords, metaData, mosaics);
		return multisigUploadData;

	}

	/**
	 * Upload file on multisig transaction.
	 *
	 * @param messageType            the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey            the sender private key
	 * @param recipientPublicKey            the recipient public key
	 * @param file            the file
	 * @param keywords            the keywords
	 * @param metaData            the meta data
	 * @return the upload data
	 * @throws IOException             Signals that an I/O exception has occurred.
	 * @throws ApiException             the api exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadData uploadFileOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, File file, String keywords, String metaData)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigFileUpload(messageType, multisigPublicKey,
				senderPrivateKey, recipientPublicKey, file, keywords, metaData);
		return multisigUploadData;

	}

	/**
	 * Upload file on multisig transaction.
	 *
	 * @param messageType the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param file the file
	 * @param keywords the keywords
	 * @param metaData the meta data
	 * @param mosaic the mosaic
	 * @return the multisig upload data
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
	public MultisigUploadData uploadFileOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, File file, String keywords, String metaData,
			Mosaic mosaic)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigFileUpload(messageType, multisigPublicKey,
				senderPrivateKey, recipientPublicKey, file, keywords, metaData, mosaic);
		return multisigUploadData;

	}

	/**
	 * Upload file on multisig transaction.
	 *
	 * @param messageType the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param file the file
	 * @param keywords the keywords
	 * @param metaData the meta data
	 * @param mosaics the mosaics
	 * @return the multisig upload data
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
	public MultisigUploadData uploadFileOnMultisigTransaction(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, File file, String keywords, String metaData,
			Mosaic... mosaics)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UploadException {

		MultisigUploadData multisigUploadData = handleMultisigFileUpload(messageType, multisigPublicKey,
				senderPrivateKey, recipientPublicKey, file, keywords, metaData, mosaics);
		return multisigUploadData;

	}

	/**
	 * Handle multisig data upload.
	 *
	 * @param messageType the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param data the data
	 * @param name the name
	 * @param keywords the keywords
	 * @param metaData the meta data
	 * @param mosaics the mosaics
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadData handleMultisigDataUpload(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, String data, String name, String keywords,
			String metaData, Mosaic... mosaics) throws IOException, ApiException, UploadException {
		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;

		try {
			if (messageType == MessageTypes.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(data.getBytes(), keyRandom.toCharArray());
				String encryptedData = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
				response = uploadApi.uploadPlainTextUsingPOST(encryptedData, null, name, keywords,
						metaData);
				multisigUploadData.setSecretKey(keyRandom);
			} else { // PLAIN
				response = uploadApi.uploadPlainTextUsingPOST(data, null, name, keywords, metaData);
			}
			resourceMessageHash = byteToSerialObject((byte[])response);
			String publishedData = "";
			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.amount(Amount.fromNem(1l))
						.message((byte[])response, messageType).buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.addMosaics(mosaics).amount(Amount.fromNem(1l)).message((byte[])response, messageType)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

			multisigUploadData.getUploadData().setDataMessage(resourceMessageHash);
			multisigUploadData.getUploadData().setNemHash(publishedData);
		} catch (Exception e) {

			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}finally {
			safeAsyncToGateways(resourceMessageHash);
		}
		return multisigUploadData;
	}

	/**
	 * Handle multisig file upload.
	 *
	 * @param messageType the message type
	 * @param multisigPublicKey the multisig public key
	 * @param senderPrivateKey the sender private key
	 * @param recipientPublicKey the recipient public key
	 * @param file the file
	 * @param keywords the keywords
	 * @param metaData the meta data
	 * @param mosaics the mosaics
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadData handleMultisigFileUpload(int messageType, String multisigPublicKey,
			String senderPrivateKey, String recipientPublicKey, File file, String keywords, String metaData,
			Mosaic... mosaics) throws IOException, ApiException, UploadException {
		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;

		try {
			if (messageType == MessageTypes.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = org.apache.commons.codec.binary.Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(FileUtils.readFileToByteArray(file), keyRandom.toCharArray());
				response = uploadApi.uploadBytesBinaryUsingPOST(encrypted, null,file.getName(),
						keywords, metaData);
				multisigUploadData.setSecretKey(keyRandom);
			} else { // PLAIN
				response = uploadApi.uploadBytesBinaryUsingPOST(FileUtils.readFileToByteArray(file), null,file.getName(), keywords,
						metaData);
			}
			resourceMessageHash = byteToSerialObject((byte[])response);
			String publishedData = "";
			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.amount(Amount.fromNem(1l)).message((byte[])response, messageType).buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(multisigPublicKey))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(recipientPublicKey))))
						.addMosaics(mosaics).amount(Amount.fromNem(1l)).message((byte[])response, messageType)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

			multisigUploadData.getUploadData().setDataMessage(resourceMessageHash);
			multisigUploadData.getUploadData().setNemHash(publishedData);
		} catch (Exception e) {

			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}

		return multisigUploadData;
	}
	
	private ResourceHashMessage byteToSerialObject(byte[] object) {
		ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(object)));
		return resourceMessage;
	}
	
	private void safeAsyncToGateways(ResourceHashMessage resource) {
		Runnable task = () -> {
		    for(String s: XpxSdkGlobalConstants.GLOBAL_GATEWAYS) {
		    	HttpURLConnection conn = null;
		    	try {
		    		conn = (HttpURLConnection)new URL(s + "/ipfs/" + resource.hash()).openConnection();
		    		System.out.println(s + "/ipfs/" + resource.hash());
		    		conn.setRequestProperty("Content-Type", 
		    			        "application/x-www-form-urlencoded");
		    		conn.setRequestMethod("GET");
		    		conn.setDoOutput(true);
		    		conn.setUseCaches(false);
		    		DataOutputStream wr = new DataOutputStream (
		    				conn.getOutputStream());
		    		wr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					conn.disconnect();
				}
		    }
		};
		task.run();
	}

}
