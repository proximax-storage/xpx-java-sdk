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

import java.util.List;

import io.proximax.xpx.facade.AbstractFacadeService;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;

import io.proximax.xpx.builder.MultisigSignatureTransactionBuilder;
import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.facade.connection.PeerConnection;





/**
 * The Class MultisigCoSign.
 */
public class MultisigCoSign extends AbstractFacadeService {

	/** The peer connection. */
	protected final PeerConnection peerConnection;
	

	/**
	 * Instantiates a new multisig co sign.
	 *
	 * @param peerConnection the peer connection
	 */
	public MultisigCoSign(PeerConnection peerConnection) {
		this.peerConnection = peerConnection;
	}

	/**
	 * Co sign.
	 *
	 * @param nemHash the proximax hash
	 * @param multisigAccount the multisig account
	 * @param signers the signers
	 * @return the multisig signature transaction
	 * @throws ApiException the api exception
	 */
	public MultisigSignatureTransaction coSign(String nemHash, String multisigAccount, List<Account> signers)
			throws ApiException {

		MultisigSignatureTransaction multisigSignatureTransaction = MultisigSignatureTransactionBuilder
				.peerConnection(peerConnection)
				.multisig(new Account(new KeyPair(PublicKey.fromHexString(multisigAccount)))) // multisig
				.addSigners(signers).otherTransaction(Hash.fromHexString(nemHash)).coSign();

		return multisigSignatureTransaction;

	}

}
