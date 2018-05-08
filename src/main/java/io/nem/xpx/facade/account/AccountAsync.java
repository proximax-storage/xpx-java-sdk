package io.nem.xpx.facade.account;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.connection.PeerConnection;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The Class Account.
 */
public class AccountAsync extends Account {

	public AccountAsync(PeerConnection peerConnection, String publicKey) {
		super(peerConnection,publicKey);

	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the incoming transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getIncomingTransactions(ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> returnListOfTxnMetaDataPairAsync = CompletableFuture
				.supplyAsync(() -> {

					List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
					List<TransactionMetaDataPair> listOfTxnMetaDataPair;
					try {
						listOfTxnMetaDataPair = nemTransactionApi.getIncomingTransactions(
								Address.fromPublicKey(PublicKey.fromHexString(this.publicKey)).getEncoded());
						
						for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
							if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
								returnListOfTxnMetaDataPair.add(metaDataPair);
							}
						}
					} catch (InterruptedException | ExecutionException | ApiException e) {
						e.printStackTrace();
					}

					return returnListOfTxnMetaDataPair;
				}).thenApply(n -> {
					callback.process(n);
					return n;
				});

		return returnListOfTxnMetaDataPairAsync;

	}

	/**
	 * Gets the all transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the all transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getAllTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> returnListOfTxnMetaDataPairAsync = CompletableFuture
				.supplyAsync(() -> {

					List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
					List<TransactionMetaDataPair> listOfTxnMetaDataPair;
					try {
						listOfTxnMetaDataPair = nemTransactionApi.getAllTransactions(
								Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
						
						for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
							if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
								returnListOfTxnMetaDataPair.add(metaDataPair);
							}
						}
					} catch (InterruptedException | ExecutionException | ApiException e) {
						e.printStackTrace();
					}

					return returnListOfTxnMetaDataPair;
				}).thenApply(n -> {
					callback.process(n);
					return n;
				});

		return returnListOfTxnMetaDataPairAsync;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @param privateKey
	 *            the private key
	 * @return the outgoing transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getOutgoingTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> returnListOfTxnMetaDataPairAsync = CompletableFuture
				.supplyAsync(() -> {

					List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
					List<TransactionMetaDataPair> listOfTxnMetaDataPair;
					try {
						listOfTxnMetaDataPair = nemTransactionApi.getOutgoingTransactions(
								Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
						
						for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
							if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
								returnListOfTxnMetaDataPair.add(metaDataPair);
							}
						}
					} catch (InterruptedException | ExecutionException | ApiException e) {
						e.printStackTrace();
					}

					return returnListOfTxnMetaDataPair;
				}).thenApply(n -> {
					callback.process(n);
					return n;
				});

		return returnListOfTxnMetaDataPairAsync;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @param privateKey
	 *            the private key
	 * @return the unconfirmed transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public CompletableFuture<List<TransactionMetaDataPair>> getUnconfirmedTransactions(
			ServiceAsyncCallback<List<TransactionMetaDataPair>> callback)
			throws ApiException, InterruptedException, ExecutionException {

		CompletableFuture<List<TransactionMetaDataPair>> returnListOfTxnMetaDataPairAsync = CompletableFuture
				.supplyAsync(() -> {

					List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
					List<TransactionMetaDataPair> listOfTxnMetaDataPair;
					try {
						listOfTxnMetaDataPair = nemTransactionApi.getUnconfirmedTransactions(
								Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
						
						for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
							if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
								returnListOfTxnMetaDataPair.add(metaDataPair);
							}
						}
					} catch (InterruptedException | ExecutionException | ApiException e) {
						e.printStackTrace();
					}

					return returnListOfTxnMetaDataPair;
				}).thenApply(n -> {
					callback.process(n);
					return n;
				});

		return returnListOfTxnMetaDataPairAsync;

	}

}
