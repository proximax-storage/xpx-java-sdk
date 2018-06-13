package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static io.nem.xpx.model.DataTextContentType.TEXT_HTML;
import static io.nem.xpx.model.DataTextContentType.TEXT_PLAIN;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Upload_uploadTextDataIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String TEST_NAME_1 = "NAME1";
	public static final String TEST_NAME_RANDOM_1 = "RandomName1";
	public static final String KEYWORDS_PLAIN_AND_DATA = "plain,data";
	public static final String KEYWORDS_SECURE_AND_DATA = "secure,data";
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String ENCODING_UTF_ASCII = "ASCII";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(peerConnection);
	}

	@Test
	public void shouldUploadPlainData() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	@Test
	public void shouldUploadPlainDataHtml() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToString(HTML_FILE))
				.name(TEST_NAME_1)
				.contentType(TEXT_HTML.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_HTML.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void shouldUploadPlainDataAscii() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog ASCII".getBytes(ENCODING_UTF_ASCII)))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	
	@Test
	public void shouldUploadSecureData() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("secure - the quick brown fox jumps over the lazy dog UFT-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_SECURE_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void shouldUploadSecureDataAscii() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("secure - the quick brown fox jumps over the lazy dog ASCII".getBytes(),ENCODING_UTF_ASCII))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_SECURE_AND_DATA)
				.metadata(METADATA_AS_MAP) // one level map to json
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	@Test
	@Ignore
	public void shouldUploadPlainDataWithMosaic() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY).receiverPublicKey(TEST_PUBLIC_KEY)
				.data("mosaic - the quick brown fox jumps over the lazy dog")
				.name(TEST_NAME_RANDOM_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(MOSAIC_PRX)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_RANDOM_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
