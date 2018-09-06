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

package io.proximax.xpx.facade.upload;

import io.proximax.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;

import static io.proximax.xpx.facade.DataTextContentType.IMAGE_PNG;
import static io.proximax.xpx.facade.DataTextContentType.TEXT_PLAIN;
import static io.proximax.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class UploadAsync_uploadFromUrlIntegrationTest.
 */
public class UploadAsync_uploadFromUrlIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String KEYWORDS_UPLOAD_FROM_URL = "url";
	public static final String NAME_UPLOAD_PNG_FROM_URL = "UploadPngFromUrl";
	public static final String NAME_UPLOAD_TXT_FROM_URL = "UploadTxtFromUrl";

	/** The unit under test. */
	private UploadAsync unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new UploadAsync(peerConnection);
	}

	/**
	 * Should upload PNG from Http URL asynchronously
	 *
	 * @throws Exception the exception
	 */
	@Test
	@Ignore("has dependency on an internet resource")
	public void shouldUploadPngFromHttpUrlAsynchronously() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(new URL(SAMPLE_URL_PNG))
				.name(NAME_UPLOAD_PNG_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadFromUrl(parameter, uploadResult -> {
			assertNotNull(uploadResult);
			assertNotNull(uploadResult.getNemHash());
			assertNotNull(uploadResult.getDataMessage());
			assertNotNull(uploadResult.getDataMessage().hash());
			assertNotNull(uploadResult.getDataMessage().digest());
			assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
			assertEquals(NAME_UPLOAD_PNG_FROM_URL, uploadResult.getDataMessage().name());
			assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
			assertEquals(IMAGE_PNG.toString(), uploadResult.getDataMessage().type());

			LOGGER.info(uploadResult.getNemHash());
		}).get();
	}

	/**
	 * Should upload text from File URL asynchronously
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadTextFromFileUrlAsynchronously() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(SMALL_FILE.toURI().toURL())
				.name(NAME_UPLOAD_TXT_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadFromUrl(parameter, uploadResult -> {
			assertNotNull(uploadResult);
			assertNotNull(uploadResult.getNemHash());
			assertNotNull(uploadResult.getDataMessage());
			assertNotNull(uploadResult.getDataMessage().hash());
			assertNotNull(uploadResult.getDataMessage().digest());
			assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
			assertEquals(NAME_UPLOAD_TXT_FROM_URL, uploadResult.getDataMessage().name());
			assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
			assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

			LOGGER.info(uploadResult.getNemHash());
		}).get();
	}
}
