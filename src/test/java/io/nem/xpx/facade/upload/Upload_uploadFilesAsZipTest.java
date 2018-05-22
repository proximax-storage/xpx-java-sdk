package io.nem.xpx.facade.upload;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.facade.download.Download;
import io.nem.xpx.facade.download.DownloadBinaryResult;
import io.nem.xpx.facade.download.DownloadParameter;
import io.nem.xpx.model.NemMessageType;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Upload_uploadFilesAsZipTest {

	public static final String KEYWORDS_PLAIN_AND_ZIP_FILE = "plain,zipfile";
	public static final String ZIP_FILE_NAME = "test.zip";

	private Upload unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Upload(REMOTE_PEER_CONNECTION);
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

    public static class Download_downloadBinaryIntegrationTest extends AbstractFacadeIntegrationTest {

        private Download unitUnderTest;

        @Before
        public void setUp() {
            unitUnderTest = new Download(peerConnection);
        }

        @Test
        public void shouldDownloadPlainBinary() throws Exception {

            byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

            final DownloadBinaryResult message = unitUnderTest.downloadBinary(DownloadParameter.create()
                    .nemHash(FILE_TO_PLAIN_MSG_NEM_HASH_MAP.get(PDF_FILE1)).build());

            assertArrayEquals(expected, message.getData());
            assertEquals(NemMessageType.PLAIN, message.getMessageType());
        }

        @Test
        public void shouldDownloadPlainLargeBinary() throws Exception {

            byte[] expected = FileUtils.readFileToByteArray(PDF_FILE2);

            final DownloadBinaryResult message = unitUnderTest.downloadBinary(DownloadParameter.create()
                    .nemHash(FILE_TO_PLAIN_MSG_NEM_HASH_MAP.get(PDF_FILE2)).build());

            assertArrayEquals(expected, message.getData());
            assertEquals(NemMessageType.PLAIN, message.getMessageType());
        }

        @Test
        public void shouldDownloadSecureBinary()throws Exception {

            byte[] expected = FileUtils.readFileToByteArray(SMALL_FILE);

            final DownloadBinaryResult message = unitUnderTest.downloadBinary(DownloadParameter.create()
                    .nemHash(FILE_TO_SECURE_MSG_NEM_HASH_MAP.get(SMALL_FILE))
                    .securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
                    .build());

            assertArrayEquals(expected, message.getData());
            assertEquals(NemMessageType.SECURE, message.getMessageType());
        }


        @Test
        public void shouldDownloadSecureLargeBinary() throws Exception {

            byte[] expected = FileUtils.readFileToByteArray(PDF_FILE2);

            final DownloadBinaryResult message = unitUnderTest.downloadBinary(DownloadParameter.create()
                    .nemHash(FILE_TO_SECURE_MSG_NEM_HASH_MAP.get(PDF_FILE2))
                    .securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
                    .build());

            assertArrayEquals(expected, message.getData());
            assertEquals(NemMessageType.SECURE, message.getMessageType());
        }
    }
}
