package io.nem.xpx.remote;
/*
 * Proximax REST API
 * Proximax REST API
 *
 * OpenAPI spec version: v0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

import io.nem.xpx.service.remote.RemoteAccountApi;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.AccountMetaDataPair;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;



/**
 * API tests for AccountApi.
 */
@Ignore
public class RemoteAccountApiTest extends AbstractApiTest {

	/** The api. */
	private final RemoteAccountApi api = new RemoteAccountApi(apiClient);

	/**
	 * getAllIncomingNemAddressTransactions.
	 *
	 * @return the all incoming nem address transactions using GET test
	 * @throws ApiException             if the Api call fails
	 */
	@Test
	public void getAllIncomingNemAddressTransactionsUsingGETTest() throws ApiException {
		String publicKey = this.xPubkey;
		String response = api.getAllIncomingNemAddressTransactionsUsingGET(publicKey);
		Assert.assertNotNull(response);

	}

	/**
	 * getAllNemAddressTransactions.
	 *
	 * @return the all nem address transactions using GET test
	 * @throws ApiException             if the Api call fails
	 */
	@Test
	public void getAllNemAddressTransactionsUsingGETTest() throws ApiException {
		String publicKey = this.xPubkey;
		String response = api.getAllNemAddressTransactionsUsingGET(publicKey);

		Assert.assertNotNull(response);
	}

	/**
	 * getAllNemAddressTransactionsWithPageSize.
	 *
	 * @return the all nem address transactions with page size using GET test
	 * @throws ApiException             if the Api call fails
	 */
	@Test
	public void getAllNemAddressTransactionsWithPageSizeUsingGETTest() throws ApiException {
		String publicKey = this.xPubkey;
		String pageSize = "100";
		String response = api.getAllNemAddressTransactionsWithPageSizeUsingGET(publicKey, pageSize);
		
		Assert.assertNotNull(response);
	}

	/**
	 * getAllOutgoingNemAddressTransactions.
	 *
	 * @return the all outgoing nem address transactions using GET test
	 * @throws ApiException             if the Api call fails
	 */
	@Test
	public void getAllOutgoingNemAddressTransactionsUsingGETTest() throws ApiException {
		String publicKey = this.xPubkey;
		String response = api.getAllOutgoingNemAddressTransactionsUsingGET(publicKey);

		Assert.assertNotNull(response);

	}

	/**
	 * Get the NEM Address Details
	 * 
	 * This endpoint returns the NEM Address/Account Information of a given
	 * address.
	 *
	 * @return the nem address details using GET test
	 * @throws ApiException             if the Api call fails
	 */
	@Test
	public void getNemAddressDetailsUsingGETTest() throws ApiException {
		String publicKey = this.xPubkey;
		AccountMetaDataPair response = api.getNemAddressDetailsUsingGET(publicKey);
		
		Assert.assertNotNull(response);
	}

}
