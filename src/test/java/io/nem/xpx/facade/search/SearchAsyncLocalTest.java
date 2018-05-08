package io.nem.xpx.facade.search;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.pmw.tinylog.Logger;
import io.nem.xpx.facade.search.Search;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.remote.AbstractApiTest;


/**
 * The Class SearchTest.
 */
@Category(LocalIntegrationTest.class)
public class SearchAsyncLocalTest extends AbstractApiTest {

	
	
	
	@Test
	public void testSearchByName() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
 			Search search = new Search(localPeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByName(this.xPvkey,this.xPubkey, "alvin");
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
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
 			Search search = new Search(localPeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(this.xPvkey,this.xPubkey, "plain");
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
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Search search = new Search(localPeerConnection);
			List<ResourceHashMessageJsonEntity> result = search.searchByMetaDataKeyValue(this.xPvkey, this.xPubkey, "key1","value1");
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
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);
		try {
			Search search = new Search(localPeerConnection);
			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(this.xPvkey, this.xPubkey, "secure");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}

	}

}
