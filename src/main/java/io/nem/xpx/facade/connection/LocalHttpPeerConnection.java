package io.nem.xpx.facade.connection;

import org.nem.core.node.NodeEndpoint;




/**
 * The Class LocalHttpPeerConnection.
 */
public class LocalHttpPeerConnection extends AbstractLocalPeerConnection {

	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint) {
		this(nodeEndpoint, "/ip4/127.0.0.1/tcp/5001");
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
