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
				.nemHash("8c6a8366462caa5e0f0b106bed7864d54f3fa29eabe797238528db329bbe64cd")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"secure - the quick brown fox jumps over the lazy dog UFT-8", result.getString());
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
				.nemHash("8fc0a1393a913086895ec784435db74566dd5bf163580e7e87731791530dfffa")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"secure - the quick brown fox jumps over the lazy dog ASCII", result.getString());
		assertEquals(NemMessageType.SECURE, result.getMessageType());
	}

}
