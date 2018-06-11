package io.nem.xpx.facade.account;

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
 * The Class AccountAsync.
 */
public class AccountAsync extends AbstractAsyncFacadeService {

	/** The account. */
	private Account account;

	/**
	 * Instantiates a new account async.
	 *
	 * @param peerConnection the peer connection
	 * @param publicKey the public key
	 */
	public AccountAsync(PeerConnection peerConnection, String publicKey) {
		this.account = new Account(peerConnection, publicKey);
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param callback the callback
	 * @return the incoming transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getIncomingTransactions(ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return account.getIncomingTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param callback the callback
	 * @return the all transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getAllTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return account.getAllTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param callback the callback
	 * @return the outgoing transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getOutgoingTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return account.getOutgoingTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param callback the callback
	 * @return the unconfirmed transactions
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getUnconfirmedTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				() -> {
					try {
						return account.getUnconfirmedTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				},  callback);
	}

}
