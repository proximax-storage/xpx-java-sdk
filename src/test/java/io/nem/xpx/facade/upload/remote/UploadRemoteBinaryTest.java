package io.nem.xpx.facade.upload.remote;

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
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.UploadBinaryParameter;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;

/**
 * The Class UploadTest.
 */
@Category(RemoteIntegrationTest.class)
public class UploadRemoteBinaryTest extends AbstractApiTest {

	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "1");

			Upload upload = new Upload(remotePeerConnection);

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
	
	public void uploadPlainLargeVideoBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "1");

			Upload upload = new Upload(remotePeerConnection);

			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_large_video.mp4")))
					.name("test_pdf_file_v1")
					.contentType("video/mp4")
					.keywords("large_video")
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
	public void uploadPlainSmallVideoBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "1");

			Upload upload = new Upload(remotePeerConnection);

			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_file.mov")))
					.name("test_file")
					.contentType("video/mp4")
					.keywords("test_file")
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
	public void uploadSecureBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "1");

			Upload upload = new Upload(remotePeerConnection);
			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.name("test_pdf_file_v1")
					.contentType("application/pdf").keywords("test_pdf_file_v1").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeBinaryTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "1");

			Upload upload = new Upload(remotePeerConnection);

			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.name("test_pdf_file_v1")
					.contentType("video/mp4").keywords("large_video").metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();

			String nemhash = upload.uploadBinary(parameter).getNemHash();
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
	public void uploadPlainBinaryWithMosaicTest() {
		try {

			RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
			Upload upload = new Upload(remotePeerConnection);

			Map<String, String> metaData = new HashMap<String, String>();
			metaData.put("key1", "value1");

			UploadBinaryParameter parameter = UploadBinaryParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
					.name("test_pdf_file_v1")
					.contentType("application/pdf").keywords("test_pdf_file_v1").metadata(JsonUtils.toJson(metaData))
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
