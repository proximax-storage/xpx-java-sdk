package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.integration.tests.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.*;


@Category(IntegrationTest.class)
public class UploadAsync_uploadMultipleFilesIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";

	private UploadAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(peerConnection);
	}

	@Test
	public void shouldUploadMultipleFilesAsync() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA)
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
			assertEquals(METADATA, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().metaData());
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
			assertEquals(METADATA, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().metaData());
			assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().type());

			LOGGER.info(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
			LOGGER.info(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
		}).get();
	}
}
