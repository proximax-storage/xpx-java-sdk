package io.nem.xpx;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.ed25519.Ed25519CryptoEngine;

import io.nem.ApiClient;
import io.nem.Configuration;

/**
 * The Class AbstractApiTest.
 */
public abstract class AbstractApiTest {
	
	protected Logger LOGGER = Logger.getAnonymousLogger();
	/** The configuration. */
	protected Configuration configuration;

	/** The x pvkey. */
	// testnet keys
	protected String xPvkey = "deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763";

	/** The x pubkey. */
	protected String xPubkey = "d24fcd87f3d1f661a0dc15f658cbbffb51b1a13cea3ad99acf73df9b896aed94";

	/** The testnet secure nem txn hash. */
	protected String testnetSecureNemTxnHash = "7eb54829f1241a6f2c55334e457dbffff8e2460a7bb02c12cb093554ce36a8c9";

	/** The testnet plain nem txn hash. */
	protected String testnetPlainNemTxnHash = "15bb85c1ee2947736ce814bbd6b2549c66eb3b8bf3891ae7a3da478c415dd288";

	/** The engine. */
	protected Ed25519CryptoEngine engine = (Ed25519CryptoEngine) CryptoEngines.ed25519Engine();
	
	protected String localNodeBasePath = "http://138.197.163.64:8881";
	protected String uploadNodeBasePath = "http://p2ptest.smartproof.io:8881";
	protected String downloadNodeBasePath = "http://p2ptest.smartproof.io:8882";
	//protected String downloadNodeBasePath = "http://138.197.163.64:8881";
	/**
	 * Instantiates a new abstract api test.
	 */
	public AbstractApiTest() {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath(uploadNodeBasePath));
	}
	
	public String extractExpectedSmallTxtFileContent() throws IOException {
		return FileUtils.readFileToString(new File("src//test//resources//small_file.txt"));
	}
	
	public long extractLargeFileSize() throws IOException {
		return FileUtils.sizeOf(new File("src//test//resources//large_file.zip"));
	}

	public long extractSmallFileSize() throws IOException {
		return FileUtils.sizeOf(new File("src//test//resources//small_file.txt"));
	}
}
