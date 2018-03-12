package io.nem.builder;

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
import io.nem.xpx.model.NodeInfo;


/**
 * The Class Globals.
 */
public class XpxJavaSdkGlobals {
	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();

	/** The Constant NODE_ENDPOINT. */
	private static NodeEndpoint NODE_ENDPOINT;
	private static IPFS PROXIMAX_CONNECTION;
	
	/** The fee calculator. */
	private static TransactionFeeCalculator feeCalculator = new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), null);
	private static TransactionFeeCalculator feeCalculatorMultiSig = new FeeUnitAwareTransactionFeeCalculator(Amount.fromMicroNem(50_000L), null);
	
	/**
	 * Gets the node endpoint.
	 *
	 * @return the node endpoint
	 * @throws ApiException the api exception
	 */
	public static NodeEndpoint getNodeEndpoint() throws ApiException {
		NodeInfo nodeInfo = new NodeApi().getNodeInfoUsingGET();
		NODE_ENDPOINT = new NodeEndpoint("http", nodeInfo.getNetworkAddress(), Integer.valueOf(nodeInfo.getNetworkPort()));
		
		return NODE_ENDPOINT;
	}
	
	public static IPFS getProximaxConnection() throws ApiException {
		return PROXIMAX_CONNECTION;
	}

	/**
	 * Sets the global transaction fee.
	 *
	 * @param feeCalculator the new global transaction fee
	 */
	public static void setGlobalTransactionFee(TransactionFeeCalculator feeCalculator) {
		XpxJavaSdkGlobals.feeCalculator = feeCalculator;
	}
	
	public static void setGlobalMultisigTransactionFee(TransactionFeeCalculator feeCalculator) {
		XpxJavaSdkGlobals.feeCalculatorMultiSig = feeCalculator;
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
	// = new NodeEndpoint(
	// AppPropertiesUtil.getProperty("node.endpoint.protocol"),
	// AppPropertiesUtil.getProperty("node.endpoint.uri"),
	// Integer.valueOf(AppPropertiesUtil.getProperty("node.endpoint.port")));

	/**
	 * Sets the node endpoint.
	 *
	 * @param endpoint the new node endpoint
	 */
	public static void setNodeEndpoint(NodeEndpoint endpoint) {
		XpxJavaSdkGlobals.NODE_ENDPOINT = endpoint;
	}
	
	public static void setProximaxConnection(String multiAddress) {
		XpxJavaSdkGlobals.PROXIMAX_CONNECTION = new IPFS(new MultiAddress(multiAddress));
	}

	/** The Constant CONNECTOR. */
	public static final DefaultAsyncNemConnector<ApiId> CONNECTOR = ConnectorFactory.createConnector();
}