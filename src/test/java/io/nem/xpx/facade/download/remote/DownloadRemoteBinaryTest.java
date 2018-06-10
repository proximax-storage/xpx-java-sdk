package io.nem.xpx.facade.download.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import io.nem.xpx.facade.download.Download;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;



/**
 * The Class DownloadTest.
 */
@Category(RemoteIntegrationTest.class)
public class DownloadRemoteBinaryTest extends AbstractApiTest {


	/**
	 * Download plain file test.
	 */
	@Test
	public void downloadPlainBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadResult message = download.downloadBinaryOrFile("e0ca6d958ba01592ddeaa40e9d810a4314707f6673c2271e5d0eeb018a4be997");
			
			FileUtils.writeByteArrayToFile(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + ".pdf"),
					message.getData());
			
			String fileContentExpected = FileUtils.readFileToString(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + ".pdf"));
			
			
			String fileActual = FileUtils.readFileToString(new File("src//test//resources//test_pdf_file_v1.pdf"));

			Assert.assertEquals(fileContentExpected, fileActual);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + ".pdf"));
			
		} catch (ApiException | InterruptedException | ExecutionException | IOException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download plain large file test.
	 */
	@Test
	public void downloadPlainLargeBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			//long expectedFileSize = this.extractLargeFileSize();
			DownloadResult message = download.downloadBinaryOrFile("980b78a6927216eeca327749861b6008fcfe24a41784ef80172443ed42556e5a");

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//test_large_file_temp"
									+ message.getDataMessage().name() + timeStamp + ".zip"),
							message.getData());

			long expectedFileSize = FileUtils.sizeOf(new File("src//test//resources//test_large_file_temp"
					+ message.getDataMessage().name() + timeStamp + ".zip"));
			
			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//test_large_file.zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//test_large_file_temp"
					+ message.getDataMessage().name() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Download plain large binary media test.
	 */
	@Test
	@Ignore
	public void downloadPlainLargeBinaryMediaTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadResult message = download.downloadBinaryOrFile("f4eab8bb8eb80628b1ba79ee9f9e7d71f3a121051c0c58d9e7b0329a37035bbd");

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//test_large_file_temp"
									+ message.getDataMessage().name() + timeStamp + ".zip"),
							message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//test_large_file.zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//test_large_file_temp"
					+ message.getDataMessage().name() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	/**
	 * Download plain public file test.
	 */
	@Test
	public void downloadPlainPublicBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadResult message = download.downloadBinaryOrFile("1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96");
			new String(message.getData());

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//test_small_file_temp"
									+ message.getDataMessage().name() + timeStamp + ".txt"),
							message.getData());

			String fileContentExpected = this.extractExpectedSmallTxtFileContent();
			String fileActual = FileUtils.readFileToString(new File("src//test//resources//test_small_file.txt"));

			Assert.assertEquals(fileContentExpected, fileActual);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//test_small_file_temp"
					+ message.getDataMessage().name() + timeStamp + ".txt"));

		} catch (ApiException | InterruptedException | ExecutionException | IOException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}

	/**
	 * Download plain public large file test.
	 */
	@Test
	public void downloadPlainPublicLargeBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			
			DownloadResult message = download.downloadBinaryOrFile("bc5dd2f86007dcbcf2fbe2be75460db866e7294f9c5f0ba7b1e8f63c12664a02");

			FileUtils.writeByteArrayToFile(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"), message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//test_pdf_file_v1.pdf"));
			long expectedFileSize = FileUtils.sizeOf(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));
			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));
			
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download secure file test.
	 */
	@Test
	public void downloadSecureBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			DownloadResult message = download.downloadSecureBinaryOrFile("99f48fa4d9c77c3946c364b3ada5fd497b03535de676d17f8bc7ca3dcce20f2a",this.xPvkey,this.xPubkey);

			FileUtils.writeByteArrayToFile(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"), message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//test_pdf_file_v1.pdf"));
			long expectedFileSize = FileUtils.sizeOf(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));
			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//test_pdf_file_v1_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));

		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	/**
	 * Download secure large file test.
	 */
	@Test
	public void downloadSecureLargeBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Download download = new Download(remotePeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadResult message = download.downloadSecureBinaryOrFile(
					"1fce4dda18a865484f9f9c2b6a15e8c64756a89c5e903adbf76ec62eab1d41c7",this.xPvkey,this.xPubkey);

			FileUtils.writeByteArrayToFile(new File("src//test//resources//test_pdf_file_v2_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"), message.getData());

			long expectedFileSize = FileUtils.sizeOf(new File("src//test//resources//test_pdf_file_v2_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));
			
			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//test_pdf_file_v2.pdf"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//test_pdf_file_v2_temp"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

}
