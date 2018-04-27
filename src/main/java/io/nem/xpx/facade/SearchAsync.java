/*
 * 
 */
package io.nem.xpx.facade;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import io.nem.ApiException;
import io.nem.xpx.callback.SearchCallback;
import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.intf.SearchApi;
import io.nem.xpx.service.local.LocalSearchApi;
import io.nem.xpx.service.remote.RemoteSearchApi;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class Search.
 */
public class SearchAsync extends Search {

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection            the peer connection
	 * @throws PeerConnectionNotFoundException the peer connection not found exception
	 */
	public SearchAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
		super(peerConnection);
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
	public CompletableFuture<String> searchByKeyword(String xPvkey, String xPubkey, String keywords,
			ServiceAsyncCallback<String> callback) {

		CompletableFuture<String> searchByKeywordAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return JsonUtils.toJson(searchApi.searchTransactionWithKeywordUsingGET(xPvkey, xPubkey, keywords));
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
	public CompletableFuture<String> searchByKeyword(String xPubkey, String keywords, ServiceAsyncCallback<String> callback) {

		CompletableFuture<String> searchByKeywordAsync = CompletableFuture.supplyAsync(() -> {
			try {
				return JsonUtils.toJson(searchApi.searchTransactionWithKeywordUsingGET(xPubkey, keywords));
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
