package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class Upload_uploadFileIntegrationTest.
 */
public class Upload_uploadFileIntegrationTest extends AbstractFacadeIntegrationTest {


	/** The Constant KEYWORDS_PLAIN_AND_FILE. */
	public static final String KEYWORDS_PLAIN_AND_FILE = "plain,file";
	
	/** The Constant KEYWORDS_SECURE_AND_FILE. */
	public static final String KEYWORDS_SECURE_AND_FILE = "secure,file";

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
	 * Should upload file.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadFile() throws Exception {
		UploadFileParameter parameter = UploadFileParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.file(PDF_FILE1)
				.keywords(KEYWORDS_PLAIN_AND_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFile(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	

	/**
	 * Should upload large file.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadLargeFile() throws Exception {
		UploadFileParameter parameter = UploadFileParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.file(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFile(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE2.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
