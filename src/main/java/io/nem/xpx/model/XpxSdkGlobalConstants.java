package io.nem.xpx.model;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.FeeUnitAwareTransactionFeeCalculator;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.primitive.Amount;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;

import io.ipfs.api.IPFS;
import io.ipfs.multiaddr.MultiAddress;
import io.nem.ApiException;
import io.nem.xpx.NodeApi;
import io.nem.xpx.factory.ConnectorFactory;
import ru.serce.jnrfuse.FuseStubFS;

/**
 * The Class Globals.
 */
public class XpxSdkGlobalConstants {
	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();
	
	/** The Constant NODE_ENDPOINT. */
	private static NodeEndpoint NODE_ENDPOINT;
	private static IPFS PROXIMAX_CONNECTION;
	private static FuseStubFS FUSE_IPFS_STUB = null;
	private static FuseStubFS FUSE_IPNS_STUB = null;
	private static String ipfsMountPoint = "/ipfs";
	private static String ipnsMountPoint = "/ipns";
	
	//	Websockets
	public static final String URL_WS_W_MESSAGES = "/w/messages";
	public static final String URL_WS_W_API_ACCOUNT_SUBSCRIBE = "/w/api/account/subscribe";
	public static final String URL_WS_TRANSACTIONS = "/transactions";
	public static final String URL_WS_UNCONFIRMED = "/unconfirmed";
	public static final String WS_PORT = "7778";

	/** The fee calculator. */
	private static TransactionFeeCalculator feeCalculator = new FeeUnitAwareTransactionFeeCalculator(
			Amount.fromMicroNem(50_000L), null);
	private static TransactionFeeCalculator feeCalculatorMultiSig = new FeeUnitAwareTransactionFeeCalculator(
			Amount.fromMicroNem(50_000L), null);

	
	public static String getWebsocketUri() {
		if(NODE_ENDPOINT == null) {
			try {
				NODE_ENDPOINT = getNodeEndpoint();
			} catch (ApiException e) {
				e.printStackTrace();
			}
		}
		StringBuilder builder = new StringBuilder();
		builder.append("ws://")
			.append(NODE_ENDPOINT.getBaseUrl().getHost())
			.append(":")
			.append(WS_PORT)
			.append(URL_WS_W_MESSAGES);
		return builder.toString();
	}
	/**
	 * Gets the node endpoint.
	 *
	 * @return the node endpoint
	 * @throws ApiException
	 *             the api exception
	 */
	public static NodeEndpoint getNodeEndpoint() throws ApiException {
		NodeInfo nodeInfo = new NodeApi().getNodeInfoUsingGET();
		NODE_ENDPOINT = new NodeEndpoint("http", nodeInfo.getNetworkAddress(),
				Integer.valueOf(nodeInfo.getNetworkPort()));

		return NODE_ENDPOINT;
	}

	public static IPFS getProximaxConnection() throws ApiException {
		return PROXIMAX_CONNECTION;
	}

	/**
	 * Sets the global transaction fee.
	 *
	 * @param feeCalculator
	 *            the new global transaction fee
	 */
	public static void setGlobalTransactionFee(TransactionFeeCalculator feeCalculator) {
		XpxSdkGlobalConstants.feeCalculator = feeCalculator;
	}

	public static void setGlobalMultisigTransactionFee(TransactionFeeCalculator feeCalculator) {
		XpxSdkGlobalConstants.feeCalculatorMultiSig = feeCalculator;
	}

	/**
	 * Gets the global transaction fee.
	 *
	 * @return the global transaction fee
	 */
	public static TransactionFeeCalculator getGlobalTransactionFee() {
		return feeCalculator;
	}

	public static TransactionFeeCalculator getGlobalMultisigTransactionFee() {
		return feeCalculatorMultiSig;
	}

	/**
	 * Sets the node endpoint.
	 *
	 * @param endpoint
	 *            the new node endpoint
	 */
	public static void setNodeEndpoint(NodeEndpoint endpoint) {
		XpxSdkGlobalConstants.NODE_ENDPOINT = endpoint;
	}

	public static void setProximaxConnection(String multiAddress) {
		XpxSdkGlobalConstants.PROXIMAX_CONNECTION = new IPFS(new MultiAddress(multiAddress));
	}

	public static String getIpfsMountPoint() {
		
		return ipfsMountPoint;
	}

	public static void setIpfsMountPoint(String ipfsMountPoint) {
		XpxSdkGlobalConstants.ipfsMountPoint = ipfsMountPoint;
	}

	public static String getIpnsMountPoint() {
		return ipnsMountPoint;
	}

	public static void setIpnsMountPoint(String ipnsMountPoint) {
		XpxSdkGlobalConstants.ipnsMountPoint = ipnsMountPoint;
	}

	public static FuseStubFS getFuseIpfsStub() {
		FUSE_IPFS_STUB = new FuseStubFS();
		FUSE_IPFS_STUB.mount(Paths.get(ipfsMountPoint));
		return FUSE_IPFS_STUB;
	}


	public static FuseStubFS getFuseIpnsStub() {
		FUSE_IPNS_STUB = new FuseStubFS();
		FUSE_IPNS_STUB.mount(Paths.get(ipnsMountPoint));
		return FUSE_IPNS_STUB;
	}


	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}