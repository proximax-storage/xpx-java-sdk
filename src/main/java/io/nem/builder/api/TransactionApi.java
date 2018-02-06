package io.nem.builder.api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.nem.core.connect.HttpJsonPostRequest;
import org.nem.core.connect.client.NisApiId;
import org.nem.core.model.Transaction;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.ncc.TransactionMetaData;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.serialization.Deserializer;

import io.nem.xpx.NodeApi;

/**
 * The Class TransactionApi.
 */
public class TransactionApi {

	/**
	 * Announce the Transaction
	 * 
	 * @param endpoint
	 * @param request
	 * @return
	 */
	public static CompletableFuture<Deserializer> announceTransaction(final NodeEndpoint endpoint,
			final RequestAnnounce request) {
		final CompletableFuture<Deserializer> des = XpxJavaSdkGlobals.CONNECTOR.postAsync(endpoint,
				NisApiId.NIS_REST_TRANSACTION_ANNOUNCE, new HttpJsonPostRequest(request));

		return des;
	}

}
