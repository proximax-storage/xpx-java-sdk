package io.nem.xpx;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.nem.ApiException;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;

public interface DataHashApiInterface {
	 public BinaryTransactionEncryptedMessage generateHashAndExposeDataToNetworkUsingPOST(String data, String name, String keywords, String metadata) throws ApiException,IOException,NoSuchAlgorithmException;
}
