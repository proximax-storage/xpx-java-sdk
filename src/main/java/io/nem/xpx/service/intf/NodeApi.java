package io.nem.xpx.service.intf;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.NodeInfo;




/**
 * The Interface NodeApi.
 */
public interface NodeApi {
	
	/**
	 * Gets the node info peers using GET.
	 *
	 * @return the node info peers using GET
	 * @throws ApiException the api exception
	 */
	public NodeInfo getNodeInfoPeersUsingGET() throws ApiException;
	
	/**
	 * Gets the node info using GET.
	 *
	 * @return the node info using GET
	 * @throws ApiException the api exception
	 */
	public NodeInfo getNodeInfoUsingGET() throws ApiException;
	
	
	
}
