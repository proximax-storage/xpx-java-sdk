package io.nem.xpx.service.local;


import io.ipfs.api.IPFS;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.intf.NodeApi;




/**
 * The Class LocalNodeApi.
 */
public class LocalNodeApi implements NodeApi {

	/** The proximax ifps connection. */
	private final IPFS proximaxIfpsConnection;
	
	/**
	 * Instantiates a new local node api.
	 *
	 * @param proximaxIfpsConnection the proximax ifps connection
	 */
	public LocalNodeApi(final IPFS proximaxIfpsConnection) {
		this.proximaxIfpsConnection = proximaxIfpsConnection;
	}
	
	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.NodeApi#getNodeInfoPeersUsingGET()
	 */
	@Override
	public NodeInfo getNodeInfoPeersUsingGET() throws ApiException {
//		try {
//			return JsonUtils.toJson(this.getNodePeers());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return JsonUtils.toJson(new GenericResponseMessage(HttpStatus.ACCEPTED, "down"));
		throw new ApiException("Method can't be accessed thru local connection");
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.NodeApi#getNodeInfoUsingGET()
	 */
	@Override
	public NodeInfo getNodeInfoUsingGET() throws ApiException {
		NodeInfo nodeInfo = new NodeInfo();
		try {
			nodeInfo.setPeerId(proximaxIfpsConnection.config.show().get("Identity").toString());
			return nodeInfo;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeInfo;
	}
	
	public IPFS getLocalProximaXIpfsConnection() {
		return proximaxIfpsConnection;
	}
	
	

}





//
//public NodeInfo getNodeInfo() {
//	LOGGER.info("getNodeInfo");
//	NodeInfo nodeInfo = new NodeInfo();
//	try {
//		nodeInfo.setContextUri(ProximaxCoreConstants.NODE_ENV_PROPERTIES.getContextUri());
//		nodeInfo.setNemAddress(ProximaxCoreConstants.NODE_ENV_PROPERTIES.getNem().getAccountPrivateKey());
//		nodeInfo.setNetwork(ProximaxCoreConstants.NODE_ENV_PROPERTIES.getNem().getDefaultNetwork());
//		nodeInfo.setNetworkPort(ProximaxCoreConstants.NODE_ENV_PROPERTIES.getNem().getPort());
//		nodeInfo.setNetworkAddress(ProximaxCoreConstants.NODE_ENV_PROPERTIES.getNem().getDefaultHost());
//		nodeInfo.setPeerId(XpxEnvironment.env.getSpfsStoreInstance().config.show().get("Identity").toString());
//		nodeInfo.setNamespace(ProximaxCoreConstants.NODE_ENV_PROPERTIES.getNem().getNamespace());
//		nodeInfo.setMosaic(ProximaxCoreConstants.NODE_ENV_PROPERTIES.getNem().getMosaic());
//		return nodeInfo;
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return nodeInfo;
//}
//
///*
// * (non-Javadoc)
// * 
// * @see io.nem.xpx.core.service.ipfs.NodeService#getNodePeers()
// */
//c
//
//	LOGGER.info("getNodeInfoPeers");
//	try {
//		List<NodePeer> nodePeers = new ArrayList<NodePeer>();
//		Iterator<Peer> ipfsNodePeers = XpxEnvironment.env.getSpfsStoreInstance().swarm.peers().iterator();
//
//		while (ipfsNodePeers.hasNext()) {
//			Peer peer = ipfsNodePeers.next();
//			NodePeer nodePeer = new NodePeer();
//			nodePeer.setAddress(peer.address.getHost() + ":" + peer.address.getTCPPort());
//			nodePeer.setId(peer.id.toBase58());
//			nodePeer.setLatency(String.valueOf(peer.latency));
//			nodePeer.setMuxer(peer.muxer);
//
//			nodePeers.add(nodePeer);
//		}
//
//		return nodePeers;
//
//	} catch (Exception e) {
//		return null;
//	}
//}
//
//public String getNodeInfoPeers() {
//	LOGGER.info("getNodeInfoPeers");
//	try {
//		return JsonUtils.toJson(this.getNodePeers());
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return JsonUtils.toJson(new GenericResponseMessage(HttpStatus.ACCEPTED, "down"));
//}
