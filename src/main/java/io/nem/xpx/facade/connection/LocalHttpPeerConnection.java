package io.nem.xpx.facade.connection;

import io.ipfs.api.IPFS;
import io.ipfs.multiaddr.MultiAddress;
import org.nem.core.node.NodeEndpoint;

import java.util.List;

import static java.util.Arrays.asList;


/**
 * The Class LocalHttpPeerConnection.
 */
public final class LocalHttpPeerConnection extends AbstractLocalPeerConnection {

	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, String... syncGateways) {
		super(nodeEndpoint, null, asList(syncGateways));
	}
	
	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, List<String> syncGateways) {
		super(nodeEndpoint, null, syncGateways);
	}

	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param ipfsConnection the ipfs connection
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, IPFS ipfsConnection, String... syncGateways) {
		super(nodeEndpoint, ipfsConnection, asList(syncGateways));
	}
	
	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param ipfsConnection the ipfs connection
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, IPFS ipfsConnection, List<String> syncGateways) {
		super(nodeEndpoint, ipfsConnection, syncGateways);
	}


	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress, String... syncGateways) {
		super(nodeEndpoint, new IPFS(new MultiAddress(multiAddress)), asList(syncGateways));
	}

	/**
	 * Instantiates a new local http peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalHttpPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress, List<String> syncGateways) {
		super(nodeEndpoint, new IPFS(new MultiAddress(multiAddress)), syncGateways);
	}

}
