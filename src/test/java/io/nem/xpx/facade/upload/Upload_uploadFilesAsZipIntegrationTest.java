package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * The Class Upload_uploadFilesAsZipIntegrationTest.
 */
public class Upload_uploadFilesAsZipIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant KEYWORDS_PLAIN_AND_ZIP_FILE. */
	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";
	
	/** The Constant KEYWORDS_SECURE_AND_ZIP_FILE. */
	public static final String KEYWORDS_SECURE_AND_ZIP_FILE = "secure,zipfile";
	
	/** The Constant ZIP_FILE_NAME. */
	public static final String ZIP_FILE_NAME = "test.zip";

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
	 * Should upload files as zip with plain message type.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadFilesAsZip() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
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
}
