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

import static io.proximax.xpx.facade.DataTextContentType.TEXT_PLAIN;
import static io.proximax.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * The Class UploadAsync_uploadTextDataIntegrationTest.
 */
public class UploadAsync_uploadTextDataIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The Constant TEST_NAME_RANDOM_1. */
	public static final String TEST_NAME_RANDOM_1 = "RandomName1";
	
	/** The Constant KEYWORDS_PLAIN_AND_DATA. */
	public static final String KEYWORDS_PLAIN_AND_DATA = "plain,data";
	
	/** The Constant ENCODING_UTF_8. */
	public static final String ENCODING_UTF_8 = "UTF-8";

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
	 * Should upload text data asynchronously.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldUploadTextDataAsynchronously() throws Exception {

		UploadTextDataParameter parameter = UploadTextDataParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
				.name(TEST_NAME_RANDOM_1)
				.contentType(TEXT_PLAIN.toString())
				.encoding(ENCODING_UTF_8)
				.keywords(KEYWORDS_PLAIN_AND_DATA)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadTextData(parameter, uploadResult -> {
			assertNotNull(uploadResult);
			assertNotNull(uploadResult.getNemHash());
			assertNotNull(uploadResult.getDataMessage());
			assertNotNull(uploadResult.getDataMessage().hash());
			assertNotNull(uploadResult.getDataMessage().digest());
			assertEquals(KEYWORDS_PLAIN_AND_DATA, uploadResult.getDataMessage().keywords());
			assertEquals(TEST_NAME_RANDOM_1, uploadResult.getDataMessage().name());
			assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());
			assertEquals(TEXT_PLAIN.toString(), uploadResult.getDataMessage().type());

			LOGGER.info(uploadResult.getNemHash());
		}).get();
	}
	

}
