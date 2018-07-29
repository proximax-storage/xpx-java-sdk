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
import org.junit.Test;

import java.net.URL;

import static io.proximax.xpx.facade.DataTextContentType.*;
import static io.proximax.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class Upload_uploadFromUrlIntegrationTest.
 */
public class Upload_uploadFromUrlIntegrationTest extends AbstractFacadeIntegrationTest {

	public static final String KEYWORDS_UPLOAD_FROM_URL = "url";
	public static final String NAME_UPLOAD_HTML_FROM_URL = "UploadHtmlFromUrl";
	public static final String NAME_UPLOAD_PNG_FROM_URL = "UploadPngFromUrl";
	public static final String NAME_UPLOAD_TXT_FROM_URL = "UploadTxtFromUrl";
	public static final String NAME_UPLOAD_PDF_FROM_URL = "UploadPdfFromUrl";

	/** The unit under test. */
	private Upload unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new Upload(peerConnection);
	}
	
	/**
	 * Should upload PNG from HTTP URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadPngFromHttpUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(new URL(SAMPLE_URL_PNG))
				.name(NAME_UPLOAD_PNG_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

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
	}

	/**
	 * Should upload html from HTTP URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadHtmlFromHttpUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(new URL(SAMPLE_URL_HTML))
				.name(NAME_UPLOAD_HTML_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
		assertEquals(NAME_UPLOAD_HTML_FROM_URL, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(TEXT_HTML.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}

	/**
	 * Should upload Text from file URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadTextFromFileUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(SMALL_FILE.toURI().toURL())
				.name(NAME_UPLOAD_TXT_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

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
	}

	/**
	 * Should upload Pdf from URL.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadPdfFromFileUrl() throws Exception {
		UploadFromUrlParameter parameter = UploadFromUrlParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.url(PDF_FILE1.toURI().toURL())
				.name(NAME_UPLOAD_PDF_FROM_URL)
				.keywords(KEYWORDS_UPLOAD_FROM_URL)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadFromUrl(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_UPLOAD_FROM_URL, uploadResult.getDataMessage().keywords());
		assertEquals(NAME_UPLOAD_PDF_FROM_URL, uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
