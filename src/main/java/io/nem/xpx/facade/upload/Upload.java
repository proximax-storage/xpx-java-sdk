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
import java.security.NoSuchAlgorithmException;
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

	/** The announceNemTransaction and announce api. */
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
	 * @param param
	 *            the upload parameter
	 * @return the upload data
	 * @throws UploadException
	 *             the upload exception
	 */
	public UploadResult uploadFile(UploadFileParameter param)
			throws UploadException {

		try {
			byte[] binaryContent = FileUtils.readFileToByteArray(param.getFile());
			return handleBinaryUpload(param.getPrivacyStrategy(), param.getSenderPrivateKey(), param.getReceiverPublicKey(),
					param.getContentType(), param.getKeywords(), param.getMetaData(), param.getName(), param.getMosaics(), binaryContent);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException(format("Error on uploading file data: %s", param.getFile().getAbsolutePath()), e);
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
	public UploadResult uploadTextData(UploadTextDataParameter uploadParameter) throws UploadException {
		return handleTextDataUpload(uploadParameter.getPrivacyStrategy(), uploadParameter.getSenderPrivateKey(),
				uploadParameter.getReceiverPublicKey(), uploadParameter.getContentType(), uploadParameter.getKeywords(),
				uploadParameter.getMetaData(), uploadParameter.getName(), uploadParameter.getMosaics(),
				uploadParameter.getData(), uploadParameter.getEncoding());
	}

	/**
	 * Upload a binary file.
	 *
	 * @param param the upload parameter
	 * @return the upload data
	 * @throws UploadException the upload exception
	 */
	public UploadResult uploadBinary(UploadBinaryParameter param) throws UploadException {
		try {
			return handleBinaryUpload(param.getPrivacyStrategy(), param.getSenderPrivateKey(), param.getReceiverPublicKey(),
					param.getContentType(), param.getKeywords(), param.getMetaData(), param.getName(), param.getMosaics(), param.getData());
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
			byte[] resourceHashMessageBytes = (byte[])((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
					uploadParameter.getKeywords(), uploadParameter.getMetaData());

			return handlePostUpload(uploadParameter.getPrivacyStrategy(), uploadParameter.getSenderPrivateKey(),
					uploadParameter.getReceiverPublicKey(), uploadParameter.getMosaics(), resourceHashMessageBytes);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException(format("Error on uploading path: %s", uploadParameter.getPath()), e);
		}
	}

	public UploadResult uploadFilesAsZip(UploadFilesAsZipParameter param) throws UploadException {

		try {
			byte[] binaryContent = zipFiles(param.getFiles());
			return handleBinaryUpload(param.getPrivacyStrategy(), param.getSenderPrivateKey(), param.getReceiverPublicKey(),
					param.getContentType(), param.getKeywords(), param.getMetaData(), param.getName(), param.getMosaics(), binaryContent);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException("Error on uploading files as zip", e);
		}
	}

	public MultiFileUploadResult uploadMultipleFiles(UploadMultipleFilesParameter param) throws UploadException {

        if (param.getFiles().size() == 0)
            throw new UploadException("No file to upload");

        List<FileUploadResult> fileUploadResults = param.getFiles()
                .parallelStream()
                .map(file -> {
                    try {
                        byte[] binaryContent = FileUtils.readFileToByteArray(file);
                        return new FileUploadResult(file,
								handleBinaryUpload(param.getPrivacyStrategy(), param.getSenderPrivateKey(),
										param.getReceiverPublicKey(), ContentTypeUtils.detectContentType(binaryContent),
										param.getKeywords(), param.getMetaData(), file.getName(), param.getMosaics(), binaryContent));
                    } catch (Exception e) {
                        return new FileUploadResult(file, new UploadException(format("Error on uploading file data: %s", file.getAbsolutePath()), e));
                    }
                }).collect(Collectors.toList());
        return new MultiFileUploadResult(fileUploadResults);
	}

	private UploadResult handleTextDataUpload(PrivacyStrategy privacyStrategy, String senderPrivateKey, String receiverPublicKey,
											String contentType, String keywords, String metadata, String name, Mosaic[] mosaics,
											String textData, String encoding) throws UploadException {
		try {
			final byte[] textInBytes = textData.getBytes(encoding);

			final byte[] encryptedTextInBytes = privacyStrategy.encrypt(textInBytes);
			final byte[] resourceHashMessageBytes = storeTextData(contentType, encoding, keywords, metadata, name,
					encryptedTextInBytes);

			return handlePostUpload(privacyStrategy, senderPrivateKey,
					receiverPublicKey, mosaics, resourceHashMessageBytes);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException("Error on uploading text data", e);
		}
	}

	private UploadResult handleBinaryUpload(PrivacyStrategy privacyStrategy, String senderPrivateKey, String receiverPublicKey,
											String contentType, String keywords, String metadata, String name, Mosaic[] mosaics,
											byte[] binaryContent) throws UploadException {
		try {
			byte[] encryptedContent = privacyStrategy.encrypt(binaryContent);
			byte[] resourceHashMessageBytes = storeBinaryData(contentType, keywords, metadata, name, encryptedContent);
			return handlePostUpload(privacyStrategy, senderPrivateKey, receiverPublicKey, mosaics, resourceHashMessageBytes);

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

	private byte[] storeTextData(String contentType, String encoding, String keywords, String metadata, String name,
								 byte[] encryptedTextInBytes) throws ApiException, IOException, NoSuchAlgorithmException {
		UploadTextRequestParameter apiParams = new UploadTextRequestParameter()
				.contentType(contentType)
				.encoding(encoding)
				.keywords(keywords)
				.metadata(metadata)
				.name(name)
				.text(encryptedTextInBytes);

		return (byte[]) uploadApi.uploadPlainTextUsingPOST(apiParams);
	}

	private byte[] storeBinaryData(String contentType, String keywords, String metadata, String name,
								   byte[] encryptedContent) throws ApiException, IOException, NoSuchAlgorithmException {
		UploadBytesBinaryRequestParameter parameter = new UploadBytesBinaryRequestParameter()
				.contentType(contentType)
				.keywords(keywords)
				.metadata(metadata)
				.name(name)
				.data(encryptedContent);

		return (byte[]) uploadApi.uploadBytesBinaryUsingPOST(parameter);
	}


	private UploadResult handlePostUpload(PrivacyStrategy privacyStrategy, String senderPrivateKey, String receiverPublicKey,
										  Mosaic[] mosaics, byte[] response) throws Exception {

		ResourceHashMessage resourceMessageHash = null;
		try {
			resourceMessageHash = deserializeResourceMessageHash(response);

			final String nemHash = createNemTransaction(privacyStrategy, senderPrivateKey, receiverPublicKey, mosaics, response);

			// Safe Sync if no errors.
			safeAsyncToGateways(resourceMessageHash);

			return new UploadResult(resourceMessageHash, nemHash);
		} catch (Exception e) {
			if (resourceMessageHash != null) {
				final String resourceHash = resourceMessageHash.hash();
				Executors.newSingleThreadExecutor().submit(() -> uploadApi.cleanupPinnedContentUsingPOST(resourceHash));
			}
			throw e;
		}
	}

	private String createNemTransaction(PrivacyStrategy privacyStrategy, String senderPrivateKey,
										String receiverPublicKey, Mosaic[] mosaics, byte[] response) throws Exception {
		final Message nemMessage = privacyStrategy.encodeToMessage(response);
		return announceNemTransaction(nemMessage, senderPrivateKey, receiverPublicKey, mosaics);
	}

	private String announceNemTransaction(Message nemMessage, String senderPrivateKey, String receiverPublicKey,
										  Mosaic[] mosaics) throws Exception {

//		if (this.isLocalPeerConnection) {
			// Announce The Signature
		NemAnnounceResult announceResult = TransferTransactionBuilder
				.peerConnection(peerConnection)
				.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
				.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(receiverPublicKey))))
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
//					.sender(new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey))))
//					.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(receiverOrSenderPublicKey))))
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


