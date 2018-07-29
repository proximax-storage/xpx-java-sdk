/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * 
 */
package io.proximax.xpx.facade.multisigcosign;

import io.proximax.xpx.callback.ServiceAsyncCallback;
import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.facade.AbstractAsyncFacadeService;
import io.proximax.xpx.facade.connection.PeerConnection;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;



/**
 * The Class MultisigCoSignAsync.
 */
public class MultisigCoSignAsync extends AbstractAsyncFacadeService {

	/** The multisig co sign. */
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
	 * @param nemHash            the proximax hash
	 * @param multisigAccount            the multisig account
	 * @param signers            the signers
	 * @param callback the callback
	 * @return the multisig signature transaction
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
