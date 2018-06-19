package io.nem.xpx.facade.search;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Assert;
import org.junit.Test;
import org.pmw.tinylog.Logger;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;



/**
 * The Class SearchLocalIntegrationTest.
 */
public class SearchLocalIntegrationTest extends AbstractApiTest {

	
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
