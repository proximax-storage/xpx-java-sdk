package io.nem.xpx.facade.publishandsubscribe;

import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.connection.PeerConnection;

/**
 * The Class PublishAndSubscribe.
 */

public class PublishAndSubscribeAsync extends PublishAndSubscribe {

	public PublishAndSubscribeAsync(PeerConnection peerConnection) {
		super(peerConnection);
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}
	}
}
