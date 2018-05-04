package io.nem.xpx.facade.download.remote;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import io.nem.xpx.facade.download.Download;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;



/**
 * The Class DownloadTest.
 */
@Category(IntegrationTest.class)
public class DownloadRemoteDataTest extends AbstractApiTest {

	/**
	 * Download plain data test.
	 */
	@Test
	public void downloadPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			DownloadResult message = download.downloadTextData(
					"2d8db574ef9c438d249d36c55137b315a68bc74ae3215d6bbc5c5e0598e6ff00");
			
			//	Validate data.
			LOGGER.info(new String(message.getData(), "UTF-8"));
			Assert.assertNotNull(message.getData());
			
			// validate the content.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "plain-data",
					new String(message.getData()));

		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException
				| IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download secure data test.
	 */
	@Test
	public void downloadSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			DownloadResult message = download.downloadSecureTextData(
					"51213456ec5fba0ca89980686ffb09310537dbf975adfb5fa808af2b52474a81",this.xPvkey,this.xPubkey);

			LOGGER.info(new String(message.getData(), "UTF-8"));
			Assert.assertTrue(true);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException | IOException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Download secure ascii data test.
	 */
	@Test
	public void downloadSecureAsciiDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			DownloadResult message = download.downloadSecureTextData(
					"fb22666a36403e25bfc724695993ab95e39a75774091a284ed96d45a90891c9a",this.xPvkey,this.xPubkey);
			
			LOGGER.info(">");
			LOGGER.info(new String(message.getData(), "ASCII"));
			Assert.assertTrue(true);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException | IOException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

	

}
