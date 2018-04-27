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
import io.nem.ApiException;
import io.nem.xpx.builder.MultisigTransactionBuilder;
import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.MultisigUploadData;
import io.nem.xpx.model.MultisigUploadBinaryParameter;
import io.nem.xpx.model.MultisigUploadDataParameter;
import io.nem.xpx.model.MultisigUploadFileParameter;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadBytesBinaryRequestParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.UploadTextRequestParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.TransactionAndAnnounceApi;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.local.LocalDataHashApi;
import io.nem.xpx.service.local.LocalUploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.service.remote.RemoteDataHashApi;
import io.nem.xpx.service.remote.RemoteUploadApi;
import io.nem.xpx.utils.CryptoUtils;
import io.nem.xpx.utils.JsonUtils;



/**
 * The Class MultisigUpload.
 */
public class MultisigUpload  extends FacadeService {
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
	public MultisigUpload(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
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
	 * @param parameters the parameters
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
	public MultisigUploadData uploadDataOnMultisigTransaction(MultisigUploadDataParameter parameters) throws ApiException, NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigDataUpload(parameters);
		return multisigUploadData;
	}

	/**
	 * Upload file on multisig transaction.
	 *
	 * @param uploadParameter the upload parameter
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
	public MultisigUploadData uploadFileOnMultisigTransaction(MultisigUploadFileParameter uploadParameter)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigFileUpload(uploadParameter);
		return multisigUploadData;

	}
	
	/**
	 * Upload binary on multisig transaction.
	 *
	 * @param uploadParameter the upload parameter
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
	public MultisigUploadData uploadBinaryOnMultisigTransaction(MultisigUploadBinaryParameter uploadParameter)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadData multisigUploadData = handleMultisigBinaryUpload(uploadParameter);
		return multisigUploadData;

	}


	/**
	 * Handle multisig data upload.
	 *
	 * @param uploadParameter the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadData handleMultisigDataUpload(MultisigUploadDataParameter uploadParameter) throws IOException, ApiException, UploadException {
		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;

		try {
			
			UploadTextRequestParameter parameter = new UploadTextRequestParameter();
			parameter.setContentType(uploadParameter.getContentType());
			parameter.setEncoding(uploadParameter.getEncoding());
			parameter.setKeywords(uploadParameter.getKeywords());
			parameter.setMetadata(uploadParameter.getMetaData());
			parameter.setName(uploadParameter.getName());
			
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(uploadParameter.getData().getBytes(), keyRandom.toCharArray());
				String encryptedData = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
				parameter.setText(encryptedData);
				response = uploadApi.uploadPlainTextUsingPOST(parameter);
				multisigUploadData.setSecretKey(keyRandom);
			} else { // PLAIN
				String encryptedData = org.apache.commons.codec.binary.Base64.encodeBase64String(uploadParameter.getData().getBytes());
				parameter.setText(encryptedData);
				response = uploadApi.uploadPlainTextUsingPOST(parameter);
			}
			resourceMessageHash = byteToSerialObject((byte[])response);
			String publishedData = "";
			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.amount(Amount.fromNem(1l))
						.version(2)
						.message((byte[])response, uploadParameter.getMessageType()).buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.addMosaics(uploadParameter.getMosaics()).amount(Amount.fromNem(1l)).message((byte[])response, uploadParameter.getMessageType())
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
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
	 * @param uploadParameter the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadData handleMultisigFileUpload(MultisigUploadFileParameter uploadParameter) throws IOException, ApiException, UploadException {
		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;

		UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter();
		parameter.setContentType(uploadParameter.getContentType());
		parameter.setKeywords(uploadParameter.getKeywords());
		parameter.setMetadata(uploadParameter.getMetaData());
		parameter.setName(uploadParameter.getName());
		
		try {
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = org.apache.commons.codec.binary.Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(FileUtils.readFileToByteArray(uploadParameter.getData()), keyRandom.toCharArray());
				parameter.setData(encrypted);
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
				multisigUploadData.setSecretKey(keyRandom);
			} else { // PLAIN
				parameter.setData(FileUtils.readFileToByteArray(uploadParameter.getData()));
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
			}
			resourceMessageHash = byteToSerialObject((byte[])response);
			String publishedData = "";
			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.amount(Amount.fromNem(1l)).message((byte[])response, uploadParameter.getMessageType()).buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.addMosaics(uploadParameter.getMosaics()).amount(Amount.fromNem(1l)).message((byte[])response, uploadParameter.getMessageType())
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
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
	
	/**
	 * Handle multisig binary upload.
	 *
	 * @param uploadParameter the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadData handleMultisigBinaryUpload(MultisigUploadBinaryParameter uploadParameter) throws IOException, ApiException, UploadException {
		MultisigUploadData multisigUploadData = new MultisigUploadData();
		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;

		UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter();
		parameter.setContentType(uploadParameter.getContentType());
		parameter.setKeywords(uploadParameter.getKeywords());
		parameter.setMetadata(uploadParameter.getMetaData());
		parameter.setName(uploadParameter.getName());
		
		try {
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = org.apache.commons.codec.binary.Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(uploadParameter.getData(), keyRandom.toCharArray());
				parameter.setData(encrypted);
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
				multisigUploadData.setSecretKey(keyRandom);
			} else { // PLAIN
				parameter.setData(uploadParameter.getData());
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
			}
			resourceMessageHash = byteToSerialObject((byte[])response);
			String publishedData = "";
			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.amount(Amount.fromNem(1l)).message((byte[])response, uploadParameter.getMessageType()).buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = TransferTransactionBuilder
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.addMosaics(uploadParameter.getMosaics()).amount(Amount.fromNem(1l)).message((byte[])response, uploadParameter.getMessageType())
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
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

}
