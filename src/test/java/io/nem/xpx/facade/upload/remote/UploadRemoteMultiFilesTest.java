package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.builder.UploadFilesAsZipParameterBuilder;
import io.nem.xpx.builder.UploadMultipleFilesParameterBuilder;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.MultiFileUploadResult;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFilesAsZipParameter;
import io.nem.xpx.model.UploadMultipleFilesParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import java.util.HashMap;
import java.util.Map;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static org.junit.Assert.*;


@Category(RemoteIntegrationTest.class)
public class UploadRemoteMultiFilesTest extends AbstractApiTest {

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		unitUnderTest.uploadMultipleFiles(parameter);
	}

	@Test
	public void hasFailureWhenUploadingNonExistentFile() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_NON_EXISTENT_FILE)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

		assertNotNull(multiFileUploadResult);
		assertTrue(multiFileUploadResult.hasFailure());
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

		assertEquals(SAMPLE_NON_EXISTENT_FILE, multiFileUploadResult.getFileUploadResults().get(1).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());
	}


	@Test
	public void shouldUploadMultipleFilesWithPlainMessageType() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_PDF_FILE2)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

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

	@Test
	public void shouldUploadMultipleFilesWithSecureMessageType() throws Exception {


		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameterBuilder
				.messageType(MessageTypes.SECURE)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_PDF_FILE2)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

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
