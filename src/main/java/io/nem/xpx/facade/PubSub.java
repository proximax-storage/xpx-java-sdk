package io.nem.xpx.facade;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;

import io.nem.xpx.LocalSearchApi;
import io.nem.xpx.PublishAndSubscribeApi;
import io.nem.xpx.RemoteSearchApi;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.intf.SearchApi;
import io.nem.xpx.model.PeerConnectionNotFoundException;

public class PubSub {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;
	
	private PublishAndSubscribeApi pubSubApi;
	private boolean isLocalPeerConnection = false;
	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException 
	 */
//	public PubSub(PeerConnection peerConnection) throws PeerConnectionNotFoundException {
//
//		if (peerConnection == null) {
//			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
//		}
//
//		if (peerConnection instanceof RemotePeerConnection) {
//			this.pubSubApi = new RemoteSearchApi();
//		} else {
//			this.isLocalPeerConnection = true;
//			this.pubSubApi = new LocalSearchApi();
//		}
//
//		this.peerConnection = peerConnection;
//		this.engine = CryptoEngines.ed25519Engine();
//	}

}
