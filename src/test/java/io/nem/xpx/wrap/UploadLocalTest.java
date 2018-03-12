package io.nem.xpx.wrap;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.HashUtils;
import org.nem.core.model.MessageTypes;
import org.nem.core.node.NodeEndpoint;

import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.model.PeerConnectionNotFoundException;

/**
 * The Class UploadTest.
 */
public class UploadLocalTest extends AbstractApiTest {

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadData(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, "This is a test data",
					null, "alvinreyes", null).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain large file test.
	 */
	@Test
	public void uploadPlainLargeFileTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadSecureDataTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadData(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					"This is a Secure Test Data", null, null, null).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure file test.
	 */
	@Test
	public void uploadSecureFileTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeFileTest() {
		LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
		
		try {
			Upload upload = new Upload(localPeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
