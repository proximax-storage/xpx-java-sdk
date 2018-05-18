package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.upload.UploadFilesAsZipParameter;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Category(RemoteIntegrationTest.class)
public class UploadAsyncRemoteZipFileTest extends AbstractApiTest {

	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";

	private UploadAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test
	public void shouldUploadFilesAsZipAsync() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA)
				.build();

		UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter,
				result -> LOGGER.info(result != null ? result.getNemHash() : null)).get();

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_ZIP_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());
	}
}
