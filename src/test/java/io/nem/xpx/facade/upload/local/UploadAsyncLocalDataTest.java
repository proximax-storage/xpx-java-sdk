package io.nem.xpx.facade.upload.local;

import io.nem.xpx.facade.DataTextContentType;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.facade.upload.UploadTextDataParameter;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * The Class UploadTest.
 */
@Category(LocalIntegrationTest.class)
@Ignore
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
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadTextDataParameter parameter1 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("plain-data - alvin reyes this is a new one yes from local 1")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.build();
			
			UploadTextDataParameter parameter2 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("plain-data - alvin reyes this is a new one yes from local 2")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.build();
			
			UploadTextDataParameter parameter3 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
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
			
			assertNotNull(combinedFuture.get());
			
		} catch (InterruptedException | ExecutionException e) {
			assertTrue(false);
		}
	}


	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadAsyncSecureDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadTextDataParameter parameter1 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("secure-data - alvin reyes this is a new one yes from local 1")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString()).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();
			
			UploadTextDataParameter parameter2 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("secure-data - alvin reyes this is a new one yes from local 2")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString()).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();
			
			UploadTextDataParameter parameter3 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString()).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
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
			Assert.assertTrue(true);
		} catch (InterruptedException | ExecutionException e) {
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
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadTextDataParameter parameter1 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();
			
			UploadTextDataParameter parameter2 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.name("RandomName")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();
			
			UploadTextDataParameter parameter3 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("secure-data - alvin reyes this is a new one yes from local 3")
					.name("RandomName")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
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
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
