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
	
	/** The proximax connection. */
	private static IPFS PROXIMAX_CONNECTION;
	
	/** The fuse ipfs stub. */
	private static FuseStubFS FUSE_IPFS_STUB = null;
	
	/** The fuse ipns stub. */
	private static FuseStubFS FUSE_IPNS_STUB = null;
	
	/** The ipfs mount point. */
	private static String ipfsMountPoint = "/ipfs";
	
	/** The ipns mount point. */
	private static String ipnsMountPoint = "/ipns";
	
	/** The Constant URL_WS_W_MESSAGES. */
	//	Websockets
	public static final String URL_WS_W_MESSAGES = "/w/messages";
	
	/** The Constant URL_WS_W_API_ACCOUNT_SUBSCRIBE. */
	public static final String URL_WS_W_API_ACCOUNT_SUBSCRIBE = "/w/api/account/subscribe";
	
	/** The Constant URL_WS_TRANSACTIONS. */
	public static final String URL_WS_TRANSACTIONS = "/transactions";
	
	/** The Constant URL_WS_UNCONFIRMED. */
	public static final String URL_WS_UNCONFIRMED = "/unconfirmed";
	
	/** The Constant WS_PORT. */
	public static final String WS_PORT = "7778";

	/** The fee calculator. */
	private static TransactionFeeCalculator feeCalculator = new FeeUnitAwareTransactionFeeCalculator(
			Amount.fromMicroNem(50_000L), null);
	
	/** The fee calculator multi sig. */
	private static TransactionFeeCalculator feeCalculatorMultiSig = new FeeUnitAwareTransactionFeeCalculator(
			Amount.fromMicroNem(50_000L), null);

	
	/**
	 * Gets the websocket uri.
	 *
	 * @return the websocket uri
	 */
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

	/**
	 * Gets the proximax connection.
	 *
	 * @return the proximax connection
	 * @throws ApiException the api exception
	 */
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

	/**
	 * Sets the global multisig transaction fee.
	 *
	 * @param feeCalculator the new global multisig transaction fee
	 */
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

	/**
	 * Gets the global multisig transaction fee.
	 *
	 * @return the global multisig transaction fee
	 */
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

	/**
	 * Sets the proximax connection.
	 *
	 * @param multiAddress the new proximax connection
	 */
	public static void setProximaxConnection(String multiAddress) {
		XpxSdkGlobalConstants.PROXIMAX_CONNECTION = new IPFS(new MultiAddress(multiAddress));
	}

	/**
	 * Gets the ipfs mount point.
	 *
	 * @return the ipfs mount point
	 */
	public static String getIpfsMountPoint() {
		
		return ipfsMountPoint;
	}

	/**
	 * Sets the ipfs mount point.
	 *
	 * @param ipfsMountPoint the new ipfs mount point
	 */
	public static void setIpfsMountPoint(String ipfsMountPoint) {
		XpxSdkGlobalConstants.ipfsMountPoint = ipfsMountPoint;
	}

	/**
	 * Gets the ipns mount point.
	 *
	 * @return the ipns mount point
	 */
	public static String getIpnsMountPoint() {
		return ipnsMountPoint;
	}

	/**
	 * Sets the ipns mount point.
	 *
	 * @param ipnsMountPoint the new ipns mount point
	 */
	public static void setIpnsMountPoint(String ipnsMountPoint) {
		XpxSdkGlobalConstants.ipnsMountPoint = ipnsMountPoint;
	}

	/**
	 * Gets the fuse ipfs stub.
	 *
	 * @return the fuse ipfs stub
	 */
	public static FuseStubFS getFuseIpfsStub() {
		FUSE_IPFS_STUB = new FuseStubFS();
		FUSE_IPFS_STUB.mount(Paths.get(ipfsMountPoint));
		return FUSE_IPFS_STUB;
	}


	/**
	 * Gets the fuse ipns stub.
	 *
	 * @return the fuse ipns stub
	 */
	public static FuseStubFS getFuseIpnsStub() {
		FUSE_IPNS_STUB = new FuseStubFS();
		FUSE_IPNS_STUB.mount(Paths.get(ipnsMountPoint));
		return FUSE_IPNS_STUB;
	}


	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}