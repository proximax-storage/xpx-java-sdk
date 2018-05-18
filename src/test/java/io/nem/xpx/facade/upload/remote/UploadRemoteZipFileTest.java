package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadException;
import io.nem.xpx.facade.upload.UploadFilesAsZipParameter;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Category(RemoteIntegrationTest.class)
public class UploadRemoteZipFileTest extends AbstractApiTest {

	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";
	public static final String KEYWORDS_SECURE_AND_ZIP_FILE = "secure,zipfile";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingSameFileTwice() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE1)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNonExistentFile() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(NON_EXISTENT_FILE)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}


	@Test
	public void shouldUploadFilesAsZipWithPlainMessageType() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_ZIP_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void shouldUploadFilesAsZipWithSecureMessageType() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_SECURE_AND_ZIP_FILE)
				.metadata(METADATA)
				.securedWithNemKeysPrivacyStrategy()
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_ZIP_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}


	@Test
	@Ignore
	public void uploadPlainFileWithMosaicTest() throws Exception {
		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderOrReceiverPrivateKey(TEST_PRIVATE_KEY)
				.receiverOrSenderPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE2)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA)
				.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
						Quantity.fromValue(0)))
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_ZIP_FILE, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
