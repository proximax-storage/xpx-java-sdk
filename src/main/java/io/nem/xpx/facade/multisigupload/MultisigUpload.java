package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.builder.MultisigTransactionBuilder;
import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadBytesBinaryRequestParameter;
import io.nem.xpx.model.UploadTextRequestParameter;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.CryptoUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.*;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;




/**
 * The Class MultisigUpload.
 */
public class MultisigUpload  extends AbstractFacadeService {
	/** The peer connection. */
	private final PeerConnection peerConnection;

	/** The engine. */
	private final CryptoEngine engine;

	/** The data hash api. */
	private final UploadApi uploadApi;

	/** The publish and announce api. */
	private final TransactionAndAnnounceApi transactionAndAnnounceApi;

	/** The is local peer connection. */
	private final boolean isLocalPeerConnection;

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection            the peer connection
	 */
	public MultisigUpload(PeerConnection peerConnection) {
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.peerConnection = peerConnection;
		this.uploadApi = peerConnection.getUploadApi();
		this.transactionAndAnnounceApi = peerConnection.getTransactionAndAnnounceApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.engine = CryptoEngines.ed25519Engine();
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
	public MultisigUploadResult uploadDataOnMultisigTransaction(MultisigUploadTextDataParameter parameters) throws ApiException, NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, UploadException {

		MultisigUploadResult multisigUploadData = handleMultisigDataUpload(parameters);
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
	public MultisigUploadResult uploadFileOnMultisigTransaction(MultisigUploadFileParameter uploadParameter)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadResult multisigUploadData = handleMultisigFileUpload(uploadParameter);
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
	public MultisigUploadResult uploadBinaryOnMultisigTransaction(MultisigUploadBinaryParameter uploadParameter)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadResult multisigUploadData = handleMultisigBinaryUpload(uploadParameter);
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
	public MultisigUploadResult handleMultisigDataUpload(MultisigUploadTextDataParameter uploadParameter) throws IOException, ApiException, UploadException {
		byte[] encrypted = null;
		byte[] response = null;
		ResourceHashMessage resourceMessageHash = null;
		String publishedData = "";
		String secretKey = null;

		try {
			
			UploadTextRequestParameter parameter = new UploadTextRequestParameter();
			parameter.setContentType(uploadParameter.getContentType());
			parameter.setEncoding(uploadParameter.getEncoding());
			parameter.setKeywords(uploadParameter.getKeywords());
			parameter.setMetadata(uploadParameter.getMetaDataAsString());
			parameter.setName(uploadParameter.getName());
			
			if (uploadParameter.getPrivacyStrategy().getNemMessageType() == NemMessageType.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(uploadParameter.getData().getBytes(), keyRandom.toCharArray());
				String encryptedData = org.apache.commons.codec.binary.Base64.encodeBase64String(encrypted);
				parameter.setText(encryptedData);
				response =  (byte[]) uploadApi.uploadPlainTextUsingPOST(parameter);
				secretKey = keyRandom;
			} else { // PLAIN
				String encryptedData = org.apache.commons.codec.binary.Base64.encodeBase64String(uploadParameter.getData().getBytes());
				parameter.setText(encryptedData);
				response = (byte[]) uploadApi.uploadPlainTextUsingPOST(parameter);
			}
			resourceMessageHash = deserializeResourceMessageHash(response);

			final Message nemMessage = uploadParameter.getPrivacyStrategy().encodeToMessage(response);
			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverPublicKey()))))
						.amount(Amount.fromNem(1l))
						.version(2)
						.message(nemMessage)
						.buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverPublicKey()))))
						.addMosaics(uploadParameter.getMosaics())
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

		} catch (Exception e) {

			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}finally {
			safeAsyncToGateways(resourceMessageHash);
		}

		return new MultisigUploadResult(new UploadResult(resourceMessageHash, publishedData), secretKey);
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
	public MultisigUploadResult handleMultisigFileUpload(MultisigUploadFileParameter uploadParameter) throws IOException, ApiException, UploadException {
		byte[] encrypted = null;
		byte[] response = null;
		ResourceHashMessage resourceMessageHash = null;
		String publishedData = "";
		String secretKey = null;

		UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter();
		parameter.setContentType(uploadParameter.getContentType());
		parameter.setKeywords(uploadParameter.getKeywords());
		parameter.setMetadata(uploadParameter.getMetaDataAsString());
		parameter.setName(uploadParameter.getName());
		
		try {
			if (uploadParameter.getPrivacyStrategy().getNemMessageType() == NemMessageType.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = org.apache.commons.codec.binary.Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(FileUtils.readFileToByteArray(uploadParameter.getFile()), keyRandom.toCharArray());
				parameter.setData(encrypted);
				response = (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);
				secretKey = keyRandom;
			} else { // PLAIN
				parameter.setData(FileUtils.readFileToByteArray(uploadParameter.getFile()));
				response = (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);
			}
			resourceMessageHash = deserializeResourceMessageHash(response);

			final Message nemMessage = uploadParameter.getPrivacyStrategy().encodeToMessage(response);

			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverPublicKey()))))
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverPublicKey()))))
						.addMosaics(uploadParameter.getMosaics())
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

		} catch (Exception e) {

			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}

		return new MultisigUploadResult(new UploadResult(resourceMessageHash, publishedData), secretKey);
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
	public MultisigUploadResult handleMultisigBinaryUpload(MultisigUploadBinaryParameter uploadParameter) throws IOException, ApiException, UploadException {
		byte[] encrypted = null;
		byte[] response = null;
		ResourceHashMessage resourceMessageHash = null;
		String publishedData = "";
		String secretKey = null;

		UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter();
		parameter.setContentType(uploadParameter.getContentType());
		parameter.setKeywords(uploadParameter.getKeywords());
		parameter.setMetadata(uploadParameter.getMetaDataAsString());
		parameter.setName(uploadParameter.getName());
		
		try {
			if (uploadParameter.getPrivacyStrategy().getNemMessageType() == NemMessageType.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = org.apache.commons.codec.binary.Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(uploadParameter.getData(), keyRandom.toCharArray());
				parameter.setData(encrypted);
				response = (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);
				secretKey = keyRandom;
			} else { // PLAIN
				parameter.setData(uploadParameter.getData());
				response = (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);
			}
			resourceMessageHash = deserializeResourceMessageHash(response);

			final Message nemMessage = uploadParameter.getPrivacyStrategy().encodeToMessage(response);

			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverPublicKey()))))
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.otherTransaction(transaction)
						.buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(uploadParameter.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(uploadParameter.getReceiverPublicKey()))))
						.addMosaics(uploadParameter.getMosaics())
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

		} catch (Exception e) {

			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}

		return new MultisigUploadResult(new UploadResult(resourceMessageHash, publishedData), secretKey);
	}

}
