package io.nem.xpx.facade;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.MessageTypes;
import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadException;

/**
 * The Class UploadTest.
 */
public class UploadRemoteTest extends AbstractApiTest {

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			String nemhash = upload.uploadData(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, "This is a test data",
					null, "alvinreyes", null).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			LOGGER.info(e.getCause().getMessage());
			assertTrue(false);
		}
	}

	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain large file test.
	 */
	@Test
	public void uploadPlainLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			String nemhash = upload.uploadData(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					"This is a Secure Test Data", null, null, null).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure file test.
	 */
	@Test
	public void uploadSecureFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException  e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
