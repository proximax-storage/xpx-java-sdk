/*
 * 
 */
package io.nem.xpx.facade.transaction;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;


/**
 * The Class TransactionAsync.
 */
public class TransactionAsync extends AbstractAsyncFacadeService {

	/** The transaction. */
	private Transaction transaction;

	/**
	 * Instantiates a new transaction async.
	 *
	 * @param peerConnection the peer connection
	 */
	public TransactionAsync(PeerConnection peerConnection) {
		this.transaction = new Transaction(peerConnection);
	}

	/**
	 * Gets the transaction.
	 *
	 * @param hash the hash
	 * @param callback the callback
	 * @return the transaction
	 */
	public CompletableFuture<TransactionMetaDataPair> getTransaction(String hash,
			ServiceAsyncCallback<TransactionMetaDataPair> callback) {

		return runAsync(
				() -> {
					try {
						return transaction.getTransaction(hash);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param publicKey the public key
	 * @param callback the callback
	 * @return the all transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getAllTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return transaction.getAllTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param publicKey the public key
	 * @param callback the callback
	 * @return the incoming transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getIncomingTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return transaction.getIncomingTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param publicKey the public key
	 * @param callback the callback
	 * @return the outgoing transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getOutgoingTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return transaction.getOutgoingTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param publicKey the public key
	 * @param callback the callback
	 * @return the unconfirmed transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getUnconfirmedTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return transaction.getUnconfirmedTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

}
