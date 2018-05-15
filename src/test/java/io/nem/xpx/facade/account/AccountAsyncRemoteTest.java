package io.nem.xpx.facade.account;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.node.NodeEndpoint;

import java.util.concurrent.ExecutionException;

import static io.nem.xpx.testsupport.Constants.TEST_PUBLIC_KEY;

/**
 * The Class AccountTest.
 */
@Category(RemoteIntegrationTest.class)
public class AccountAsyncRemoteTest extends AbstractApiTest {

	/**
	 * Test get incoming transactions.
	 */
	@Test
	public void testGetIncomingTransactions() {

		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection, TEST_PUBLIC_KEY);

		try {
			Assert.assertTrue(account.getIncomingTransactions().size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Test get all transactions.
	 */
	@Test
	public void testGetAllTransactions() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection, TEST_PUBLIC_KEY);

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
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection, TEST_PUBLIC_KEY);

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
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection, TEST_PUBLIC_KEY);

		try {
			Assert.assertTrue(account.getUnconfirmedTransactions().size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
