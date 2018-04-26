/*
 * 
 */
package io.nem.xpx.facade;


import java.util.concurrent.ExecutionException;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import io.nem.ApiException;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.service.intf.SearchApi;
import io.nem.xpx.service.local.LocalSearchApi;
import io.nem.xpx.service.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.remote.RemoteSearchApi;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class Search.
 */
public class Search extends FacadeService {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	protected CryptoEngine engine;

	protected SearchApi searchApi;
	protected boolean isLocalPeerConnection = false;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 */
	public Search(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

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

	public String searchByKeyword(String xPvkey, String xPubkey, String keywords)
			throws ApiException, InterruptedException, ExecutionException {
		return JsonUtils.toJson(searchApi.searchTransactionWithKeywordUsingGET(xPvkey, xPubkey, keywords));
	}

	public String searchByKeyword(String xPubkey, String keywords)
			throws io.nem.ApiException, InterruptedException, ExecutionException {
		return JsonUtils.toJson(searchApi.searchTransactionWithKeywordUsingGET(xPubkey, keywords));
	}

	public String searchByMetaDataKeyValue(String xPvkey, String xPubkey, String key, String value)
			throws ApiException, InterruptedException, ExecutionException {
		return JsonUtils.toJson(searchApi.searchTransactionWithMetadataKeyValuePair(xPvkey, xPubkey, key, value));
	}

}
