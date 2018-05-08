package io.nem.xpx.facade.account;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.node.NodeEndpoint;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.account.Account;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.integration.tests.LocalIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;

/**
 * The Class AccountTest.
 */
@Category(LocalIntegrationTest.class)
public class AccountAsyncLocalTest extends AbstractApiTest {

	private Account account;

	@Before
	public void setup() {
		
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001"));

		this.account = new Account(localPeerConnection, this.xPubkey);
	}

	/**
	 * Test get incoming transactions.
	 */
	@Test
	public void testGetIncomingTransactions() {

		try {
			Assert.assertTrue(this.account.getIncomingTransactions().size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test get all transactions.
	 */
	@Test
	public void testGetAllTransactions() {

		try {
			Assert.assertTrue(account.getAllTransactions().size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test get outgoing transactions.
	 */
	@Test
	public void testGetOutgoingTransactions() {

		try {

			Assert.assertTrue(account.getOutgoingTransactions().size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test get unconfirmed transactions.
	 */
	@Test
	@Ignore
	public void testGetUnconfirmedTransactions() {

		try {
			Assert.assertTrue(account.getUnconfirmedTransactions().size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
