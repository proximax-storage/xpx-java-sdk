package io.nem.xpx.facade.upload.local;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.model.primitive.Supply;
import org.nem.core.node.NodeEndpoint;

import io.nem.xpx.builder.UploadDataParameterBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class UploadTest.
 */
@Category(IntegrationTest.class)
public class UploadAsyncLocalDataTest extends AbstractApiTest {

	/**
	 * The Class TestMonitor.
	 */

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadAsyncPlainDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadDataParameter parameter1 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 1")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData)).build();
			
			UploadDataParameter parameter2 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 2")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData)).build();
			
			UploadDataParameter parameter3 =UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.build();

			// 	Run the computation on another thread and wait for it to finish.
			//	Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadTextData(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future2 = upload.uploadTextData(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture 
			  = CompletableFuture.allOf(future1, future2, future3);
			
			combinedFuture.get();
			
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadAsyncSecureDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadDataParameter parameter1 = UploadDataParameterBuilder.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("secure-data - alvin reyes this is a new one yes from local 1")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData)).build();
			
			UploadDataParameter parameter2 = UploadDataParameterBuilder.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("secure-data - alvin reyes this is a new one yes from local 2")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData)).build();
			
			UploadDataParameter parameter3 = UploadDataParameterBuilder.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData)).build();

			// 	Run the computation on another thread and wait for it to finish.
			//	Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadTextData(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future2 = upload.uploadTextData(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture 
			  = CompletableFuture.allOf(future1, future2, future3);
			
			combinedFuture.get();
			Assert.assertTrue(true);
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	/**
	 * Upload plain data with mosaic test.
	 */
	@Test
	public void uploadPlainDataWithMosaicTest() {

		
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadDataParameter parameter1 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();
			
			UploadDataParameter parameter2 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName")
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();
			
			UploadDataParameter parameter3 = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName")
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();

			// 	Run the computation on another thread and wait for it to finish.
			//	Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadTextData(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future2 = upload.uploadTextData(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});
			
			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture 
			  = CompletableFuture.allOf(future1, future2, future3);
			
			combinedFuture.get();
			
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException e) {
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