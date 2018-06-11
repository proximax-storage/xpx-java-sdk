/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.ApiClient;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.service.remote.*;
import org.nem.core.node.NodeEndpoint;


/**
 * The Class RemotePeerConnection.
 */
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

	/**
	 * Instantiates a new remote peer connection.
	 *
	 * @param baseUrl the base url
	 */
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

	/**
	 * Gets the api client.
	 *
	 * @return the api client
	 */
	public ApiClient getApiClient() {
		return apiClient;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#isLocal()
	 */
	@Override
	public boolean isLocal() {
		return false;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getAccountApi()
	 */
	@Override
	public AccountApi getAccountApi() {
		if (accountApi == null)
			accountApi = new RemoteAccountApi(apiClient);
		return accountApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getDataHashApi()
	 */
	@Override
	public DataHashApi getDataHashApi() {
		if (dataHashApi == null)
			dataHashApi = new RemoteDataHashApi(apiClient);
		return dataHashApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getDirectoryLoadApi()
	 */
	@Override
	public DirectoryLoadApi getDirectoryLoadApi() {
		if (directoryLoadApi == null)
			directoryLoadApi = new RemoteDirectoryLoadApi(apiClient);
		return directoryLoadApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getDownloadApi()
	 */
	@Override
	public DownloadApi getDownloadApi() {
		if (downloadApi == null)
			downloadApi = new RemoteDownloadApi(apiClient);
		return downloadApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getNodeApi()
	 */
	@Override
	public NodeApi getNodeApi() {
		if (nodeApi == null)
			nodeApi = new RemoteNodeApi(apiClient);
		return nodeApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getPublishAndSubscribeApi()
	 */
	@Override
	public PublishAndSubscribeApi getPublishAndSubscribeApi() {
		if (publishAndSubscribeApi == null)
			publishAndSubscribeApi = new RemotePublishAndSubscribeApi(apiClient);
		return publishAndSubscribeApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getSearchApi()
	 */
	@Override
	public SearchApi getSearchApi() {
		if (searchApi == null)
			searchApi = new RemoteSearchApi(apiClient,getNemTransactionApi());
		return searchApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getTransactionAndAnnounceApi()
	 */
	@Override
	public TransactionAndAnnounceApi getTransactionAndAnnounceApi() {
		if (transactionAndAnnounceApi == null)
			transactionAndAnnounceApi = new RemoteTransactionAndAnnounceApi(apiClient);
		return transactionAndAnnounceApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getUploadApi()
	 */
	@Override
	public UploadApi getUploadApi() {
		if (uploadApi == null)
			uploadApi = new RemoteUploadApi(apiClient);
		return uploadApi;
	}
}
