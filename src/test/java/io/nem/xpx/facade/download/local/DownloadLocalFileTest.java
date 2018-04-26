package io.nem.xpx.facade.download.local;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
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
import io.nem.xpx.facade.model.DownloadData;
import io.nem.xpx.service.model.PeerConnectionNotFoundException;


/**
 * The Class DownloadTest.
 */
public class DownloadLocalFileTest extends AbstractApiTest {


	/**
	 * Download plain file test.
	 */
	@Test
	public void downloadPlainFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadData message = download.downloadBinaryOrFile("e0ca6d958ba01592ddeaa40e9d810a4314707f6673c2271e5d0eeb018a4be997");
			System.out.println(message.getDataMessage().name());
			
			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadPlainFileTest_"
					+ message.getDataMessage().name() + ".pdf"),
					message.getData());
			
			
			
			String fileContentExpected = FileUtils.readFileToString(new File("src//test//resources//downloadPlainFileTest_"
					+ message.getDataMessage().name() + ".pdf"));
			
			String fileActual = FileUtils.readFileToString(new File("src//test//resources//pdf_file2.pdf"));

			Assert.assertEquals(fileContentExpected, fileActual);

			// Remove file after.
//			FileUtils.forceDelete(new File("src//test//resources//downloadPlainFileTest_"
//					+ message.getDataMessage().name() + ".pdf"));
			
		} catch (ApiException | InterruptedException | ExecutionException | IOException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download plain large file test.
	 */
	@Test
	public void downloadPlainLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadBinaryOrFile("980b78a6927216eeca327749861b6008fcfe24a41784ef80172443ed42556e5a");

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//downloadPlainLargeFileTest_"
									+ message.getDataMessage().name() + timeStamp + ".zip"),
							message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//large_file.zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainLargeFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void downloadPlainLargeFileMediaTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadBinaryOrFile("f4eab8bb8eb80628b1ba79ee9f9e7d71f3a121051c0c58d9e7b0329a37035bbd");

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//downloadPlainLargeFileTest_"
									+ message.getDataMessage().name() + timeStamp + ".zip"),
							message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//large_file.zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainLargeFileTest_"
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
	public void downloadPlainPublicFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadData message = download.downloadBinaryOrFile("1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96");
			new String(message.getData());

			FileUtils
					.writeByteArrayToFile(
							new File("src//test//resources//downloadPlainPublicFileTest_"
									+ message.getDataMessage().name() + timeStamp + ".txt"),
							message.getData());

			String fileContentExpected = this.extractExpectedSmallTxtFileContent();
			String fileActual = FileUtils.readFileToString(new File("src//test//resources//small_file.txt"));

			Assert.assertEquals(fileContentExpected, fileActual);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainPublicFileTest_"
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
	public void downloadPlainPublicLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadBinaryOrFile("1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96");

			FileUtils.writeByteArrayToFile(
					new File("src//test//resources//downloadPlainPublicLargeFileTest_"
							+ message.getDataMessage().name() + timeStamp + ".zip"),
					message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//large_file.zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainPublicLargeFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Download secure file test.
	 */
	@Test
	public void downloadSecureFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractSmallFileSize();
			DownloadData message = download.downloadBinaryOrFile("9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404");

			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadSecureFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".txt"), message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//small_file.txt"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//downloadSecureFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".txt"));

		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {

			e.printStackTrace();
			assertTrue(false);
		}

	}

	
	/**
	 * Download secure large file test.
	 */
	@Test
	public void downloadSecureLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadData message = download.downloadSecureBinaryOrFile(
					"1fce4dda18a865484f9f9c2b6a15e8c64756a89c5e903adbf76ec62eab1d41c7",this.xPvkey,this.xPubkey);

			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".pdf"), message.getData());

			long expectedFileSize = FileUtils.sizeOf(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));
			
			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//pdf_file.pdf"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".pdf"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

}
