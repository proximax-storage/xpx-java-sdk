/*
 * 
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.*;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.local.LocalUploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.service.remote.RemoteTransactionAndAnnounceApi;
import io.nem.xpx.service.remote.RemoteUploadApi;
import io.nem.xpx.utils.JsonUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.*;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import org.pmw.tinylog.Logger;

import java.io.IOException;



/**
 * The Class Upload.
 */
public class Upload extends AbstractFacadeService {

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
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public Upload(PeerConnection peerConnection) {
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.uploadApi = peerConnection.getUploadApi();
		this.transactionAndAnnounceApi = peerConnection.getTransactionAndAnnounceApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		
	}

	/**
	 * Upload file.
	 *
	 * @param uploadParameter
	 *            the upload parameter
	 * @return the upload data
	 * @throws UploadException
	 *             the upload exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ApiException
	 *             the api exception
	 */
	public UploadResult uploadFile(UploadFileParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		UploadResult uploadData = handleFileUpload(uploadParameter);
		return uploadData;

	}

	/**
	 * Upload data.
	 *
	 * @param uploadParameter
	 *            the upload parameter
	 * @return the upload data
	 * @throws UploadException
	 *             the upload exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ApiException
	 *             the api exception
	 */
	public UploadResult uploadTextData(UploadDataParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		UploadResult uploadData = handleTextDataUpload(uploadParameter);
		return uploadData;
	}

	/**
	 * Upload a binary file.
	 *
	 * @param uploadParameter the upload parameter
	 * @return the upload data
	 * @throws UploadException the upload exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 */
	public UploadResult uploadBinary(UploadBinaryParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		UploadResult uploadData = handleBinaryUpload(uploadParameter);
		return uploadData;
	}

	/**
	 * Upload path.
	 *
	 * @param uploadParameter
	 *            the upload parameter
	 * @return the upload data
	 * @throws UploadException
	 *             the upload exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ApiException
	 *             the api exception
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public UploadResult uploadPath(UploadPathParameter uploadParameter)
			throws UploadException, IOException, ApiException, PeerConnectionNotFoundException {
		UploadResult uploadData = handlePathUpload(uploadParameter);
		return uploadData;
	}

	/**
	 * Handle data upload.
	 *
	 * @param uploadParameter
	 *            the upload parameter
	 * @return the upload data
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ApiException
	 *             the api exception
	 * @throws UploadException
	 *             the upload exception
	 */
	protected UploadResult handleTextDataUpload(UploadDataParameter uploadParameter)
			throws IOException, ApiException, UploadException {
		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		byte[] encrypted = null;
		Object response = null; // flat buffer object.
		ResourceHashMessage resourceMessageHash = null;
		
		try {

			// initialize the request parameter.
			UploadTextRequestParameter parameter = new UploadTextRequestParameter();
			parameter.setContentType(uploadParameter.getContentType());
			parameter.setEncoding(uploadParameter.getEncoding());
			parameter.setKeywords(uploadParameter.getKeywords());
			parameter.setMetadata(uploadParameter.getMetaData());
			parameter.setName(uploadParameter.getName());

			byte[] serializedData = uploadParameter.getData().getBytes(uploadParameter.getEncoding());
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine.createBlockCipher(
						new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()), engine),
						new KeyPair(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()), engine))
						.encrypt(serializedData);

				String encryptedData = Base64.encodeBase64String(encrypted);
				parameter.setText(encryptedData);
				response = uploadApi.uploadPlainTextUsingPOST(parameter);
			} else { // PLAIN

				String data = Base64.encodeBase64String(uploadParameter.getData().getBytes());
				parameter.setText(data);
				response = uploadApi.uploadPlainTextUsingPOST(parameter);
			}
			resourceMessageHash = byteToSerialObject((byte[]) response);
			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = JsonUtils.fromJson(transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature),DataResponse.class).getData();
			}
			safeAsyncToGateways(resourceMessageHash);
		} catch (Exception e) {
			Logger.error("Error on uploading text data: " + e.getMessage());
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}
		return new UploadResult(resourceMessageHash, publishedData);
	}

	/**
	 * Handle file upload.
	 *
	 * @param uploadParameter
	 *            the upload parameter
	 * @return the upload data
	 * @throws UploadException
	 *             the upload exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ApiException
	 *             the api exception
	 */
	protected UploadResult handleFileUpload(UploadFileParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;
		try {

			UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter();
			parameter.setContentType(uploadParameter.getContentType());
			parameter.setKeywords(uploadParameter.getKeywords());
			parameter.setMetadata(uploadParameter.getMetaData());
			parameter.setName(uploadParameter.getName());

			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine.createBlockCipher(
						new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()), engine),
						new KeyPair(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()), engine))
						.encrypt(FileUtils.readFileToByteArray(uploadParameter.getData()));

				byte[] data = Base64.encodeBase64(encrypted);
				parameter.setData(data);
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
			} else { // PLAIN
				byte[] data = Base64.encodeBase64(FileUtils.readFileToByteArray(uploadParameter.getData()));
				parameter.setData(data);
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
			}

			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = JsonUtils.fromJson(transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature),DataResponse.class).getData();
			}
			resourceMessageHash = byteToSerialObject((byte[]) response);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);
		} catch (Exception e) {
			Logger.error("Error on uploading file data: " + e.getMessage());
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}

		return new UploadResult(resourceMessageHash, publishedData);
	}

	/**
	 * Handle binary upload.
	 *
	 * @param uploadParameter the upload parameter
	 * @return the upload data
	 * @throws UploadException the upload exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 */
	protected UploadResult handleBinaryUpload(UploadBinaryParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;

		try {

			UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter();
			parameter.setContentType(uploadParameter.getContentType());
			parameter.setKeywords(uploadParameter.getKeywords());
			parameter.setMetadata(uploadParameter.getMetaData());
			parameter.setName(uploadParameter.getName());
			
			//	SECURE
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine.createBlockCipher(
						new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()), engine),
						new KeyPair(PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()), engine))
						.encrypt(uploadParameter.getData());

				byte[] data = Base64.encodeBase64(encrypted);
				parameter.setData(data);
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
			} else { // PLAIN
				byte[] data = Base64.encodeBase64(uploadParameter.getData());
				parameter.setData(data);
				response = uploadApi.uploadBytesBinaryUsingPOST(parameter);
			}

			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2)
						.amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = JsonUtils.fromJson(transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature),DataResponse.class).getData();
			}
			resourceMessageHash = byteToSerialObject((byte[]) response);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.error("Error on uploading binary data: " + e.getMessage());
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}
		return new UploadResult(resourceMessageHash, publishedData);
	}

	/**
	 * Handle path upload.
	 *
	 * @param uploadParameter
	 *            the upload parameter
	 * @return the upload data
	 * @throws UploadException
	 *             the upload exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ApiException
	 *             the api exception
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	// can only be called if the connection is local really.
	protected UploadResult handlePathUpload(UploadPathParameter uploadParameter)
			throws UploadException, IOException, ApiException, PeerConnectionNotFoundException {

		if (peerConnection instanceof RemotePeerConnection) {
			throw new PeerConnectionNotFoundException("Can't use RemotePeerConnection for Path upload");
		}

		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		Object response = null;
		ResourceHashMessage resourceMessageHash = null;
		try {

			response = ((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
					uploadParameter.getKeywords(), uploadParameter.getMetaData());

			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			}
			resourceMessageHash = byteToSerialObject((byte[]) response);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);
		} catch (Exception e) {
			Logger.error("Error on uploading path: " + e.getMessage());
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}
		return new UploadResult(resourceMessageHash, publishedData);
	}
	
}


