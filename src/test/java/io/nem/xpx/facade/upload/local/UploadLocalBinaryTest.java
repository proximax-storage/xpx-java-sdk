package io.nem.xpx.facade.upload.local;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
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
import org.pmw.tinylog.Logger;

import io.nem.xpx.builder.UploadBinaryParameterBuilder;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.model.UploadBinaryParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class UploadTest.
 */
@Category(LocalIntegrationTest.class)
public class UploadLocalBinaryTest extends AbstractApiTest {

	/**
	 * Upload plain file test.
	 */
	@Test
	public void testUploadPlainBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "23.228.67.85", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);
		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(localPeerConnection);
			
			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("test_pdf_file_v1")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.contentType("application/pdf")
					.keywords("test_pdf_file_v1")
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
	
	@Test
	public void testUploadPlainZipBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "23.228.67.85", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);
		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(localPeerConnection);
			
			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("test_large_file")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_large_file.zip")))
					.contentType("application/zip")
					.keywords("test_large_file")
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
	public void testUploadSecureBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(localPeerConnection);
			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("test_pdf_file_v1")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.contentType("application/pdf")
					.keywords("test_pdf_file_v1")
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
	public void testUploadSecureLargeBinaryTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(localPeerConnection);
			
			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.SECURE)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("test_pdf_file_v1")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_large_video.mp4")))
					.contentType("video/mp4")
					.keywords("large_video")
					.metadata(JsonUtils.toJson(metaData))
					.build();
			
			String nemhash = upload.uploadBinary(parameter).getNemHash();
			Logger.info(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload plain binary with mosaic test.
	 */
	@Test
	@Ignore
	public void testUploadPlainBinaryWithMosaicTest() {
		try {
			
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
					ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
					);
			
			Upload upload = new Upload(localPeerConnection);
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter = UploadBinaryParameterBuilder
					.messageType(MessageTypes.PLAIN)
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.name("test_pdf_file_v1")
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.contentType("application/pdf")
					.keywords("test_pdf_file_v1")
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
