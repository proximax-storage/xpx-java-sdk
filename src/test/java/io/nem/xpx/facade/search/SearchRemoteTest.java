package io.nem.xpx.facade.search;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.node.NodeEndpoint;
import org.pmw.tinylog.Logger;

import io.nem.xpx.facade.search.Search;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.remote.AbstractApiTest;


/**
 * The Class SearchTest.
 */
@Category(RemoteIntegrationTest.class)
public class SearchRemoteTest extends AbstractApiTest {

	/**
	 * Search P key search sample.
	 */
	@Test
	public void testSearchByKeyword() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
 			Search search = new Search(remotePeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(this.xPvkey,this.xPubkey, "plain");
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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
 			Search search = new Search(remotePeerConnection);
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
		
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
 			Search search = new Search(remotePeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(this.xPvkey, this.xPubkey, "secure");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}

	}

}
