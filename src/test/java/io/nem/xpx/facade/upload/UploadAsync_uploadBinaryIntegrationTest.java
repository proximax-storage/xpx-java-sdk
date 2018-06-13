package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.model.DataTextContentType.VIDEO_QUICKTIME;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class UploadAsync_uploadBinaryIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String KEYWORDS_PLAIN_AND_BINARY = "plain,binary";

	private UploadAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(peerConnection);
	}

	@Test
	public void shouldUploadPlainBinaryTest() throws Exception{

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(SMALL_VIDEO_MOV_FILE))
				.name(SMALL_VIDEO_MOV_FILE.getName())
				.contentType(VIDEO_QUICKTIME.toString())
				.keywords(KEYWORDS_PLAIN_AND_BINARY)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadBinary(parameter, uploadResult -> {
			assertNotNull(uploadResult);
			assertNotNull(uploadResult.getNemHash());
			assertNotNull(uploadResult.getDataMessage());
			assertNotNull(uploadResult.getDataMessage().hash());
			assertNotNull(uploadResult.getDataMessage().digest());
			assertEquals(KEYWORDS_PLAIN_AND_BINARY, uploadResult.getDataMessage().keywords());
			assertEquals(SMALL_VIDEO_MOV_FILE.getName(), uploadResult.getDataMessage().name());
			assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
			assertEquals(VIDEO_QUICKTIME.toString(), uploadResult.getDataMessage().type());

			LOGGER.info(uploadResult.getNemHash());
		}).get();
	}
}
