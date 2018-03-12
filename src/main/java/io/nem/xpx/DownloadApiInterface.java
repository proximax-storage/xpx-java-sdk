package io.nem.xpx;

import java.io.IOException;

import io.nem.ApiException;

public interface DownloadApiInterface {
	public byte[] downloadStreamUsingHashUsingPOST(String hash) throws ApiException,IOException;
}
