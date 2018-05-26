/*
 *
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PathUploadNotSupportedException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.MultiFileUploadResult.FileUploadResult;
import io.nem.xpx.model.UploadBytesBinaryRequestParameter;
import io.nem.xpx.model.UploadTextRequestParameter;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.local.LocalUploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.utils.ContentTypeUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.String.format;


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
	 */
	public UploadResult uploadFile(UploadFileParameter uploadParameter)
			throws UploadException {

		try {
			byte[] data = FileUtils.readFileToByteArray(uploadParameter.getFile());
			return handleBinaryUpload(uploadParameter, data);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException(format("Error on uploading file data: %s", uploadParameter.getFile().getAbsolutePath()), e);
		}
	}

	/**
	 * Upload data.
	 *
	 * @param uploadParameter
	 *            the upload parameter
	 * @return the upload data
	 * @throws UploadException
	 *             the upload exception
	 */
	public UploadResult uploadTextData(UploadTextDataParameter uploadParameter)
			throws UploadException {
		try {
			final byte[] data = uploadParameter.getData().getBytes(uploadParameter.getEncoding());
			final byte[] dataWithPrivacy = uploadParameter.getPrivacyStrategy().encrypt(data);
			final String encryptedData = Base64.encodeBase64String(dataWithPrivacy);

			UploadTextRequestParameter apiParams = new UploadTextRequestParameter()
					.contentType(uploadParameter.getContentType())
					.encoding(uploadParameter.getEncoding())
					.keywords(uploadParameter.getKeywords())
					.metadata(uploadParameter.getMetaData())
					.name(uploadParameter.getName())
					.text(encryptedData);

			final byte[] response = (byte[]) uploadApi.uploadPlainTextUsingPOST(apiParams);

			return handlePostUpload(uploadParameter.getPrivacyStrategy(), uploadParameter.getSenderOrReceiverPrivateKey(),
					uploadParameter.getReceiverOrSenderPublicKey(), uploadParameter.getMosaics(), response);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException(format("Error on uploading text data: %s", uploadParameter.getData()), e);
		}
	}

	/**
	 * Upload a binary file.
	 *
	 * @param uploadParameter the upload parameter
	 * @return the upload data
	 * @throws UploadException the upload exception
	 */
	public UploadResult uploadBinary(UploadBinaryParameter uploadParameter)
			throws UploadException {
		try {
			return handleBinaryUpload(uploadParameter, uploadParameter.getData());
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException("Error on uploading binary data", e);
		}
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
	 */
	public UploadResult uploadPath(UploadPathParameter uploadParameter)
			throws UploadException {
		if (peerConnection instanceof RemotePeerConnection) {
			throw new PathUploadNotSupportedException("Path upload is not supported for remote peer connection");
		}

		try {
			byte[] response = (byte[])((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
					uploadParameter.getKeywords(), uploadParameter.getMetaData());

			return handlePostUpload(uploadParameter.getPrivacyStrategy(), uploadParameter.getSenderOrReceiverPrivateKey(),
					uploadParameter.getReceiverOrSenderPublicKey(), uploadParameter.getMosaics(), response);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException(format("Error on uploading path: %s", uploadParameter.getPath()), e);
		}
	}

	public UploadResult uploadFilesAsZip(UploadFilesAsZipParameter uploadParameter)
			throws UploadException {

		try {
			byte[] data = zipFiles(uploadParameter.getFiles());
			return handleBinaryUpload(uploadParameter, data);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException("Error on uploading files as zip", e);
		}
	}

	public MultiFileUploadResult uploadMultipleFiles(UploadMultipleFilesParameter param)
			throws UploadException {

        if (param.getFiles().size() == 0)
            throw new UploadException("No file to upload");

        List<FileUploadResult> fileUploadResults = param.getFiles()
                .parallelStream()
                .map(file -> {
                    try {
                        byte[] data = FileUtils.readFileToByteArray(file);
                        return new FileUploadResult(file,
								handleBinaryUpload(param.getPrivacyStrategy(), param.getSenderOrReceiverPrivateKey(),
										param.getReceiverOrSenderPublicKey(), ContentTypeUtils.detectContentType(data),
										param.getKeywords(), param.getMetaData(), file.getName(), param.getMosaics(), data));
                    } catch (Exception e) {
                        return new FileUploadResult(file, new UploadException(format("Error on uploading file data: %s", file.getAbsolutePath()), e));
                    }
                }).collect(Collectors.toList());
        return new MultiFileUploadResult(fileUploadResults);
	}

	private UploadResult handleBinaryUpload(AbstractUploadParameter param, byte[] data)
			throws UploadException {
		return handleBinaryUpload(param.getPrivacyStrategy(), param.getSenderOrReceiverPrivateKey(), param.getReceiverOrSenderPublicKey(),
				param.getContentType(), param.getKeywords(), param.getMetaData(), param.getName(), param.getMosaics(), data);
	}

	private UploadResult handleBinaryUpload(PrivacyStrategy privacyStrategy, String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey,
											String contentType, String keywords, String metadata, String name, Mosaic[] mosaics,
											byte[] data) throws UploadException {
		try {
			byte[] dataWithPrivacy = privacyStrategy.encrypt(data);
			byte[] encryptedData = Base64.encodeBase64(dataWithPrivacy);

			UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter()
					.contentType(contentType)
					.keywords(keywords)
					.metadata(metadata)
					.name(name)
					.data(encryptedData);

			byte[] response = (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);

			return handlePostUpload(privacyStrategy, senderOrReceiverPrivateKey, receiverOrSenderPublicKey, mosaics, response);

		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException("Error on uploading binary data", e);

		}
	}


	private byte[] zipFiles(List<File> files) throws UploadException {

		validateZipFilesArguments(files);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ZipOutputStream zos = new ZipOutputStream(baos)) {

			for (File file : files) {
				ZipEntry entry = new ZipEntry(file.getName());

				zos.putNextEntry(entry);
				zos.write(FileUtils.readFileToByteArray(file));
				zos.closeEntry();
			};

		} catch (IOException e) {
			throw new UploadException(format("Unable to zip files together: %s", String.join(",",
					files.stream().map(file -> file.getAbsolutePath()).collect(Collectors.toList()))), e);
		}
		return baos.toByteArray();
	}

	private void validateZipFilesArguments(List<File> files) throws UploadException {
		if (files.size() == 0)
			throw new UploadException("No file to upload");

		long uniqueFileNameCount = files.stream().map(file -> file.getName()).distinct().count();
		if (uniqueFileNameCount < files.size())
			throw new UploadException(format("File names should be unique to zip files together: %s",
					String.join(",", files.stream().map(file -> file.getName()).collect(Collectors.toList()))));
	}

	private UploadResult handlePostUpload(PrivacyStrategy privacyStrategy, String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey,
										  Mosaic[] mosaics, byte[] response) throws Exception {

		ResourceHashMessage resourceMessageHash = null;
		try {
			resourceMessageHash = byteToSerialObject(response);

			final Message nemMessage = privacyStrategy.encodeToMessage(response);
			String publishedData = publish(nemMessage, senderOrReceiverPrivateKey, receiverOrSenderPublicKey, mosaics);


			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);

			return new UploadResult(resourceMessageHash, publishedData);
		} catch (Exception e) {
			if (resourceMessageHash != null) {
				final String resourceHash = resourceMessageHash.hash();
				Executors.newSingleThreadExecutor().submit(() -> uploadApi.cleanupPinnedContentUsingPOST(resourceHash));
			}
			throw e;
		}
	}

	private String publish(Message nemMessage, String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey,
						   Mosaic[] mosaics) throws Exception {

//		if (this.isLocalPeerConnection) {
			// Announce The Signature
		NemAnnounceResult announceResult = TransferTransactionBuilder
				.peerConnection(peerConnection)
				.sender(new Account(
						new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(
						PublicKey.fromHexString(receiverOrSenderPublicKey))))
				.version(2)
				.amount(Amount.fromNem(1l))
				.message(nemMessage)
				.addMosaics(mosaics).buildAndSendTransaction();

		if (announceResult.getCode() == NemAnnounceResult.CODE_SUCCESS)
			return announceResult.getTransactionHash().toString();
		else
			throw new UploadException(
					format("Announcement of Nem transaction failed: %s", announceResult.getMessage()));

//		} else {
//
//			// Announce The Signature
//			RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
//					.peerConnection(peerConnection)
//					.sender(new Account(
//							new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey))))
//					.recipient(new Account(Address.fromPublicKey(
//							PublicKey.fromHexString(receiverOrSenderPublicKey))))
//					.version(2)
//					.amount(Amount.fromNem(1l))
//					.message(nemMessage)
//					.addMosaics(mosaics).buildAndSignTransaction();
//
//			// Return the NEM Txn Hash
//			return JsonUtils.fromJson(transactionAndAnnounceApi
//					.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature),DataResponse.class).getData();
//
//		}
	}
}


