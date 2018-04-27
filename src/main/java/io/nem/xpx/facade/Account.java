package io.nem.xpx.facade;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import io.nem.ApiException;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.intf.AccountApi;
import io.nem.xpx.service.local.LocalAccountApi;
import io.nem.xpx.service.remote.RemoteAccountApi;


/**
 * The Class Account.
 */
public class Account {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;

	/** The account api. */
	private AccountApi accountApi;
	
	/** The is local peer connection. */
	private boolean isLocalPeerConnection = false;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection            the peer connection
	 * @throws PeerConnectionNotFoundException the peer connection not found exception
	 */
	public Account(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.accountApi = new RemoteAccountApi();
		} else {
			this.isLocalPeerConnection = true;
			this.accountApi = new LocalAccountApi();
		}

		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
	}
	

	/**
	 * Gets the incoming transactions.
	 *
	 * @param publicKey the public key
	 * @param privateKey the private key
	 * @return the incoming transactions
	 * @throws ApiException the api exception
	 */
	public String getIncomingTransactions(String publicKey, String privateKey) throws ApiException {
		return null;
	}


	/**
	 * Gets the all transactions.
	 *
	 * @param publicKey the public key
	 * @param privateKey the private key
	 * @return the all transactions
	 * @throws ApiException the api exception
	 */
	public String getAllTransactions(String publicKey, String privateKey) throws ApiException {

		return null;
	}



	/**
	 * Gets the outgoing transactions.
	 *
	 * @param publicKey the public key
	 * @param privateKey the private key
	 * @return the outgoing transactions
	 * @throws ApiException the api exception
	 */
	public String getOutgoingTransactions(String publicKey,String privateKey) throws ApiException {
		return null;
	}


	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param publicKey the public key
	 * @param privateKey the private key
	 * @return the unconfirmed transactions
	 * @throws ApiException the api exception
	 */
	public String getUnconfirmedTransactions(String publicKey, String privateKey) throws ApiException {
		return null;
	}
}

	
