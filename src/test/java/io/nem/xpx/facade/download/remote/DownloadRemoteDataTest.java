package io.nem.xpx.facade.download.remote;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.node.NodeEndpoint;
import io.nem.ApiException;
import io.nem.xpx.facade.Download;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DownloadResult;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.remote.AbstractApiTest;


/**
 * The Class DownloadTest.
 */
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
					"627e3b70b2e902c8ca33447216535c5f0cc90da408a3db9b5b7ded95873bb47c");
			
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
					"be7e880d16e8b7c93d74dc79c6b9caf73466a63d44d9b19ccdaa34ae7e790722",this.xPvkey,this.xPubkey);
			
			LOGGER.info(">");
			LOGGER.info(new String(message.getData(), "UTF-8"));
			Assert.assertTrue(true);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException | IOException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void downloadSecureAsciiDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			DownloadResult message = download.downloadSecureTextData(
					"e3c95cc11780224408f7c379b1ebba415b0e82a8304444b1d531c4a1a645e258",this.xPvkey,this.xPubkey);
			
			LOGGER.info(">");
			LOGGER.info(new String(message.getData(), "ASCII"));
			Assert.assertTrue(true);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException | IOException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

	

}
