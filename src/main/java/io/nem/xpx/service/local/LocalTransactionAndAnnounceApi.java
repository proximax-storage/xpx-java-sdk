package io.nem.xpx.service.local;

import io.nem.ApiException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;


/**
 * The Class LocalTransactionAndAnnounceApi.
 */
public class LocalTransactionAndAnnounceApi implements TransactionAndAnnounceApi {

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.TransactionAndAnnounceApi#announceRequestPublishDataSignatureUsingPOST(io.nem.xpx.model.RequestAnnounceDataSignature)
	 */
	@Override
	public String announceRequestPublishDataSignatureUsingPOST(
			RequestAnnounceDataSignature requestAnnounceDataSignature) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.TransactionAndAnnounceApi#getXPXTransactionUsingGET(java.lang.String)
	 */
	@Override
	public String getXPXTransactionUsingGET(String nemHash) throws ApiException {
		// TODO Auto-generated method stub
		return null;
	}

}
