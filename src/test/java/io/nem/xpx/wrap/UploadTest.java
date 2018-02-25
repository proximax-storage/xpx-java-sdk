package io.nem.xpx.wrap;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
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
			String nemhash = upload.uploadData(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, "This is a test data1",
					null, null, null);
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException e) {
			e.printStackTrace();
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
					new File("src//test//resources//small_file.txt"), null, null);
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException e) {
			e.printStackTrace();
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
					new File("src//test//resources//large_file.zip"), null, null);
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
			e.printStackTrace();
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
					"This is a Secure Test Data", null, null, null);
			LOGGER.info(nemhash);
		} catch (ApiException e) {
			e.printStackTrace();
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
					new File("src//test//resources//small_file.txt"), null, null);
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
			e.printStackTrace();
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
					new File("src//test//resources//large_file.zip"), null, null);
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
			e.printStackTrace();
		}
	}

}
