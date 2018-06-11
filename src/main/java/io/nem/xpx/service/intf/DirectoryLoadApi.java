package io.nem.xpx.service.intf;

import io.nem.xpx.exceptions.ApiException;




/**
 * The Interface DirectoryLoadApi.
 */
public interface DirectoryLoadApi {
	
	/**
	 * Load directory using GET.
	 *
	 * @param nemHash the nem hash
	 * @return the object
	 * @throws ApiException the api exception
	 */
	public Object loadDirectoryUsingGET(String nemHash) throws ApiException;
}
