package io.nem.xpx.facade.upload.local;

import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.upload.MultiFileUploadResult;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.facade.upload.UploadMultipleFilesParameter;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.stream.Collectors;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static org.junit.Assert.*;


@Category(RemoteIntegrationTest.class)
public class UploadAsyncLocalMultipleFilesTest extends AbstractApiTest {

	private UploadAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
		));
	}
	@Test
	public void shouldUploadMultipleFilesAsync() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_PDF_FILE2)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter,
				result -> LOGGER.info(result.getFileUploadResults().stream()
						.map(f -> f.getUploadResult().getNemHash()).collect(Collectors.joining(",")))).get();

		assertNotNull(multiFileUploadResult);
		assertFalse(multiFileUploadResult.hasFailure());
		assertEquals(2, multiFileUploadResult.getFileUploadResults().size());

		assertEquals(SAMPLE_PDF_FILE1, multiFileUploadResult.getFileUploadResults().get(0).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().digest());
		assertEquals(SAMPLE_KEYWORDS, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().keywords());
		assertEquals(SAMPLE_PDF_FILE1.getName(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().name());
		assertEquals(SAMPLE_METADATA, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().type());

		assertEquals(SAMPLE_PDF_FILE2, multiFileUploadResult.getFileUploadResults().get(1).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().digest());
		assertEquals(SAMPLE_KEYWORDS, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().keywords());
		assertEquals(SAMPLE_PDF_FILE2.getName(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().name());
		assertEquals(SAMPLE_METADATA, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().type());
	}
}
