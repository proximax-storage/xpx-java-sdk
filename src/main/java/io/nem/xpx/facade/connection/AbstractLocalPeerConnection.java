package io.nem.xpx.facade.connection;

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

	AbstractLocalPeerConnection(NodeEndpoint nodeEndpoint) {
		this.nodeEndpoint = nodeEndpoint;
	}

	@Override
	public NodeEndpoint getNodeEndpoint() {
		return nodeEndpoint;
	}

	/**
	 * Is local
	 * @return the is local
	 */
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
			dataHashApi = new LocalDataHashApi();
		return dataHashApi;
	}

	@Override
	public DirectoryLoadApi getDirectoryLoadApi() {
		throw new RuntimeException("not supported");
	}

	@Override
	public DownloadApi getDownloadApi() {
		if (downloadApi == null)
			downloadApi = new LocalDownloadApi(getNemTransactionApi());
		return downloadApi;
	}

	@Override
	public NodeApi getNodeApi() {
		if (nodeApi == null)
			nodeApi = new LocalNodeApi();
		return nodeApi;
	}

	@Override
	public PublishAndSubscribeApi getPublishAndSubscribeApi() {
		if (publishAndSubscribeApi == null)
			publishAndSubscribeApi = new LocalPublishAndSubscribeApi();
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
			uploadApi = new LocalUploadApi();
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
