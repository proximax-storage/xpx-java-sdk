package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadBinaryParameter;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import static io.nem.xpx.facade.DataTextContentType.*;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Category(RemoteIntegrationTest.class)
public class UploadRemoteBinaryTest extends AbstractApiTest {

	public static final String KEYWORDS_PLAIN_AND_BINARY = "plain,binary";
	public static final String KEYWORDS_SECURE_AND_BINARY = "secure,binary";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test
	public void uploadPlainBinaryTest() throws Exception{

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(SMALL_VIDEO_MOV_FILE))
				.name(SMALL_VIDEO_MOV_FILE.getName())
				.contentType(VIDEO_QUICKTIME.toString())
				.keywords(KEYWORDS_PLAIN_AND_BINARY)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(SMALL_VIDEO_MOV_FILE.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(VIDEO_QUICKTIME.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	@Test
	public void uploadPlainLargeBinaryTest() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(LARGE_VIDEO_MP4_FILE))
				.name(LARGE_VIDEO_MP4_FILE.getName())
				.contentType(VIDEO_MP4.toString())
				.keywords(KEYWORDS_PLAIN_AND_BINARY)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(LARGE_VIDEO_MP4_FILE.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(VIDEO_MP4.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	@Test
	public void uploadSecureBinaryTest() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(PDF_FILE1))
				.name(PDF_FILE1.getName())
				.contentType(APPLICATION_PDF.toString())
				.keywords(KEYWORDS_SECURE_AND_BINARY)
				.metadata(METADATA)
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void uploadSecureLargeBinaryTest() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(LARGE_VIDEO_MP4_FILE))
				.name(LARGE_VIDEO_MP4_FILE.getName())
				.contentType(VIDEO_MP4.toString())
				.keywords(KEYWORDS_SECURE_AND_BINARY)
				.metadata(METADATA)
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(LARGE_VIDEO_MP4_FILE.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(VIDEO_MP4.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	@Ignore
	public void uploadPlainBinaryWithMosaicTest() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(PDF_FILE1))
				.name(PDF_FILE1.getName())
				.contentType(APPLICATION_PDF.toString())
				.keywords(KEYWORDS_PLAIN_AND_BINARY)
				.metadata(METADATA)
				.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
						Quantity.fromValue(0)))
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

}
