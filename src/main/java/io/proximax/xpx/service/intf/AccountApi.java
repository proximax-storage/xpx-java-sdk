
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

package io.proximax.xpx.service.intf;

import java.util.concurrent.ExecutionException;

import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.model.AccountMetaDataPair;




/**
 * The Interface AccountApi.
 */
public interface AccountApi {
	
	
	
	/**
	 * Gets the all incoming proximax address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all incoming proximax address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllIncomingNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all proximax address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all proximax address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all proximax address transactions with page size using GET.
	 *
	 * @param publicKey the public key
	 * @param pageSize the page size
	 * @return the all proximax address transactions with page size using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllNemAddressTransactionsWithPageSizeUsingGET(String publicKey, String pageSize)
			throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all outgoing proximax address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all outgoing proximax address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllOutgoingNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the all unconfirmed proximax address transactions using GET.
	 *
	 * @param publicKey the public key
	 * @return the all unconfirmed proximax address transactions using GET
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public String getAllUnconfirmedNemAddressTransactionsUsingGET(String publicKey) throws ApiException, InterruptedException, ExecutionException;

	/**
	 * Gets the proximax address details using GET.
	 *
	 * @param publicKey the public key
	 * @return the proximax address details using GET
	 * @throws ApiException the api exception
	 */
	public AccountMetaDataPair getNemAddressDetailsUsingGET(String publicKey) throws ApiException;
}
