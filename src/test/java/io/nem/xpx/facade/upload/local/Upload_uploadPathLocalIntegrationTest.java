package io.nem.xpx.facade.upload.local;

import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.facade.upload.UploadPathParameter;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.IntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Category(IntegrationTest.class)
@Ignore
public class Upload_uploadPathLocalIntegrationTest extends AbstractApiTest {

	private Upload unitUnderTest;

	public static final String KEYWORDS_PLAIN_AND_PATH = "plain,path";


	@Before
	public void setUp() {
		unitUnderTest = new Upload(LOCAL_HTTP_PEER_CONNECTION);
	}

	@Test
	@Ignore
	public void uploadPath() throws UploadException {

		UploadPathParameter parameter = UploadPathParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.path("src/test/resources/")
				.metadata(METADATA)
				.keywords(KEYWORDS_PLAIN_AND_PATH)
				.mosaics(MOSAIC_LAND_REGISTRY)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadPath(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_PATH, uploadResult.getDataMessage().keywords());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());

		LOGGER.info(uploadResult.getNemHash());
	}

}
