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

package io.proximax.xpx.facade.account;

import io.proximax.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;

import static io.proximax.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static org.junit.Assert.assertTrue;


/**
 * The Class AccountAsyncIntegrationTest.
 */
public class AccountAsyncIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The unit under test. */
	private AccountAsync unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new AccountAsync(peerConnection, TEST_PUBLIC_KEY);
	}


	/**
	 * Test get incoming transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetIncomingTransactions() throws Exception {
		unitUnderTest.getIncomingTransactions(incomingTransactions ->
				assertTrue(incomingTransactions.size() > 0)).get();
	}
	
	/**
	 * Test get all transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAllTransactions() throws Exception {
		unitUnderTest.getAllTransactions(allTransactions ->
				assertTrue(allTransactions.size() > 0)).get();
	}

	/**
	 * Test get outgoing transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetOutgoingTransactions() throws Exception {
		unitUnderTest.getOutgoingTransactions(outgoingTransactions ->
				assertTrue(outgoingTransactions.size() > 0)).get();
	}

	/**
	 * Test get unconfirmed transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetUnconfirmedTransactions() throws Exception {
		unitUnderTest.getUnconfirmedTransactions(unconfirmedTransactions ->
				assertTrue(unconfirmedTransactions.size() >= 0)).get();
	}

}
