package io.nem.xpx.service.local;



import io.ipfs.api.IPFS;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.intf.NodeApi;



/**
 * The Class LocalNodeApi.
 */
public class LocalNodeApi implements NodeApi {

	private final IPFS proximaxIfpsConnection;
	
	public LocalNodeApi(final IPFS proximaxIfpsConnection) {
		this.proximaxIfpsConnection = proximaxIfpsConnection;
	}
	
	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.NodeApi#getNodeInfoPeersUsingGET()
	 */
	@Override
	public NodeInfo getNodeInfoPeersUsingGET() throws ApiException {
		throw new ApiException("Method can't be accessed thru local connection");
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.NodeApi#getNodeInfoUsingGET()
	 */
	@Override
	public NodeInfo getNodeInfoUsingGET() throws ApiException {
		throw new ApiException("Method can't be accessed thru local connection");
	}

}
