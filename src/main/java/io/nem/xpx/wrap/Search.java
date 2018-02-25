package io.nem.xpx.wrap;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import io.nem.xpx.SearchApi;

public class Search {
	private PeerConnection peerConnection;
	private CryptoEngine engine;
	private SearchApi searchApi;

	public Search(PeerConnection peerConnection) {
		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		this.searchApi = new SearchApi();
	}

	public String searchDataWithKeyword(String senderOrReceiverPrivateKey, String senderOrReceiverPublicKey,
			String... keywords) {

		return null;

	}
}
