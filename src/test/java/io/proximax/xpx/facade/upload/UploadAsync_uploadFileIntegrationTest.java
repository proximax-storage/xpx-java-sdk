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

import static io.proximax.xpx.facade.DataTextContentType.APPLICATION_PDF;
import static io.proximax.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * The Class UploadAsync_uploadFileIntegrationTest.
 */
public class UploadAsync_uploadFileIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant KEYWORDS_PLAIN_AND_FILE. */
	public static final String KEYWORDS_PLAIN_AND_FILE = "plain,file";

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
	 * Should upload file asynchronously
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadFileAsynchronously() throws Exception {
		UploadFileParameter parameter = UploadFileParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.file(PDF_FILE1)
				.keywords(KEYWORDS_PLAIN_AND_FILE)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadFile(parameter, uploadResult -> {
			assertNotNull(uploadResult);
			assertNotNull(uploadResult.getNemHash());
			assertNotNull(uploadResult.getDataMessage());
			assertNotNull(uploadResult.getDataMessage().hash());
			assertNotNull(uploadResult.getDataMessage().digest());
			assertEquals(KEYWORDS_PLAIN_AND_FILE, uploadResult.getDataMessage().keywords());
			assertEquals(PDF_FILE1.getName(), uploadResult.getDataMessage().name());
			assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
			assertEquals(APPLICATION_PDF.toString(), uploadResult.getDataMessage().type());

			LOGGER.info(uploadResult.getNemHash());
		}).get();
	}
}
