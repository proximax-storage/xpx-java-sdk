package io.nem.xpx.service.intf;

import java.util.concurrent.ExecutionException;

import io.nem.ApiException;
import io.nem.xpx.model.RequestAnnounceDataSignature;


/**
 * The Interface TransactionAndAnnounceApi.
 */
public interface TransactionAndAnnounceApi {
	
	/**
	 * Announce request publish data signature using POST.
	 *
	 * @param requestAnnounceDataSignature the request announce data signature
	 * @return the string
	 * @throws ApiException the api exception
	 */
	public String announceRequestPublishDataSignatureUsingPOST(RequestAnnounceDataSignature requestAnnounceDataSignature) throws  ApiException, InterruptedException, ExecutionException;
	
	/**
	 * Gets the XPX transaction using GET.
	 *
	 * @param nemHash the nem hash
	 * @return the XPX transaction using GET
	 * @throws ApiException the api exception
	 */
	public String getXPXTransactionUsingGET(String nemHash) throws ApiException, InterruptedException, ExecutionException;
}
