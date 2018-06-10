/*
 * 
 */
package io.nem.xpx.facade.multisigcosign;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;

import io.nem.xpx.builder.MultisigSignatureTransactionBuilder;
import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.connection.PeerConnection;



/**
 * The Class MultisigCoSign.
 */
public class MultisigCoSignAsync extends MultisigCoSign {

	/**
	 * Instantiates a new multisig co sign.
	 *
	 * @param peerConnection
	 *            the peer connection
	 */
	public MultisigCoSignAsync(PeerConnection peerConnection) {
		super(peerConnection);
	}

	/**
	 * Co sign.
	 *
	 * @param nemHash            the nem hash
	 * @param multisigAccount            the multisig account
	 * @param signers            the signers
	 * @param callback the callback
	 * @return the multisig signature transaction
	 * @throws ApiException             the api exception
	 */
	public CompletableFuture<MultisigSignatureTransaction> coSign(String nemHash, String multisigAccount, List<Account> signers, 
			ServiceAsyncCallback<MultisigSignatureTransaction> callback)
			throws ApiException {

		
		CompletableFuture<MultisigSignatureTransaction> multisigSignatureTransactionAsync = CompletableFuture
				.supplyAsync(() -> {
					MultisigSignatureTransaction multisigSignatureTransaction = null;
					try {
						multisigSignatureTransaction = MultisigSignatureTransactionBuilder
								.peerConnection(peerConnection)
								.multisig(new Account(new KeyPair(PublicKey.fromHexString(multisigAccount)))) // multisig
								.addSigners(signers).otherTransaction(Hash.fromHexString(nemHash)).coSign();
					} catch (ApiException e) {
						throw new CompletionException(e);
					}
					return multisigSignatureTransaction;
				}).thenApply(n -> {
					// call the callback?
					callback.process(n);
					return n;
				});

		return multisigSignatureTransactionAsync;


	}

}
