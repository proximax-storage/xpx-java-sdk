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

import io.nem.api.ApiException;
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
			DownloadData message = download.downloadPlain(
					"9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404");
			
			//	Validate data.
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
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			DownloadData message = download.downloadSecure(
					"d48a3b84feaa75d8e06bfe53058e60c87d751f4c33297b80afb68cb154ec1669", "bytes",this.xPvkey,this.xPubkey);

			LOGGER.info(new String(message.getData(), "UTF-8"));
			Assert.assertTrue(true);
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException | IOException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}



}
