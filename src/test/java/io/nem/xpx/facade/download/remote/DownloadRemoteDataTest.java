package io.nem.xpx.facade.download.remote;

import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.download.Download;
import io.nem.xpx.facade.download.DownloadParameter;
import io.nem.xpx.facade.download.DownloadResult;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.model.NemMessageType;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static org.junit.Assert.assertEquals;



@Category(RemoteIntegrationTest.class)
@Ignore
public class DownloadRemoteDataTest extends AbstractApiTest {

	private Download unitUnderTest;

	@Before
	public void setUp() {
		final RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);
		unitUnderTest = new Download(remotePeerConnection);
	}

	@Test
	public void downloadPlainDataTest() throws Exception {

		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash("2d8db574ef9c438d249d36c55137b315a68bc74ae3215d6bbc5c5e0598e6ff00").build());

		assertEquals("Assertion failed: Decryted data is not equal to expected", "test plain - new 1",
			new String(message.getData(), "UTF-8"));
		assertEquals(NemMessageType.PLAIN, message.getMessageType());
	}

	@Test
	public void downloadSecureDataTest() throws Exception {
		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash("51213456ec5fba0ca89980686ffb09310537dbf975adfb5fa808af2b52474a81")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected", "test plain - new 1",
				new String(message.getData(), "UTF-8"));
		assertEquals(NemMessageType.SECURE, message.getMessageType());
	}
	
	@Test
	public void downloadSecureAsciiDataTest() throws Exception {

		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash("51213456ec5fba0ca89980686ffb09310537dbf975adfb5fa808af2b52474a81")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected", "test plain - new 1",
				new String(message.getData(), "ASCII"));
		assertEquals(NemMessageType.SECURE, message.getMessageType());
	}

}
