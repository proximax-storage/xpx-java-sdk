
package io.nem.xpx.service.intf;

import io.nem.ApiException;
import io.nem.xpx.service.model.AccountMetaDataPair;

public interface AccountApi {

	public String getAllIncomingNemAddressTransactionsUsingGET(String publicKey) throws ApiException;

	public String getAllNemAddressTransactionsUsingGET(String publicKey) throws ApiException;

	public String getAllNemAddressTransactionsWithPageSizeUsingGET(String publicKey, String pageSize)
			throws ApiException;

	public String getAllOutgoingNemAddressTransactionsUsingGET(String publicKey) throws ApiException;

	public String getAllUnconfirmedNemAddressTransactionsUsingGET(String publicKey) throws ApiException;

	public AccountMetaDataPair getNemAddressDetailsUsingGET(String publicKey) throws ApiException;
}
