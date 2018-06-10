package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.builder.UploadFilesAsZipParameterBuilder;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.UploadFilesAsZipParameter;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.MessageTypes;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Category(RemoteIntegrationTest.class)
public class UploadAsyncRemoteZipFileTest extends AbstractApiTest {

	private UploadAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test
	public void shouldUploadFilesAsZipAsync() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName(SAMPLE_ZIP_FILE_NAME)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_PDF_FILE2)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter,
				result -> LOGGER.info(result != null ? result.getNemHash() : null)).get();

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(SAMPLE_KEYWORDS, uploadResult.getDataMessage().keywords());
		assertEquals(SAMPLE_ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(SAMPLE_METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());
	}
}
