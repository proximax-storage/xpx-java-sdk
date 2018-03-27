package io.nem.xpx.facade.connection;

import java.nio.file.Paths;

import org.nem.core.node.NodeEndpoint;

import io.nem.xpx.model.XpxSdkGlobalConstants;
import jnr.ffi.Platform;
import ru.serce.jnrfuse.FuseStubFS;

public class LocalFusePeerConnection implements PeerConnection {

	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint) {
		XpxSdkGlobalConstants.setNodeEndpoint(nodeEndpoint);
		
	}

	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		XpxSdkGlobalConstants.setNodeEndpoint(nodeEndpoint);
	}


}
