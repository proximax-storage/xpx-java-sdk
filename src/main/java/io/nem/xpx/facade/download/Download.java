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
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
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

	public DownloadResult download(DownloadParameter downloadParameter) throws DownloadException {

		final TransferTransaction transaction = getNemTransferTransaction(downloadParameter.getNemHash());

		final ResourceHashMessage resourceMessage = getResourceHashMessage(downloadParameter.getPrivacyStrategy(),
				downloadParameter.getSenderOrReceiverPrivateKey(), downloadParameter.getReceiverOrSenderPublicKey(),
				transaction);

		final byte[] downloadedData = downloadUsingDataHash(resourceMessage.hash());

		final byte[] decrypted = downloadParameter.getPrivacyStrategy().decrypt(downloadedData, transaction, resourceMessage);

		return new DownloadResult(resourceMessage, decrypted, NemMessageType.fromInt(transaction.getMessage().getType()));
	}

	private TransferTransaction getNemTransferTransaction(final String nemHash) throws DownloadException {
		try {
			return (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();
		} catch (Exception e) {
			throw new DownloadException(format("Failed to retrieve a Nem Transaction for hash %s", nemHash), e);
		}
	}

	private ResourceHashMessage getResourceHashMessage(final PrivacyStrategy privacyStrategy,
													   final String senderOrReceiverPrivateKey,
													   final String receiverOrSenderPublicKey,
													   final TransferTransaction transaction) throws DownloadException {
		if (transaction.getMessage().getType() == NemMessageType.SECURE.getValue()) {
			if (privacyStrategy.getNemMessageType() != NemMessageType.SECURE) {
				throw new DownloadException("Privacy strategy used is not allowed to decrypt a Nem secured message. " +
						"Use securedWithNemKeysPrivacyStrategy() to decrypt the message ");
			}

			final SecureMessage secureMessage = decryptNemMessage(senderOrReceiverPrivateKey,
					receiverOrSenderPublicKey, transaction);

			return ResourceHashMessage.getRootAsResourceHashMessage(
					ByteBuffer.wrap(Base64.decodeBase64(secureMessage.getDecodedPayload())));
		} else {

			return ResourceHashMessage.getRootAsResourceHashMessage(
					ByteBuffer.wrap(Base64.decodeBase64(transaction.getMessage().getDecodedPayload())));
		}
	}
	private SecureMessage decryptNemMessage(final String senderOrReceiverPrivateKey,
											final String receiverOrSenderPublicKey,
											final TransferTransaction transaction) throws DownloadException {
		final Address nemAddress = MessageUtils.getNemAddressFromPrivateKey(senderOrReceiverPrivateKey);

		if (transaction.getSigner().getAddress().getEncoded().equals(nemAddress.getEncoded())) {
			return SecureMessage.fromEncodedPayload(
					new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
					new Account(new KeyPair(PublicKey.fromHexString(receiverOrSenderPublicKey), engine)),
					transaction.getMessage().getEncodedPayload());

		} else if (transaction.getRecipient().getAddress().getEncoded().equals(nemAddress.getEncoded())) {

			return SecureMessage.fromEncodedPayload(
					new Account(new KeyPair(PublicKey.fromHexString(receiverOrSenderPublicKey), engine)),
					new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
					transaction.getMessage().getEncodedPayload());
		} else {
			throw new DownloadException("Private key cannot be used to decrypt the Nem secured message.");
		}
	}

	private byte[] downloadUsingDataHash(String hash) throws DownloadException {
		try {
			return downloadApi.downloadUsingDataHashUsingGET(hash);
		} catch (Exception e) {
			throw new DownloadException(format("Failed to download using data hash %s", hash), e);
		}
	}
}
