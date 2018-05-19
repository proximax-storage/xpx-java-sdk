package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.integration.tests.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.facade.DataTextContentType.TEXT_PLAIN;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Category(IntegrationTest.class)
public class UploadAsync_uploadTextDataIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String TEST_NAME_RANDOM_1 = "RandomName1";
	public static final String KEYWORDS_PLAIN_AND_DATA = "plain,data";
	public static final String ENCODING_UTF_8 = "UTF-8";

	private UploadAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(peerConnection);
	}

	@Test
	public void shouldUploadMultipleFilesAsync() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY_2)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_RANDOM_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA)
				.build();

		unitUnderTest.uploadTextData(parameter, uploadResult -> {
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
		}).get();
	}
}
