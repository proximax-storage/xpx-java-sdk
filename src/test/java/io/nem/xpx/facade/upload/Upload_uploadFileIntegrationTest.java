package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.integration.tests.IntegrationTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.nem.xpx.facade.DataTextContentType.TEXT_PLAIN;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Category(IntegrationTest.class)
public class Upload_uploadFileIntegrationTest extends AbstractFacadeIntegrationTest {


	public static final String KEYWORDS_PLAIN_AND_FILE = "plain,file";
	public static final String KEYWORDS_SECURE_AND_FILE = "secure,file";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(peerConnection);
	}
	
	@Test
	public void shouldUploadPlainFile() throws Exception {
		UploadFileParameter parameter = UploadFileParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.file(PDF_FILE1)
				.keywords(KEYWORDS_PLAIN_AND_FILE)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFile(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	

	@Test
	public void shouldUploadPlainLargeFile() throws Exception {
		UploadFileParameter parameter = UploadFileParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.file(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_FILE)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFile(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE2.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void shouldUploadSecureFile() throws Exception {

		UploadFileParameter parameter = UploadFileParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.file(PDF_FILE1)
				.keywords(KEYWORDS_SECURE_AND_FILE)
				.metadata(METADATA)
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFile(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	@Ignore
	public void shouldUploadPlainFileWithMosaic() throws Exception {

		UploadFileParameter parameter = UploadFileParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.file(PDF_FILE1)
				.keywords(KEYWORDS_PLAIN_AND_FILE)
				.metadata(METADATA)
				.mosaics(MOSAIC_PRX)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFile(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
