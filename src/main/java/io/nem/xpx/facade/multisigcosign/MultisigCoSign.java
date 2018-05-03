/*
 * 
 */
package io.nem.xpx.facade.multisigcosign;

import java.util.List;

import io.nem.xpx.facade.AbstractFacadeService;
import org.nem.core.crypto.Hash;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.MultisigSignatureTransaction;

import io.nem.ApiException;
import io.nem.xpx.builder.MultisigSignatureTransactionBuilder;
import io.nem.xpx.facade.connection.PeerConnection;



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
	 * @param nemHash the nem hash
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
