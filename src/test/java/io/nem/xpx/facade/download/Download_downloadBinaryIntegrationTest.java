package io.nem.xpx.facade.download;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import io.nem.xpx.model.NemMessageType;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.testsupport.Constants.*;
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
