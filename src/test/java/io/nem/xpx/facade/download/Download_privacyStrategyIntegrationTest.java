package io.nem.xpx.facade.download;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.model.NemMessageType;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.nem.xpx.testsupport.Constants.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class Download_privacyStrategyIntegrationTest extends AbstractFacadeIntegrationTest {

	private Download unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Download(peerConnection);
	}

	@Test
	public void shouldDownloadWithPlainPrivacyStrategy() throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(NEM_HASH_PDF_FILE1_PLAIN_PRIVACY_STRATEGY)
				.plainPrivacy()
				.build());

		assertThat(result.getData(), is(expected));
		assertThat(result.getMessageType(), is(NemMessageType.PLAIN));
		assertThat(result.getDataMessage(), is(notNullValue()));
	}

	@Test
	public void shouldDownloadWithSecuredWithNemKeysPrivacyStrategy()throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(NEM_HASH_PDF_FILE1_SECURED_WITH_NEM_KEYS_PRIVACY_STRATEGY)
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertThat(result.getData(), is(expected));
		assertThat(result.getMessageType(), is(NemMessageType.SECURE));
		assertThat(result.getDataMessage(), is(notNullValue()));
	}

	@Test
	public void shouldDownloadWithSecuredWithSenderNemKeysPrivacyStrategy()throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(NEM_HASH_PDF_FILE1_SECURED_WITH_SENDER_NEM_KEYS_PRIVACY_STRATEGY)
				.securedWithSenderNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY_2)
				.build());

		assertThat(result.getData(), is(expected));
		assertThat(result.getMessageType(), is(NemMessageType.SECURE));
		assertThat(result.getDataMessage(), is(notNullValue()));
	}

	@Test
	public void shouldDownloadWithSecuredWithNemPasswordPrivacyStrategy()throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(NEM_HASH_PDF_FILE1_SECURED_WITH_PASSWOR_PRIVACY_STRATEGY)
				.securedWithPasswordPrivacyStrategy(SECURE_PASSWORD)
				.build());

		assertThat(result.getData(), is(expected));
		assertThat(result.getMessageType(), is(NemMessageType.PLAIN));
		assertThat(result.getDataMessage(), is(notNullValue()));
	}

	@Test
	public void shouldDownloadWithSecuredWithShamirSecretSharingPrivacyStrategy()throws Exception {
		final Map<Integer, byte[]> minimumSecretParts = new HashMap<>();
		minimumSecretParts.put(3, SHAMIR_SECRET_PARTS.get(3));
		minimumSecretParts.put(4, SHAMIR_SECRET_PARTS.get(4));
		minimumSecretParts.put(7, SHAMIR_SECRET_PARTS.get(7));
		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(NEM_HASH_PDF_FILE1_SECURED_WITH_SHAMIR_SECRET_SHARING_PRIVACY_STRATEGY)
				.securedWithShamirSecretSharingPrivacyStrategy(SHAMIR_SECRET_TOTAL_PART_COUNT, SHAMIR_SECRET_MINIMUM_PART_COUNT_TO_BUILD,
						minimumSecretParts)
				.build());

		assertThat(result.getData(), is(expected));
		assertThat(result.getMessageType(), is(NemMessageType.PLAIN));
		assertThat(result.getDataMessage(), is(notNullValue()));
	}
}
