package io.nem.xpx.facade.upload.local;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
import io.nem.xpx.builder.UploadDataParameterBuilder;
import io.nem.xpx.builder.UploadFileParameterBuilder;
import io.nem.xpx.builder.UploadPathParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.UploadAsync;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.facade.model.UploadData;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.UploadPathParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
public class UploadLocalDataTest extends AbstractApiTest {

	/**
	 * The Class TestMonitor.
	 */

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadDataParameter parameter = UploadDataParameterBuilder
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data("plain-data - alvin reyes this is a new one yes from local 3")
					.contentType(DataTextContentType.APPLICATION_XML)
					.metaData(JsonUtils.toJson(metaData))
					.keywords("plain,data")
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void uploadPlainDataTestAsync() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			
			UploadAsync upload = new UploadAsync(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadDataParameter parameter = UploadDataParameterBuilder
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data("plain-data")
					.metaData(JsonUtils.toJson(metaData))
					.keywords("plain,data")
					.build();

			String nemhash = upload.uploadTextData(parameter, n -> {System.out.println(n.getDataMessage());}).get().getNemHash();
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
	public void uploadSecureDataTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadDataParameter parameter = UploadDataParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.messageType(MessageTypes.SECURE)
					.data("secure-data")
					.metaData(JsonUtils.toJson(metaData))
					.keywords("secure,data")
					.build();

			UploadData data = upload.uploadTextData(parameter);
			String hash =data.getDataMessage().hash();
			String nemhash = data.getNemHash();
			LOGGER.info(nemhash);
			LOGGER.info(hash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}



	/**
	 * Upload plain data with mosaic test.
	 */
	@Test
	public void uploadPlainDataWithMosaicTest() {
		try {
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					new NodeEndpoint("http", "104.128.226.60", 7890));

			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadDataParameter parameter = UploadDataParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data("data-with-mosaics")
					.metaData(JsonUtils.toJson(metaData))
					.keywords("plain,data,wmosaics")
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"),
							Quantity.fromValue(10000)))
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
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
