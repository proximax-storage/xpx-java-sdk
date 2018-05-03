package io.nem.xpx.facade.download.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;

import io.nem.ApiException;
import io.nem.xpx.facade.download.Download;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.remote.AbstractApiTest;

/**
 * The Class UploadTest.
 */
public class MultiThreadDownloadRemoteBinaryTest extends AbstractApiTest {

	public MultiThreadDownloadRemoteBinaryTest() {

		for (int i = 0; i < 500; i++) {
			System.out.println("Download Start: " + i);
			Runnable task = () -> {

				RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

				try {
					Download download = new Download(remotePeerConnection);
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
					System.out.println("Download End: ");
				} catch (IOException | ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
					e.printStackTrace();
					//assertTrue(false);
				}
			};
			Thread thread = new Thread(task);
			thread.start();
			System.out.println("Download End: ");
		}
	}

	public static void main(String[] args) {
		new MultiThreadDownloadRemoteBinaryTest();
	}

}
