package io.nem.xpx.performance.tests;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.download.Download;
import io.nem.xpx.facade.download.DownloadParameter;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.remote.AbstractApiTest;
import org.apache.commons.io.FileUtils;

import java.io.File;


/**
 * The Class UploadTest.
 */
public class MultiThreadDownloadRemoteBinaryTest extends AbstractApiTest {

	/**
	 * Instantiates a new multi thread download remote binary test.
	 */
	public MultiThreadDownloadRemoteBinaryTest() {

		for (int i = 0; i < 500; i++) {

			Runnable task = () -> {

				RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

				try {
					Download download = new Download(remotePeerConnection);
					String timeStamp = System.currentTimeMillis() + "";
					long expectedFileSize = this.extractLargeFileSize();
					DownloadResult message = download.download(DownloadParameter.create()
							.nemHash("980b78a6927216eeca327749861b6008fcfe24a41784ef80172443ed42556e5a").build());

					FileUtils
							.writeByteArrayToFile(
									new File("src//test//resources//downloadPlainLargeFileTest_"
											+ message.getDataMessage().name() + timeStamp + ".zip"),
									message.getData());

					long actualFileSize = FileUtils.sizeOf(new File("src//test//resources//test_large_file.zip"));

					//Assert.assertEquals(expectedFileSize, actualFileSize);

					// Remove file after.
					FileUtils.forceDelete(new File("src//test//resources//downloadPlainLargeFileTest_"
							+ message.getDataMessage().name() + timeStamp + ".zip"));
		
				} catch (Exception e) {
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
		new MultiThreadDownloadRemoteBinaryTest();
	}

}
