package io.nem.xpx.factory;

import org.nem.core.connect.ErrorResponseDeserializerUnion;
import org.nem.core.connect.HttpMethodClient;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.model.Account;
import org.nem.core.model.NetworkInfo;
import org.nem.core.model.NetworkInfos;
import org.nem.core.node.ApiId;
import org.nem.core.node.NodeEndpoint;

import io.ipfs.api.IPFS;
import io.ipfs.multiaddr.MultiAddress;








/**
 * A factory for creating Connector objects.
 */
public class ConnectionFactory {

	/** The Constant CLIENT. */
	private static final HttpMethodClient<ErrorResponseDeserializerUnion> CLIENT = createHttpMethodClient();

	/**
	 * Creates a new Connector object.
	 *
	 * @return the default async nem connector 
	 */
	public static DefaultAsyncNemConnector<ApiId> createConnector() {
		final DefaultAsyncNemConnector<ApiId> connector = new DefaultAsyncNemConnector<>(CLIENT, r -> {
			throw new RuntimeException(r.toString());
		});
		connector.setAccountLookup(Account::new);
		return connector;
	}
	
	/**
	 * Creates a new Connection object.
	 *
	 * @param protocol the protocol
	 * @param domainOrIp the domain or ip
	 * @param port the port
	 * @param wsPort the ws port
	 * @return the node endpoint
	 */
	public static NodeEndpoint createNemNodeConnection(String network, String protocol, String domainOrIp, int port, String wsPort) {
		setNetwork(network);
		return new NodeEndpoint(protocol, domainOrIp, port);
	}
	
	/**
	 * Creates a new Connection object.
	 *
	 * @param protocol the protocol
	 * @param domainOrIp the domain or ip
	 * @param port the port
	 * @return the node endpoint
	 */
	public static NodeEndpoint createNemNodeConnection(String network, String protocol, String domainOrIp, int port) {
		setNetwork(network);
		return new NodeEndpoint(protocol, domainOrIp, port);
	}
	
	/**
	 * Creates a new Connection object.
	 *
	 * @param multiAddress the multi address
	 * @return the ipfs
	 */
	public static IPFS createIPFSNodeConnection(String multiAddress) {
		return new IPFS(new MultiAddress(multiAddress));
	}

	/**
	 * Creates a new Connector object.
	 *
	 * @return the http method client< error response deserializer union>
	 */
	private static HttpMethodClient<ErrorResponseDeserializerUnion> createHttpMethodClient() {
		final int connectionTimeout = 4000;
		final int socketTimeout = 10000;
		final int requestTimeout = 30000;
		return new HttpMethodClient<>(connectionTimeout, socketTimeout, requestTimeout);
	}
	
	private static void setNetwork(String network) {
		NetworkInfo networkInfo = NetworkInfos.getTestNetworkInfo();
		if(network.equals("mainnet")) {
			networkInfo = NetworkInfos.getMainNetworkInfo();
		}else if(network.equals("mijinnet")) {
			networkInfo = NetworkInfos.getMijinNetworkInfo();
		}
		NetworkInfos.setDefault(networkInfo);
	}
	
	
}
