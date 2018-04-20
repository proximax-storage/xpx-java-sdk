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

import io.nem.api.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.facade.Download;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.model.DownloadData;
import io.nem.xpx.model.PeerConnectionNotFoundException;


/**
 * The Class DownloadTest.
 */
public class DownloadLocalBinaryTest extends AbstractApiTest {


	/**
	 * Download plain file test.
	 */
	@Test
	public void downloadPlainBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadData message = download.downloadPlain("511ad868fe67f315f30b5962aa285deabd71e59aa8801dbe3846e9b1ff633f3f");
			System.out.println(message.getDataMessage().name());
			
			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadPlainFileTest_"
					+ message.getDataMessage().name() + ".pdf"),
					message.getData());

			
			
			String fileContentExpected = FileUtils.readFileToString(new File("src//test//resources//downloadPlainFileTest_"
					+ message.getDataMessage().name() + ".pdf"));
			
			String fileActual = FileUtils.readFileToString(new File("src//test//resources//pdf_file.pdf"));

			Assert.assertEquals(fileContentExpected, fileActual);

			// Remove file after.
			FileUtils.forceDelete(new File("src//test//resources//downloadPlainFileTest_"
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
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadPlain("1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96");

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
	public void downloadPlainPublicBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";

			DownloadData message = download.downloadPlain("1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96");
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
	public void downloadPlainPublicLargeBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadPlain("1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96");

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
	public void downloadSecureBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractSmallFileSize();
			DownloadData message = download.downloadBinary("9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404", "bytes");

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
	public void downloadSecureLargeBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Download download = new Download(localPeerConnection);
			String timeStamp = System.currentTimeMillis() + "";
			long expectedFileSize = this.extractLargeFileSize();
			DownloadData message = download.downloadSecure(
					"d48a3b84feaa75d8e06bfe53058e60c87d751f4c33297b80afb68cb154ec1669", "bytes",this.xPvkey,this.xPubkey);

			FileUtils.writeByteArrayToFile(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".zip"), message.getData());

			long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//large_file.zip"));

			Assert.assertEquals(expectedFileSize, actualFileSize);
			FileUtils.forceDelete(new File("src//test//resources//downloadSecureLargeFileTest_"
					+ message.getDataMessage().name() + timeStamp + ".zip"));
		} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {

			e.printStackTrace();
			assertTrue(false);
		}
	}

}
