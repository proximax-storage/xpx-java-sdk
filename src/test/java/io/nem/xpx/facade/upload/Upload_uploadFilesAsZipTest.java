package io.nem.xpx.facade.upload;

import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.testsupport.Constants.*;


/**
 * The Class Upload_uploadFilesAsZipTest.
 */
public class Upload_uploadFilesAsZipTest {

	/** The Constant KEYWORDS_PLAIN_AND_ZIP_FILE. */
	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";
	
	/** The Constant ZIP_FILE_NAME. */
	public static final String ZIP_FILE_NAME = "test.zip";

	/** The unit under test. */
	private Upload unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new Upload(REMOTE_PEER_CONNECTION);
	}

	/**
	 * Fail when uploading same file twice.
	 *
	 * @throws Exception the exception
	 */
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

	/**
	 * Fail when uploading no file.
	 *
	 * @throws Exception the exception
	 */
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

	/**
	 * Fail when uploading non existent file.
	 *
	 * @throws Exception the exception
	 */
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
