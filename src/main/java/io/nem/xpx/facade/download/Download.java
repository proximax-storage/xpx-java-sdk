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

	/**
	 * Download binary.
	 *
	 * @param downloadParameter the download parameter
	 * @return the download binary result
	 * @throws DownloadException the download exception
	 */
	public DownloadBinaryResult downloadBinary(DownloadParameter downloadParameter) throws DownloadException {
		return DownloadBinaryResult.fromDownloadResult(download(downloadParameter));
	}

	/**
	 * Download text data.
	 *
	 * @param downloadParameter the download parameter
	 * @return the download text data result
	 * @throws DownloadException the download exception
	 */
	public DownloadTextDataResult downloadTextData(DownloadParameter downloadParameter) throws DownloadException {
		return DownloadTextDataResult.fromDownloadResult(download(downloadParameter));
	}

	/**
	 * Download file.
	 *
	 * @param downloadParameter the download parameter
	 * @return the download file result
	 * @throws DownloadException the download exception
	 */
	public DownloadFileResult downloadFile(DownloadParameter downloadParameter) throws DownloadException {
		return DownloadFileResult.fromDownloadResult(download(downloadParameter));
	}

	/**
	 * Download.
	 *
	 * @param downloadParameter the download parameter
	 * @return the download result
	 * @throws DownloadException the download exception
	 */
	private DownloadResult download(DownloadParameter downloadParameter) throws DownloadException {

		final TransferTransaction transaction = getNemTransaction(downloadParameter.getNemHash());

		final byte[] decodedDataFromNemMessage = downloadParameter.getPrivacyStrategy().decodeTransaction(transaction);

		final ResourceHashMessage resourceMessage = deserializeResourceMessageHash(decodedDataFromNemMessage);

		final byte[] downloadedData = downloadUsingDataHash(resourceMessage.hash());

		final byte[] decryptedDownloadedData = downloadParameter.getPrivacyStrategy().decrypt(downloadedData, transaction, resourceMessage);

		return new DownloadResult(resourceMessage, decryptedDownloadedData, NemMessageType.fromInt(transaction.getMessage().getType()));
	}

	/**
	 * Gets the nem transaction.
	 *
	 * @param nemHash the nem hash
	 * @return the nem transaction
	 * @throws DownloadException the download exception
	 */
	private TransferTransaction getNemTransaction(final String nemHash) throws DownloadException {
		try {
			return (TransferTransaction) nemTransactionApi.getTransaction(nemHash).getEntity();
		} catch (Exception e) {
			throw new DownloadException(format("Failed to retrieve a Nem Transaction for hash %s", nemHash), e);
		}
	}

	/**
	 * Download using data hash.
	 *
	 * @param hash the hash
	 * @return the byte[]
	 * @throws DownloadException the download exception
	 */
	private byte[] downloadUsingDataHash(String hash) throws DownloadException {
		try {
			return downloadApi.downloadUsingDataHashUsingGET(hash);
		} catch (Exception e) {
			throw new DownloadException(format("Failed to download using data hash %s", hash), e);
		}
	}
}
