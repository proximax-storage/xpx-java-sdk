package io.nem.xpx.performance.tests;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.upload.UploadBinaryParameter;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.pmw.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertNotNull;


/**
 * The Class UploadTest.
 */

public class MultiThreadUploadRemoteBinaryTest extends AbstractApiTest {

	/**
	 * Instantiates a new multi thread upload remote binary test.
	 */
	public MultiThreadUploadRemoteBinaryTest() {

		for (int i = 0; i < 100; i++) {
			Runnable task = () -> {

				RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localNodeBasePath);

				try {
					UploadAsync upload = new UploadAsync(remotePeerConnection);
					Map<String, String> metaData = new HashMap<String, String>();
					metaData.put("key1", "value1");

					UploadBinaryParameter parameter1 = UploadBinaryParameter.create()
							.senderOrReceiverPrivateKey(this.xPvkey)
							.receiverOrSenderPublicKey(this.xPubkey)
							.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
							.name("test_pdf_file_v1")
							.contentType("application/pdf")
							.keywords("pdf_file_version_file1")
							.metadata(JsonUtils.toJson(metaData))
							.build();
					
					UploadBinaryParameter parameter2 = UploadBinaryParameter.create()
							.senderOrReceiverPrivateKey(this.xPvkey)
							.receiverOrSenderPublicKey(this.xPubkey)
							.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v2.pdf")))
							.name("test_pdf_file_v2")
							.contentType("application/pdf")
							.keywords("pdf_file_version_file2")
							.metadata(JsonUtils.toJson(metaData))
							.build();
					
					UploadBinaryParameter parameter3 = UploadBinaryParameter.create()
							.senderOrReceiverPrivateKey(this.xPvkey)
							.receiverOrSenderPublicKey(this.xPubkey)
							.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v2.pdf")))
							.name("test_pdf_file_v2")
							.contentType("application/pdf")
							.keywords("pdf_file_version_file3")
							.metadata(JsonUtils.toJson(metaData))
							.build();

					// 	Run the computation on another thread and wait for it to finish.
					//	Callbacks are then handled.
					CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
						Logger.info(n.getNemHash());
						Logger.info(n.getDataMessage().hash());
						assertNotNull(n.getNemHash());
						
					}).exceptionally(e -> {
						e.printStackTrace();
						return null;
						
					});
					
					CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
						Logger.info(n.getNemHash());
						Logger.info(n.getDataMessage().hash());
						assertNotNull(n.getNemHash());
					}).exceptionally(e -> {
						e.printStackTrace();
						return null;
						
					});
					
					CompletableFuture<UploadResult> future3 = upload.uploadBinary(parameter3, (n) -> {
						Logger.info(n.getNemHash());
						Logger.info(n.getDataMessage().hash());
						assertNotNull(n.getNemHash());
					}).exceptionally(e -> {
						e.printStackTrace();
						return null;
						
					});

					CompletableFuture<Void> combinedFuture 
					  = CompletableFuture.allOf(future1, future2, future3);
					
					combinedFuture.get();
					
				} catch (IOException | InterruptedException | ExecutionException e) {
					e.printStackTrace();
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
		new MultiThreadUploadRemoteBinaryTest();
	}

}
