package io.nem.xpx.facade.connection;

import io.ipfs.api.IPFS;
import io.ipfs.multiaddr.MultiAddress;
import io.nem.xpx.service.NemAccountApi;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.TransactionFeeCalculators;
import io.nem.xpx.service.TransactionSender;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.service.local.*;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;

/**
 * The Class AbstractLocalPeerConnection.
 */
public abstract class AbstractLocalPeerConnection extends PeerConnection {

	/** The Ipfs **/
	private IPFS proximaxIpfsConnection;

	/** The account api. */
	private AccountApi accountApi;

	/** The data hash api. */
	private DataHashApi dataHashApi;

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

	private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

	/** The nem transaction api. */
	private NemTransactionApi nemTransactionApi;

	/** The nem account api. */
	private NemAccountApi nemAccountApi;

	/** The transaction sender. */
	private TransactionSender transactionSender;

	private TransactionFeeCalculators transactionFeeCalculators;

	/**
	 * Instantiates a new abstract local peer connection.
	 *
	 * @param nodeEndpoint
	 *            the node endpoint
	 * @param multiAddress
	 *            the multi address
	 */
	AbstractLocalPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		super.nodeEndpoint = nodeEndpoint;
		this.proximaxIpfsConnection = new IPFS(new MultiAddress(multiAddress));
	}
	
	//	For local connections that doesn't need IPFS (Some modules doesn't required IPFS but uses local connection).
	AbstractLocalPeerConnection(NodeEndpoint nodeEndpoint) {
		this.nodeEndpoint = nodeEndpoint;
	}
	
	
	AbstractLocalPeerConnection(NodeEndpoint nodeEndpoint, IPFS ipfsConnection) {
		this.nodeEndpoint = nodeEndpoint;
		this.proximaxIpfsConnection = ipfsConnection;
	}

	@Override
	public final boolean isLocal() {
		return true;
	}

	@Override
	public AccountApi getAccountApi() {
		if (accountApi == null)
			accountApi = new LocalAccountApi(getNemTransactionApi());
		return accountApi;
	}

	@Override
	public DataHashApi getDataHashApi() {
		if (dataHashApi == null)
			dataHashApi = new LocalDataHashApi(proximaxIpfsConnection);
		return dataHashApi;
	}

	@Override
	public DirectoryLoadApi getDirectoryLoadApi() {
		throw new RuntimeException("not supported");
	}

	@Override
	public DownloadApi getDownloadApi() {
		if (downloadApi == null)
			downloadApi = new LocalDownloadApi(getNemTransactionApi(), proximaxIpfsConnection);
		return downloadApi;
	}

	@Override
	public NodeApi getNodeApi() {
		if (nodeApi == null)
			nodeApi = new LocalNodeApi(proximaxIpfsConnection);
		return nodeApi;
	}

	@Override
	public PublishAndSubscribeApi getPublishAndSubscribeApi() {
		if (publishAndSubscribeApi == null)
			publishAndSubscribeApi = new LocalPublishAndSubscribeApi(proximaxIpfsConnection);
		return publishAndSubscribeApi;
	}

	@Override
	public SearchApi getSearchApi() {
		if (searchApi == null)
			searchApi = new LocalSearchApi(getNemTransactionApi());
		return searchApi;
	}

	@Override
	public TransactionAndAnnounceApi getTransactionAndAnnounceApi() {
		if (transactionAndAnnounceApi == null)
			transactionAndAnnounceApi = new LocalTransactionAndAnnounceApi(getNemTransactionApi());
		return transactionAndAnnounceApi;
	}

	@Override
	public UploadApi getUploadApi() {
		if (uploadApi == null)
			uploadApi = new LocalUploadApi(proximaxIpfsConnection);
		return uploadApi;
	}

}
