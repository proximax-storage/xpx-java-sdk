
package io.nem.xpx.service.intf;

import java.util.concurrent.ExecutionException;

import io.nem.ApiException;
import io.nem.xpx.model.AccountMetaDataPair;


/**
 * The Interface AccountApi.
 */
public interface AccountApi {
	
	
	
	/**
	 * Gets the all incoming nem address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all incoming nem address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllIncomingNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all nem address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all nem address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all nem address transactions with page size using GET.
	 *
	 * @param publicKey the public key
	 * @param pageSize the page size
	 * @return the all nem address transactions with page size using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllNemAddressTransactionsWithPageSizeUsingGET(String publicKey, String pageSize)
			throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all outgoing nem address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all outgoing nem address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllOutgoingNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all unconfirmed nem address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all unconfirmed nem address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllUnconfirmedNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the nem address details using GET.
	 *
	 * @param publicKey the public key
	 * @return the nem address details using GET
	 * @throws ApiException the api exception
	 */
	public AccountMetaDataPair getNemAddressDetailsUsingGET(String publicKey) throws ApiException;
}
