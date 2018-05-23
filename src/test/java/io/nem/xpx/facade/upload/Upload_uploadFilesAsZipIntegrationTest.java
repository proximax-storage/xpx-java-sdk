package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class Upload_uploadFilesAsZipIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";
	public static final String KEYWORDS_SECURE_AND_ZIP_FILE = "secure,zipfile";
	public static final String ZIP_FILE_NAME = "test.zip";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(peerConnection);
	}


	@Test
	public void shouldUploadFilesAsZipWithPlainMessageType() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(SMALL_FILE)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_ZIP_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void shouldUploadFilesAsZipWithSecureMessageType() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(SMALL_FILE)
				.keywords(KEYWORDS_SECURE_AND_ZIP_FILE)
				.metadata(METADATA_AS_MAP)
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_ZIP_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	@Ignore
	public void uploadPlainFileWithMosaicTest() throws Exception {
		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(SMALL_FILE)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA_AS_MAP)
				.mosaics(MOSAIC_LAND_REGISTRY)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_ZIP_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
