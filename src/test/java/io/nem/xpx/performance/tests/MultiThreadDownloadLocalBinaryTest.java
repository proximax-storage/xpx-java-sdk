package io.nem.xpx.performance.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;

import io.nem.xpx.facade.download.Download;
import io.nem.xpx.facade.download.DownloadAsync;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.remote.AbstractApiTest;


/**
 * The Class UploadTest.
 */

public class MultiThreadDownloadLocalBinaryTest extends AbstractApiTest {

	/**
	 * Instantiates a new multi thread download local binary test.
	 */
	public MultiThreadDownloadLocalBinaryTest() {

		for (int i = 0; i < 500; i++) {
			Runnable task = () -> {

				LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
						ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
						ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
						);

				try {

					Download download = new DownloadAsync(localPeerConnection);
					String timeStamp = System.currentTimeMillis() + "";
					long expectedFileSize = this.extractLargeFileSize();
					DownloadResult message = download.downloadBinaryOrFile("980b78a6927216eeca327749861b6008fcfe24a41784ef80172443ed42556e5a");

					FileUtils
							.writeByteArrayToFile(
									new File("src//test//resources//downloadPlainLargeFileTest_"
											+ message.getDataMessage().name() + timeStamp + ".zip"),
									message.getData());

					long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//large_file.zip"));

					//Assert.assertEquals(expectedFileSize, actualFileSize);

					// Remove file after.
					FileUtils.forceDelete(new File("src//test//resources//downloadPlainLargeFileTest_"
							+ message.getDataMessage().name() + timeStamp + ".zip"));
				} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
					e.printStackTrace();
					//assertTrue(false);
				}
			};
			Thread thread = new Thread(task);
			thread.start();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new MultiThreadDownloadLocalBinaryTest();
	}

}
