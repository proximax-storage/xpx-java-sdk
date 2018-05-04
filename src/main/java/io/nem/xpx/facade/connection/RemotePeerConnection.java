/*
 * 
 */
package io.nem.xpx.facade.connection;

import io.nem.ApiClient;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.NemAccountApi;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.TransactionFeeCalculators;
import io.nem.xpx.service.TransactionSender;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.service.remote.*;
import org.nem.core.node.NodeEndpoint;



/**
 * The Class RemotePeerConnection.
 */
public final class RemotePeerConnection implements PeerConnection {

	/** The api client. */
	private final ApiClient apiClient;
	
	/** The node endpoint. */
	private final NodeEndpoint nodeEndpoint;

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
	
	/** The nem transaction api. */
	private NemTransactionApi nemTransactionApi;
	
	/** The nem account api. */
	private NemAccountApi nemAccountApi;
	
	/** The transaction sender. */
	private TransactionSender transactionSender;

	private TransactionFeeCalculators transactionFeeCalculators;

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
			// TODO - throw cannot be initialized exception?
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get Api Client.
	 *
	 * @return the Api Client
	 */
	public ApiClient getApiClient() {
		return apiClient;
	}

	/**
	 * Get Node Endpoint.
	 *
	 * @return the node endpoint
	 */
	@Override
	public NodeEndpoint getNodeEndpoint() {
		return nodeEndpoint;
	}

	/**
	 * Is local.
	 *
	 * @return the is local
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
			searchApi = new RemoteSearchApi(apiClient);
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

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getNemTransactionApi()
	 */
	@Override
	public NemTransactionApi getNemTransactionApi() {
		if (nemTransactionApi == null)
			nemTransactionApi = new NemTransactionApi(nodeEndpoint);
		return nemTransactionApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getNemAccountApi()
	 */
	@Override
	public NemAccountApi getNemAccountApi() {
		if (nemAccountApi == null)
			nemAccountApi = new NemAccountApi(nodeEndpoint);
		return nemAccountApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getTransactionSender()
	 */
	@Override
	public TransactionSender getTransactionSender() {
		if (transactionSender == null)
			transactionSender = new TransactionSender(getNemTransactionApi(), getNemAccountApi());
		return transactionSender;
	}

	@Override
	public TransactionFeeCalculators getTransactionFeeCalculators() {
        if (transactionFeeCalculators == null)
            transactionFeeCalculators = new TransactionFeeCalculators();
		return transactionFeeCalculators;
	}
}
