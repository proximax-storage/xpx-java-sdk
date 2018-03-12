package io.nem.xpx.wrap;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;

public class SearchTest extends AbstractApiTest {

	@Test
	public void searchPKeySearchSample() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(searchNodeBasePath);
		Search search = new Search(remotePeerConnection);
		try {
			String s = search.searchAllTransactionWithKeyword(this.xPvkey, "alvinreyes");

			if(s.contains("alvinreyes")) {
				LOGGER.info("Found it");
				Assert.assertTrue(true);
				return;
			}
			Assert.assertTrue(false);
		} catch (UnsupportedEncodingException | InterruptedException | ExecutionException | ApiException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void searchPublicKeySearchSample() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(searchNodeBasePath);
		Search search = new Search(remotePeerConnection);
		try {
			String s = search.searchAllPublicTransactionWithKeyword(this.xPubkey, "alvinreyes");

			if(s.contains("alvinreyes")) {
				LOGGER.info("Found it");
				Assert.assertTrue(true);
				return;
			}
			Assert.assertTrue(false);
		} catch (UnsupportedEncodingException | InterruptedException | ExecutionException | ApiException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void searchKeySearchRegexSample() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(searchNodeBasePath);
		Search search = new Search(remotePeerConnection);
		try {
			String s = search.searchAllTransactionWithRegexKeyword(this.xPvkey, "alvinreyes");
			System.out.println(s);
			if(s.contains("alvinreyes")) {
				LOGGER.info("Found it");
				Assert.assertTrue(true);
				return;
			}
			Assert.assertTrue(false);
		} catch (UnsupportedEncodingException | InterruptedException | ExecutionException | ApiException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void searchPublicKeySearchRegexSample() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(searchNodeBasePath);
		Search search = new Search(remotePeerConnection);
		try {
			String s = search.searchAllPublicTransactionWithRegexKeyword(this.xPubkey, "alvinreyes");
			System.out.println(s);
			if(s.contains("alvinreyes")) {
				LOGGER.info("Found it");
				Assert.assertTrue(true);
				return;
			}
			Assert.assertTrue(false);
		} catch (UnsupportedEncodingException | InterruptedException | ExecutionException | ApiException e) {
			e.printStackTrace();
		}
		
	}
}
