package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.connection.PeerConnection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static io.nem.xpx.testsupport.Constants.*;

public class Upload_uploadMultipleFilesTest {

	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";

	private Upload unitUnderTest;

	@Mock
	private PeerConnection peerConnection;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		unitUnderTest = new Upload(peerConnection);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadMultipleFiles(parameter);
	}
}
