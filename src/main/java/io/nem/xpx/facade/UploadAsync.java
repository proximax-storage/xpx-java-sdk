/*
 * 
 */
package io.nem.xpx.facade;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.Hashes;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;

import io.nem.api.ApiException;
import io.nem.xpx.LocalUploadApi;
import io.nem.xpx.RemoteUploadApi;
import io.nem.xpx.TransactionAndAnnounceApi;
import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.intf.UploadApi;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadCallback;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.UploadPathParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.model.buffers.ResourceHashMessage;


/**
 * The Class Upload.
 */
public class UploadAsync {

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
	public UploadAsync() {
	}

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public UploadAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
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

	public Future<UploadData> uploadFile(UploadFileParameter uploadParameter, UploadCallback callback)
	{
		CompletableFuture<UploadData> uploadFileAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handleFileUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return uploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});
		return uploadFileAsync;
	}

	public Future<UploadData> uploadTextData(UploadDataParameter uploadParameter, UploadCallback callback) {
		CompletableFuture<UploadData> uploadDataAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handleTextDataUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return uploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});
		return uploadDataAsync;
	}

	public Future<UploadData> uploadBinary(UploadBinaryParameter uploadParameter, UploadCallback callback) {

		CompletableFuture<UploadData> uploadBinaryAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handleBinaryUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException e) {
				throw new CompletionException(e);
			}
			return uploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return uploadBinaryAsync;
	}

	public Future<UploadData> uploadPath(UploadPathParameter uploadParameter, UploadCallback callback) {
		
		CompletableFuture<UploadData> uploadPathAsync = CompletableFuture.supplyAsync(() -> {
			UploadData uploadData = null;
			try {
				uploadData = handlePathUpload(uploadParameter);
			} catch (UploadException | IOException | ApiException | PeerConnectionNotFoundException e) {
				throw new CompletionException(e);
			}
			return uploadData;
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return uploadPathAsync;
		
	}

	private UploadData handleTextDataUpload(UploadDataParameter uploadParameter)
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
			byte[] serializedData = uploadParameter.getData().getBytes();
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()), engine),
								new KeyPair(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()), engine))
						.encrypt(serializedData);

				String encryptedData = Base64.encodeBase64String(encrypted);
				response = uploadApi.uploadPlainTextUsingPOST(encryptedData, uploadParameter.getName(),
						uploadParameter.getEncoding(), uploadParameter.getKeywords(), uploadParameter.getMetaData());
			} else { // PLAIN
				String data = Base64.encodeBase64String(uploadParameter.getData().getBytes());
				response = uploadApi.uploadPlainTextUsingPOST(data, uploadParameter.getName(),
						uploadParameter.getEncoding(), uploadParameter.getKeywords(), uploadParameter.getMetaData());
			}
			resourceMessageHash = byteToSerialObject((byte[]) response);
			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
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

	private UploadData handleFileUpload(UploadFileParameter uploadParameter)
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
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()), engine),
								new KeyPair(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()), engine))
						.encrypt(FileUtils.readFileToByteArray(uploadParameter.getData()));

				// byte[] data = Base64.encodeBase64(encrypted);
				response = uploadApi.uploadBytesBinaryUsingPOST(encrypted, uploadParameter.getContentType(),
						uploadParameter.getData().getName(), uploadParameter.getKeywords(),
						uploadParameter.getMetaData());
			} else { // PLAIN
				// byte[] data =
				// Base64.encodeBase64(FileUtils.readFileToByteArray(uploadParameter.getData()));
				response = uploadApi.uploadBytesBinaryUsingPOST(
						FileUtils.readFileToByteArray(uploadParameter.getData()), uploadParameter.getContentType(),
						uploadParameter.getData().getName(), uploadParameter.getKeywords(),
						uploadParameter.getMetaData());
			}

			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
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
		} catch (Exception e) {
			e.printStackTrace();
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		} finally {
			safeAsyncToGateways(resourceMessageHash);
		}
		return uploadData;
	}

	private UploadData handleBinaryUpload(UploadBinaryParameter uploadParameter)
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
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()), engine),
								new KeyPair(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()), engine))
						.encrypt(uploadParameter.getData());

				// byte[] data = Base64.encodeBase64(encrypted);
				response = uploadApi.uploadBytesBinaryUsingPOST(encrypted, uploadParameter.getContentType(),
						uploadParameter.getName(), uploadParameter.getKeywords(), uploadParameter.getMetaData());
			} else { // PLAIN
				// byte[] data = Base64.encodeBase64(uploadParameter.getData());
				response = uploadApi.uploadBytesBinaryUsingPOST(uploadParameter.getData(),
						uploadParameter.getContentType(), uploadParameter.getName(), uploadParameter.getKeywords(),
						uploadParameter.getMetaData());
			}

			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				RequestAnnounceDataSignature requestAnnounceDataSignature = TransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
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
		} catch (Exception e) {
			e.printStackTrace();
			uploadApi.cleanupPinnedContentUsingPOST(resourceMessageHash.hash());
			throw new UploadException(e);
		} finally {
			safeAsyncToGateways(resourceMessageHash);
		}
		return uploadData;
	}

	private UploadData handlePathUpload(UploadPathParameter uploadParameter)
			throws UploadException, IOException, ApiException, PeerConnectionNotFoundException {

		if (peerConnection instanceof RemotePeerConnection) {
			throw new PeerConnectionNotFoundException("Can't use RemotePeerConnection for Path upload");
		}

		String publishedData = "";
		if (uploadParameter.getMosaics() == null) {
			uploadParameter.setMosaics(new Mosaic[0]);
		}

		UploadData uploadData = new UploadData();
		byte[] encrypted = null;
		Object response = null;
		ResourceHashMessage resourceMessageHash = null;
		try {
			if (uploadParameter.getMessageType() == MessageTypes.SECURE) {
				encrypted = engine
						.createBlockCipher(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()), engine),
								new KeyPair(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()), engine))
						.encrypt(uploadParameter.getPath().getBytes());

				// byte[] data = Base64.encodeBase64(encrypted);
				response = ((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
						uploadParameter.getKeywords(), uploadParameter.getMetaData());
			} else { // PLAIN
				// byte[] data = Base64.encodeBase64(uploadParameter.getPath());
				response = ((LocalUploadApi) uploadApi).uploadPath(uploadParameter.getPath(), uploadParameter.getName(),
						uploadParameter.getKeywords(), uploadParameter.getMetaData());
			}

			if (this.isLocalPeerConnection) {
				// Announce The Signature
				NemAnnounceResult announceResult = TransferTransactionBuilder
						.sender(new Account(
								new KeyPair(PrivateKey.fromHexString(uploadParameter.getSenderPrivateKey()))))
						.recipient(new Account(Address
								.fromPublicKey(PublicKey.fromHexString(uploadParameter.getRecipientPublicKey()))))
						.version(2).amount(Amount.fromNem(1l))
						.message((byte[]) response, uploadParameter.getMessageType())
						.addMosaics(uploadParameter.getMosaics()).buildAndSendTransaction();
				publishedData = announceResult.getTransactionHash().toString();

			}
			resourceMessageHash = byteToSerialObject((byte[]) response);
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

	private ResourceHashMessage byteToSerialObject(byte[] object) {
		ResourceHashMessage resourceMessage = ResourceHashMessage
				.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(object)));
		return resourceMessage;
	}

	private void safeAsyncToGateways(ResourceHashMessage resource) {
		Runnable task = () -> {
			for (String s : XpxSdkGlobalConstants.GLOBAL_GATEWAYS) {
				HttpURLConnection conn = null;
				try {
					conn = (HttpURLConnection) new URL(s + "/ipfs/" + resource.hash()).openConnection();
					// System.out.println(s + "/ipfs/" + resource.hash());
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestMethod("GET");
					conn.setDoOutput(true);
					conn.setUseCaches(false);
					DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
					wr.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					conn.disconnect();
				}
			}
		};
		task.run();
	}

}
