package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.DataTextContentType;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.facade.upload.UploadTextDataParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
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
@Category(RemoteIntegrationTest.class)
public class UploadAsyncRemoteDataTest extends AbstractApiTest {

	/**
	 * The Class TestMonitor.
	 */

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadAsyncPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
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

			// Run the computation on another thread and wait for it to finish.
			// Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadTextData(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future2 = upload.uploadTextData(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
			combinedFuture.get();
			assertTrue(true);

		} catch (InterruptedException | ExecutionException e) {
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadAsyncSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			UploadAsync upload = new UploadAsync(remotePeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadTextDataParameter parameter1 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("Secure Data 1")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();

			UploadTextDataParameter parameter2 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("Secure Data 2")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();

			UploadTextDataParameter parameter3 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("Secure Data 3")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();

			// Run the computation on another thread and wait for it to finish.
			// Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadTextData(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future2 = upload.uploadTextData(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
			combinedFuture.get();
			assertTrue(true);

			
		} catch (InterruptedException | ExecutionException e) {
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

			UploadTextDataParameter parameter1 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("Secure Data 3")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();

			UploadTextDataParameter parameter2 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("Secure Data 3")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();
			UploadTextDataParameter parameter3 = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.data("Secure Data 3")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"), Quantity.fromValue(10000)))
					.build();

			// Run the computation on another thread and wait for it to finish.
			// Callbacks are then handled.
			CompletableFuture<UploadResult> future1 = upload.uploadTextData(parameter1, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future2 = upload.uploadTextData(parameter2, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<UploadResult> future3 = upload.uploadTextData(parameter3, (n) -> {
				assertNotNull(n.getNemHash());
			});

			CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

			combinedFuture.get();
			assertTrue(true);

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
