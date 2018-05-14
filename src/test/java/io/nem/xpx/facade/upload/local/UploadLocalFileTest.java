package io.nem.xpx.facade.upload.local;

import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.facade.upload.UploadFileParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;



/**
 * The Class UploadTest.
 */
@Category(LocalIntegrationTest.class)
@Ignore
public class UploadLocalFileTest extends AbstractApiTest {

	
	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);
		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.file(new File("src//test//resources//test_pdf_file_v1.pdf"))
					.keywords("plain,file")
					.metadata(JsonUtils.toJson(metaData))
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (IOException | UploadException e) {
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
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.file(new File("src//test//resources//test_large_file.zip"))
					.keywords("plain,large,file")
					.metadata(JsonUtils.toJson(metaData))
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (IOException | UploadException e) {
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
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.file(new File("src//test//resources//test_small_file.txt"))
					.keywords("secure,small,file")
					.metadata(JsonUtils.toJson(metaData))
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
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
	public void uploadSecureLargeFileTest() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
				);

		try {
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.file(new File("src//test//resources//test_large_file.zip"))
					.keywords("secure,large,file")
					.metadata(JsonUtils.toJson(metaData))
					.securedWithNemKeysPrivacyStrategy()
					.build();
			
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Upload plain file with mosaic test.
	 */
	@Test
	@Ignore
	public void uploadPlainFileWithMosaicTest() {
		try {
			LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
					ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
					ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
					);
			
			Upload upload = new Upload(localPeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.file(new File("src//test//resources//test_pdf_file_v1.pdf"))
					.keywords("plain,data,wmosaics")
					.metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
