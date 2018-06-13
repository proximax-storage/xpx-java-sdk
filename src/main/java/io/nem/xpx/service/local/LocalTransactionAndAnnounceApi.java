package io.nem.xpx.service.local;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.utils.HexEncoder;

import java.util.concurrent.ExecutionException;



/**
 * The Class LocalTransactionAndAnnounceApi.
 */
public class LocalTransactionAndAnnounceApi implements TransactionAndAnnounceApi {

	/** The nem transaction api. */
	private final NemTransactionApi nemTransactionApi;

	/**
	 * Instantiates a new local transaction and announce api.
	 *
	 * @param nemTransactionApi the nem transaction api
	 */
	public LocalTransactionAndAnnounceApi(NemTransactionApi nemTransactionApi) {
		this.nemTransactionApi = nemTransactionApi;
	}


	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.TransactionAndAnnounceApi#announceRequestPublishDataSignatureUsingPOST(io.nem.xpx.model.RequestAnnounceDataSignature)
	 */
	@Override
	public String announceRequestPublishDataSignatureUsingPOST(
			RequestAnnounceDataSignature requestAnnounceDataSignature) throws ApiException, InterruptedException, ExecutionException {
		
		NemAnnounceResult announceResult = nemTransactionApi.sendTransferTransaction(
				HexEncoder.getBytes(requestAnnounceDataSignature.getData()),
				HexEncoder.getBytes(requestAnnounceDataSignature.getSignature()));
		
		TransactionMetaDataPair transactionMpair = nemTransactionApi
				.getTransaction(announceResult.getTransactionHash().toString());
		TransferTransaction transferTransaction = ((TransferTransaction) transactionMpair.getEntity());
		return JsonSerializer.serializeToJson(transferTransaction).toJSONString();
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.TransactionAndAnnounceApi#getXPXTransactionUsingGET(java.lang.String)
	 */
	@Override
	public String getXPXTransactionUsingGET(String nemHash) throws ApiException, InterruptedException, ExecutionException {
		return JsonSerializer.serializeToJson(nemTransactionApi.getTransaction(nemHash)).toJSONString();
	}

}
