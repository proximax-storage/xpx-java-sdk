package io.nem.xpx.facade.transaction.local;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.pmw.tinylog.Logger;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.LocalHttpPeerConnection;
import io.nem.xpx.facade.transaction.Transaction;
import io.nem.xpx.facade.transaction.TransactionAsync;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.remote.AbstractApiTest;

public class TransactionAsyncLocalTest extends AbstractApiTest {

	@Test
	public void testGetTransactionAsync() {
		LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(
				ConnectionFactory.createNemNodeConnection("http", "104.128.226.60", 7890),
				ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001"));

		try {
			TransactionAsync transactionApi = new TransactionAsync(localPeerConnection);
			CompletableFuture<TransactionMetaDataPair> transaction = transactionApi
					.getTransaction("5b81144a82d0f37acc7490d8d2e8912af47a11bca326c65aaa32fce3db781965", (n) -> {
						assertNotNull(n.getEntity());
					});
			
			Logger.info(transaction.get().getMetaData().getHash());
			Assert.assertNotNull(transaction.get().getEntity().getSignature());
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			Logger.error("Exception: " + e.getMessage());
		}
	}

}
