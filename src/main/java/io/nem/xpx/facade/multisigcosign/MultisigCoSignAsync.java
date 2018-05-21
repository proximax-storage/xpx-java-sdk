/*
 * 
 */
package io.nem.xpx.facade.multisigcosign;

import io.nem.xpx.callback.ServiceAsyncCallback;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.AbstractAsyncFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;


public class MultisigCoSignAsync extends AbstractAsyncFacadeService {

	private MultisigCoSign multisigCoSign;
	/**
	 * Instantiates a new multisig co sign.
	 *
	 * @param peerConnection
	 *            the peer connection
	 */
	public MultisigCoSignAsync(PeerConnection peerConnection) {
		this.multisigCoSign = new MultisigCoSign(peerConnection);
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
			ServiceAsyncCallback<MultisigSignatureTransaction> callback) {

		return runAsync(
				() -> {
					try {
						return multisigCoSign.coSign(nemHash, multisigAccount, signers);
					} catch (ApiException e) {
						throw new CompletionException(e);
					}
				}, callback);
	}

}
