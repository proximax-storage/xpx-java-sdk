package io.nem.xpx.facade.upload.local;

import io.nem.xpx.builder.UploadMultiFilesParameterBuilder;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.upload.Upload;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadMultiFilesParameter;
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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;


@Category(RemoteIntegrationTest.class)
public class UploadLocalZipFileTest extends AbstractApiTest {

	public static final File FILE1 = new File("src//test//resources//test_pdf_file_v1.pdf");
	public static final File FILE2 = new File("src//test//resources//test_pdf_file_v2.pdf");
	public static final File NON_EXISTENT_FILE = new File("src//test//resources//pdf_non_existent.pdf");

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
		));
	}
	@Test(expected = UploadException.class)
	public void failWhenUploadingSameFileTwice() throws Exception {

		UploadMultiFilesParameter parameter = UploadMultiFilesParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName("test.zip")
				.addFile(FILE1)
				.addFile(FILE1)
				.keywords("plain,file")
				.metadata(JsonUtils.toJson(aSampleMetadata()))
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadMultiFilesParameter parameter = UploadMultiFilesParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName("test.zip")
				.keywords("plain,file")
				.metadata(JsonUtils.toJson(aSampleMetadata()))
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNonExistentFile() throws Exception {

		UploadMultiFilesParameter parameter = UploadMultiFilesParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName("test.zip")
				.addFile(NON_EXISTENT_FILE)
				.keywords("plain,file")
				.metadata(JsonUtils.toJson(aSampleMetadata()))
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}


	@Test
	public void shouldUploadFilesAsZipWithPlainMessageType() throws Exception {

		UploadMultiFilesParameter parameter = UploadMultiFilesParameterBuilder
				.messageType(MessageTypes.PLAIN)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName("test.zip")
				.addFile(FILE1)
				.addFile(FILE2)
				.keywords("plain,file")
				.metadata(JsonUtils.toJson(aSampleMetadata()))
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());

		LOGGER.info(uploadResult.getNemHash());
	}

	@Test
	public void shouldUploadFilesAsZipWithSecureMessageType() throws Exception {

		UploadMultiFilesParameter parameter = UploadMultiFilesParameterBuilder
				.messageType(MessageTypes.SECURE)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName("test.zip")
				.addFile(FILE1)
				.addFile(FILE2)
				.keywords("plain,file")
				.metadata(JsonUtils.toJson(aSampleMetadata()))
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());

		LOGGER.info(uploadResult.getNemHash());
	}


	@Test
	@Ignore
	public void uploadPlainFileWithMosaicTest() throws Exception {
		UploadMultiFilesParameter parameter = UploadMultiFilesParameterBuilder
				.messageType(MessageTypes.SECURE)
				.senderOrReceiverPrivateKey(this.xPvkey)
				.receiverOrSenderPublicKey(this.xPubkey)
				.zipFileName("test.zip")
				.addFile(FILE1)
				.addFile(FILE2)
				.keywords("plain,file")
				.metadata(JsonUtils.toJson(aSampleMetadata()))
				.mosaics(new Mosaic(new MosaicId(new NamespaceId("landregistry1"), "registry"),
						Quantity.fromValue(0)))
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());

		LOGGER.info(uploadResult.getNemHash());
	}

	private Map<String, String> aSampleMetadata() {
		Map<String,String> metaData = new HashMap<String,String>();
		metaData.put("key1", "value1");
		return metaData;
	}
}
