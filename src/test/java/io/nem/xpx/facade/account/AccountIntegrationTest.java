package io.nem.xpx.facade.account;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import java.util.List;

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static org.junit.Assert.assertTrue;

public class AccountIntegrationTest extends AbstractFacadeIntegrationTest {

	private Account unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new Account(peerConnection, TEST_PUBLIC_KEY);
	}


	@Test
	public void testGetIncomingTransactions() throws Exception {
		final List<TransactionMetaDataPair> incomingTransactions = unitUnderTest.getIncomingTransactions();

		assertTrue(incomingTransactions.size() > 0);
	}
	
	@Test
	public void testGetAllTransactions() throws Exception {
		final List<TransactionMetaDataPair> allTransactions = unitUnderTest.getAllTransactions();

		assertTrue(allTransactions.size() > 0);
	}

	@Test
	public void testGetOutgoingTransactions() throws Exception {
		final List<TransactionMetaDataPair> outgoingTransactions = unitUnderTest.getOutgoingTransactions();

		assertTrue(outgoingTransactions.size() > 0);
	}

	@Test
	public void testGetUnconfirmedTransactions() throws Exception {
		final List<TransactionMetaDataPair> unconfirmedTransactions = unitUnderTest.getUnconfirmedTransactions();

		assertTrue(unconfirmedTransactions.size() >= 0);
	}

}
