/*
 * 
 */
package io.nem.xpx.facade.search;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class Search.
 */
public class SearchAsync extends Search {

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection            the peer connection
	 */
	public SearchAsync(PeerConnection peerConnection) {
		super(peerConnection);
	}

	
	/**
	 * Search by name.
	 *
	 * @param xPvkey the x pvkey
	 * @param xPubkey the x pubkey
	 * @param name the name
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<List<ResourceHashMessageJsonEntity>> searchByName(String xPvkey, String xPubkey, String name,
			ServiceAsyncCallback<List<ResourceHashMessageJsonEntity>> callback) {

		CompletableFuture<List<ResourceHashMessageJsonEntity>> searchByNameAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return searchApi.searchTransactionWithNameUsingGET(xPvkey, xPubkey, name);
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return searchByNameAsync;

	}
	
	/**
	 * Search by keyword.
	 *
	 * @param xPvkey the x pvkey
	 * @param xPubkey the x pubkey
	 * @param keywords the keywords
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<List<ResourceHashMessageJsonEntity>> searchByKeyword(String xPvkey, String xPubkey, String keywords,
			ServiceAsyncCallback<List<ResourceHashMessageJsonEntity>> callback) {

		CompletableFuture<List<ResourceHashMessageJsonEntity>> searchByKeywordAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return searchApi.searchTransactionWithKeywordUsingGET(xPvkey, xPubkey, keywords);
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return searchByKeywordAsync;

	}

	/**
	 * Search by keyword.
	 *
	 * @param xPubkey the x pubkey
	 * @param keywords the keywords
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<List<ResourceHashMessageJsonEntity>> searchByKeyword(String xPubkey, String keywords, ServiceAsyncCallback<List<ResourceHashMessageJsonEntity>> callback) {

		CompletableFuture<List<ResourceHashMessageJsonEntity>> searchByKeywordAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return searchApi.searchTransactionWithKeywordUsingGET(xPubkey, keywords);
			} catch (ApiException | InterruptedException | ExecutionException e) {
				throw new CompletionException(e);
			}
		}).thenApply(n -> {
			// call the callback?
			callback.process(n);
			return n;
		});

		return searchByKeywordAsync;

	}

}
