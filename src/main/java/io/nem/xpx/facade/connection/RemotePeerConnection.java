/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.ApiClient;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionInitFailureException;
import io.nem.xpx.factory.ConnectionFactory;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.common.FileAndNamingRouteApi;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.service.remote.*;
import org.nem.core.node.NodeEndpoint;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;


/**
 * The Class RemotePeerConnection.
 */
public final class RemotePeerConnection extends PeerConnection {

	/** The api client. */
	private ApiClient apiClient;
	
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
	
	private FileAndNamingRouteApi fileAndNamingRouteApi;
	
	/** The upload api. */
	private UploadApi uploadApi;

	/**
	 * Instantiates a new remote peer connection.
	 *
	 * @param baseUrl the base url
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public RemotePeerConnection(String baseUrl, String... syncGateways) {
		this(baseUrl, asList(syncGateways));
	}

	/**
	 * Instantiates a new remote peer connection.
	 *
	 * @param baseUrl the base url
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public RemotePeerConnection(String baseUrl, List<String> syncGateways) {
		init(baseUrl, syncGateways, null);
	}

	/**
	 * Instantiates a new remote peer connection.
	 *
	 * @param baseUrl the base url
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 * @param remoteNodeApi the remote node api
	 */
	RemotePeerConnection(String baseUrl, List<String> syncGateways, RemoteNodeApi remoteNodeApi) {
		init(baseUrl, syncGateways, remoteNodeApi);
	}

	private void init(String baseUrl, List<String> syncGateways, RemoteNodeApi remoteNodeApi) {
		this.apiClient = new ApiClient().setBasePath(baseUrl);
		this.nodeApi = remoteNodeApi == null ? new RemoteNodeApi(apiClient) : remoteNodeApi;

		try {
			final NodeInfo nodeInfo = nodeApi.getNodeInfoUsingGET();
			ConnectionFactory.setNetwork(nodeInfo.getNetwork());
			super.nodeEndpoint = new NodeEndpoint("http", nodeInfo.getNetworkAddress(), Integer.valueOf(nodeInfo.getNetworkPort()));
			setSyncGateways(syncGateways, nodeInfo.getSyncGateways());
		} catch (ApiException e) {
			throw new PeerConnectionInitFailureException(format("Failed it initialise due exception on API: %s", baseUrl), e);
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
	 * @see io.nem.xpx.facade.connection.PeerConnection#getFileAndNamingRouteApi()
	 */
	@Override
	public FileAndNamingRouteApi getFileAndNamingRouteApi() {
		if (fileAndNamingRouteApi == null)
			fileAndNamingRouteApi = new FileAndNamingRouteApi(apiClient);
		return fileAndNamingRouteApi;
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
