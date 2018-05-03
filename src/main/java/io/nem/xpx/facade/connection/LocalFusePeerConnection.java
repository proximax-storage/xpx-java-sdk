package io.nem.xpx.facade.connection;

import org.nem.core.node.NodeEndpoint;



/**
 * The Class LocalFusePeerConnection.
 */
public class LocalFusePeerConnection extends AbstractLocalPeerConnection {

	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint) {
		super(nodeEndpoint);
	}

	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		super(nodeEndpoint);
	}
}
