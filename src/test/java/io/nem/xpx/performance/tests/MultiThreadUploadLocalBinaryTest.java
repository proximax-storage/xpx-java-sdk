package io.nem.xpx.performance.tests;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.apache.commons.io.FileUtils;
import org.nem.core.model.MessageTypes;
import org.nem.core.node.NodeEndpoint;
import org.pmw.tinylog.Logger;

import io.nem.xpx.builder.UploadBinaryParameterBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
public class MultiThreadUploadLocalBinaryTest extends AbstractApiTest {

	/**
	 * Instantiates a new multi thread upload local binary test.
	 */
	//	Simple Multithreading test.
	public MultiThreadUploadLocalBinaryTest() {

		for (int i = 0; i < 250; i++) {
			Runnable task = () -> {

				LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
						ConnectionFactory.createNemNodeConnection("http", "23.228.67.85", 7890),
						ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
						);
				try {
					UploadAsync upload = new UploadAsync(localPeerConnection);
					Map<String, String> metaData = new HashMap<String, String>();
					metaData.put("key1", "value1");

					UploadBinaryParameter parameter1 = UploadBinaryParameterBuilder
								.messageType(MessageTypes.PLAIN)
								.senderOrReceiverPrivateKey(this.xPvkey)
								.receiverOrSenderPublicKey(this.xPubkey)
								.name("pdf_file_version2.pdf")
								.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
								.contentType("application/pdf")
								.keywords("pdf_file_version1")
								.metadata(JsonUtils.toJson(metaData))
								.build();
					
					UploadBinaryParameter parameter2 = UploadBinaryParameterBuilder
								.messageType(MessageTypes.PLAIN)
								.senderOrReceiverPrivateKey(this.xPvkey)
								.receiverOrSenderPublicKey(this.xPubkey)
								.name("pdf_file_version2.pdf")
								.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
								.contentType("application/pdf")
								.keywords("pdf_file_version2")
								.metadata(JsonUtils.toJson(metaData))
								.build();
					
					UploadBinaryParameter parameter3 = UploadBinaryParameterBuilder
								.messageType(MessageTypes.PLAIN)
								.senderOrReceiverPrivateKey(this.xPvkey)
								.receiverOrSenderPublicKey(this.xPubkey)
								.name("pdf_file_version1.pdf")
								.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
								.contentType("application/pdf")
								.keywords("pdf_file_version1")
								.metadata(JsonUtils.toJson(metaData))
								.build();

					// 	Run the computation on another thread and wait for it to finish.
					//	Callbacks are then handled.
					CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
						Logger.info(n.getNemHash());
						assertNotNull(n.getNemHash());
					});
					
					CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
						Logger.info(n.getNemHash());
						assertNotNull(n.getNemHash());
					});
					
					CompletableFuture<UploadResult> future3 = upload.uploadBinary(parameter3, (n) -> {
						Logger.info(n.getNemHash());
						assertNotNull(n.getNemHash());
					});

					CompletableFuture<Void> combinedFuture 
					  = CompletableFuture.allOf(future1, future2, future3);
					
					combinedFuture.get();
					
					
				} catch (ApiException | IOException | PeerConnectionNotFoundException | InterruptedException | ExecutionException e) {
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
		new MultiThreadUploadLocalBinaryTest();
		//System.exit(0);
	}

}
