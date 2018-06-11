package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.*;



/**
 * The Class UploadAsync_uploadMultipleFilesIntegrationTest.
 */
public class UploadAsync_uploadMultipleFilesIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant KEYWORDS_PLAIN_AND_MULTIFILES. */
	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";

	/** The unit under test. */
	private UploadAsync unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(peerConnection);
	}

	/**
	 * Should upload multiple files async.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadMultipleFilesAsync() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadMultipleFiles(parameter, multiFileUploadResult -> {
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

			assertEquals(PDF_FILE2, multiFileUploadResult.getFileUploadResults().get(1).getFile());
			assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());
			assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
			assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
			assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage());
			assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().hash());
			assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().digest());
			assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().keywords());
			assertEquals(PDF_FILE2.getName(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().name());
			assertEquals(METADATA_AS_STRING, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().metaData());
			assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().type());

			LOGGER.info(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
			LOGGER.info(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
		}).get();
	}
}
