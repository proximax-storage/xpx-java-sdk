package io.nem.xpx.facade.upload.local;

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
import org.nem.core.model.mosaic.MosaicFeeInformationLookup;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.mosaic.MosaicFeeInformation;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.model.primitive.Supply;
import org.nem.core.node.NodeEndpoint;

import io.nem.ApiException;
import io.nem.xpx.builder.UploadFileParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
public class UploadLocalFileTest extends AbstractApiTest {

	
	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));
		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//pdf_file.pdf"))
					.metaData(JsonUtils.toJson(metaData)).keywords("plain,file")
					.contentType("application/pdf") // make sure to put this in for files.
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain large file test.
	 */
	@Test
	public void uploadPlainLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//large_file.zip"))
					.metaData(JsonUtils.toJson(metaData)).keywords("plain,file")
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}


	/**
	 * Upload secure file test.
	 */
	@Test
	public void uploadSecureFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.SECURE).data(new File("src//test//resources//small_file.txt"))
					.metaData(JsonUtils.toJson(metaData)).keywords("secure,file")
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.SECURE).data(new File("src//test//resources//large_file.zip"))
					.metaData(JsonUtils.toJson(metaData)).keywords("secure,file")
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void uploadPlainFileWithMosaicTest() {
		try {
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					new NodeEndpoint("http", "104.128.226.60", 7890));
			XpxSdkGlobalConstants.setGlobalTransactionFee(
					new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadFileParameter parameter = UploadFileParameterBuilder.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//large_file.zip"))
					.metaData(JsonUtils.toJson(metaData))
					.keywords("plain,data,wmosaics")
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadFile(parameter).getNemHash();
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
