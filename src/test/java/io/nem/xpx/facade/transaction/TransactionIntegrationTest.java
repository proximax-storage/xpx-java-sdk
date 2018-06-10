package io.nem.xpx.facade.transaction;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.pmw.tinylog.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionIntegrationTest extends AbstractFacadeIntegrationTest {

	private Transaction unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Transaction(peerConnection);
	}


	@Test
	public void testGetTransaction() throws Exception {

		final String nemHash = "5b81144a82d0f37acc7490d8d2e8912af47a11bca326c65aaa32fce3db781965";
		final TransactionMetaDataPair transaction = unitUnderTest.getTransaction(nemHash);

		assertNotNull(transaction.getEntity().getSignature());
		assertEquals(nemHash, transaction.getMetaData().getHash().toString());

		Logger.info(transaction.getMetaData().getHash());

	}
	
	
}
