package io.nem.xpx;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.ipfs.multihash.Multihash;
import io.nem.ApiException;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;

public interface DataHashApiInterface {
	public BinaryTransactionEncryptedMessage generateHashAndExposeDataToNetworkUsingPOST(String data, String name,
			String keywords, String metadata) throws ApiException, IOException, NoSuchAlgorithmException;

	public String cleanupPinnedContentUsingPOST(String multiHash) throws IOException, ApiException;

}
