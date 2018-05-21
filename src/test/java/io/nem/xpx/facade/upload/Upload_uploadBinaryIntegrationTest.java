package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.integration.tests.IntegrationTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.facade.DataTextContentType.*;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Category(IntegrationTest.class)
public class Upload_uploadBinaryIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String KEYWORDS_PLAIN_AND_BINARY = "plain,binary";
	public static final String KEYWORDS_SECURE_AND_BINARY = "secure,binary";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(peerConnection);
	}

	@Test
	public void shouldUploadPlainBinaryTest() throws Exception{

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
	public void shouldUploadPlainLargeBinaryTest() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(PDF_FILE2))
				.name(PDF_FILE2.getName())
				.contentType(APPLICATION_PDF.toString())
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
		assertEquals(PDF_FILE2.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	@Test
	public void shouldUploadSecureBinaryTest() throws Exception {

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
	@Ignore
	public void shouldUploadSecureLargeBinaryTest() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(PDF_FILE2))
				.name(PDF_FILE2.getName())
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
		assertEquals(PDF_FILE2.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	@Ignore
	public void shouldUploadPlainBinaryWithMosaicTest() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(PDF_FILE1))
				.name(PDF_FILE1.getName())
				.contentType(APPLICATION_PDF.toString())
				.keywords(KEYWORDS_PLAIN_AND_BINARY)
				.metadata(METADATA)
				.mosaics(MOSAIC_PRX)
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
