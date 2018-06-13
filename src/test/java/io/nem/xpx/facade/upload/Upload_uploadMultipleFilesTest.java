package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.connection.PeerConnection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static io.nem.xpx.testsupport.Constants.*;


/**
 * The Class Upload_uploadMultipleFilesTest.
 */
public class Upload_uploadMultipleFilesTest {

	/** The Constant KEYWORDS_PLAIN_AND_MULTIFILES. */
	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";

	/** The unit under test. */
	private Upload unitUnderTest;

	@Mock
	private PeerConnection peerConnection;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		unitUnderTest = new Upload(peerConnection);
	}

	/**
	 * Fail when uploading no file.
	 *
	 * @throws Exception the exception
	 */
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
