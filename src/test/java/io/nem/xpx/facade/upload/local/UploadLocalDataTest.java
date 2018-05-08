package io.nem.xpx.facade.upload.local;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
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
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.DataTextContentType;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class UploadTest.
 */
@Category(LocalIntegrationTest.class)
public class UploadLocalDataTest extends AbstractApiTest {

	/**
	 * The Class TestMonitor.
	 */

	/**
	 * Upload plain data test.
	 */
	@Test
	public void testUploadPlainDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadDataParameter parameter = UploadDataParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName")
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.APPLICATION_XML)
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData))
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain data test async.
	 */
	@Test
	public void testUploadPlainDataTestAsync() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890),"/ip4/127.0.0.1/tcp/5001");

		try {
			
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadDataParameter parameter =  UploadDataParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName")
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.APPLICATION_XML)
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData))
					.build();
			
	
			String nemhash = upload.uploadTextData(parameter, n -> {assertNotNull(n.getNemHash());}).get().getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void testUploadSecureDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890),"/ip4/127.0.0.1/tcp/5001");

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadDataParameter parameter =  UploadDataParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName")
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.APPLICATION_XML)
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData))
					.build();

			UploadResult data = upload.uploadTextData(parameter);
			String hash =data.getDataMessage().hash();
			String nemhash = data.getNemHash();
			LOGGER.info(nemhash);
			LOGGER.info(hash);
		} catch (ApiException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}



	/**
	 * Upload plain data with mosaic test.
	 */
	@Test
	public void testUploadPlainDataWithMosaicTest() {
		try {
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					new NodeEndpoint("http", "104.128.226.60", 7890),
					"/ip4/127.0.0.1/tcp/5001");

			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadDataParameter parameter =  UploadDataParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.APPLICATION_XML)
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"),
							Quantity.fromValue(10000)))
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | UploadException e) {
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
