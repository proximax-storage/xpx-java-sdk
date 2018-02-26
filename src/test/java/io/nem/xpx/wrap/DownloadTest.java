package io.nem.xpx.wrap;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.utils.HexEncoder;

import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;

/**
 * The Class DownloadTest.
 */
public class DownloadTest extends AbstractApiTest {

	/**
	 * Download plain data test.
	 */
	@Test
	public void downloadPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);
		
		try {

			DownloadData message = download.downloadData(
					"565e5eafe7902d856a5a2c05a9b5a15c5aa5f941cbff7c19369ecbe4367f0b9c", this.xPvkey, this.xPubkey);

			// validate the name.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "1519574600186",
					message.getDataMessage().getName());
			LOGGER.info(message.getDataMessage().getName());
			// validate the content.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "This is a test data1",
					new String(message.getData(), "UTF-8"));

		} catch (UnsupportedEncodingException | ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download plain file test.
	 */
	@Test
	public void downloadPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {
			String timeStamp = System.currentTimeMillis() + "";

			DownloadData message = download.downloadData(
					"1c04846e4043a8c5a1908a0f200c8b70bb83e03e78b57ebf3b70002098de2a91", this.xPvkey, this.xPubkey);
			

			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadPlainFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"),
					HexEncoder.getBytes(new String(message.getData())));

			String fileContentExpected = this.extractExpectedSmallTxtFileContent();
			String fileActual = FileUtils.readFileToString(new File("src//test//resources//downloadPlainFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"));

			Assert.assertEquals(fileContentExpected, fileActual);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"));
		} catch (ApiException | InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download plain large file test.
	 */
	@Test
	public void downloadPlainLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {

			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadData(
					"d4ac2a34df6e9dcff27115f66cf88fb902d62092e4f54fa3637d0dafa1c9b362", this.xPvkey, this.xPubkey);

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//downloadPlainLargeFileTest_"
									+ message.getDataMessage().getName() + timeStamp + ".zip"),
							HexEncoder.getBytes(new String(message.getData())));

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//downloadPlainLargeFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainLargeFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download public data test.
	 */
	@Test
	public void downloadPublicDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {

			DownloadData message = download
					.downloadPublicData("f260b569a32c00fa49533e0af976bb356e781f2a703cbdd9ae3372fda8e4f43a");

			// validate the name.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "1519535969725",
					message.getDataMessage().getName());
			LOGGER.info(message.getDataMessage().getName());
			// validate the content.
			Assert.assertEquals("Assertion failed: Decryted data is not equal to expected", "This is a test data1",
					new String(message.getData(), "UTF-8"));
			LOGGER.info(new String(message.getData(), "UTF-8"));
		} catch (UnsupportedEncodingException | ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download plain public file test.
	 */
	@Test
	public void downloadPlainPublicFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {
			String timeStamp = System.currentTimeMillis() + "";

			DownloadData message = download
					.downloadPublicData("1c04846e4043a8c5a1908a0f200c8b70bb83e03e78b57ebf3b70002098de2a91");
			new String(message.getData());

			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadPlainPublicFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"),
					HexEncoder.getBytes(new String(message.getData())));

			String fileContentExpected = this.extractExpectedSmallTxtFileContent();
			String fileActual = FileUtils.readFileToString(new File("src//test//resources//downloadPlainPublicFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"));

			Assert.assertEquals(fileContentExpected, fileActual);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainPublicFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"));

		} catch (ApiException | InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}

	}

	/**
	 * Download plain public large file test.
	 */
	@Test
	public void downloadPlainPublicLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {

			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download
					.downloadPublicData("359f9808b2b535f4816377a0adfef7adc6637dabc2131e7fcb329bbccf437be8");

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//downloadPlainPublicLargeFileTest_"
									+ message.getDataMessage().getName() + timeStamp + ".zip"),
							HexEncoder.getBytes(new String(message.getData())));

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//downloadPlainPublicLargeFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainPublicLargeFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download secure data test.
	 */
	@Test
	public void downloadSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {
			DownloadData message = download.downloadData(
					"82dda8b1f2c5be931e1ada8ab41a1ce79be8b21c6b1a89eef0678b97783c4b2c", this.xPvkey, this.xPubkey);

			LOGGER.info(new String(message.getData(), "UTF-8"));
			Assert.assertTrue(true);
		} catch (UnsupportedEncodingException | ApiException | InterruptedException | ExecutionException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download secure file test.
	 */
	@Test
	public void downloadSecureFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {

			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractSmallFileSize();
			DownloadData message = download.downloadData(
					"37098d2d5d36070ec9e9db94e3e7d07659866b0de53c2d3c30b8918cb5967de4", this.xPvkey, this.xPubkey);

			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadSecureFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"), message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//downloadSecureFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//downloadSecureFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".txt"));
			
		} catch (IOException | ApiException | InterruptedException | ExecutionException e) {

			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	/**
	 * Download secure large file test.
	 */
	@Test
	public void downloadSecureLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(downloadNodeBasePath);
		Download download = new Download(remotePeerConnection);

		try {

			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadData(
					"e7c985d1ab4ad527240f49d743d779260aee28c6f1de0a250fa90e7a222959eb", this.xPvkey, this.xPubkey);

			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".zip"), message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().getName() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

}
