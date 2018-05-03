package io.nem.xpx.facade.download.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.model.primitive.Supply;
import org.nem.core.node.NodeEndpoint;

import io.nem.ApiException;
import io.nem.xpx.builder.UploadBinaryParameterBuilder;
import io.nem.xpx.facade.Download;
import io.nem.xpx.facade.DownloadAsync;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DownloadResult;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;

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
