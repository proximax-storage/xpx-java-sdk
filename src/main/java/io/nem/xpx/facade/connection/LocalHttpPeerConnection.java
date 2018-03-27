package io.nem.xpx.facade.connection;

import org.nem.core.node.NodeEndpoint;

import io.nem.xpx.model.XpxSdkGlobalConstants;

public class LocalHttpPeerConnection implements PeerConnection {

	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint) {
		XpxSdkGlobalConstants.setNodeEndpoint(nodeEndpoint);
		XpxSdkGlobalConstants.setProximaxConnection("/ip4/127.0.0.1/tcp/5001");	// yes, constant.
	}
	
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		XpxSdkGlobalConstants.setNodeEndpoint(nodeEndpoint);
		XpxSdkGlobalConstants.setProximaxConnection(multiAddress);
	}

}
