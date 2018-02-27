/*
 * 
 */
package io.nem.xpx.wrap;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import io.nem.xpx.SearchApi;

/**
 * The Class Search.
 */
public class Search {
	
	/** The peer connection. */
	private PeerConnection peerConnection;
	
	/** The engine. */
	private CryptoEngine engine;
	
	/** The search api. */
	private SearchApi searchApi;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection the peer connection
	 */
	public Search(PeerConnection peerConnection) {
		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		this.searchApi = new SearchApi();
	}

	/**
	 * Search data with keyword.
	 *
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @param senderOrReceiverPublicKey the sender or receiver public key
	 * @param keywords the keywords
	 * @return the string
	 */
	public String searchDataWithKeyword(String senderOrReceiverPrivateKey, String senderOrReceiverPublicKey,
			String... keywords) {

		return null;

	}
}
