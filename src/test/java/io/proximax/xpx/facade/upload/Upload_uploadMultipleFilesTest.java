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

import io.proximax.xpx.facade.connection.PeerConnection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static io.proximax.xpx.testsupport.Constants.*;


/**
 * The Class Upload_uploadMultipleFilesTest.
 */
public class Upload_uploadMultipleFilesTest {

	/** The Constant KEYWORDS_PLAIN_AND_MULTIFILES. */
	public static final String KEYWORDS_PLAIN_AND_MULTIFILES = "plain,multifiles";

	/** The unit under test. */
	private Upload unitUnderTest;

	@Mock
	private PeerConnection peerConnection;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		unitUnderTest = new Upload(peerConnection);
	}

	/**
	 * Fail when uploading no file.
	 *
	 * @throws Exception the exception
	 */
	@Test(expected = UploadException.class)
	public void failWhenUploadingNoFile() throws Exception {

		UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.keywords(KEYWORDS_PLAIN_AND_MULTIFILES)
				.metadata(METADATA_AS_MAP)
				.build();

		unitUnderTest.uploadMultipleFiles(parameter);
	}
}
