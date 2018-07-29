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
import io.ipfs.multiaddr.MultiAddress;
import org.nem.core.node.NodeEndpoint;

import java.util.List;

import static java.util.Arrays.asList;


/**
 * The Class LocalFusePeerConnection.
 */
public final class LocalFusePeerConnection extends AbstractLocalPeerConnection {


	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint, String... syncGateways) {
		super(nodeEndpoint, new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001")), asList(syncGateways));
	}

	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint, List<String> syncGateways) {
		super(nodeEndpoint, new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001")), syncGateways);
	}

	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint, String multiAddress, String... syncGateways) {
		super(nodeEndpoint, new IPFS(new MultiAddress(multiAddress)), asList(syncGateways));
	}

	/**
	 * Instantiates a new local fuse peer connection.
	 *
	 * @param nodeEndpoint the node endpoint
	 * @param multiAddress the multi address
	 * @param syncGateways list of gateway URLs where uploads will be sync
	 */
	public LocalFusePeerConnection(NodeEndpoint nodeEndpoint, String multiAddress, List<String> syncGateways) {
		super(nodeEndpoint, new IPFS(new MultiAddress(multiAddress)), syncGateways);
	}

}
