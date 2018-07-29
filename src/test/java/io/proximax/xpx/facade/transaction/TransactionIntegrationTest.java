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

package io.proximax.xpx.facade.transaction;

import io.proximax.xpx.facade.AbstractFacadeIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.pmw.tinylog.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * The Class TransactionIntegrationTest.
 */
public class TransactionIntegrationTest extends AbstractFacadeIntegrationTest {

	/** The unit under test. */
	private Transaction unitUnderTest;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		unitUnderTest = new Transaction(peerConnection);
	}


	/**
	 * Test get transaction.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTransaction() throws Exception {

		final String nemHash = "5b81144a82d0f37acc7490d8d2e8912af47a11bca326c65aaa32fce3db781965";
		final TransactionMetaDataPair transaction = unitUnderTest.getTransaction(nemHash);

		assertNotNull(transaction.getEntity().getSignature());
		assertEquals(nemHash, transaction.getMetaData().getHash().toString());

		Logger.info(transaction.getMetaData().getHash());

	}
	
	
}
