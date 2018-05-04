package io.nem.xpx.facade.connection;

import io.ipfs.api.IPFS;
import io.ipfs.multiaddr.MultiAddress;
import io.nem.xpx.service.NemAccountApi;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.*;
import io.nem.xpx.service.local.*;
import io.nem.xpx.utils.TransactionSender;
import org.nem.core.node.NodeEndpoint;


/**
 * The Class AbstractLocalPeerConnection.
 */
public abstract class AbstractLocalPeerConnection implements PeerConnection {

	/** The node endpoint. */
	private final NodeEndpoint nodeEndpoint;

	/** The Ipfs **/
	private final IPFS proximaxIpfsConnection;

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
	
	/** The nem transaction api. */
	private NemTransactionApi nemTransactionApi;
	
	/** The nem account api. */
	private NemAccountApi nemAccountApi;
	
	/** The transaction sender. */
	private TransactionSender transactionSender;

	/**
	 * Instantiates a new abstract local peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 */
	AbstractLocalPeerConnection(NodeEndpoint nodeEndpoint, String multiAddress) {
		this.nodeEndpoint = nodeEndpoint;
		this.proximaxIpfsConnection = new IPFS(new MultiAddress(multiAddress));
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getNodeEndpoint()
	 */
	@Override
	public NodeEndpoint getNodeEndpoint() {
		return nodeEndpoint;
	}

	@Override
	public IPFS getProximaxIpfsConnection() {
		return proximaxIpfsConnection;
	}

	/**
	 * Is local.
	 *
	 * @return the is local
	 */
	@Override
	public final boolean isLocal() {
		return true;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getAccountApi()
	 */
	@Override
	public AccountApi getAccountApi() {
		if (accountApi == null)
			accountApi = new LocalAccountApi(getNemTransactionApi());
		return accountApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getDataHashApi()
	 */
	@Override
	public DataHashApi getDataHashApi() {
		if (dataHashApi == null)
			dataHashApi = new LocalDataHashApi(proximaxIpfsConnection);
		return dataHashApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getDirectoryLoadApi()
	 */
	@Override
	public DirectoryLoadApi getDirectoryLoadApi() {
		throw new RuntimeException("not supported");
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getDownloadApi()
	 */
	@Override
	public DownloadApi getDownloadApi() {
		if (downloadApi == null)
			downloadApi = new LocalDownloadApi(getNemTransactionApi(), getProximaxIpfsConnection());
		return downloadApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getNodeApi()
	 */
	@Override
	public NodeApi getNodeApi() {
		if (nodeApi == null)
			nodeApi = new LocalNodeApi();
		return nodeApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getPublishAndSubscribeApi()
	 */
	@Override
	public PublishAndSubscribeApi getPublishAndSubscribeApi() {
		if (publishAndSubscribeApi == null)
			publishAndSubscribeApi = new LocalPublishAndSubscribeApi(getProximaxIpfsConnection());
		return publishAndSubscribeApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getSearchApi()
	 */
	@Override
	public SearchApi getSearchApi() {
		if (searchApi == null)
			searchApi = new LocalSearchApi(getNemTransactionApi());
		return searchApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getTransactionAndAnnounceApi()
	 */
	@Override
	public TransactionAndAnnounceApi getTransactionAndAnnounceApi() {
		if (transactionAndAnnounceApi == null)
			transactionAndAnnounceApi = new LocalTransactionAndAnnounceApi(getNemTransactionApi());
		return transactionAndAnnounceApi;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.facade.connection.PeerConnection#getUploadApi()
	 */
	@Override
	public UploadApi getUploadApi() {
		if (uploadApi == null)
			uploadApi = new LocalUploadApi(getProximaxIpfsConnection());
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

}
