package io.nem.xpx.model;

import io.nem.xpx.factory.ConnectorFactory;
import org.nem.core.connect.client.DefaultAsyncNemConnector;
import org.nem.core.node.ApiId;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeProvider;
import ru.serce.jnrfuse.FuseStubFS;

import java.nio.file.Paths;



/**
 * The Class Globals.
 */
public class XpxSdkGlobalConstants {
	/** The Constant TIME_PROVIDER. */
	public static final TimeProvider TIME_PROVIDER = new SystemTimeProvider();

	/** The fuse ipfs stub. */
	private static FuseStubFS FUSE_IPFS_STUB = null;

	/** The fuse ipns stub. */
	private static FuseStubFS FUSE_IPNS_STUB = null;

	/** The ipfs mount point. */
	private static String ipfsMountPoint = "/ipfs";

	/** The ipns mount point. */
	private static String ipnsMountPoint = "/ipns";

	/** The Constant URL_WS_W_MESSAGES. */
	// Websockets
	public static final String URL_WS_W_MESSAGES = "/w/messages";

	/** The Constant URL_WS_W_API_ACCOUNT_SUBSCRIBE. */
	public static final String URL_WS_W_API_ACCOUNT_SUBSCRIBE = "/w/api/account/subscribe";

	/** The Constant URL_WS_TRANSACTIONS. */
	public static final String URL_WS_TRANSACTIONS = "/transactions";

	/** The Constant URL_WS_UNCONFIRMED. */
	public static final String URL_WS_UNCONFIRMED = "/unconfirmed";

	/** The Constant WS_PORT. */
	public static final String WS_PORT = "7778";

	/** The Constant GLOBAL_GATEWAYS. */
	public static final String[] GLOBAL_GATEWAYS = { "https://ipfs.io", "https://gateway.ipfs.io" };

//	/**
//	 * Gets the ipfs mount point.
//	 *
//	 * @return the ipfs mount point
//	 */
//	public static String getIpfsMountPoint() {
//
//		return ipfsMountPoint;
//	}
//
//	/**
//	 * Sets the ipfs mount point.
//	 *
//	 * @param ipfsMountPoint
//	 *            the new ipfs mount point
//	 */
//	public static void setIpfsMountPoint(String ipfsMountPoint) {
//		XpxSdkGlobalConstants.ipfsMountPoint = ipfsMountPoint;
//	}
//
//	/**
//	 * Gets the ipns mount point.
//	 *
//	 * @return the ipns mount point
//	 */
//	public static String getIpnsMountPoint() {
//		return ipnsMountPoint;
//	}
//
//	/**
//	 * Sets the ipns mount point.
//	 *
//	 * @param ipnsMountPoint
//	 *            the new ipns mount point
//	 */
//	public static void setIpnsMountPoint(String ipnsMountPoint) {
//		XpxSdkGlobalConstants.ipnsMountPoint = ipnsMountPoint;
//	}
//
//	/**
//	 * Gets the fuse ipfs stub.
//	 *
//	 * @return the fuse ipfs stub
//	 */
//	public static FuseStubFS getFuseIpfsStub() {
//		FUSE_IPFS_STUB = new FuseStubFS();
//		FUSE_IPFS_STUB.mount(Paths.get(ipfsMountPoint));
//		return FUSE_IPFS_STUB;
//	}
//
//	/**
//	 * Gets the fuse ipns stub.
//	 *
//	 * @return the fuse ipns stub
//	 */
//	public static FuseStubFS getFuseIpnsStub() {
//		FUSE_IPNS_STUB = new FuseStubFS();
//		FUSE_IPNS_STUB.mount(Paths.get(ipnsMountPoint));
//		return FUSE_IPNS_STUB;
//	}
}