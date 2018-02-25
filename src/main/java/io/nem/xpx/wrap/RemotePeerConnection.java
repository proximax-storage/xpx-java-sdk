package io.nem.xpx.wrap;

import io.nem.ApiClient;
import io.nem.Configuration;

public class RemotePeerConnection implements PeerConnection	{
	public RemotePeerConnection(String baseUrl) {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath(baseUrl));
	}
	public RemotePeerConnection setPeerBaseUrl(String baseUrl) {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath(baseUrl));
		return this;
	}
	
}
