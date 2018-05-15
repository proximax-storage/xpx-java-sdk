package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
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

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static org.junit.Assert.assertTrue;



/**
 * The Class UploadTest.
 */
@Category(RemoteIntegrationTest.class)
@Ignore
public class UploadRemoteFileTest extends AbstractApiTest {

	
	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
					.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
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
	 * Upload plain pdf file test.
	 */
	@Test
	public void uploadPlainPdfFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
					.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
					.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.file(new File("src//test//resources//test_pdf_file_v2.pdf"))
					.keywords("plain,file")
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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
					.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.file(new File("src//test//resources//test_pdf_file_v1.pdf"))
					.keywords("plain,file")
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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadFileParameter parameter = UploadFileParameter.create()
					.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
					.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
					.file(new File("src//test//resources//test_large_file.zip"))
					.keywords("plain,file")
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
