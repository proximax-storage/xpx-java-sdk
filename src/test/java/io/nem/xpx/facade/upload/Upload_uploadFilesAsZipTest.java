package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.connection.PeerConnection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static io.nem.xpx.testsupport.Constants.*;

public class Upload_uploadFilesAsZipTest {

	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";
	public static final String ZIP_FILE_NAME = "test.zip";

	private Upload unitUnderTest;

	@Mock
	private PeerConnection peerConnection;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		unitUnderTest = new Upload(peerConnection);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingSameFileTwice() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(PDF_FILE1)
				.addFile(PDF_FILE1)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}

	@Test(expected = UploadException.class)
	public void failWhenUploadingNonExistentFile() throws Exception {

		UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.zipFileName(ZIP_FILE_NAME)
				.addFile(NON_EXISTENT_FILE)
				.keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadFilesAsZip(parameter);
	}
}
