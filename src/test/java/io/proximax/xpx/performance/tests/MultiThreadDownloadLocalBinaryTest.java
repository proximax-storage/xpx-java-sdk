/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.performance.tests;

import io.proximax.xpx.facade.connection.LocalHttpPeerConnection;
import io.proximax.xpx.facade.download.Download;
import io.proximax.xpx.facade.download.DownloadParameter;
import io.proximax.xpx.facade.download.DownloadResult;
import io.proximax.xpx.factory.ConnectionFactory;
import io.proximax.xpx.remote.AbstractApiTest;
import org.apache.commons.io.FileUtils;

import java.io.File;



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
						ConnectionFactory.createNemNodeConnection("testnet","http", "104.128.226.60", 7890),
						ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
						);

				try {

					Download download = new Download(localPeerConnection);
					String timeStamp = System.currentTimeMillis() + "";
					long expectedFileSize = this.extractLargeFileSize();
					DownloadResult message = download.downloadBinary(DownloadParameter.create()
							.nemHash("980b78a6927216eeca327749861b6008fcfe24a41784ef80172443ed42556e5a").build());

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
		new MultiThreadDownloadLocalBinaryTest();
	}

}
