package io.nem.xpx.facade.search.local;

import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.node.NodeEndpoint;
import io.nem.ApiException;
import io.nem.xpx.facade.search.Search;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.remote.AbstractApiTest;

/**
 * The Class SearchTest.
 */
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
			System.out.println(s);
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
			System.out.println(s);
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
			System.out.println(s);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(false);

	}

	// /**
	// * Search public key search sample.
	// */
	// @Test
	// public void searchPublicKeySearchSample() {
	// RemotePeerConnection remotePeerConnection = new
	// RemotePeerConnection(searchNodeBasePath);
	// Search search = new Search(remotePeerConnection);
	// try {
	// String s = search.searchAllPublicTransactionWithKeyword(this.xPubkey,
	// "alvinreyes");
	//
	// if(s.contains("alvinreyes")) {
	// LOGGER.info("Found it");
	// Assert.assertTrue(true);
	// return;
	// }
	// Assert.assertTrue(false);
	// } catch (UnsupportedEncodingException | InterruptedException |
	// ExecutionException | ApiException e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// /**
	// * Search key search regex sample.
	// */
	// @Test
	// public void searchKeySearchRegexSample() {
	// RemotePeerConnection remotePeerConnection = new
	// RemotePeerConnection(searchNodeBasePath);
	// Search search = new Search(remotePeerConnection);
	// try {
	// String s = search.searchAllTransactionWithRegexKeyword(this.xPvkey,
	// "alvinreyes");
	// System.out.println(s);
	// if(s.contains("alvinreyes")) {
	// LOGGER.info("Found it");
	// Assert.assertTrue(true);
	// return;
	// }
	// Assert.assertTrue(false);
	// } catch (UnsupportedEncodingException | InterruptedException |
	// ExecutionException | ApiException e) {
	// e.printStackTrace();
	// }
	//
	// }
	//
	// /**
	// * Search public key search regex sample.
	// */
	// @Test
	// public void searchPublicKeySearchRegexSample() {
	// RemotePeerConnection remotePeerConnection = new
	// RemotePeerConnection(searchNodeBasePath);
	// Search search = new Search(remotePeerConnection);
	// try {
	// String s = search.searchAllPublicTransactionWithRegexKeyword(this.xPubkey,
	// "alvinreyes");
	// System.out.println(s);
	// if(s.contains("alvinreyes")) {
	// LOGGER.info("Found it");
	// Assert.assertTrue(true);
	// return;
	// }
	// Assert.assertTrue(false);
	// } catch (UnsupportedEncodingException | InterruptedException |
	// ExecutionException | ApiException e) {
	// e.printStackTrace();
	// }
	//
	// }
}
