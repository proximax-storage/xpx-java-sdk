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

import io.proximax.xpx.callback.ServiceAsyncCallback;
import io.proximax.xpx.facade.AbstractAsyncFacadeService;
import io.proximax.xpx.facade.connection.PeerConnection;
import io.proximax.xpx.model.ResourceHashMessageJsonEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;



/**
 * The Class Search.
 */
public class SearchAsync extends AbstractAsyncFacadeService {

	/** The search. */
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
	 * @param xPvkey the x pvkey
	 * @param xPubkey the x pubkey
	 * @param key the key
	 * @param value the value
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
