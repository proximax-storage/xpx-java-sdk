package io.nem.xpx.facade.download.local;

import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.download.Download;
import io.nem.xpx.facade.download.DownloadParameter;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.remote.AbstractApiTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.nem.xpx.testsupport.Constants.*;
import static io.nem.xpx.testsupport.Constants.SAMPLE_LARGE_VIDEO_FILE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@Category(LocalIntegrationTest.class)
public class DownloadLocalBinaryTest extends AbstractApiTest {

	private Download unitUnderTest;

	@Before
	public void setUp() {
		final LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
		);
		unitUnderTest = new Download(localPeerConnection);
	}

	@Test
	public void downloadPlainBinaryTest() throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(SAMPLE_PDF_FILE1);

		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash(FILE_TO_PLAIN_NEM_HASH_MAP.get(SAMPLE_PDF_FILE1)).build());

		assertArrayEquals(expected, message.getData());
		assertEquals(NemMessageType.PLAIN, message.getMessageType());
	}

	@Test
	public void downloadPlainLargeBinaryTest() throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(SAMPLE_LARGE_VIDEO_FILE);

		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash(FILE_TO_PLAIN_NEM_HASH_MAP.get(SAMPLE_LARGE_VIDEO_FILE)).build());

		assertArrayEquals(expected, message.getData());
		assertEquals(NemMessageType.PLAIN, message.getMessageType());
	}

//	@Test
//	public void downloadPlainPublicBinaryTest() throws Exception {
//
//		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_small_file.txt"));
//
//		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
//				.nemHash("1c66641e3340ef14d617e327ca8a4c4484d749df7e3400aa65c9d34dd0738d96").build());
//
//		assertArrayEquals(expected, message.getData());
//		assertEquals(NemMessageType.PLAIN, message.getMessageType());
//	}
//
//	@Test
//	public void downloadPlainPublicLargeBinaryTest() throws Exception {
//
//		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_large_file.zip"));
//
//		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
//				.nemHash("9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404").build());
//
//		assertArrayEquals(expected, message.getData());
//		assertEquals(NemMessageType.PLAIN, message.getMessageType());
//	}

	@Test
	public void downloadSecureBinaryTest()throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_small_file.txt"));

		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash("9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertArrayEquals(expected, message.getData());
		assertEquals(NemMessageType.SECURE, message.getMessageType());
	}

	
	@Test
	public void downloadSecureLargeBinaryTest() throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v2.pdf"));

		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash("1fce4dda18a865484f9f9c2b6a15e8c64756a89c5e903adbf76ec62eab1d41c7")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertArrayEquals(expected, message.getData());
		assertEquals(NemMessageType.SECURE, message.getMessageType());
	}

}
