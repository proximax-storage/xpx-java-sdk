/*
 *
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.InsufficientAmountException;
import io.nem.xpx.exceptions.PathUploadNotSupportedException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.*;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.local.LocalUploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.JsonUtils;
import io.nem.xpx.utils.MessageEncryptUtils;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;


/**
 * The Class Upload.
 */
public class Upload extends AbstractFacadeService {

	/** The peer connection. */
	private final PeerConnection peerConnection;

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
			throws UploadException {
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
			throws UploadException {
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
			throws UploadException {
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
			throws UploadException, PeerConnectionNotFoundException {
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
			throws UploadException {

		try {
			byte[] data = uploadParameter.getData().getBytes(uploadParameter.getEncoding());
			String encryptedData = encryptToString(uploadParameter, data);

			UploadTextRequestParameter apiParams = new UploadTextRequestParameter()
					.contentType(uploadParameter.getContentType())
					.encoding(uploadParameter.getEncoding())
					.keywords(uploadParameter.getKeywords())
					.metadata(uploadParameter.getMetaData())
					.name(uploadParameter.getName())
					.text(encryptedData);

			byte[] response = (byte[]) uploadApi.uploadPlainTextUsingPOST(apiParams);

			return handlePostUpload(uploadParameter, response);

		} catch (Exception e) {
			Logger.error("Error on uploading text data: " + e.getMessage());
			throw new UploadException(e);
		}
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
			throws UploadException {

		try {

			byte[] data = FileUtils.readFileToByteArray(uploadParameter.getData());
			byte[] encryptedData = encryptToByte(uploadParameter, data);

			UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter()
					.contentType(uploadParameter.getContentType())
					.keywords(uploadParameter.getKeywords())
					.metadata(uploadParameter.getMetaData())
					.name(uploadParameter.getName())
					.data(encryptedData);

			byte[] response = (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);

			return handlePostUpload(uploadParameter, response);

		} catch (Exception e) {
			Logger.error("Error on uploading file data: " + e.getMessage());
			throw new UploadException(e);
		}
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
			throws UploadException {

		try {
			byte[] encryptedData = encryptToByte(uploadParameter, uploadParameter.getData());

			UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter()
					.contentType(uploadParameter.getContentType())
					.keywords(uploadParameter.getKeywords())
					.metadata(uploadParameter.getMetaData())
					.name(uploadParameter.getName())
					.data(encryptedData);

			byte[] response = (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);

			return handlePostUpload(uploadParameter, response);

		} catch (Exception e) {
			Logger.error("Error on uploading binary data: " + e.getMessage());
			throw new UploadException(e);
		}
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
	protected UploadResult handlePathUpload(UploadPathParameter uploadParameter) throws UploadException {

		if (peerConnection instanceof RemotePeerConnection) {
			throw new PathUploadNotSupportedException("Path upload is not supported for remote peer connection");
		}

		try {
			byte[] response = (byte[])((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
					uploadParameter.getKeywords(), uploadParameter.getMetaData());

			return handlePostUpload(uploadParameter, response);
		} catch (Exception e) {
			Logger.error("Error on uploading path: " + e.getMessage());
			throw new UploadException(e);
		}
	}

	private UploadResult handlePostUpload(DataParameter parameter, byte[] response) throws Exception {

		ResourceHashMessage resourceMessageHash = null;
		try {
			resourceMessageHash = byteToSerialObject(response);
			String publishedData = publish(parameter, response);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);

			return new UploadResult(resourceMessageHash, publishedData);
		} catch (Exception e) {
			if (resourceMessageHash != null)
				uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw e;
		}
	}

	private String encryptToString(DataParameter parameter, byte[] messageData) throws UnsupportedEncodingException {
		return MessageEncryptUtils.encryptToString(parameter.getMessageType(), messageData,
				parameter.getSenderOrReceiverPrivateKey(), parameter.getSenderOrReceiverPrivateKey());
	}

	private byte[] encryptToByte(DataParameter parameter, byte[] messageData) throws UnsupportedEncodingException {
		return MessageEncryptUtils.encryptToByte(parameter.getMessageType(), messageData,
				parameter.getSenderOrReceiverPrivateKey(), parameter.getSenderOrReceiverPrivateKey());
	}

	private String publish(DataParameter parameter, byte[] response)
			throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException {

		if (this.isLocalPeerConnection) {
			// Announce The Signature
			NemAnnounceResult announceResult = TransferTransactionBuilder
					.peerConnection(peerConnection)
					.sender(new Account(
							new KeyPair(PrivateKey.fromHexString(parameter.getSenderOrReceiverPrivateKey()))))
					.recipient(new Account(Address.fromPublicKey(
							PublicKey.fromHexString(parameter.getReceiverOrSenderPublicKey()))))
					.version(2).amount(Amount.fromNem(1l))
					.message(response, parameter.getMessageType())
					.addMosaics(parameter.getMosaics()).buildAndSendTransaction();

			return announceResult.getTransactionHash().toString();

		} else {

			// Announce The Signature
			RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
					.peerConnection(peerConnection)
					.sender(new Account(
							new KeyPair(PrivateKey.fromHexString(parameter.getSenderOrReceiverPrivateKey()))))
					.recipient(new Account(Address.fromPublicKey(
							PublicKey.fromHexString(parameter.getReceiverOrSenderPublicKey()))))
					.version(2).amount(Amount.fromNem(1l))
					.message(response, parameter.getMessageType())
					.addMosaics(parameter.getMosaics()).buildAndSignTransaction();

			// Return the NEM Txn Hash
			return JsonUtils.fromJson(transactionAndAnnounceApi
					.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature),DataResponse.class).getData();

		}
	}
}


