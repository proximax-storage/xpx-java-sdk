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
import org.nem.core.model.TransferTransaction;

import java.nio.ByteBuffer;

import static java.lang.String.format;


/**
 * The Class Download.
 */
public class Download extends AbstractFacadeService {

	/** The download api. */
	private final DownloadApi downloadApi;

	/** The nem transaction api. */
	private final NemTransactionApi nemTransactionApi;


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

		this.downloadApi = peerConnection.getDownloadApi();
        this.nemTransactionApi = peerConnection.getNemTransactionApi();
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

		final byte[] decodedDataFromNemMessage = downloadParameter.getPrivacyStrategy().decodeTransaction(transaction);

		final ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
				ByteBuffer.wrap(Base64.decodeBase64(decodedDataFromNemMessage)));

		final byte[] downloadData = downloadUsingDataHash(resourceMessage.hash());

		final byte[] decryptedDownloadData = downloadParameter.getPrivacyStrategy().decrypt(downloadData, transaction, resourceMessage);

		return new DownloadResult(resourceMessage, decryptedDownloadData, NemMessageType.fromInt(transaction.getMessage().getType()));
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
