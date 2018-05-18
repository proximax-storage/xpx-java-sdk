package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.MultiFileUploadResult;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.facade.upload.UploadMultipleFilesParameter;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.*;


@Category(RemoteIntegrationTest.class)
public class UploadRemoteMultiFilesTest extends AbstractApiTest {

	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";
	public static final String KEYWORDS_SECURE_AND_MULTIFILES = "secure,multifiles";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA)
				.build();

		unitUnderTest.uploadMultipleFiles(parameter);
	}

	@Test
	public void hasFailureWhenUploadingNonExistentFile() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.addFile(PDF_FILE1)
				.addFile(NON_EXISTENT_FILE)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

		assertNotNull(multiFileUploadResult);
		assertTrue(multiFileUploadResult.hasFailure());
		assertEquals(2, multiFileUploadResult.getFileUploadResults().size());

		assertEquals(PDF_FILE1, multiFileUploadResult.getFileUploadResults().get(0).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().name());
		assertEquals(METADATA, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().type());

		assertEquals(NON_EXISTENT_FILE, multiFileUploadResult.getFileUploadResults().get(1).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());
	}


	@Test
	public void shouldUploadMultipleFilesWithPlainMessageType() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA)
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

		assertNotNull(multiFileUploadResult);
		assertFalse(multiFileUploadResult.hasFailure());
		assertEquals(2, multiFileUploadResult.getFileUploadResults().size());

		assertEquals(PDF_FILE1, multiFileUploadResult.getFileUploadResults().get(0).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().name());
		assertEquals(METADATA, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().type());

		assertEquals(PDF_FILE2, multiFileUploadResult.getFileUploadResults().get(1).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().keywords());
		assertEquals(PDF_FILE2.getName(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().name());
		assertEquals(METADATA, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().type());
	}

	@Test
	public void shouldUploadMultipleFilesWithSecureMessageType() throws Exception {


		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_SECURE_AND_MULTIFILES)
				.metadata(METADATA)
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final MultiFileUploadResult multiFileUploadResult = unitUnderTest.uploadMultipleFiles(parameter);

		assertNotNull(multiFileUploadResult);
		assertFalse(multiFileUploadResult.hasFailure());
		assertEquals(2, multiFileUploadResult.getFileUploadResults().size());

		assertEquals(PDF_FILE1, multiFileUploadResult.getFileUploadResults().get(0).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().keywords());
		assertEquals(PDF_FILE1.getName(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().name());
		assertEquals(METADATA, multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getDataMessage().type());

		assertEquals(PDF_FILE2, multiFileUploadResult.getFileUploadResults().get(1).getFile());
		assertNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadException());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getNemHash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().hash());
		assertNotNull(multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_MULTIFILES, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().keywords());
		assertEquals(PDF_FILE2.getName(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().name());
		assertEquals(METADATA, multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), multiFileUploadResult.getFileUploadResults().get(1).getUploadResult().getDataMessage().type());
	}

}
