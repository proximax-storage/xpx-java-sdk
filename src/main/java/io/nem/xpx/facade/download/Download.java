/*
 * 
 */
package io.nem.xpx.facade.download;

import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.DownloadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.apache.commons.codec.binary.Base64;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.model.TransferTransaction;

import java.nio.ByteBuffer;

import static java.lang.String.format;


/**
 * The Class Download.
 */
public class Download extends AbstractFacadeService {

	/** The peer connection. */
	private final PeerConnection peerConnection;

	/** The download api. */
	private final DownloadApi downloadApi;

	/** The nem transaction api. */
	private final NemTransactionApi nemTransactionApi;

	/** The engine. */
	private final CryptoEngine engine;


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

	public DownloadBinaryResult downloadBinary(DownloadParameter downloadParameter) throws DownloadException {
		return DownloadBinaryResult.fromDownloadResult(download(downloadParameter));
	}

	public DownloadTextDataResult downloadTextData(DownloadParameter downloadParameter) throws DownloadException {
		return DownloadTextDataResult.fromDownloadResult(download(downloadParameter));
	}

	public DownloadFileResult downloadFile(DownloadParameter downloadParameter) throws DownloadException {
		return DownloadFileResult.fromDownloadResult(download(downloadParameter));
	}

	private DownloadResult download(DownloadParameter downloadParameter) throws DownloadException {

		final TransferTransaction transaction = getNemTransferTransaction(downloadParameter.getNemHash());

		final ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
				ByteBuffer.wrap(Base64.decodeBase64(downloadParameter.getPrivacyStrategy().decodeTransaction(transaction))));

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

	private byte[] downloadUsingDataHash(String hash) throws DownloadException {
		try {
			return downloadApi.downloadUsingDataHashUsingGET(hash);
		} catch (Exception e) {
			throw new DownloadException(format("Failed to download using data hash %s", hash), e);
		}
	}
}
