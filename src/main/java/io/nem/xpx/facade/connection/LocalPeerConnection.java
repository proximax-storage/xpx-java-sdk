package io.nem.xpx.facade.connection;

import org.nem.core.node.NodeEndpoint;

import io.nem.xpx.builder.XpxJavaSdkGlobals;

public class LocalPeerConnection implements PeerConnection {

	public LocalPeerConnection(NodeEndpoint nodeEndpoint) {
		XpxJavaSdkGlobals.setNodeEndpoint(nodeEndpoint);
		XpxJavaSdkGlobals.setProximaxConnection("/ip4/127.0.0.1/tcp/5001");	// yes, constant.
	}
	
	public LocalPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		XpxJavaSdkGlobals.setNodeEndpoint(nodeEndpoint);
		XpxJavaSdkGlobals.setProximaxConnection(multiAddress);
	}

}