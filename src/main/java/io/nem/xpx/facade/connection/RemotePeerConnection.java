/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.ApiClient;
import io.nem.Configuration;

/**
 * The Class RemotePeerConnection.
 */
public class RemotePeerConnection implements PeerConnection	{
	
	/**
	 * Instantiates a new remote peer connection.
	 *
	 * @param baseUrl the base url
	 */
	public RemotePeerConnection(String baseUrl) {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath(baseUrl));
	}
	
	/**
	 * Sets the peer base url.
	 *
	 * @param baseUrl the base url
	 * @return the remote peer connection
	 */
	public RemotePeerConnection setPeerBaseUrl(String baseUrl) {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath(baseUrl));
		return this;
	}
	
}
