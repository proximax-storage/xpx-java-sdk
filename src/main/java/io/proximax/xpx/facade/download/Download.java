/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * 
 */
package io.proximax.xpx.facade.download;

import io.proximax.xpx.exceptions.PeerConnectionNotFoundException;
import io.proximax.xpx.facade.AbstractFacadeService;
import io.proximax.xpx.facade.connection.PeerConnection;
import io.proximax.xpx.model.NemMessageType;
import io.proximax.xpx.service.NemTransactionApi;
import io.proximax.xpx.service.intf.DownloadApi;
import io.proximax.xpx.service.model.buffers.ResourceHashMessage;
import org.nem.core.model.TransferTransaction;

import static java.lang.String.format;



/**
 * The Class Download.
 */
public class Download extends AbstractFacadeService {

	/** The download api. */
	private final DownloadApi downloadApi;

	/** The proximax transaction api. */
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
		
		if(downloadParameter.getNemHash() != null) {
			final TransferTransaction transaction = getNemTransaction(downloadParameter.getNemHash());
			final byte[] decodedDataFromNemMessage = downloadParameter.getPrivacyStrategy().decodeTransaction(transaction);
			final ResourceHashMessage resourceMessage = deserializeResourceMessageHash(decodedDataFromNemMessage);
			final byte[] downloadedData = downloadUsingDataHash(resourceMessage.hash());
			final byte[] decryptedDownloadedData = downloadParameter.getPrivacyStrategy().decrypt(downloadedData, transaction, resourceMessage);
			return new DownloadResult(resourceMessage, decryptedDownloadedData, NemMessageType.fromInt(transaction.getMessage().getType()));
		}else {
			final byte[] downloadedData = downloadUsingDataHash(downloadParameter.getIpfsHash());
			final byte[] decryptedDownloadedData = downloadParameter.getPrivacyStrategy().decrypt(downloadedData, null, null);
			return new DownloadResult(null, decryptedDownloadedData, null);
		}
	}

	/**
	 * Gets the proximax transaction.
	 *
	 * @param nemHash the proximax hash
	 * @return the proximax transaction
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
