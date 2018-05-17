package io.nem.xpx.facade.download.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.download.Download;
import io.nem.xpx.facade.download.DownloadParameter;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.remote.AbstractApiTest;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;



@Category(RemoteIntegrationTest.class)
@Ignore
public class DownloadRemoteFileTest extends AbstractApiTest {

	private Download unitUnderTest;

	@Before
	public void setUp() {
		final RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		unitUnderTest = new Download(remotePeerConnection);
	}

//	@Test
//	public void downloadPlainBinaryTest() throws Exception {
//
//		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf"));
//
//		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
//				.nemHash("e0ca6d958ba01592ddeaa40e9d810a4314707f6673c2271e5d0eeb018a4be997").build());
//
//		assertArrayEquals(expected, message.getData());
//		assertEquals(NemMessageType.PLAIN, message.getMessageType());
//	}
//
//	@Test
//	public void downloadPlainLargeBinaryTest() throws Exception {
//
//		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_large_file.zip"));
//
//		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
//				.nemHash("980b78a6927216eeca327749861b6008fcfe24a41784ef80172443ed42556e5a").build());
//
//		assertArrayEquals(expected, message.getData());
//		assertEquals(NemMessageType.PLAIN, message.getMessageType());
//	}
//
//	@Test
//	public void downloadSecureBinaryTest()throws Exception {
//
//		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_small_file.txt"));
//
//		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
//				.nemHash("9a625840797fbac0e1c4db7f1d68de6e04cbcf325630ebf595ba0e7ee6fb0404")
//				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
//				.build());
//
//		assertArrayEquals(expected, message.getData());
//		assertEquals(NemMessageType.SECURE, message.getMessageType());
//	}
//
//
//	@Test
//	public void downloadSecureLargeBinaryTest() throws Exception {
//
//		byte[] expected = FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v2.pdf"));
//
//		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
//				.nemHash("1fce4dda18a865484f9f9c2b6a15e8c64756a89c5e903adbf76ec62eab1d41c7")
//				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
//				.build());
//
//		assertArrayEquals(expected, message.getData());
//		assertEquals(NemMessageType.SECURE, message.getMessageType());
//	}
}
