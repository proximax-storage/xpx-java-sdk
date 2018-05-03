package io.nem.xpx.service.local;

import io.nem.ApiException;
import io.nem.xpx.model.AccountMetaDataPair;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.AccountApi;
import io.nem.xpx.utils.JsonUtils;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.JsonSerializer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * The Class LocalAccountApi.
 */
public class LocalAccountApi implements AccountApi {

	private final NemTransactionApi nemTransactionApi;

	public LocalAccountApi(NemTransactionApi nemTransactionApi) {
		this.nemTransactionApi = nemTransactionApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.AccountApi#getAllIncomingNemAddressTransactionsUsingGET(java.lang.String)
	 */
	@Override
	public String getAllIncomingNemAddressTransactionsUsingGET(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getIncomingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				transactionString.add(JsonSerializer.serializeToJson(metaDataPair.getEntity()).toJSONString());
			}
		}
		return JsonUtils.toJson(transactionString);
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.AccountApi#getAllNemAddressTransactionsUsingGET(java.lang.String)
	 */
	@Override
	public String getAllNemAddressTransactionsUsingGET(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getAllTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				transactionString.add(JsonSerializer.serializeToJson(metaDataPair.getEntity()).toJSONString());
			}
		}
		return JsonUtils.toJson(transactionString);
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.AccountApi#getAllNemAddressTransactionsWithPageSizeUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAllNemAddressTransactionsWithPageSizeUsingGET(String publicKey, String pageSize)
			throws ApiException, InterruptedException, ExecutionException {

		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getAllTransactionsWithPageSize(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded(),
						pageSize);
		List<String> transactionString = new ArrayList<String>();
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				transactionString.add(JsonSerializer.serializeToJson(metaDataPair.getEntity()).toJSONString());
			}
		}
		return JsonUtils.toJson(transactionString);
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.AccountApi#getAllOutgoingNemAddressTransactionsUsingGET(java.lang.String)
	 */
	@Override
	public String getAllOutgoingNemAddressTransactionsUsingGET(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getOutgoingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				transactionString.add(JsonSerializer.serializeToJson(metaDataPair.getEntity()).toJSONString());
			}
		}

		return JsonUtils.toJson(transactionString);
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.AccountApi#getAllUnconfirmedNemAddressTransactionsUsingGET(java.lang.String)
	 */
	@Override
	public String getAllUnconfirmedNemAddressTransactionsUsingGET(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getUnconfirmedTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			transactionString.add(JsonSerializer.serializeToJson(metaDataPair.getEntity()).toJSONString());
		}

		return JsonUtils.toJson(transactionString);
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.AccountApi#getNemAddressDetailsUsingGET(java.lang.String)
	 */
	@Override
	public AccountMetaDataPair getNemAddressDetailsUsingGET(String publicKey) throws ApiException {

		return null;
	}

	/**
	 * Check if txn have XPX mosaic.
	 *
	 * @param transaction the transaction
	 * @return true, if successful
	 */
	protected boolean checkIfTxnHaveXPXMosaic(Transaction transaction) {

		if (transaction instanceof TransferTransaction) {
			Iterator<Mosaic> mosaicIter = ((TransferTransaction) transaction).getMosaics().iterator();
			while (mosaicIter.hasNext()) {
				Mosaic mosaic = mosaicIter.next();
				if (mosaic.getMosaicId().getNamespaceId().getRoot().toString().equals("prx")
						&& mosaic.getMosaicId().getName().equals("xpx")) {
					return true;
				}
			}

		}
		return false;
	}

}
