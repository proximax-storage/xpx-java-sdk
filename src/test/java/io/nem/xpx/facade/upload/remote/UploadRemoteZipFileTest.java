package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.builder.UploadFilesAsZipParameterBuilder;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFilesAsZipParameter;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@Category(RemoteIntegrationTest.class)
public class UploadRemoteZipFileTest extends AbstractApiTest {


	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingSameFileTwice() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName(SAMPLE_ZIP_FILE_NAME)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_PDF_FILE1)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName(SAMPLE_ZIP_FILE_NAME)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNonExistentFile() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName(SAMPLE_ZIP_FILE_NAME)
				.addFile(SAMPLE_NON_EXISTENT_FILE)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}


	@Test
	public void shouldUploadFilesAsZipWithPlainMessageType() throws Exception {

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

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(SAMPLE_KEYWORDS, uploadResult.getDataMessage().keywords());
		assertEquals(SAMPLE_ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(SAMPLE_METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void shouldUploadFilesAsZipWithSecureMessageType() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameterBuilder
				.messageType(MessageTypes.SECURE)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName(SAMPLE_ZIP_FILE_NAME)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_PDF_FILE2)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(SAMPLE_KEYWORDS, uploadResult.getDataMessage().keywords());
		assertEquals(SAMPLE_ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(SAMPLE_METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}


	@Test
	@Ignore
	public void uploadPlainFileWithMosaicTest() throws Exception {
		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameterBuilder
				.messageType(MessageTypes.SECURE)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName(SAMPLE_ZIP_FILE_NAME)
				.addFile(SAMPLE_PDF_FILE1)
				.addFile(SAMPLE_PDF_FILE2)
				.keywords(SAMPLE_KEYWORDS)
				.metadata(SAMPLE_METADATA)
				.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
						Quantity.fromValue(0)))
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(SAMPLE_KEYWORDS, uploadResult.getDataMessage().keywords());
		assertEquals(SAMPLE_ZIP_FILE_NAME, uploadResult.getDataMessage().name());
		assertEquals(SAMPLE_METADATA, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
