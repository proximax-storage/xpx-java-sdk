/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * 
 */
package io.proximax.xpx.facade.search;


import java.util.List;
import java.util.concurrent.ExecutionException;

import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.exceptions.PeerConnectionNotFoundException;
import io.proximax.xpx.facade.AbstractFacadeService;
import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;

import io.proximax.xpx.facade.connection.PeerConnection;
import io.proximax.xpx.model.ResourceHashMessageJsonEntity;
import io.proximax.xpx.service.intf.SearchApi;



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
