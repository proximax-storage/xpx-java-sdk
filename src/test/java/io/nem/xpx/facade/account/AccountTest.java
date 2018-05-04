package io.nem.xpx.facade.account;

import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.node.NodeEndpoint;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.remote.AbstractApiTest;

public class AccountTest extends AbstractApiTest {

	@Test
	public void testGetIncomingTransactions() {

		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection);

		try {
			Assert.assertTrue(account.getIncomingTransactions(this.xPubkey).size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGetAllTransactions() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection);

		try {
			Assert.assertTrue(account.getAllTransactions(this.xPubkey).size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetOutgoingTransactions() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection);

		try {
			
			Assert.assertTrue(account.getOutgoingTransactions(this.xPubkey).size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

	@Test
	@Ignore
	public void testGetUnconfirmedTransactions() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				new NodeEndpoint("http", "23.228.67.85", 7890));

		Account account = new Account(localPeerConnection);

		try {
			Assert.assertTrue(account.getUnconfirmedTransactions(this.xPubkey).size() > 0);
		} catch (ApiException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
