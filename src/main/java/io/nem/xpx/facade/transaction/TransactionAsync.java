/*
 * 
 */
package io.nem.xpx.facade.transaction;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.connection.PeerConnection;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
public class TransactionAsync extends Transaction {

	/**
	 * Instantiates a new transaction async.
	 *
	 * @param peerConnection the peer connection
	 */
	public TransactionAsync(PeerConnection peerConnection) {
		super(peerConnection);
	}

	/**
	 * Gets the transaction.
	 *
	 * @param hash the hash
	 * @param callback the callback
	 * @return the transaction
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public CompletableFuture<TransactionMetaDataPair> getTransaction(String hash,
			ServiceAsyncCallback<TransactionMetaDataPair> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<TransactionMetaDataPair> transactionAsync = CompletableFuture.supplyAsync(() -> {
			try {
				TransactionMetaDataPair transaction = nemTransactionApi.getTransaction(hash);
				return transaction;
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return transactionAsync;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param publicKey            the public key
	 * @param callback the callback
	 * @return the all transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getAllTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> transactionAsync = CompletableFuture.supplyAsync(() -> {
			try {
				List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
				List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
						.getAllTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
				for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
					if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
						returnListOfTxnMetaDataPair.add(metaDataPair);
					}
				}
				return returnListOfTxnMetaDataPair;
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return transactionAsync;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param publicKey            the public key
	 * @param callback the callback
	 * @return the incoming transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getIncomingTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> transactionAsync = CompletableFuture.supplyAsync(() -> {
			try {
				List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
				List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi.getIncomingTransactions(
						Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
				for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
					if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
						returnListOfTxnMetaDataPair.add(metaDataPair);
					}
				}
				return returnListOfTxnMetaDataPair;
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return transactionAsync;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param publicKey            the public key
	 * @param callback the callback
	 * @return the outgoing transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getOutgoingTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> transactionAsync = CompletableFuture.supplyAsync(() -> {
			try {
				List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
				List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi.getOutgoingTransactions(
						Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
				for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
					if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
						returnListOfTxnMetaDataPair.add(metaDataPair);
					}
				}
				return returnListOfTxnMetaDataPair;
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return transactionAsync;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param publicKey            the public key
	 * @param callback the callback
	 * @return the unconfirmed transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException             the interrupted exception
	 * @throws ExecutionException             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getUnconfirmedTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> transactionAsync = CompletableFuture.supplyAsync(() -> {
			try {
				List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
				List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi.getUnconfirmedTransactions(
						Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
				for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
					if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
						returnListOfTxnMetaDataPair.add(metaDataPair);
					}
				}
				return returnListOfTxnMetaDataPair;
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return transactionAsync;
	}

}
