package io.nem.xpx.facade.download.local;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.utils.HexEncoder;

import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.facade.Download;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DownloadData;
import io.nem.xpx.model.PeerConnectionNotFoundException;


/**
 * The Class DownloadTest.
 */
public class DownloadLocalDataTest extends AbstractApiTest {

	/**
	 * Download plain data test.
	 */
	@Test
	public void downloadPlainDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			DownloadData message = download.downloadText(
					"9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404", "bytes");
			
			//	Validate data.
			Assert.assertNotNull(message.getData());
			// validate the content.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "plain-data",
					new String(Base64.decodeBase64(message.getData())));

		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException
				| IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}



	/**
	 * Download public data test.
	 */
	@Test
	public void downloadPublicDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			DownloadData message = download.downloadText(
					"9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404", "bytes");

			// validate the name.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "1519880173787",
					message.getDataMessage().name());
			LOGGER.info(message.getDataMessage().name());
			// validate the content.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "This is a test data",
					new String(message.getData(), "UTF-8"));
			LOGGER.info(new String(message.getData(), "UTF-8"));
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	/**
	 * Download secure data test.
	 */
	@Test
	public void downloadSecureDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			DownloadData message = download.downloadText(
					"088407b66cead2ace58aae312b4ba0986d1e9a8062cc2ef1a8744ca20e7ea1e0", "bytes");

			LOGGER.info(new String(message.getData(), "UTF-8"));
			Assert.assertTrue(true);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException | IOException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}



}
