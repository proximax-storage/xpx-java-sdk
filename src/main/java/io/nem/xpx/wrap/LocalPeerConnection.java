package io.nem.xpx.wrap;

import io.nem.ApiClient;
import io.nem.Configuration;

public class LocalPeerConnection implements PeerConnection {
	
	public LocalPeerConnection() {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath("http://127.0.0.1:8881"));
	}
	
	/**
	 * Sets the peer base url.
	 *
	 * @param baseUrl the base url
	 * @return the remote peer connection
	 */
	public LocalPeerConnection setLocalBasePort(String basePort) {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath("http://127.0.0.1:"+basePort));
		return this;
	}
}
