package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.nem.xpx.facade.DataTextContentType.TEXT_PLAIN;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.*;



/**
 * The Class Upload_uploadMultipleFilesIntegrationTest.
 */
public class Upload_uploadMultipleFilesIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant KEYWORDS_PLAIN_AND_MULTIFILES. */
	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";
	
	/** The Constant KEYWORDS_SECURE_AND_MULTIFILES. */
	public static final String KEYWORDS_SECURE_AND_MULTIFILES = "secure,multifiles";

	/** The unit under test. */
	private Upload unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new Upload(peerConnection);
	}

	/**
	 * Checks for failure when uploading non existent file.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void hasFailureWhenUploadingNonExistentFile() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.addFile(PDF_FILE1)
				.addFile(NON_EXISTENT_FILE)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA_AS_MAP)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

		assertNotNull(multiFileUploadResult);
		assertTrue(multiFileUploadResult.hasFailure());
		assertEquals(2, multiFileUploadResult.getFileUploadResults().size());

		assertEquals(PDF_FILE1, multiFileUploadResult.getFileUploadResults().get(0).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().name());
		assertEquals(METADATA_AS_STRING, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().type());

		assertEquals(NON_EXISTENT_FILE, multiFileUploadResult.getFileUploadResults().get(1).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());

		LOGGER.info(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
	}


	/**
	 * Should upload multiple files
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadMultipleFiles() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.addFile(PDF_FILE1)
				.addFile(SMALL_FILE)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA_AS_MAP)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

		assertNotNull(multiFileUploadResult);
		assertFalse(multiFileUploadResult.hasFailure());
		assertEquals(2, multiFileUploadResult.getFileUploadResults().size());

		assertEquals(PDF_FILE1, multiFileUploadResult.getFileUploadResults().get(0).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().name());
		assertEquals(METADATA_AS_STRING, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().type());

		assertEquals(SMALL_FILE, multiFileUploadResult.getFileUploadResults().get(1).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().keywords());
		assertEquals(SMALL_FILE.getName(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().name());
		assertEquals(METADATA_AS_STRING, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().type());

		LOGGER.info(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
		LOGGER.info(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
	}
}
