package io.nem.xpx.facade.transaction;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.pmw.tinylog.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class TransactionAsyncIntegrationTest.
 */
public class TransactionAsyncIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The unit under test. */
	private TransactionAsync unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new TransactionAsync(peerConnection);
	}

	/**
	 * Test get transaction async.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTransactionAsync() throws Exception {
		final String nemHash = "5b81144a82d0f37acc7490d8d2e8912af47a11bca326c65aaa32fce3db781965";

		unitUnderTest.getTransaction(nemHash, transaction -> {
			assertNotNull(transaction.getEntity().getSignature());
			assertEquals(nemHash, transaction.getMetaData().getHash().toString());

			Logger.info(transaction.getMetaData().getHash());
		}).get();
	}

}
