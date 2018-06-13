/*
 *
 */
package io.nem.xpx.facade.upload;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PathUploadNotSupportedException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.MultiFileUploadResult.FileUploadResult;
import io.nem.xpx.model.UploadBytesBinaryRequestParameter;
import io.nem.xpx.model.UploadTextRequestParameter;
import io.nem.xpx.service.TransactionAnnouncer;
import io.nem.xpx.service.intf.UploadApi;
import io.nem.xpx.service.local.LocalUploadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.utils.ContentTypeUtils;
import org.apache.commons.io.FileUtils;
import org.nem.core.model.Message;
import org.nem.core.model.mosaic.Mosaic;

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

	/** The upload api. */
	private final UploadApi uploadApi;

	/** The transaction announcer. */
	private final TransactionAnnouncer transactionAnnouncer;



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
		this.transactionAnnouncer = peerConnection.getTransactionAnnouncer();
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
					param.getContentType(), param.getKeywords(), param.getMetaDataAsString(), param.getName(), param.getMosaics(), binaryContent);
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
				uploadParameter.getMetaDataAsString(), uploadParameter.getName(), uploadParameter.getMosaics(),
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
					param.getContentType(), param.getKeywords(), param.getMetaDataAsString(), param.getName(), param.getMosaics(), param.getData());
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException("Error on uploading binary data", e);
		}
	}

	/**
	 * Upload path.
	 *
	 * @param uploadParameter            the upload parameter
	 * @return the upload data
	 * @throws UploadException             the upload exception
	 */
	public UploadResult uploadPath(UploadPathParameter uploadParameter)
			throws UploadException {
		if (peerConnection instanceof RemotePeerConnection) {
			throw new PathUploadNotSupportedException("Path upload is not supported for remote peer connection");
		}

		try {
			byte[] resourceHashMessageBytes = (byte[])((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
					uploadParameter.getKeywords(), uploadParameter.getMetaDataAsString());

			return handlePostUpload(uploadParameter.getPrivacyStrategy(), uploadParameter.getSenderPrivateKey(),
					uploadParameter.getReceiverPublicKey(), uploadParameter.getMosaics(), resourceHashMessageBytes);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException(format("Error on uploading path: %s", uploadParameter.getPath()), e);
		}
	}

	/**
	 * Upload files as zip.
	 *
	 * @param param the param
	 * @return the upload result
	 * @throws UploadException the upload exception
	 */
	public UploadResult uploadFilesAsZip(UploadFilesAsZipParameter param) throws UploadException {

		try {
			byte[] binaryContent = zipFiles(param.getFiles());
			return handleBinaryUpload(param.getPrivacyStrategy(), param.getSenderPrivateKey(), param.getReceiverPublicKey(),
					param.getContentType(), param.getKeywords(), param.getMetaDataAsString(), param.getName(), param.getMosaics(), binaryContent);
		} catch (UploadException e) {
			throw e;
		} catch (Exception e) {
			throw new UploadException("Error on uploading files as zip", e);
		}
	}

	/**
	 * Upload multiple files.
	 *
	 * @param param the param
	 * @return the multi file upload result
	 * @throws UploadException the upload exception
	 */
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
										param.getKeywords(), param.getMetaDataAsString(), file.getName(), param.getMosaics(), binaryContent));
                    } catch (Exception e) {
                        return new FileUploadResult(file, new UploadException(format("Error on uploading file data: %s", file.getAbsolutePath()), e));
                    }
                }).collect(Collectors.toList());
        return new MultiFileUploadResult(fileUploadResults);
	}

	/**
	 * Handle text data upload.
	 *
	 * @param privacyStrategy the privacy strategy
	 * @param senderPrivateKey the sender private key
	 * @param receiverPublicKey the receiver public key
	 * @param contentType the content type
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @param name the name
	 * @param mosaics the mosaics
	 * @param textData the text data
	 * @param encoding the encoding
	 * @return the upload result
	 * @throws UploadException the upload exception
	 */
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

	/**
	 * Handle binary upload.
	 *
	 * @param privacyStrategy the privacy strategy
	 * @param senderPrivateKey the sender private key
	 * @param receiverPublicKey the receiver public key
	 * @param contentType the content type
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @param name the name
	 * @param mosaics the mosaics
	 * @param binaryContent the binary content
	 * @return the upload result
	 * @throws UploadException the upload exception
	 */
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


	/**
	 * Zip files.
	 *
	 * @param files the files
	 * @return the byte[]
	 * @throws UploadException the upload exception
	 */
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

	/**
	 * Validate zip files arguments.
	 *
	 * @param files the files
	 * @throws UploadException the upload exception
	 */
	private void validateZipFilesArguments(List<File> files) throws UploadException {
		if (files.size() == 0)
			throw new UploadException("No file to upload");

		long uniqueFileNameCount = files.stream().map(file -> file.getName()).distinct().count();
		if (uniqueFileNameCount < files.size())
			throw new UploadException(format("File names should be unique to zip files together: %s",
					String.join(",", files.stream().map(file -> file.getName()).collect(Collectors.toList()))));
	}

	/**
	 * Store text data.
	 *
	 * @param contentType the content type
	 * @param encoding the encoding
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @param name the name
	 * @param encryptedTextInBytes the encrypted text in bytes
	 * @return the byte[]
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
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

	/**
	 * Store binary data.
	 *
	 * @param contentType the content type
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @param name the name
	 * @param encryptedContent the encrypted content
	 * @return the byte[]
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
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


	/**
	 * Handle post upload.
	 *
	 * @param privacyStrategy the privacy strategy
	 * @param senderPrivateKey the sender private key
	 * @param receiverPublicKey the receiver public key
	 * @param mosaics the mosaics
	 * @param response the response
	 * @return the upload result
	 * @throws Exception the exception
	 */
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

	/**
	 * Creates the nem transaction.
	 *
	 * @param privacyStrategy the privacy strategy
	 * @param senderPrivateKey the sender private key
	 * @param receiverPublicKey the receiver public key
	 * @param mosaics the mosaics
	 * @param response the response
	 * @return the string
	 * @throws Exception the exception
	 */
	private String createNemTransaction(PrivacyStrategy privacyStrategy, String senderPrivateKey,
										String receiverPublicKey, Mosaic[] mosaics, byte[] response) throws Exception {
		final Message nemMessage = privacyStrategy.encodeToMessage(response);
		return transactionAnnouncer.announceTransactionForUploadedContent(nemMessage, senderPrivateKey, receiverPublicKey, mosaics);
	}
}


