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
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static io.proximax.xpx.facade.DataTextContentType.*;
import static io.proximax.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class Upload_uploadBinaryIntegrationTest.
 */
public class Upload_uploadBinaryIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant KEYWORDS_PLAIN_AND_BINARY. */
	public static final String KEYWORDS_PLAIN_AND_BINARY = "plain,binary";
	
	/** The Constant KEYWORDS_SECURE_AND_BINARY. */
	public static final String KEYWORDS_SECURE_AND_BINARY = "secure,binary";

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
	 * Should upload binary
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadBinary() throws Exception{

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(SMALL_VIDEO_MOV_FILE))
				.name(SMALL_VIDEO_MOV_FILE.getName())
				.contentType(VIDEO_QUICKTIME.toString())
				.keywords(KEYWORDS_PLAIN_AND_BINARY)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(SMALL_VIDEO_MOV_FILE.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(VIDEO_QUICKTIME.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	
	/**
	 * Should upload large binary
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadLargeBinary() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(PDF_FILE2))
				.name(PDF_FILE2.getName())
				.contentType(APPLICATION_PDF.toString())
				.keywords(KEYWORDS_PLAIN_AND_BINARY)
				.metadata(METADATA_AS_MAP)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(PDF_FILE2.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
	

	/**
	 * Should upload zip binary
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadZipBinary() throws Exception {

		UploadBinaryParameter parameter = UploadBinaryParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(FileUtils.readFileToByteArray(ZIP_FILE))
				.name(ZIP_FILE.getName())
				.contentType(APPLICATION_ZIP.toString())
				.keywords(KEYWORDS_SECURE_AND_BINARY)
				.metadata(METADATA_AS_MAP)
				.securedWithPasswordPrivacyStrategy(SECURE_PASSWORD)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadBinary(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_SECURE_AND_BINARY, uploadResult.getDataMessage().keywords());
		assertEquals(ZIP_FILE.getName(), uploadResult.getDataMessage().name());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
		assertEquals(APPLICATION_ZIP.toString(), uploadResult.getDataMessage().type());

		LOGGER.info(uploadResult.getNemHash());
	}
}
