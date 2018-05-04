package io.nem.xpx.facade.upload.remote;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.model.primitive.Supply;

import io.nem.xpx.builder.UploadDataParameterBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.DataTextContentType;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;

// TODO: Auto-generated Javadoc
/** 
 * The Class UploadTest.
 */
@Category(IntegrationTest.class)
public class UploadRemoteDataTest extends AbstractApiTest {

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);

			UploadDataParameter parameter = UploadDataParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("NAME1")
					.data(new String("test plain - new 1".getBytes(),"UTF-8"))
					.contentType(DataTextContentType.TEXT_PLAIN)
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
			
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			LOGGER.info(e.getCause().getMessage());
			assertTrue(false);
		}
	}
	
	/**
	 * Upload plain data ascii test.
	 */
	@Test
	public void uploadPlainDataAsciiTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);

			UploadDataParameter parameter = UploadDataParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("NAME1")
					.data(new String("test plain - new 1".getBytes("ASCII")))
					.contentType(DataTextContentType.TEXT_PLAIN)
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
			
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			LOGGER.info(e.getCause().getMessage());
			assertTrue(false);
		}
	}

	

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadDataParameter parameter = UploadDataParameterBuilder
					.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("NAME1")
					.data(new String("test secure - new 2".getBytes(),"UTF-8"))
					.contentType(DataTextContentType.TEXT_PLAIN)
					.encoding("UTF-8")
					.keywords("secure,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.build();
			
			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	/**
	 * Upload secure data ascii test.
	 */
	@Test
	public void uploadSecureDataAsciiTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadDataParameter parameter = UploadDataParameterBuilder
					.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("NAME1")
					.data(new String("test secure - new 2".getBytes(),"ASCII"))
					.contentType(DataTextContentType.TEXT_PLAIN)
					.encoding("UTF-8")
					.keywords("secure,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.build();
			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadDataParameter parameter = UploadDataParameterBuilder.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.name("RandomName1")
					.data("plain-data - alvin reyes this is a new one yes from local 1")
					.contentType(DataTextContentType.TEXT_PLAIN).encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"),
							Quantity.fromValue(0)))
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
				return new MosaicFeeInformation(Supply.fromValue(8_999_999_999L), 6);
			}
			final int multiplier = Integer.parseInt(id.getName().substring(4));
			final int divisibilityChange = multiplier - 1;
			return new MosaicFeeInformation(Supply.fromValue(100_000_000 * multiplier), 3 + divisibilityChange);
		};
	}

	
}
