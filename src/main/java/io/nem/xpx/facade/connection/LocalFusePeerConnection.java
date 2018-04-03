package io.nem.xpx.facade.connection;

import java.nio.file.Paths;

import org.nem.core.node.NodeEndpoint;

import io.nem.xpx.model.XpxSdkGlobalConstants;
import jnr.ffi.Platform;
import ru.serce.jnrfuse.FuseStubFS;


/**
 * The Class LocalFusePeerConnection.
 */
public class LocalFusePeerConnection implements PeerConnection {

	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint) {
		XpxSdkGlobalConstants.setNodeEndpoint(nodeEndpoint);
		
	}

	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		XpxSdkGlobalConstants.setNodeEndpoint(nodeEndpoint);
	}


}
