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

import static io.proximax.xpx.facade.upload.Upload_uploadTextDataIntegrationTest.ENCODING_UTF_ASCII;
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
				.nemHash("712f8911302156815b95d19b6f96213713489bd663170c86dd333f882ebe6660").build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"plain - the quick brown fox jumps over the lazy dog UTF-8", result.getString());
	}

	/**
	 * Should download ascii data.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadAsciiData() throws Exception {

		final DownloadTextDataResult result = unitUnderTest.downloadTextData(DownloadParameter.create()
				.nemHash("967cee633d0dec52842d4d1c449051b828ab8baa6fd20549f269aaa5e6d643b7")
				.build());

		assertEquals("Assertion failed: Decryted data is not equal to expected",
				"plain - the quick brown fox jumps over the lazy dog ASCII", result.getString(ENCODING_UTF_ASCII));
	}

}
