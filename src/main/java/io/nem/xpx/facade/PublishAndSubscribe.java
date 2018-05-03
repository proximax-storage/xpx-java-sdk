package io.nem.xpx.facade;

import org.nem.core.crypto.CryptoEngine;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.intf.PublishAndSubscribeApi;


/**
 * The Class PublishAndSubscribe.
 */
// TODO can I delete this? no reference from anywhere
public class PublishAndSubscribe {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;
	
	/** The publish and subscribe api. */
	private PublishAndSubscribeApi publishAndSubscribeApi;
	
	/** The is local peer connection. */
	private boolean isLocalPeerConnection = false;
	
	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection            the peer connection
	 * @throws PeerConnectionNotFoundException the peer connection not found exception
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
