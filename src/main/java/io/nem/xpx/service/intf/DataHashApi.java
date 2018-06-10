package io.nem.xpx.service.intf;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;




/**
 * The Interface DataHashApiInterface.
 */
public interface DataHashApi {
	
	/**
	 * Generate hash and expose data to network using POST.
	 *
	 * @param data the data
	 * @return the binary transaction encrypted message
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public String generateHashForDataOnlyUsingPOST(byte[] data) throws ApiException, IOException, NoSuchAlgorithmException;


}
