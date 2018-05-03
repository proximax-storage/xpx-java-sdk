package io.nem.xpx.facade.upload.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.model.primitive.Supply;
import org.nem.core.node.NodeEndpoint;
import io.nem.ApiException;
import io.nem.xpx.builder.UploadBinaryParameterBuilder;
import io.nem.xpx.builder.UploadDataParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.UploadAsync;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.facade.model.UploadResult;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class UploadTest.
 */
public class UploadAsyncRemoteBinaryTest extends AbstractApiTest {

	/**
	 * The Class TestMonitor.
	 */

	/**
	 * Upload plain data test.
	 */
	@Test
	@Ignore
	public void uploadAsyncPlainBinaryAndDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);


		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter1 = UploadBinaryParameterBuilder
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.metaData(JsonUtils.toJson(metaData))
					.keywords("proximax_vid")
					.contentType("video/mp4")
					.build();
			
			UploadBinaryParameter parameter2 = UploadBinaryParameterBuilder
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.metaData(JsonUtils.toJson(metaData))
					.keywords("proximax_vid")
					.contentType("video/mp4")
					.build();
			
			UploadDataParameter parameter3 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 1")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData)).build();

			// 	Run the computation on another thread and wait for it to finish.
			//	Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
				System.out.println(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
				System.out.println(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				System.out.println(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture 
			  = CompletableFuture.allOf(future1, future2, future3);
			
			combinedFuture.get();
			
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	/**
	 * Upload secure data test.
	 */
	@Test
	@Ignore
	public void uploadAsyncSecureBinaryAndDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);


		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter1 = UploadBinaryParameterBuilder
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.messageType(MessageTypes.SECURE)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.metaData(JsonUtils.toJson(metaData))
					.keywords("proximax_vid")
					.contentType("video/mp4")
					.build();
			
			UploadBinaryParameter parameter2 = UploadBinaryParameterBuilder
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.messageType(MessageTypes.SECURE)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.metaData(JsonUtils.toJson(metaData))
					.keywords("proximax_vid")
					.contentType("video/mp4")
					.build();
			
			UploadDataParameter parameter3 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData)).build();

			// 	Run the computation on another thread and wait for it to finish.
			//	Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
				System.out.println(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
				System.out.println(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				System.out.println(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture 
			  = CompletableFuture.allOf(future1, future2, future3);
			
			combinedFuture.get();
			
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain data with mosaic test.
	 */
	@Test
	@Ignore
	public void uploadAsyncPlainDataWithMosaicTest() {

		
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);


		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter1 = UploadBinaryParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.metaData(JsonUtils.toJson(metaData)).keywords("plain,data,wmosaics")
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();
			
			UploadBinaryParameter parameter2 = UploadBinaryParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.metaData(JsonUtils.toJson(metaData)).keywords("plain,data,wmosaics")
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();
			
			UploadDataParameter parameter3 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();

			// 	Run the computation on another thread and wait for it to finish.
			//	Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadBinary(parameter1, (n) -> {
				System.out.println(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future2 = upload.uploadBinary(parameter2, (n) -> {
				System.out.println(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				System.out.println(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture 
			  = CompletableFuture.allOf(future1, future2, future3);
			
			combinedFuture.get();
			
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException | IOException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Mosaic info lookup.
	 *
	 * @return the mosaic fee information lookup
	 */
	private MosaicFeeInformationLookup mosaicInfoLookup() {
		return id -> {
			if (id.getName().equals("xpx")) {
				return new MosaicFeeInformation(Supply.fromValue(8_999_999_999L), 4);
			}
			final int multiplier = Integer.parseInt(id.getName().substring(4));
			final int divisibilityChange = multiplier - 1;
			return new MosaicFeeInformation(Supply.fromValue(100_000_000 * multiplier), 3 + divisibilityChange);
		};
	}

}
