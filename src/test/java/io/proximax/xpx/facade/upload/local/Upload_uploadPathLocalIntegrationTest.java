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

package io.proximax.xpx.facade.upload.local;

import io.proximax.xpx.facade.upload.Upload;
import io.proximax.xpx.facade.upload.UploadException;
import io.proximax.xpx.facade.upload.UploadPathParameter;
import io.proximax.xpx.facade.upload.UploadResult;
import io.proximax.xpx.remote.AbstractApiTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static io.proximax.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * The Class Upload_uploadPathLocalIntegrationTest.
 */
@Ignore
public class Upload_uploadPathLocalIntegrationTest extends AbstractApiTest {

	/** The unit under test. */
	private Upload unitUnderTest;

	/** The Constant KEYWORDS_PLAIN_AND_PATH. */
	public static final String KEYWORDS_PLAIN_AND_PATH = "plain,path";


	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new Upload(LOCAL_HTTP_PEER_CONNECTION);
	}

	/**
	 * Upload path.
	 *
	 * @throws UploadException the upload exception
	 */
	@Test
	@Ignore
	public void uploadPath() throws UploadException {

		UploadPathParameter parameter = UploadPathParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.path("src/test/resources/")
				.metadata(METADATA_AS_MAP)
				.keywords(KEYWORDS_PLAIN_AND_PATH)
				.mosaics(MOSAIC_LAND_REGISTRY)
				.build();

		final UploadResult uploadResult = unitUnderTest.uploadPath(parameter);

		assertNotNull(uploadResult);
		assertNotNull(uploadResult.getNemHash());
		assertNotNull(uploadResult.getDataMessage());
		assertNotNull(uploadResult.getDataMessage().hash());
		assertNotNull(uploadResult.getDataMessage().digest());
		assertEquals(KEYWORDS_PLAIN_AND_PATH, uploadResult.getDataMessage().keywords());
		assertEquals(METADATA_AS_STRING, uploadResult.getDataMessage().metaData());

		LOGGER.info(uploadResult.getNemHash());
	}

}
