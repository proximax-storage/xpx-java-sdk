/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.facade.download;

import io.proximax.xpx.facade.AbstractFacadeIntegrationTest;
import io.proximax.xpx.model.NemMessageType;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.proximax.xpx.testsupport.Constants.*;
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

		assertArrayEquals(expected, result.getData());
		assertEquals(NemMessageType.PLAIN, result.getMessageType());
	}

	@Test
	public void shouldDownloadWithSecuredWithNemKeysPrivacyStrategy()throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(NEM_HASH_PDF_FILE1_SECURED_WITH_NEM_KEYS_PRIVACY_STRATEGY)
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertArrayEquals(expected, result.getData());
		assertEquals(NemMessageType.SECURE, result.getMessageType());
	}

	@Test
	public void shouldDownloadWithSecuredWithNemPasswordPrivacyStrategy()throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(NEM_HASH_PDF_FILE1_SECURED_WITH_PASSWOR_PRIVACY_STRATEGY)
				.securedWithPasswordPrivacyStrategy(SECURE_PASSWORD)
				.build());

		assertArrayEquals(expected, result.getData());
		assertEquals(NemMessageType.PLAIN, result.getMessageType());
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

		assertArrayEquals(expected, result.getData());
		assertEquals(NemMessageType.PLAIN, result.getMessageType());
	}
}
