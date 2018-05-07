package io.nem.xpx.facade.publishandsubscribe;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;

import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.service.intf.PublishAndSubscribeApi;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;



/**
 * The Class PublishAndSubscribe.
 */

public class PublishAndSubscribe {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;
	
	/** The publish and subscribe api. */
	private PublishAndSubscribeApi publishAndSubscribeApi;
	
	private final TransactionAndAnnounceApi transactionAndAnnounceApi;
	
	/** The is local peer connection. */
	private boolean isLocalPeerConnection = false;
	
	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection            the peer connection
	 * @throws PeerConnectionNotFoundException the peer connection not found exception
	 */
	public PublishAndSubscribe(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.peerConnection = peerConnection;
		this.publishAndSubscribeApi = peerConnection.getPublishAndSubscribeApi();
		this.transactionAndAnnounceApi = peerConnection.getTransactionAndAnnounceApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.engine = CryptoEngines.ed25519Engine();
		
		
	}
	
	//	Local and Remote.
	//	Remote needs a listener.
	public void subscribeAndListenToTopic(String topic) {
	}
	
	public void publishTopic(String topic, String initialMessage) throws Exception {
		//	Create a NEM Txn first, store the topic name.
		
		//	Create the topic and then send the message.
		publishAndSubscribeApi.publishTopicUsingGET(topic, initialMessage);
		
	}
	
	
	//	Send to existing topic
	
	public void sendToTopic(String topic, String message) throws Exception {
		publishAndSubscribeApi.sendToTopicUsingGET(topic, message);
	}
	
	public void sendToTopicByNemHash(String nemHash, String message) throws Exception {
		
		publishAndSubscribeApi.sendToTopicUsingGET(nemHash, message);
	}

}
