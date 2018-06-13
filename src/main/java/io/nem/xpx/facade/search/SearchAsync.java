/*
 * 
 */
package io.nem.xpx.facade.search;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.service.model.ResourceHashMessageJsonEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


/**
 * The Class Search.
 */
public class SearchAsync extends AbstractAsyncFacadeService {

	private Search search;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection            the peer connection
	 */
	public SearchAsync(PeerConnection peerConnection) {
		this.search = new Search(peerConnection);
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

		return runAsync(
				() -> {
					try {
						return search.searchByName(xPvkey, xPubkey, name);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);
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

		return runAsync(
				() -> {
					try {
						return search.searchByKeyword(xPvkey, xPubkey, keywords);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

	/**
	 * Search by keyword.
	 *
	 * @param xPubkey the x pubkey
	 * @param keywords the keywords
	 * @param callback the callback
	 * @return the completable future
	 */
	public CompletableFuture<List<ResourceHashMessageJsonEntity>> searchByMetaDataKeyValue(String xPvkey, String xPubkey, String key, String value, ServiceAsyncCallback<List<ResourceHashMessageJsonEntity>> callback) {

		return runAsync(
				() -> {
					try {
						return search.searchByMetaDataKeyValue(xPvkey, xPubkey, key, value);
					} catch (Exception e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

}
