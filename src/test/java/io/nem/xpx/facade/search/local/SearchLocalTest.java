package io.nem.xpx.facade.search.local;

import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.node.NodeEndpoint;
import org.pmw.tinylog.Logger;

import io.nem.xpx.facade.search.Search;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.remote.AbstractApiTest;

/**
 * The Class SearchTest.
 */
@Category(IntegrationTest.class)
public class SearchLocalTest extends AbstractApiTest {

	/**
	 * Search P key search sample.
	 */
	@Test
	public void testSearchByKeyword() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Search search = new Search(localPeerConnection);
			String result = search.searchByKeyword(this.xPubkey, "plain");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}
		Assert.assertTrue(false);

	}

	@Test
	public void testSearchByMetaDataWithSecure() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Search search = new Search(localPeerConnection);
			String result = search.searchByMetaDataKeyValue(this.xPvkey, this.xPubkey, "key1","value1");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}
		Assert.assertTrue(false);
	}
	
	@Test
	public void testSearchByKeywordWithSecure() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Search search = new Search(localPeerConnection);
			String result = search.searchByKeyword(this.xPvkey, this.xPubkey, "secure");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}
		Assert.assertTrue(false);

	}

}
