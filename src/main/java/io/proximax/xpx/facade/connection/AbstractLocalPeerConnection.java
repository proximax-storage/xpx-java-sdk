/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.facade.connection;

import io.ipfs.api.IPFS;
import io.proximax.xpx.service.NemAccountApi;
import io.proximax.xpx.service.NemTransactionApi;
import io.proximax.xpx.service.TransactionFeeCalculators;
import io.proximax.xpx.service.TransactionSender;
import io.proximax.xpx.service.common.FileAndNamingRouteApi;
import io.proximax.xpx.service.intf.*;
import io.proximax.xpx.service.local.*;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;

import java.util.List;


/**
 * The Class AbstractLocalPeerConnection.
 */
public abstract class AbstractLocalPeerConnection extends PeerConnection {

	/**  The Ipfs *. */
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

    private FileAndNamingRouteApi fileAndNamingRouteApi;

	/** The upload api. */
	private UploadApi uploadApi;

	/** The async proximax connector. */
	private DefaultAsyncNemConnector<ApiId> asyncNemConnector;

	/** The proximax transaction api. */
	private NemTransactionApi nemTransactionApi;

	/** The proximax account api. */
	private NemAccountApi nemAccountApi;

	/** The transaction sender. */
	private TransactionSender transactionSender;

	/** The transaction fee calculators. */
	private TransactionFeeCalculators transactionFeeCalculators;

	/**
	 * Instantiates a new abstract local peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param ipfsConnection the ipfs connection
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	AbstractLocalPeerConnection(NodeEndpoint nodeEndpoint, IPFS ipfsConnection, List<String> syncGateways) {
		this.nodeEndpoint = nodeEndpoint;
		this.proximaxIpfsConnection = ipfsConnection;
		this.setSyncGateways(syncGateways);
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#isLocal()
	 */
	@Override
	public final boolean isLocal() {
		return true;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getAccountApi()
	 */
	@Override
	public AccountApi getAccountApi() {
		if (accountApi == null)
			accountApi = new LocalAccountApi(getNemTransactionApi());
		return accountApi;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getDataHashApi()
	 */
	@Override
	public DataHashApi getDataHashApi() {
		if (dataHashApi == null)
			dataHashApi = new LocalDataHashApi(proximaxIpfsConnection);
		return dataHashApi;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getDirectoryLoadApi()
	 */
	@Override
	public DirectoryLoadApi getDirectoryLoadApi() {
		throw new RuntimeException("not supported");
	}
	
	@Override
	public FileAndNamingRouteApi getFileAndNamingRouteApi() {
		throw new RuntimeException("not supported");
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getDownloadApi()
	 */
	@Override
	public DownloadApi getDownloadApi() {
		if (downloadApi == null)
			downloadApi = new LocalDownloadApi(getNemTransactionApi(), proximaxIpfsConnection);
		return downloadApi;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getNodeApi()
	 */
	@Override
	public NodeApi getNodeApi() {
		if (nodeApi == null)
			nodeApi = new LocalNodeApi(proximaxIpfsConnection);
		return nodeApi;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getPublishAndSubscribeApi()
	 */
	@Override
	public PublishAndSubscribeApi getPublishAndSubscribeApi() {
		if (publishAndSubscribeApi == null)
			publishAndSubscribeApi = new LocalPublishAndSubscribeApi(proximaxIpfsConnection);
		return publishAndSubscribeApi;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getSearchApi()
	 */
	@Override
	public SearchApi getSearchApi() {
		if (searchApi == null)
			searchApi = new LocalSearchApi(getNemTransactionApi());
		return searchApi;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getTransactionAndAnnounceApi()
	 */
	@Override
	public TransactionAndAnnounceApi getTransactionAndAnnounceApi() {
		if (transactionAndAnnounceApi == null)
			transactionAndAnnounceApi = new LocalTransactionAndAnnounceApi(getNemTransactionApi());
		return transactionAndAnnounceApi;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.facade.connection.PeerConnection#getUploadApi()
	 */
	@Override
	public UploadApi getUploadApi() {
		if (uploadApi == null)
			uploadApi = new LocalUploadApi(proximaxIpfsConnection);
		return uploadApi;
	}

}
