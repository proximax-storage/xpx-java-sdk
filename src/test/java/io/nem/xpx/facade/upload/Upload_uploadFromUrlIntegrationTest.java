package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static io.nem.xpx.facade.DataTextContentType.*;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class Upload_uploadFromUrlIntegrationTest.
 */
public class Upload_uploadFromUrlIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String KEYWORDS_UPLOAD_FROM_URL = "url";
	public static final String NAME_UPLOAD_HTML_FROM_URL = "UploadHtmlFromUrl";
	public static final String NAME_UPLOAD_PNG_FROM_URL = "UploadPngFromUrl";
	public static final String NAME_UPLOAD_TXT_FROM_URL = "UploadTxtFromUrl";
	public static final String NAME_UPLOAD_PDF_FROM_URL = "UploadPdfFromUrl";

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
	 * Should upload PNG from HTTP URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadPngFromHttpUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(new URL(SAMPLE_URL_PNG))
				.name(NAME_UPLOAD_PNG_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
		assertEquals(NAME_UPLOAD_PNG_FROM_URL, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(IMAGE_PNG.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	/**
	 * Should upload html from HTTP URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadHtmlFromHttpUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(new URL(SAMPLE_URL_HTML))
				.name(NAME_UPLOAD_HTML_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
		assertEquals(NAME_UPLOAD_HTML_FROM_URL, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_HTML.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	/**
	 * Should upload Text from file URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadTextFromFileUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(SMALL_FILE.toURI().toURL())
				.name(NAME_UPLOAD_TXT_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
		assertEquals(NAME_UPLOAD_TXT_FROM_URL, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	/**
	 * Should upload Pdf from URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadPdfFromFileUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(PDF_FILE1.toURI().toURL())
				.name(NAME_UPLOAD_PDF_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
		assertEquals(NAME_UPLOAD_PDF_FROM_URL, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
