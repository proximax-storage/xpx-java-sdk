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

/*
 * 
 */
package io.proximax.xpx.facade.transaction;

import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.exceptions.PeerConnectionNotFoundException;
import io.proximax.xpx.facade.AbstractFacadeService;
import io.proximax.xpx.facade.connection.PeerConnection;
import io.proximax.xpx.service.NemTransactionApi;
import io.proximax.xpx.service.intf.AccountApi;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * The Class Account.
 */
public class Transaction extends AbstractFacadeService {

	/** The peer connection. */
	private final PeerConnection peerConnection;

	/** The engine. */
	private final CryptoEngine engine;

	/** The account api. */
	private final AccountApi accountApi;

	/** The proximax transaction api. */
	protected final NemTransactionApi nemTransactionApi;

	/** The is local peer connection. */
	private final boolean isLocalPeerConnection;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public Transaction(PeerConnection peerConnection) {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.accountApi = peerConnection.getAccountApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.peerConnection = peerConnection;
		this.nemTransactionApi = peerConnection.getNemTransactionApi();
		this.engine = CryptoEngines.ed25519Engine();
	}

	/**
	 * Gets the transaction.
	 *
	 * @param hash the hash
	 * @return the transaction
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public TransactionMetaDataPair getTransaction(String hash)
			throws ApiException, InterruptedException, ExecutionException {

		TransactionMetaDataPair transaction = nemTransactionApi.getTransaction(hash);

		return transaction;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the incoming transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<TransactionMetaDataPair> getIncomingTransactions(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {

		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getIncomingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the all transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<TransactionMetaDataPair> getAllTransactions(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {

		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getAllTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param publicKey            the public key
	 * @return the outgoing transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 */
	public List<TransactionMetaDataPair> getOutgoingTransactions(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getOutgoingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param publicKey            the public key
	 * @return the unconfirmed transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 */
	public List<TransactionMetaDataPair> getUnconfirmedTransactions(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getUnconfirmedTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

}
