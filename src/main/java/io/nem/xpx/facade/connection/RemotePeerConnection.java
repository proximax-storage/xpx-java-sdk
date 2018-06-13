/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.ApiClient;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.service.model.NodeInfo;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.service.remote.*;
import org.nem.core.node.NodeEndpoint;

public final class RemotePeerConnection extends PeerConnection {

	/** The api client. */
	private final ApiClient apiClient;
	
	/** The account api. */
	private AccountApi accountApi;
	
	/** The data hash api. */
	private DataHashApi dataHashApi;
	
	/** The directory load api. */
	private DirectoryLoadApi directoryLoadApi;
	
	/** The download api. */
	private DownloadApi downloadApi;
	
	/** The node api. */
	private NodeApi nodeApi;
	
	/** The publish and subscribe api. */
	private PublishAndSubscribeApi publishAndSubscribeApi;
	
	/** The search api. */
	private SearchApi searchApi;
	
	/** The transaction and announce api. */
	private TransactionAndAnnounceApi transactionAndAnnounceApi;
	
	/** The upload api. */
	private UploadApi uploadApi;

	public RemotePeerConnection(String baseUrl) {
		apiClient = new ApiClient().setBasePath(baseUrl);

		try {
			NodeInfo nodeInfo = new RemoteNodeApi(apiClient).getNodeInfoUsingGET();
			super.nodeEndpoint = new NodeEndpoint("http", nodeInfo.getNetworkAddress(), Integer.valueOf(nodeInfo.getNetworkPort()));
		} catch (ApiException e) {
			// TODO - throw cannot be initialized exception?
			throw new RuntimeException(e);
		}
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	@Override
	public boolean isLocal() {
		return false;
	}

	@Override
	public AccountApi getAccountApi() {
		if (accountApi == null)
			accountApi = new RemoteAccountApi(apiClient);
		return accountApi;
	}

	@Override
	public DataHashApi getDataHashApi() {
		if (dataHashApi == null)
			dataHashApi = new RemoteDataHashApi(apiClient);
		return dataHashApi;
	}

	@Override
	public DirectoryLoadApi getDirectoryLoadApi() {
		if (directoryLoadApi == null)
			directoryLoadApi = new RemoteDirectoryLoadApi(apiClient);
		return directoryLoadApi;
	}

	@Override
	public DownloadApi getDownloadApi() {
		if (downloadApi == null)
			downloadApi = new RemoteDownloadApi(apiClient);
		return downloadApi;
	}

	@Override
	public NodeApi getNodeApi() {
		if (nodeApi == null)
			nodeApi = new RemoteNodeApi(apiClient);
		return nodeApi;
	}

	@Override
	public PublishAndSubscribeApi getPublishAndSubscribeApi() {
		if (publishAndSubscribeApi == null)
			publishAndSubscribeApi = new RemotePublishAndSubscribeApi(apiClient);
		return publishAndSubscribeApi;
	}

	@Override
	public SearchApi getSearchApi() {
		if (searchApi == null)
			searchApi = new RemoteSearchApi(apiClient,getNemTransactionApi());
		return searchApi;
	}

	@Override
	public TransactionAndAnnounceApi getTransactionAndAnnounceApi() {
		if (transactionAndAnnounceApi == null)
			transactionAndAnnounceApi = new RemoteTransactionAndAnnounceApi(apiClient);
		return transactionAndAnnounceApi;
	}

	@Override
	public UploadApi getUploadApi() {
		if (uploadApi == null)
			uploadApi = new RemoteUploadApi(apiClient);
		return uploadApi;
	}
}
