package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * The Class UploadAsync_uploadFilesAsZipIntegrationTest.
 */
public class UploadAsync_uploadFilesAsZipIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant KEYWORDS_PLAIN_AND_ZIP_FILE. */
	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";
	
	/** The Constant ZIP_FILE_NAME. */
	public static final String ZIP_FILE_NAME = "test.zip";

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
	 * Should upload files as zip async.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadFilesAsZipAsync() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter, uploadResult -> {
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
		}).get();

	}
}
