package io.nem.xpx.facade.connection;

import org.nem.core.node.NodeEndpoint;

import io.ipfs.api.IPFS;




/**
 * The Class LocalHttpPeerConnection.
 */
public final class LocalHttpPeerConnection extends AbstractLocalPeerConnection {

	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint) {
		super(nodeEndpoint);
	}
	
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, IPFS ipfsConnection) {
		super(nodeEndpoint,ipfsConnection);
	}
	
	
	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		super(nodeEndpoint, multiAddress);
	}

}
