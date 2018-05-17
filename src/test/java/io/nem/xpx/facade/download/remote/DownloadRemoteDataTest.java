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
				.nemHash("7f2d1944016f1259e552b34cb5029d7473074856229094acc5ba479549e59411").build());

		assertEquals("Assertion failed: Decryted data is not equal to expected", "!\"#$%&'()*+,-.:\t ;<=>?@[\\]^_`{|}~",
			new String(message.getData(), "UTF-8"));
		assertEquals(NemMessageType.PLAIN, message.getMessageType());
	}

	@Test
	public void downloadSecureDataTest() throws Exception {
		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash("47ef7e2a12ea7413a69ef215e33b1d32f21ccbf743b9358efc9909b869ab7e70")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected", "test secure - new 2",
				new String(message.getData(), "UTF-8"));
		assertEquals(NemMessageType.SECURE, message.getMessageType());
	}
	
	@Test
	public void downloadSecureAsciiDataTest() throws Exception {

		final DownloadResult message = unitUnderTest.download(DownloadParameter.create()
				.nemHash("e469a236fe5ac1d0bc07d50fc2e009aaf10b68517bfc81517bf83de87fa80594")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected", "test secure - new 2",
				new String(message.getData(), "ASCII"));
		assertEquals(NemMessageType.SECURE, message.getMessageType());
	}

}
