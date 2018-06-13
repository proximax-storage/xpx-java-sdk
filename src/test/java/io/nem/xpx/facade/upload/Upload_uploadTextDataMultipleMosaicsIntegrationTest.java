package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import static io.nem.xpx.model.DataTextContentType.TEXT_HTML;
import static io.nem.xpx.model.DataTextContentType.TEXT_PLAIN;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Upload_uploadTextDataMultipleMosaicsIntegrationTest extends AbstractFacadeIntegrationTest {

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
	public void shouldUploadPlainDataWithMosaics() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
				Quantity.fromValue(10000));
		
	
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(blueNumberAsset)
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
	public void shouldUploadPlainDataHtmlWithMosaics() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
				Quantity.fromValue(10000));
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToString(HTML_FILE))
				.name(TEST_NAME_1)
				.contentType(TEXT_HTML.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(blueNumberAsset)
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
	public void shouldUploadPlainDataAsciiWithMosaics() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
				Quantity.fromValue(10000));
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog ASCII".getBytes(ENCODING_UTF_ASCII)))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(blueNumberAsset)
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
	public void shouldUploadSecureDataWithMosaics() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
				Quantity.fromValue(10000));
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("secure - the quick brown fox jumps over the lazy dog UFT-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_SECURE_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(blueNumberAsset)
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
	public void shouldUploadSecureDataAsciiWithMosaics() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
				Quantity.fromValue(10000));
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("secure - the quick brown fox jumps over the lazy dog ASCII".getBytes(),ENCODING_UTF_ASCII))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_SECURE_AND_DATA)
				.metadata(METADATA_AS_MAP) // one level map to json
				.mosaics(blueNumberAsset)
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
	
	@Test(expected=UploadException.class)
	public void shouldUploadSecureDataAsciiWithInvalidMosaics() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("invalid"), "invalid"),
				Quantity.fromValue(10000));
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("secure - the quick brown fox jumps over the lazy dog ASCII".getBytes(),ENCODING_UTF_ASCII))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_SECURE_AND_DATA)
				.metadata(METADATA_AS_MAP) // one level map to json
				.mosaics(blueNumberAsset)
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
	public void shouldUploadPlainDataWithMosaicWithMosaics() throws Exception {
		Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
				Quantity.fromValue(10000));
		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY).receiverPublicKey(TEST_PUBLIC_KEY)
				.data("mosaic - the quick brown fox jumps over the lazy dog")
				.name(TEST_NAME_RANDOM_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.mosaics(MOSAIC_PRX,blueNumberAsset)
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
