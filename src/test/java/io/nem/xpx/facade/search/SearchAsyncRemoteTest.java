package io.nem.xpx.facade.search;

import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.pmw.tinylog.Logger;

import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.remote.AbstractApiTest;

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;


/**
 * The Class SearchTest.
 */
@Category(RemoteIntegrationTest.class)
public class SearchAsyncRemoteTest extends AbstractApiTest {

	/**
	 * Search P key search sample.
	 */
	@Test
	public void testSearchByKeyword() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
 			Search search = new Search(remotePeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "plain");
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
		
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
 			Search search = new Search(remotePeerConnection);
 			List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "secure");
			Assert.assertNotNull(result);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			Logger.error("Exception: " + e.getMessage());
		}

	}

}
