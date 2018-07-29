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

package io.proximax.xpx.service;

import io.proximax.xpx.exceptions.ApiException;
import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;



/**
 * The Class TransactionApi.
 */
public class NemTransactionApi {

	/** The node endpoint. */
	private final NodeEndpoint nodeEndpoint;

	/** The async proximax connector. */
	private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

	/**
	 * Instantiates a new proximax transaction api.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param asyncNemConnector the async proximax connector
	 */
	public NemTransactionApi(NodeEndpoint nodeEndpoint, DefaultAsyncNemConnector<ApiId> asyncNemConnector) {
		this.nodeEndpoint = nodeEndpoint;
		this.asyncNemConnector = asyncNemConnector;
	}

	/**
	 * Gets the transaction.
	 *
	 * @param hash
	 *            the hash
	 * @return the transaction
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public TransactionMetaDataPair getTransaction(String hash)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		TransactionMetaDataPair trans;
		des = asyncNemConnector
				.getAsync(nodeEndpoint, NisApiId.NIS_REST_TRANSACTION_GET, "hash=" + hash)
				.get();
		trans = new TransactionMetaDataPair(des);
		return trans;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address
	 *            the address
	 * @return the all transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getAllTransactions(String address)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the all transactions with a page size.
	 *
	 * @param address
	 *            the address
	 * @param pageSize
	 *            the page size
	 * @return the all transactions with page size
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getAllTransactionsWithPageSize(String address, String pageSize)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address + "&pageSize=" + pageSize).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @return the all transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getAllTransactions(String address, String hash)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @param id
	 *            the id
	 * @return the all transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getAllTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_ALL, "address=" + address + "&hash=" + hash + "&id=" + id).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address
	 *            the address
	 * @return the incoming transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getIncomingTransactions(String address)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING, "address=" + address).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @return the incoming transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING, "address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @param id
	 *            the id
	 * @return the incoming transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getIncomingTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_INCOMING, "address=" + address + "&hash=" + hash + "&id=" + id)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address
	 *            the address
	 * @return the outgoing transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getOutgoingTransactions(String address)
			throws InterruptedException, ExecutionException, ApiException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING, "address=" + address).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @return the outgoing transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash)
			throws InterruptedException, ExecutionException, ApiException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING, "address=" + address + "&hash=" + hash).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param address
	 *            the address
	 * @param hash
	 *            the hash
	 * @param id
	 *            the id
	 * @return the outgoing transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getOutgoingTransactions(String address, String hash, String id)
			throws InterruptedException, ExecutionException, ApiException {
		List<TransactionMetaDataPair> list;
		Deserializer des;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_TRANSFERS_OUTGOING, "address=" + address + "&hash=" + hash + "&id=" + id)
				.get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param address
	 *            the address
	 * @return the unconfirmed transactions
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 * @throws ApiException
	 *             the api exception
	 */
	public List<TransactionMetaDataPair> getUnconfirmedTransactions(String address)
			throws InterruptedException, ExecutionException, ApiException {
		Deserializer des;
		List<TransactionMetaDataPair> list;
		des = asyncNemConnector.getAsync(nodeEndpoint,
				NisApiId.NIS_REST_ACCOUNT_UNCONFIRMED, "address=" + address).get();
		list = (ArrayList<TransactionMetaDataPair>) des.readObjectArray("data", TransactionMetaDataPair::new);
		return list;
	}

	/**
	 * Send transfer transaction.
	 *
	 * @param data the data
	 * @param signature the signature
	 * @return the proximax announce result
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws ApiException the api exception
	 */
	public NemAnnounceResult sendTransferTransaction(byte[] data, byte[] signature) throws InterruptedException, ExecutionException, ApiException {
		final RequestAnnounce request = new RequestAnnounce(data, signature);
		final CompletableFuture<Deserializer> future = announceTransaction(request);
		Deserializer transDes = future.get();
		return new NemAnnounceResult(transDes);
	}

	/**
	 * Announce transaction.
	 *
	 * @param request
	 *            the request
	 * @return the completable future
	 */
	public CompletableFuture<Deserializer> announceTransaction(final RequestAnnounce request) {
		final CompletableFuture<Deserializer> des = asyncNemConnector.postAsync(nodeEndpoint,
				NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, new HttpJsonPostRequest(request));

		return des;
	}

}
