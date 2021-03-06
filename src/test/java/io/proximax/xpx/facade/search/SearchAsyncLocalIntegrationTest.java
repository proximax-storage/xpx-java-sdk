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

package io.proximax.xpx.facade.search;

import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.exceptions.PeerConnectionNotFoundException;
import io.proximax.xpx.facade.connection.LocalHttpPeerConnection;
import io.proximax.xpx.factory.ConnectionFactory;
import io.proximax.xpx.model.ResourceHashMessageJsonEntity;
import io.proximax.xpx.remote.AbstractApiTest;
import org.junit.Assert;
import org.junit.Test;
import org.pmw.tinylog.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.proximax.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static io.proximax.xpx.testsupport.Constants.TEST_PUBLIC_KEY;



/**
 * The Class SearchAsyncLocalIntegrationTest.
 */
public class SearchAsyncLocalIntegrationTest extends AbstractApiTest {

	/**
	 * Test search by name.
	 */
	@Test
	public void testSearchByName() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("testnet","http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
 			Search search = new Search(localPeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByName(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "alvin");
 			Logger.info(result.size());
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			Logger.error("Exception: " + e.getMessage());
		}

	}
	
	/**
	 * Search P key search sample.
	 */
	@Test
	public void testSearchByKeyword() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("testnet","http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
 			Search search = new Search(localPeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "plain");
 			Logger.info(result.size());
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			Logger.error("Exception: " + e.getMessage());
		}

	}

	/**
	 * Test search by meta data with secure.
	 */
	@Test
	public void testSearchByMetaDataWithSecure() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("testnet","http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Search search = new Search(localPeerConnection);
			List<ResourceHashMessageJsonEntity> result = search.searchByMetaDataKeyValue(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "key1","value1");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}
	}
	
	/**
	 * Test search by keyword with secure.
	 */
	@Test
	public void testSearchByKeywordWithSecure() {
		
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("testnet","http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);
		try {
			Search search = new Search(localPeerConnection);
			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "secure");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}

	}

}
