package io.nem.xpx.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;
import org.nem.core.model.MultisigSignatureTransaction;
import org.nem.core.model.MultisigTransaction;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.primitive.Amount;
import org.nem.core.serialization.BinarySerializer;
import org.nem.core.serialization.Deserializer;
import io.nem.ApiException;
import io.nem.xpx.NemAccountApi;
import io.nem.xpx.TransactionApi;
import io.nem.xpx.model.InsufficientAmountException;
import io.nem.xpx.model.XpxSdkGlobalConstants;


/**
 * The Class TransactionSenderUtil.
 */
public class TransactionSenderUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(TransactionSenderUtil.class.getName());

	/**
	 * Send transaction.
	 *
	 * @param transaction
	 *            the transaction
	 * @throws ApiException
	 *             the api exception
	 */
	public static void sendTransaction(Transaction transaction) throws ApiException {
		
		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = TransactionApi
				.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);
		try {
			future.thenAcceptAsync(d -> {
				final NemAnnounceResult result = new NemAnnounceResult(d);

				switch (result.getCode()) {
				case 1:
					LOGGER.info(String.format("successfully send xem " + result.getMessage()));
					break;
				default:
					LOGGER.warning(String.format("could not send xem " + result.getMessage()));
				}
			}).exceptionally(e -> {
				e.printStackTrace();
				LOGGER.warning(String.format("could not send xem:" + e.getMessage()));
				return null;
			}).get();
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
			// e.printStackTrace();
		}
	}

	/**
	 * Send transfer transaction.
	 *
	 * @param transaction            the transaction
	 * @return the nem announce result
	 * @throws ApiException             the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws InsufficientAmountException the insufficient amount exception
	 */
	public static NemAnnounceResult sendTransferTransaction(TransferTransaction transaction) throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException {
		
		checkAddressBalanceAgainstAmount(transaction.getSigner().getAddress().toString(), transaction.getAmount());
		
		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());
		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = TransactionApi
				.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);
		try {
			Deserializer transDes = future.get();

			return new NemAnnounceResult(transDes);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warning("Error Occured: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Send transfer transaction.
	 *
	 * @param data
	 *            the data
	 * @param signature
	 *            the signature
	 * @return the nem announce result
	 * @throws ApiException
	 *             the api exception
	 */
	public static NemAnnounceResult sendTransferTransaction(byte[] data, byte[] signature) throws ApiException {

		final RequestAnnounce request = new RequestAnnounce(data, signature);
		final CompletableFuture<Deserializer> future = TransactionApi
				.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);
		try {
			Deserializer transDes = future.get();

			return new NemAnnounceResult(transDes);
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Send future transfer transaction.
	 *
	 * @param transaction            the transaction
	 * @return the completable future
	 * @throws ApiException             the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws InsufficientAmountException the insufficient amount exception
	 */
	public static CompletableFuture<Deserializer> sendFutureTransferTransaction(TransferTransaction transaction)
			throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException {
		
		checkAddressBalanceAgainstAmount(transaction.getSigner().getAddress().toString(), transaction.getAmount());
		
		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		return TransactionApi.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);
	}

	/**
	 * Send multi sig transaction.
	 *
	 * @param transaction
	 *            the transaction
	 * @return the nem announce result
	 * @throws ApiException
	 *             the api exception
	 */
	public static NemAnnounceResult sendMultisigTransaction(MultisigTransaction transaction) throws ApiException {
		
		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = TransactionApi
				.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);
		try {
			Deserializer transDes = future.get();
			return new NemAnnounceResult(transDes);
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Send future multi sig transaction.
	 *
	 * @param transaction
	 *            the transaction
	 * @return the completable future
	 * @throws ApiException
	 *             the api exception
	 */
	public static CompletableFuture<Deserializer> sendFutureMultiSigTransaction(MultisigTransaction transaction)
			throws ApiException {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		return TransactionApi.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);
	}

	/**
	 * Send multisig signature transaction.
	 *
	 * @param transaction
	 *            the transaction
	 * @return the nem announce result
	 * @throws ApiException
	 *             the api exception
	 */
	public static NemAnnounceResult sendMultisigSignatureTransaction(MultisigSignatureTransaction transaction)
			throws ApiException {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());

		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		final CompletableFuture<Deserializer> future = TransactionApi
				.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);
		try {
			Deserializer transDes = future.get();
			return new NemAnnounceResult(transDes);
		} catch (Exception e) {
			LOGGER.warning("Error Occured: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Send future multisig signature transaction.
	 *
	 * @param transaction
	 *            the transaction
	 * @return the completable future
	 * @throws ApiException
	 *             the api exception
	 */
	public static CompletableFuture<Deserializer> sendFutureMultisigSignatureTransaction(
			MultisigSignatureTransaction transaction) throws ApiException {

		final byte[] data = BinarySerializer.serializeToBytes(transaction.asNonVerifiable());
		final RequestAnnounce request = new RequestAnnounce(data, transaction.getSignature().getBytes());
		return TransactionApi.announceTransaction(XpxSdkGlobalConstants.getNodeEndpoint(), request);

	}

	/**
	 * Check address balance against amount.
	 *
	 * @param address the address
	 * @param amount the amount
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 * @throws ApiException the api exception
	 * @throws InsufficientAmountException the insufficient amount exception
	 */
	public static void checkAddressBalanceAgainstAmount(String address, Amount amount) throws InterruptedException, ExecutionException, ApiException, InsufficientAmountException {
		long balance = NemAccountApi.getAccountByAddress(address).getEntity().getBalance().getNumNem();
		long transactionAmount = amount.getNumNem();
		if (balance < transactionAmount) {
			
			throw new InsufficientAmountException("Insufficient amount for Address: " + address + ". Balance: " +amount.getNumNem() + ". Transaction Amount: " + transactionAmount);

		}
	}

}
