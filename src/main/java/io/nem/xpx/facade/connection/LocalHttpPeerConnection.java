package io.nem.xpx.facade.connection;

import org.nem.core.node.NodeEndpoint;

import io.nem.xpx.model.XpxSdkGlobalConstants;



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
		super(nodeEndpoint);
		XpxSdkGlobalConstants.setProximaxConnection("/ip4/127.0.0.1/tcp/5001");	// yes, constant.
	}
	
	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		super(nodeEndpoint);
		XpxSdkGlobalConstants.setProximaxConnection(multiAddress);
	}

}
