package io.nem.xpx.facade.upload.remote;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import io.nem.xpx.builder.UploadBinaryParameterBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
@Category(IntegrationTest.class)
public class UploadAsyncRemoteBinaryTest extends AbstractApiTest {

	/**
	 * The Class TestMonitor.
	 */

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadAsyncPlainBinaryAndDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter1 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_1.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData)).build();

			UploadBinaryParameter parameter2 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_2.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData)).build();

			UploadBinaryParameter parameter3 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_3.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData)).build();

			// Run the computation on another thread and wait for it to finish.
			// Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future3 = upload.uploadBinary(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

			assertNotNull(combinedFuture.get());
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException
				| IOException e) {
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadAsyncSecureBinaryAndDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter1 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_1.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData)).build();

			UploadBinaryParameter parameter2 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_2.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData)).build();

			UploadBinaryParameter parameter3 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_3.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData)).build();

			// Run the computation on another thread and wait for it to finish.
			// Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future3 = upload.uploadBinary(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

			assertNotNull(combinedFuture.get());

		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException
				| IOException e) {
			assertTrue(false);
		}
	}

	/**
	 * Upload plain data with mosaic test.
	 */
	@Test
	public void uploadAsyncPlainDataWithMosaicTest() {

		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter1 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_3.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();

			UploadBinaryParameter parameter2 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_3.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();

			UploadBinaryParameter parameter3 = UploadBinaryParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("large_video_3.mp4")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();

			// Run the computation on another thread and wait for it to finish.
			// Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future3 = upload.uploadBinary(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

			assertNotNull(combinedFuture.get());
			
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException
				| IOException e) {
			assertTrue(false);
		}
	}

}
