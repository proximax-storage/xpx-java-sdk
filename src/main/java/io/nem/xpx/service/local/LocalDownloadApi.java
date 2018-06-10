/*
 * Proximax P2P Storage REST API
 * Proximax P2P Storage REST API
 *
 * OpenAPI spec version: v0.0.1
 * Contact: alvin.reyes@botmill.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.nem.xpx.service.local;

import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.MessageDigestNotMatchException;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.DownloadApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import org.apache.commons.codec.binary.Base64;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;



/**
 * The Class LocalDownloadApi.
 */
public class LocalDownloadApi implements DownloadApi {

	/** The nem transaction api. */
	private final NemTransactionApi nemTransactionApi;
	private final IPFS proximaxIfpsConnection;

	public LocalDownloadApi(NemTransactionApi nemTransactionApi, IPFS proximaxIfpsConnection) {
		this.nemTransactionApi = nemTransactionApi;
		this.proximaxIfpsConnection = proximaxIfpsConnection;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.DownloadApi#downloadUsingDataHashUsingGET(java.lang.String)
	 */
	@Override
	public byte[] downloadUsingDataHashUsingGET(String hash) throws ApiException, IOException {
		return proximaxIfpsConnection.cat(Multihash.fromBase58(hash));
	}
	
	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.DownloadApi#downloadTextUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public byte[] downloadTextUsingGET(String nemHash, String transferMode) throws ApiException, IOException, InterruptedException, ExecutionException {
		TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
		TransferTransaction transfer = ((TransferTransaction) transactionMetaDataPair.getEntity());
		ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(transfer.getMessage().getEncodedPayload())));
		return proximaxIfpsConnection.cat(Multihash.fromBase58(resourceMessage.hash()));
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.DownloadApi#downloadBinaryUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public byte[] downloadBinaryUsingGET(String nemHash, String transferMode) throws ApiException, IOException, InterruptedException, ExecutionException {
		TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
		TransferTransaction transfer = ((TransferTransaction) transactionMetaDataPair.getEntity());
		ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(transfer.getMessage().getEncodedPayload())));
		
		return proximaxIfpsConnection.cat(Multihash.fromBase58(resourceMessage.hash()));
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.DownloadApi#downloadFileUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public byte[] downloadFileUsingGET(String nemHash, String transferMode) throws ApiException, IOException, InterruptedException, ExecutionException {
		TransactionMetaDataPair transactionMetaDataPair = nemTransactionApi.getTransaction(nemHash);
		TransferTransaction transfer = ((TransferTransaction) transactionMetaDataPair.getEntity());
		ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(ByteBuffer.wrap(Base64.decodeBase64(transfer.getMessage().getEncodedPayload())));
		
		return proximaxIfpsConnection.cat(Multihash.fromBase58(resourceMessage.hash()));
	}
	
	/**
	 * Load resource.
	 *
	 * @param resourceMessage the resource message
	 * @return the byte[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 */
	public byte[] loadResource(ResourceHashMessage resourceMessage) throws IOException, ApiException {
		return proximaxIfpsConnection.cat(Multihash.fromBase58(resourceMessage.hash()));
	}
	
	
	
	/**
	 * Validate digest.
	 *
	 * @param binaryData the binary data
	 * @param digest the digest
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws MessageDigestNotMatchException the message digest not match exception
	 */
	private void validateDigest(byte[] binaryData, String digest)
			throws NoSuchAlgorithmException, MessageDigestNotMatchException {

		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		String binaryDigest = new String(org.bouncycastle.util.encoders.Hex.encode(messageDigest.digest(binaryData)));
	
		if (!binaryDigest.equals(digest)) {
			throw new MessageDigestNotMatchException(
					"Message Digest Validation Failed. Resource requested seems to be corrupted.");
		}
	}


    
    /* (non-Javadoc)
     * @see io.nem.xpx.DownloadApiInterface#downloadStreamUsingHashUsingPOST(java.lang.String)
     */
//    @Override
//    public byte[] downloadStreamUsingHashUsingPOST(String hash) throws ApiException, IOException {
//    	return XpxSdkGlobalConstants.getProximaxConnection().cat(Multihash.fromBase58(hash));
//    }
}
