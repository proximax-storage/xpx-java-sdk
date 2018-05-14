package io.nem.xpx.facade.upload.local;

import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.facade.upload.UploadBinaryParameter;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;
import org.pmw.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


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
			
			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.name("test_pdf_file_v1")
					.contentType("application/pdf")
					.keywords("test_pdf_file_v1")
					.metadata(JsonUtils.toJson(metaData))
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (IOException | UploadException e) {
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
			
			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_large_file.zip")))
					.name("test_large_file")
					.contentType("application/zip")
					.keywords("test_large_file")
					.metadata(JsonUtils.toJson(metaData))
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (IOException | UploadException e) {
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
			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.name("test_pdf_file_v1")
					.contentType("application/pdf")
					.keywords("test_pdf_file_v1")
					.metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (IOException |  UploadException e) {
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
			
			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_large_video.mp4")))
					.name("test_pdf_file_v1")
					.contentType("video/mp4")
					.keywords("large_video")
					.metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();
			
			String nemhash = upload.uploadBinary(parameter).getNemHash();
			Logger.info(nemhash);
		} catch (IOException | UploadException e) {
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

			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.name("test_pdf_file_v1")
					.contentType("application/pdf")
					.keywords("test_pdf_file_v1")
					.metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
