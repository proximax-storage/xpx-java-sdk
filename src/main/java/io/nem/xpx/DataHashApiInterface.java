package io.nem.xpx;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.ipfs.multihash.Multihash;
import io.nem.ApiException;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;


/**
 * The Interface DataHashApiInterface.
 */
public interface DataHashApiInterface {
	
	/**
	 * Generate hash and expose data to network using POST.
	 *
	 * @param data the data
	 * @param name the name
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @return the binary transaction encrypted message
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public BinaryTransactionEncryptedMessage generateHashAndExposeDataToNetworkUsingPOST(String data, String name,
			String keywords, String metadata) throws ApiException, IOException, NoSuchAlgorithmException;

	/**
	 * Cleanup pinned content using POST.
	 *
	 * @param multiHash the multi hash
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 */
	public String cleanupPinnedContentUsingPOST(String multiHash) throws IOException, ApiException;

}
