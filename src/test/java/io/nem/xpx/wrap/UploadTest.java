package io.nem.xpx.wrap;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.HashUtils;
import org.nem.core.model.MessageTypes;

import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;

/**
 * The Class UploadTest.
 */
public class UploadTest extends AbstractApiTest {

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadData(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, "This is a test data",
					null, null, null).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException e) {
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
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
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
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadData(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					"This is a Secure Test Data", null, null, null).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException e) {
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
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//small_file.txt"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
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
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey,
					new File("src//test//resources//large_file.zip"), null, null).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
