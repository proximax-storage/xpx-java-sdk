package io.nem.xpx.facade.upload.local;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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

import io.nem.xpx.builder.UploadBinaryParameterBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
@Category(IntegrationTest.class)
public class UploadLocalBinaryTest extends AbstractApiTest {

	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));
		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(localPeerConnection);
			
			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("pdf_file_version12.pdf")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//pdf_file_version2.pdf")))
					.contentType("application/pdf")
					.keywords("pdf_file_version2")
					.metadata(JsonUtils.toJson(metaData))
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	

	/**
	 * Upload secure file test.
	 */
	@Test
	public void uploadSecureBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(localPeerConnection);
			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("pdf_file_version12.pdf")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_audio.mp3")))
					.contentType("audio/mpeg")
					.keywords("large_audio")
					.metadata(JsonUtils.toJson(metaData))
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "104.128.226.60", 7890));

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(localPeerConnection);
			
			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("pdf_file_version12.pdf")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//large_video.mp4")))
					.contentType("video/mp4")
					.keywords("large_video")
					.metadata(JsonUtils.toJson(metaData))
					.build();
			
			String nemhash = upload.uploadBinary(parameter).getNemHash();
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain binary with mosaic test.
	 */
	@Test
	public void uploadPlainBinaryWithMosaicTest() {
		try {
			
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					new NodeEndpoint("http", "104.128.226.60", 7890));
			XpxSdkGlobalConstants.setGlobalTransactionFee(
					new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), mosaicInfoLookup()));
			Upload upload = new Upload(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("pdf_file_version12.pdf")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//pdf_file_version2.pdf")))
					.contentType("application/pdf")
					.keywords("pdf_file_version2")
					.metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
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
