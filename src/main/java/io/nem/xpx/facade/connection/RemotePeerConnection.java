/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.ApiClient;
import io.nem.ApiException;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.NemAccountApi;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.service.remote.*;
import io.nem.xpx.utils.TransactionSender;
import org.nem.core.node.NodeEndpoint;


/**
 * The Class RemotePeerConnection.
 */
public class RemotePeerConnection implements PeerConnection {

	private final ApiClient apiClient;
	private final NodeEndpoint nodeEndpoint;

	private AccountApi accountApi;
	private DataHashApi dataHashApi;
	private DirectoryLoadApi directoryLoadApi;
	private DownloadApi downloadApi;
	private NodeApi nodeApi;
	private PublishAndSubscribeApi publishAndSubscribeApi;
	private SearchApi searchApi;
	private TransactionAndAnnounceApi transactionAndAnnounceApi;
	private UploadApi uploadApi;
	private NemTransactionApi nemTransactionApi;
	private NemAccountApi nemAccountApi;
	private TransactionSender transactionSender;

	/**
	 * Instantiates a new remote peer connection.
	 *
	 * @param baseUrl
	 *            the base url
	 */
	public RemotePeerConnection(String baseUrl) {
		apiClient = new ApiClient().setBasePath(baseUrl);

		try {
			NodeInfo nodeInfo = new RemoteNodeApi(apiClient).getNodeInfoUsingGET();
			nodeEndpoint = new NodeEndpoint("http", nodeInfo.getNetworkAddress(), Integer.valueOf(nodeInfo.getNetworkPort()));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			// TODO - throw cannot be initialized exception?
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get Api Client
	 * @return the Api Client
	 */
	public ApiClient getApiClient() {
		return apiClient;
	}

	/**
	 * Get Node Endpoint
	 * @return the node endpoint
	 */
	@Override
	public NodeEndpoint getNodeEndpoint() {
		return nodeEndpoint;
	}

	/**
	 * Is local
	 * @return the is local
	 */
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
			searchApi = new RemoteSearchApi(apiClient);
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

	@Override
	public NemTransactionApi getNemTransactionApi() {
		if (nemTransactionApi == null)
			nemTransactionApi = new NemTransactionApi(nodeEndpoint);
		return nemTransactionApi;
	}

	@Override
	public NemAccountApi getNemAccountApi() {
		if (nemAccountApi == null)
			nemAccountApi = new NemAccountApi(nodeEndpoint);
		return nemAccountApi;
	}

	@Override
	public TransactionSender getTransactionSender() {
		if (transactionSender == null)
			transactionSender = new TransactionSender(getNemTransactionApi(), getNemAccountApi());
		return transactionSender;
	}
}
