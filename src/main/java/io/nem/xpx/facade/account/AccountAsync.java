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

public class AccountAsync extends AbstractAsyncFacadeService {

	private Account account;

	public AccountAsync(PeerConnection peerConnection, String publicKey) {
		this.account = new Account(peerConnection, publicKey);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getIncomingTransactions(ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return account.getIncomingTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, null,  callback);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getAllTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return account.getAllTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, null,  callback);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getOutgoingTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return account.getOutgoingTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, null,  callback);
	}

	public CompletableFuture<List<TransactionMetaDataPair>> getUnconfirmedTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback) {

		return runAsync(
				parameter -> {
					try {
						return account.getUnconfirmedTransactions();
					} catch (ApiException | InterruptedException | ExecutionException e) {
						throw new CompletionException(e);
					}
				}, null,  callback);
	}

}
