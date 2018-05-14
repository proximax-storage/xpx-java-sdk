package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.DataTextContentType;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.facade.upload.UploadTextDataParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
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

// TODO: Auto-generated Javadoc
/** 
 * The Class UploadTest.
 */
@Category(RemoteIntegrationTest.class)
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

			UploadTextDataParameter parameter = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(new String("!\"#$%&'()*+,-.:	 ;<=>?@[\\]^_`{|}~".getBytes(),"UTF-8"))
					.name("NAME1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
			
		} catch (IOException | UploadException e) {
			LOGGER.info(e.getCause().getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void uploadPlainDataHtmlTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);

			UploadTextDataParameter parameter = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(FileUtils.readFileToString(new File("src//test//resources//test_html.html")))
					.name("NAME1")
					.contentType(DataTextContentType.TEXT_HTML.toString())
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
			
		} catch (IOException | UploadException e) {
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

			UploadTextDataParameter parameter = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(new String("test plain - new 1".getBytes("ASCII")))
					.name("NAME1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("plain,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
			
		} catch (IOException | UploadException e) {
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
			UploadTextDataParameter parameter = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(new String("test secure - new 2".getBytes(),"UTF-8"))
					.name("NAME1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.securedWithNemKeysPrivacyStrategy()
					.build();
			
			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	/**
	 * Upload secure data ascii test.
	 */
	@Test
	public void uploadSecureDataAsciiTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			Upload upload = new Upload(remotePeerConnection);
			UploadTextDataParameter parameter = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey)
					.receiverOrSenderPublicKey(this.xPubkey)
					.data(new String("test secure - new 2".getBytes(),"ASCII"))
					.name("NAME1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("secure,data")
					.metadata(JsonUtils.toJson(metaData)) // one level map to json
					.securedWithNemKeysPrivacyStrategy()
					.build();
			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	/**
	 * Upload plain data with mosaic test.
	 */
	@Test
	public void uploadPlainDataWithMosaicTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		
		try {
			Upload upload = new Upload(remotePeerConnection);
			Map<String,String> metaData = new HashMap<String,String>();
			metaData.put("key1", "value1");
			
			UploadTextDataParameter parameter = UploadTextDataParameter.create()
					.senderOrReceiverPrivateKey(this.xPvkey).receiverOrSenderPublicKey(this.xPubkey)
					.data("plain-data - alvin reyes this is a new one yes from local 1")
					.name("RandomName1")
					.contentType(DataTextContentType.TEXT_PLAIN.toString())
					.encoding("UTF-8")
					.keywords("plain,data").metadata(JsonUtils.toJson(metaData))
					.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"),
							Quantity.fromValue(0)))
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
