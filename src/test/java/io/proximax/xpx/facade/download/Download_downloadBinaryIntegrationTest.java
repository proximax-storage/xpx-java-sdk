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

import static io.proximax.xpx.testsupport.Constants.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;



/**
 * The Class Download_downloadBinaryIntegrationTest.
 */
public class Download_downloadBinaryIntegrationTest extends AbstractFacadeIntegrationTest {

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
	 * Should download plain binary.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadBinary() throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE1);
		
		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(FILE_TO_PLAIN_MSG_NEM_HASH_MAP.get(PDF_FILE1)).build());
		
		assertArrayEquals(expected, result.getData());
		assertEquals(NemMessageType.PLAIN, result.getMessageType());
	}

	/**
	 * Should download plain large binary.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldDownloadLargeBinary() throws Exception {

		byte[] expected = FileUtils.readFileToByteArray(PDF_FILE2);

		final DownloadBinaryResult result = unitUnderTest.downloadBinary(DownloadParameter.create()
				.nemHash(FILE_TO_PLAIN_MSG_NEM_HASH_MAP.get(PDF_FILE2)).build());

		assertArrayEquals(expected, result.getData());
		assertEquals(NemMessageType.PLAIN, result.getMessageType());
	}
}
