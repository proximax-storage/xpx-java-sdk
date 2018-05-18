package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.facade.upload.UploadTextDataParameter;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import static io.nem.xpx.facade.DataTextContentType.TEXT_HTML;
import static io.nem.xpx.facade.DataTextContentType.TEXT_PLAIN;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Category(RemoteIntegrationTest.class)
public class UploadRemoteDataTest extends AbstractApiTest {

	public static final String TEST_NAME_1 = "NAME1";
	public static final String TEST_NAME_RANDOM_1 = "RandomName1";
	public static final String KEYWORDS_PLAIN_AND_DATA = "plain,data";
	public static final String KEYWORDS_SECURE_AND_DATA = "secure,data";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test
	public void uploadPlainDataTest() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	@Test
	public void uploadPlainDataHtmlTest() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToString(HTML_FILE))
				.name(TEST_NAME_1)
				.contentType(TEXT_HTML.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_HTML.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void uploadPlainDataAsciiTest() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog ASCII".getBytes(ENCODING_UTF_ASCII)))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	
	@Test
	public void uploadSecureDataTest() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(new String("secure - the quick brown fox jumps over the lazy dog UFT-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_SECURE_AND_DATA)
				.metadata(METADATA)
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
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void uploadSecureDataAsciiTest() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(new String("secure - the quick brown fox jumps over the lazy dog ASCII".getBytes(),ENCODING_UTF_ASCII))
				.name(TEST_NAME_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_SECURE_AND_DATA)
				.metadata(METADATA) // one level map to json
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
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	@Test
	public void uploadPlainDataWithMosaicTest() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY).receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data("mosaic - the quick brown fox jumps over the lazy dog")
				.name(TEST_NAME_RANDOM_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA)
				.mosaics(new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"),
						Quantity.fromValue(0)))
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
		assertEquals(TEST_NAME_RANDOM_1, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
