package io.nem.xpx.facade.upload;

import io.nem.xpx.integration.tests.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.testsupport.Constants.*;

@Category(UnitTest.class)
public class Upload_uploadMultipleFilesTest {

	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(REMOTE_PEER_CONNECTION);
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
}
