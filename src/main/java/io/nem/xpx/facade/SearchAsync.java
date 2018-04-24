/*
 * 
 */
package io.nem.xpx.facade;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import io.nem.api.ApiException;
import io.nem.xpx.LocalSearchApi;
import io.nem.xpx.RemoteSearchApi;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.intf.SearchApi;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.SearchCallback;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class Search.
 */
public class SearchAsync {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;

	private SearchApi searchApi;
	private boolean isLocalPeerConnection = false;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 */
	public SearchAsync(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.searchApi = new RemoteSearchApi();
		} else {
			this.isLocalPeerConnection = true;
			this.searchApi = new LocalSearchApi();
		}

		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
	}

	public CompletableFuture<String> searchByKeyword(String xPvkey, String xPubkey, String keywords,
			SearchCallback callback) {

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

	public CompletableFuture<String> searchByKeyword(String xPubkey, String keywords, SearchCallback callback) {

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
