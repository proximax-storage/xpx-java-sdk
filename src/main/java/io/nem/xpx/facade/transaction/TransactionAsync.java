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

public class TransactionAsync extends AbstractAsyncFacadeService {

	private Transaction transaction;

	public TransactionAsync(PeerConnection peerConnection) {
		this.transaction = new Transaction(peerConnection);
	}

	public CompletableFuture<TransactionMetaDataPair> getTransaction(String hash,
			ServiceAsyncCallback<TransactionMetaDataPair> callback) {

		return runAsync(
				parameter -> {
					try {
						return transaction.getTransaction(hash);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, hash,  callback);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getAllTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return transaction.getAllTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, publicKey,  callback);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getIncomingTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return transaction.getIncomingTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, publicKey,  callback);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getOutgoingTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return transaction.getOutgoingTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, publicKey,  callback);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getUnconfirmedTransactions(String publicKey,
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return transaction.getUnconfirmedTransactions(publicKey);
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, publicKey,  callback);
	}

}
