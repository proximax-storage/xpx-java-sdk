package io.nem.xpx.facade.account;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.nem.core.node.NodeEndpoint;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.account.Account;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.integration.tests.RemoteIntegrationTest;
import io.nem.xpx.remote.AbstractApiTest;

/**
 * The Class AccountTest.
 */
@Category(RemoteIntegrationTest.class)
public class AccountRemoteTest extends AbstractApiTest {

	/**
	 * Test get incoming transactions.
	 */
	@Test
	public void testGetIncomingTransactions() {

		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		Account account = new Account(remotePeerConnection,this.xPubkey);

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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		Account account = new Account(remotePeerConnection,this.xPubkey);

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
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		Account account = new Account(remotePeerConnection,this.xPubkey);

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

		Account account = new Account(localPeerConnection,this.xPubkey);

		try {
			Assert.assertTrue(account.getUnconfirmedTransactions().size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
