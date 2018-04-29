/*
 * 
 */
package io.nem.xpx.facade.connection;

import org.nem.core.node.NodeEndpoint;

import io.nem.Configuration;
import io.nem.ApiClient;
import io.nem.ApiException;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.remote.RemoteNodeApi;



/**
 * The Class RemotePeerConnection.
 */
public class RemotePeerConnection implements PeerConnection {

	/**
	 * Instantiates a new remote peer connection.
	 *
	 * @param baseUrl
	 *            the base url
	 */
	public RemotePeerConnection(String baseUrl) {
		Configuration.setDefaultApiClient(new ApiClient()
				.setBasePath(baseUrl));
		NodeInfo nodeInfo;
		try {
			nodeInfo = new RemoteNodeApi().getNodeInfoUsingGET();
			XpxSdkGlobalConstants.setNodeEndpoint(
					new NodeEndpoint("http", nodeInfo.getNetworkAddress(), Integer.valueOf(nodeInfo.getNetworkPort())));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Sets the peer base url.
	 *
	 * @param baseUrl
	 *            the base url
	 * @return the remote peer connection
	 */
	public RemotePeerConnection setPeerBaseUrl(String baseUrl) {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath(baseUrl));
		NodeInfo nodeInfo;
		try {
			nodeInfo = new RemoteNodeApi().getNodeInfoUsingGET();
			XpxSdkGlobalConstants.setNodeEndpoint(
					new NodeEndpoint("http", nodeInfo.getNetworkAddress(), Integer.valueOf(nodeInfo.getNetworkPort())));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

}
