package io.nem.xpx.facade.upload.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
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

import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.builder.UploadDataParameterBuilder;
import io.nem.xpx.builder.UploadFileParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.service.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.model.UploadDataParameter;
import io.nem.xpx.service.model.UploadException;
import io.nem.xpx.service.model.UploadFileParameter;
import io.nem.xpx.service.model.XpxSdkGlobalConstants;
//import io.nem.xpx.monitor.UploadTransactionMonitor;
import io.nem.xpx.utils.JsonUtils;


/** 
 * The Class UploadTest.
 */
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
					.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data(new String("test plain - new 1".getBytes(),"UTF-8"))
					.contentType(DataTextContentType.TEXT_PLAIN)
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("plain,test")
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
			
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			LOGGER.info(e.getCause().getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void uploadPlainDataAsciiTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);

			UploadDataParameter parameter = UploadDataParameterBuilder
					.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data(new String("test plain - new 2".getBytes(),"ASCII"))
					.contentType(DataTextContentType.TEXT_PLAIN)
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("plain,test")
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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadDataParameter parameter = UploadDataParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE)
					.data("This is a Secure Test Data")
					.contentType(DataTextContentType.TEXT_PLAIN)
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("secure,test")
					.build();
			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	@Test
	public void uploadPlainDataWithMosaicTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			XpxSdkGlobalConstants.setGlobalTransactionFee(
					new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadDataParameter parameter = UploadDataParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE)
					.data("This is a Secure Test Data")
					.contentType(DataTextContentType.TEXT_PLAIN)
					.metaData(JsonUtils.toJson(metaData)) // one level map to json
					.keywords("secure,test")
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
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
			if (id.getName().equals("registry")) {
				return new MosaicFeeInformation(Supply.fromValue(8_999_999_999L), 6);
			}
			final int multiplier = Integer.parseInt(id.getName().substring(4));
			final int divisibilityChange = multiplier - 1;
			return new MosaicFeeInformation(Supply.fromValue(100_000_000 * multiplier), 3 + divisibilityChange);
		};
	}

	
}
