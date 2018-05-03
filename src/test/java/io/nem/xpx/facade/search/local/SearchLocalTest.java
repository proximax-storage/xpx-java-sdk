package io.nem.xpx.facade.search.local;

import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.node.NodeEndpoint;
import io.nem.ApiException;
import io.nem.xpx.facade.search.Search;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
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
			String s = search.searchByKeyword(this.xPubkey, "plain");

		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(false);

	}

	@Test
	public void testSearchByMetaDataWithSecure() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Search search = new Search(localPeerConnection);
			String s = search.searchByMetaDataKeyValue(this.xPvkey, this.xPubkey, "key1","value1");
	
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(false);
	}
	
	@Test
	public void testSearchByKeywordWithSecure() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Search search = new Search(localPeerConnection);
			String s = search.searchByKeyword(this.xPvkey, this.xPubkey, "secure");

		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(false);

	}

}
