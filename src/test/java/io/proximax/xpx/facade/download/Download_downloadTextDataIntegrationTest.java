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
import org.junit.Before;
import org.junit.Test;

import static io.proximax.xpx.testsupport.Constants.TEST_PRIVATE_KEY;
import static io.proximax.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
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
	 * Should download text data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadTextData() throws Exception {

		final DownloadTextDataResult result = unitUnderTest.downloadTextData(DownloadParameter.create()
				.nemHash("7f2d1944016f1259e552b34cb5029d7473074856229094acc5ba479549e59411").build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"!\"#$%&'()*+,-.:\t ;<=>?@[\\]^_`{|}~", result.getString());
	}

	/**
	 * Should download secure ascii data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadAsciiData() throws Exception {

		final DownloadTextDataResult result = unitUnderTest.downloadTextData(DownloadParameter.create()
				.nemHash("8fc0a1393a913086895ec784435db74566dd5bf163580e7e87731791530dfffa")
				.securedWithNemKeysPrivacyStrategy(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY)
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"secure - the quick brown fox jumps over the lazy dog ASCII", result.getString());
	}

}