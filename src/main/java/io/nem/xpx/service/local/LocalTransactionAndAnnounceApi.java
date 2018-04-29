package io.nem.xpx.service.local;

import java.util.concurrent.ExecutionException;

import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.utils.HexEncoder;
import io.nem.ApiException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.service.NemTransactionApi;
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
			RequestAnnounceDataSignature requestAnnounceDataSignature) throws ApiException, InterruptedException, ExecutionException {
		
		NemAnnounceResult announceResult = NemTransactionApi.sendTransferTransaction(
				HexEncoder.getBytes(requestAnnounceDataSignature.getData()),
				HexEncoder.getBytes(requestAnnounceDataSignature.getSignature()));
		
		TransactionMetaDataPair transactionMpair = NemTransactionApi
				.getTransaction(announceResult.getTransactionHash().toString());
		TransferTransaction transferTransaction = ((TransferTransaction) transactionMpair.getEntity());
		return JsonSerializer.serializeToJson(transferTransaction).toJSONString();
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.TransactionAndAnnounceApi#getXPXTransactionUsingGET(java.lang.String)
	 */
	@Override
	public String getXPXTransactionUsingGET(String nemHash) throws ApiException, InterruptedException, ExecutionException {
		return JsonSerializer.serializeToJson(NemTransactionApi.getTransaction(nemHash)).toJSONString();
	}

}
