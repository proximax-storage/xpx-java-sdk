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
import org.nem.core.model.ncc.TransactionMetaDataPair;

import java.util.List;

import static io.proximax.xpx.testsupport.Constants.TEST_PUBLIC_KEY;
import static org.junit.Assert.assertTrue;


/**
 * The Class AccountIntegrationTest.
 */
public class AccountIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The unit under test. */
	private Account unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new Account(peerConnection, TEST_PUBLIC_KEY);
	}


	/**
	 * Test get incoming transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetIncomingTransactions() throws Exception {
		final List<TransactionMetaDataPair> incomingTransactions = unitUnderTest.getIncomingTransactions();

		assertTrue(incomingTransactions.size() > 0);
	}
	
	/**
	 * Test get all transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetAllTransactions() throws Exception {
		final List<TransactionMetaDataPair> allTransactions = unitUnderTest.getAllTransactions();

		assertTrue(allTransactions.size() > 0);
	}

	/**
	 * Test get outgoing transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetOutgoingTransactions() throws Exception {
		final List<TransactionMetaDataPair> outgoingTransactions = unitUnderTest.getOutgoingTransactions();

		assertTrue(outgoingTransactions.size() > 0);
	}

	/**
	 * Test get unconfirmed transactions.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetUnconfirmedTransactions() throws Exception {
		final List<TransactionMetaDataPair> unconfirmedTransactions = unitUnderTest.getUnconfirmedTransactions();

		assertTrue(unconfirmedTransactions.size() >= 0);
	}

}
