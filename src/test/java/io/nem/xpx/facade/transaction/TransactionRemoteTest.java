package io.nem.xpx.facade.transaction;

import java.util.concurrent.ExecutionException;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.pmw.tinylog.Logger;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.facade.transaction.Transaction;
import io.nem.xpx.remote.AbstractApiTest;

public class TransactionRemoteTest  extends AbstractApiTest {

	@Test
	public void testGetTransaction() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(uploadNodeBasePath);

		try {
			Transaction transactionApi = new Transaction(remotePeerConnection);
 			TransactionMetaDataPair transaction = transactionApi.getTransaction("5b81144a82d0f37acc7490d8d2e8912af47a11bca326c65aaa32fce3db781965");
 			Logger.info(transaction.getMetaData().getHash());
			Assert.assertNotNull(transaction.getEntity().getSignature());
		} catch (ApiException | InterruptedException | ExecutionException | PeerConnectionNotFoundException e) {
			e.printStackTrace();
			Logger.error("Exception: " + e.getMessage());
		}
	}
	
}
