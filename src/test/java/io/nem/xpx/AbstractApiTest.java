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
	//protected String xPubkey = "092f13a06496c002510a6afc03f5db522664716aaeefdded450106df1624dd3d";
	protected String xPubkey = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85";

	/** The testnet secure nem txn hash. */
	protected String testnetSecureNemTxnHash = "7eb54829f1241a6f2c55334e457dbffff8e2460a7bb02c12cb093554ce36a8c9";

	/** The testnet plain nem txn hash. */
	protected String testnetPlainNemTxnHash = "15bb85c1ee2947736ce814bbd6b2549c66eb3b8bf3891ae7a3da478c415dd288";

	/** The engine. */
	protected Ed25519CryptoEngine engine = (Ed25519CryptoEngine) CryptoEngines.ed25519Engine();
	
	protected String localNodeBasePath = "http://localhost:8881";
	protected String uploadNodeBasePath = "http://p2ptest.smartproof.io:8881";//"http://128.199.196.118:8881";
	protected String downloadNodeBasePath = "http://p2ptest.smartproof.io:8881";//"http://178.62.225.175:8881";
	
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
