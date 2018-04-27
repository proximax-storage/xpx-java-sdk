/*
 * 
 */
package io.nem.xpx.facade;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
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
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import io.nem.ApiException;
import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadBytesBinaryRequestParameter;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.UploadPathParameter;
import io.nem.xpx.model.UploadTextRequestParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.TransactionAndAnnounceApi;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.local.LocalUploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.service.remote.RemoteUploadApi;


/**
 * The Class Upload.
 */
public class Upload extends FacadeService {

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
	 */
	public Upload() {
	}

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public Upload(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.uploadApi = new RemoteUploadApi();
		} else {
			this.isLocalPeerConnection = true;
			this.uploadApi = new LocalUploadApi();
		}

		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		this.transactionAndAnnounceApi = new TransactionAndAnnounceApi();
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
	public UploadData uploadFile(UploadFileParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		UploadData uploadData = handleFileUpload(uploadParameter);
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
	public UploadData uploadTextData(UploadDataParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		UploadData uploadData = handleTextDataUpload(uploadParameter);
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
	public UploadData uploadBinary(UploadBinaryParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		UploadData uploadData = handleBinaryUpload(uploadParameter);
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
	public UploadData uploadPath(UploadPathParameter uploadParameter)
			throws UploadException, IOException, ApiException, PeerConnectionNotFoundException {
		UploadData uploadData = handlePathUpload(uploadParameter);
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
	protected UploadData handleTextDataUpload(UploadDataParameter uploadParameter)
			throws IOException, ApiException, UploadException {
		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		UploadData uploadData = new UploadData();
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

			byte[] serializedData = uploadParameter.getData().getBytes();
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
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

			uploadData.setDataMessage(resourceMessageHash);
			uploadData.setNemHash(publishedData);
		} catch (Exception e) {
			e.printStackTrace();
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		} finally {
			safeAsyncToGateways(resourceMessageHash);
		}
		return uploadData;
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
	protected UploadData handleFileUpload(UploadFileParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		UploadData uploadData = new UploadData();
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
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}
			resourceMessageHash = byteToSerialObject((byte[]) response);
			uploadData.setDataMessage(resourceMessageHash);
			uploadData.setNemHash(publishedData);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);
		} catch (Exception e) {
			e.printStackTrace();
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}
		return uploadData;
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
	protected UploadData handleBinaryUpload(UploadBinaryParameter uploadParameter)
			throws UploadException, IOException, ApiException {
		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		UploadData uploadData = new UploadData();
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
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderOrReceiverPrivateKey()))))
						.recipient(new Account(Address.fromPublicKey(
								PublicKey.fromHexString(uploadParameter.getReceiverOrSenderPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSignTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}
			resourceMessageHash = byteToSerialObject((byte[]) response);
			uploadData.setDataMessage(resourceMessageHash);
			uploadData.setNemHash(publishedData);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);
		} catch (Exception e) {
			e.printStackTrace();
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}
		return uploadData;
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
	protected UploadData handlePathUpload(UploadPathParameter uploadParameter)
			throws UploadException, IOException, ApiException, PeerConnectionNotFoundException {

		if (peerConnection instanceof RemotePeerConnection) {
			throw new PeerConnectionNotFoundException("Can't use RemotePeerConnection for Path upload");
		}

		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		UploadData uploadData = new UploadData();
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;
		try {

			response = ((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
					uploadParameter.getKeywords(), uploadParameter.getMetaData());

			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
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
			uploadData.setDataMessage(resourceMessageHash);
			uploadData.setNemHash(publishedData);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);
		} catch (Exception e) {
			e.printStackTrace();
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		}
		return uploadData;
	}

}
