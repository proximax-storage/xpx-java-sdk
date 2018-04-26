package io.nem.xpx.facade;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;

import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.service.PublishAndSubscribeApi;
import io.nem.xpx.service.intf.SearchApi;
import io.nem.xpx.service.local.LocalSearchApi;
import io.nem.xpx.service.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.remote.RemoteSearchApi;

public class PublishAndSubscribe {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;
	
	private PublishAndSubscribeApi publishAndSubscribeApi;
	private boolean isLocalPeerConnection = false;
	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException 
	 */
	public PublishAndSubscribe(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

//		if (peerConnection == null) {
//			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
//		}
//
//		if (peerConnection instanceof RemotePeerConnection) {
//			this.publishAndSubscribeApi = new RemoteSearchApi();
//		} else {
//			this.isLocalPeerConnection = true;
//			this.publishAndSubscribeApi = new LocalSearchApi();
//		}
//
//		this.peerConnection = peerConnection;
//		this.engine = CryptoEngines.ed25519Engine();
	}

}
