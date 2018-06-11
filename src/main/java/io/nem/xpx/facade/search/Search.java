/*
 * 
 */
package io.nem.xpx.facade.search;


import java.util.List;
import java.util.concurrent.ExecutionException;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.service.NemTransactionApi;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;

import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.service.intf.SearchApi;
import io.nem.xpx.service.local.LocalSearchApi;
import io.nem.xpx.service.remote.RemoteSearchApi;
import io.nem.xpx.utils.JsonUtils;



/**
 * The Class Search.
 */
public class Search extends AbstractFacadeService {

	/** The peer connection. */
	private final PeerConnection peerConnection;

	/** The engine. */
	protected final CryptoEngine engine;

	/** The search api. */
	protected final SearchApi searchApi;
	
	/** The is local peer connection. */
	protected final boolean isLocalPeerConnection;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection            the peer connection
	 */
	public Search(PeerConnection peerConnection) {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.searchApi = peerConnection.getSearchApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
	}
	
	
	/**
	 * Search by name.
	 *
	 * @param xPvkey the x pvkey
	 * @param xPubkey the x pubkey
	 * @param name the name
	 * @return the list
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<ResourceHashMessageJsonEntity> searchByName(String xPvkey, String xPubkey, String name)
			throws ApiException, InterruptedException, ExecutionException {
		return searchApi.searchTransactionWithNameUsingGET(xPvkey, xPubkey, name);
	}
	
	/**
	 * Search by keyword.
	 *
	 * @param xPvkey the x pvkey
	 * @param xPubkey the x pubkey
	 * @param keywords the keywords
	 * @return the list
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<ResourceHashMessageJsonEntity> searchByKeyword(String xPvkey, String xPubkey, String keywords)
			throws ApiException, InterruptedException, ExecutionException {
		return searchApi.searchTransactionWithKeywordUsingGET(xPvkey, xPubkey, keywords);
	}
	
	


	/**
	 * Search by meta data key value.
	 *
	 * @param xPvkey the x pvkey
	 * @param xPubkey the x pubkey
	 * @param key the key
	 * @param value the value
	 * @return the string
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<ResourceHashMessageJsonEntity> searchByMetaDataKeyValue(String xPvkey, String xPubkey, String key, String value)
			throws ApiException, InterruptedException, ExecutionException {
		return searchApi.searchTransactionWithMetadataKeyValuePair(xPvkey, xPubkey, key, value);
	}

}
