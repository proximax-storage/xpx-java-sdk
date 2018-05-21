package io.nem.xpx.facade.account;

import io.nem.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static org.junit.Assert.assertTrue;

public class AccountAsyncIntegrationTest extends AbstractFacadeIntegrationTest {

	private AccountAsync unitUnderTest;

	@Before
	public void setUp() {
		unitUnderTest = new AccountAsync(peerConnection, TEST_PUBLIC_KEY);
	}


	@Test
	public void testGetIncomingTransactions() throws Exception {
		unitUnderTest.getIncomingTransactions(incomingTransactions ->
				assertTrue(incomingTransactions.size() > 0)).get();
	}
	
	@Test
	public void testGetAllTransactions() throws Exception {
		unitUnderTest.getAllTransactions(allTransactions ->
				assertTrue(allTransactions.size() > 0)).get();
	}

	@Test
	public void testGetOutgoingTransactions() throws Exception {
		unitUnderTest.getOutgoingTransactions(outgoingTransactions ->
				assertTrue(outgoingTransactions.size() > 0)).get();
	}

	@Test
	public void testGetUnconfirmedTransactions() throws Exception {
		unitUnderTest.getUnconfirmedTransactions(unconfirmedTransactions ->
				assertTrue(unconfirmedTransactions.size() >= 0)).get();
	}

}
