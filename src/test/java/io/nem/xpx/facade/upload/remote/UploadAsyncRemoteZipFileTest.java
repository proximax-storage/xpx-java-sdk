package io.nem.xpx.facade.upload.remote;

import io.nem.xpx.builder.UploadMultiFilesParameterBuilder;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.upload.UploadAsync;
import io.nem.xpx.facade.upload.UploadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.UploadMultiFilesParameter;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.utils.JsonUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.model.MessageTypes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;


@Category(RemoteIntegrationTest.class)
public class UploadAsyncRemoteZipFileTest extends AbstractApiTest {

	public static final File FILE1 = new File("src//test//resources//test_pdf_file_v1.pdf");
	public static final File FILE2 = new File("src//test//resources//test_pdf_file_v2.pdf");

	private UploadAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(new RemotePeerConnection(uploadNodeBasePath));
	}

	@Test
	public void shouldUploadFilesAsZipAsync() throws Exception {

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

		UploadResult uploadResult = unitUnderTest.uploadFilesAsZip(parameter,
				result -> LOGGER.info(result != null ? result.getNemHash() : null)).get();

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
