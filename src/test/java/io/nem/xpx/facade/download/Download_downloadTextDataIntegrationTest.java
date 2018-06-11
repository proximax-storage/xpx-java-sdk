package io.nem.xpx.facade.download;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.model.NemMessageType;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static org.junit.Assert.assertEquals;



/**
 * The Class Download_downloadTextDataIntegrationTest.
 */
public class Download_downloadTextDataIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The unit under test. */
	private Download unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new Download(peerConnection);
	}

	/**
	 * Should download plain data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadPlainData() throws Exception {

		final DownloadTextDataResult result = unitUnderTest.downloadTextData(DownloadParameter.create()
				.nemHash("7f2d1944016f1259e552b34cb5029d7473074856229094acc5ba479549e59411").build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"!\"#$%&'()*+,-.:\t ;<=>?@[\\]^_`{|}~", result.getString());
		assertEquals(NemMessageType.PLAIN, result.getMessageType());
	}

	/**
	 * Should download secure data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadSecureData() throws Exception {
		final DownloadTextDataResult result = unitUnderTest.downloadTextData(DownloadParameter.create()
				.nemHash("47ef7e2a12ea7413a69ef215e33b1d32f21ccbf743b9358efc9909b869ab7e70")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"test secure - new 2", result.getString());
		assertEquals(NemMessageType.SECURE, result.getMessageType());
	}
	
	/**
	 * Should download secure ascii data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadSecureAsciiData() throws Exception {

		final DownloadTextDataResult result = unitUnderTest.downloadTextData(DownloadParameter.create()
				.nemHash("e469a236fe5ac1d0bc07d50fc2e009aaf10b68517bfc81517bf83de87fa80594")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"test secure - new 2", result.getString());
		assertEquals(NemMessageType.SECURE, result.getMessageType());
	}

}
