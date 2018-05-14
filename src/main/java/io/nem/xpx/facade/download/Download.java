/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.DownloadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.CryptoUtils;
import io.nem.xpx.utils.MessageUtils;
import org.apache.commons.codec.binary.Base64;
import org.nem.core.crypto.*;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;


/**
 * The Class Download.
 */
public class Download extends AbstractFacadeService {

	/** The peer connection. */
	private final PeerConnection peerConnection;

	/** The download api. */
	protected final DownloadApi downloadApi;

	/** The nem transaction api. */
	protected final NemTransactionApi nemTransactionApi;

	/** The engine. */
	protected final CryptoEngine engine;


	/**
	 * Instantiates a new download.
	 *
	 * @param peerConnection            the peer connection
	 * @throws PeerConnectionNotFoundException the peer connection not found exception
	 */
	public Download(PeerConnection peerConnection) {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

        this.peerConnection = peerConnection;
		this.downloadApi = peerConnection.getDownloadApi();
        this.nemTransactionApi = peerConnection.getNemTransactionApi();
		this.engine = CryptoEngines.ed25519Engine();
	}

	public DownloadResult downloadTextData(DownloadParameter downloadParameter) throws DownloadException {

		final TransferTransaction transaction = getNemTransferTransaction(downloadParameter.getNemHash());

		if (transaction.getMessage().getType() == NemMessageType.SECURE.getValue()) {

			if (downloadParameter.getPrivacyStrategy().getNemMessageType() != NemMessageType.SECURE) {
				throw new DownloadException("Privacy strategy used is not allowed to decrypt a Nem secured message.");
			}

			final SecureMessage secureMessage = decryptNemMessage(downloadParameter.getSenderOrReceiverPrivateKey(),
					downloadParameter.getReceiverOrSenderPublicKey(), transaction);

			final ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
					ByteBuffer.wrap(Base64.decodeBase64(secureMessage.getDecodedPayload())));

			final byte[] downloadedData = downloadUsingDataHash(resourceMessage.hash());

			final byte[] decrypted = downloadParameter.getPrivacyStrategy().decrypt(downloadedData, transaction, resourceMessage);

			return new DownloadResult(resourceMessage, decrypted, transaction.getMessage().getType());
		} else {

			final ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
					ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getEncodedPayload())));

			final byte[] downloadedData = downloadUsingDataHash(resourceMessage.hash());

			final byte[] decrypted = downloadParameter.getPrivacyStrategy().decrypt(downloadedData, transaction, resourceMessage);

			return new DownloadResult(resourceMessage, decrypted, transaction.getMessage().getType());
		}
	}

	private SecureMessage decryptNemMessage(final String senderOrReceiverPrivateKey,
											final String receiverOrSenderPublicKey,
											final TransferTransaction transaction) throws DownloadException {
		final Address nemAddress = MessageUtils.getNemAddressFromPrivateKey(senderOrReceiverPrivateKey);
		if (transaction.getSigner().getAddress().getEncoded().equals(nemAddress)) {
			return SecureMessage.fromEncodedPayload(
					new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
					new Account(new KeyPair(PublicKey.fromHexString(receiverOrSenderPublicKey), engine)),
					transaction.getMessage().getEncodedPayload());
		} else if (transaction.getRecipient().getAddress().getEncoded().equals(nemAddress)) {
			return SecureMessage.fromEncodedPayload(
					new Account(new KeyPair(PrivateKey.fromHexString(receiverOrSenderPublicKey), engine)),
					new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPrivateKey), engine)),
					transaction.getMessage().getEncodedPayload());
		} else {
			throw new DownloadException("Private key cannot be used to decrypt the Nem secured message.");
		}
	}



	private TransferTransaction getNemTransferTransaction(final String nemHash) throws DownloadException {
		try {
			return (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();
		} catch (Exception e) {
			throw new DownloadException(format("Failed to retrieve a Nem Transaction for hash %s", nemHash), e);
		}
	}

	private byte[] downloadUsingDataHash(String hash) throws DownloadException {
		try {
			return downloadApi.downloadUsingDataHashUsingGET(hash);
		} catch (Exception e) {
			throw new DownloadException(format("Failed to download using data hash %s", hash), e);
		}
	}


	/**
	 * Download public data.
	 *
	 * @param nemHash            the nem hash
	 * @return the download data
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 * @throws ApiException             the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DownloadResult downloadTextData(String nemHash)
			throws InterruptedException, ExecutionException, ApiException, IOException {

		TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
		TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
		ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));

		byte[] securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

		return new DownloadResult(resourceMessage, Base64.decodeBase64(securedResponse), MessageTypes.PLAIN);
	}
	
	/**
	 * Download binary or file.
	 *
	 * @param nemHash the nem hash
	 * @return the download data
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DownloadResult downloadBinaryOrFile(String nemHash)
			throws InterruptedException, ExecutionException, ApiException, IOException {

		TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
		TransferTransaction bTrans = ((TransferTransaction) transactionMetaDataPair.getEntity());
		ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(bTrans.getMessage().getEncodedPayload())));

		byte[] securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

		return new DownloadResult(resourceMessage, securedResponse, MessageTypes.PLAIN);

	}
	
	
	/**
	 * Download secure binary or file.
	 *
	 * @param nemHash the nem hash
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @return the download data
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DownloadResult downloadSecureBinaryOrFile(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		return downloadSecureBinaryOrFile(nemHash,"bytes",senderOrReceiverPrivateKey,senderOrReceiverPublicKey);
	}
	
	/**
	 * Download secure text data.
	 *
	 * @param nemHash the nem hash
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @return the download data
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DownloadResult downloadSecureTextData(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		return downloadSecureTextData(nemHash,"bytes",senderOrReceiverPrivateKey,senderOrReceiverPublicKey);
	}
	
	/**
	 * Download secure binary or file.
	 *
	 * @param nemHash the nem hash
	 * @param transferType the transfer type
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @return the download data
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DownloadResult downloadSecureBinaryOrFile(String nemHash, String transferType, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		byte[] securedResponse = null;
		SecureMessage message;
		//BinaryTransactionEncryptedMessage binaryEncryptedData = new BinaryTransactionEncryptedMessage();

		// get the addresses
		// private key address
		PrivateKey privateKey = PrivateKey.fromHexString(senderOrReceiverPrivateKey);
		KeyPair keyPair = new KeyPair(privateKey);
		String senderOrReceiverPrivateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		// Evaluate the transaction.
		TransferTransaction transaction = (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();

		if (transaction.getMessage().getType() == 2) {
			if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

				message = SecureMessage.fromEncodedPayload(
						new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
						new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
						transaction.getMessage().getEncodedPayload());
				
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));
				securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

				byte[] decrypted = engine
						.createBlockCipher(
								new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine),
								new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine))
						.decrypt(securedResponse);
				
				return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);

			} else if (transaction.getRecipient().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

				message = SecureMessage.fromEncodedPayload(
						new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
						new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
						transaction.getMessage().getEncodedPayload());

				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));

				securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

				byte[] decrypted = engine
						.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine),
								new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine))
						.decrypt(securedResponse);
				//byte[] decoded = Base64.decodeBase64(decrypted);

				return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);
			}

		} else if (transaction.getMessage().getType() == 1) {

			ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

			securedResponse = downloadApi.downloadTextUsingGET(nemHash,transferType);

			return new DownloadResult(resourceMessage, securedResponse, MessageTypes.PLAIN);
		}

		return new DownloadResult(null, null, 0);
	}
	
	/**
	 * Download secure text data.
	 *
	 * @param nemHash the nem hash
	 * @param transferType the transfer type
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @return the download data
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DownloadResult downloadSecureTextData(String nemHash, String transferType, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey)
			throws ApiException, InterruptedException, ExecutionException, IOException {
		byte[] securedResponse = null;
		SecureMessage message;
		//BinaryTransactionEncryptedMessage binaryEncryptedData = new BinaryTransactionEncryptedMessage();

		// get the addresses
		// private key address
		PrivateKey privateKey = PrivateKey.fromHexString(senderOrReceiverPrivateKey);
		KeyPair keyPair = new KeyPair(privateKey);
		String senderOrReceiverPrivateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		// Evaluate the transaction.
		TransferTransaction transaction = (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();

		if (transaction.getMessage().getType() == 2) {
			if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

				message = SecureMessage.fromEncodedPayload(
						new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
						new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
						transaction.getMessage().getEncodedPayload());
				
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));
				securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());
				
				byte[] decrypted = engine
						.createBlockCipher(
								new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine),
								new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine))
						.decrypt(Base64.decodeBase64(securedResponse));

				return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);

			} else if (transaction.getRecipient().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

				message = SecureMessage.fromEncodedPayload(
						new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
						new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
						transaction.getMessage().getEncodedPayload());

				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(message.getDecodedPayload())));

				securedResponse = downloadApi.downloadUsingDataHashUsingGET(resourceMessage.hash());

				byte[] decrypted = engine
						.createBlockCipher(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine),
								new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine))
						.decrypt(Base64.decodeBase64(securedResponse));

				return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);
			}

		} else if (transaction.getMessage().getType() == 1) {

			ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

			securedResponse = downloadApi.downloadTextUsingGET(nemHash,transferType);

			return new DownloadResult(resourceMessage, securedResponse, MessageTypes.PLAIN);
		}

		return new DownloadResult(null, null, 0);
	}


	/**
	 * Download multisig file or data.
	 *
	 * @param messageType the message type
	 * @param nemHash the nem hash
	 * @param keySecret the key secret
	 * @return the download data
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 */
	public DownloadResult downloadMultisigFileOrData(int messageType, String nemHash, String keySecret)
			throws ApiException, InterruptedException, ExecutionException, IOException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException {
		byte[] securedResponse = null;
		// Evauate the transaction.
		TransferTransaction transaction = (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();
		
		
		if(transaction.getSignature() != null) {
			if(messageType == MessageTypes.SECURE) {
				byte[] decrypted = null;
				
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));

				securedResponse = downloadApi.downloadBinaryUsingGET(nemHash,"");
				decrypted = CryptoUtils.decrypt(securedResponse, keySecret.toCharArray());
				//String encryptedData = HexEncoder.getString(decrypted);

				return new DownloadResult(resourceMessage, decrypted, MessageTypes.SECURE);
			} else {
				byte[] decrypted = null;
				ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));
				//String encryptedData = HexEncoder.getString(decrypted);

				return new DownloadResult(resourceMessage, decrypted, MessageTypes.PLAIN);
			}
		}
		return new DownloadResult(null, null, 0);
	}
}
