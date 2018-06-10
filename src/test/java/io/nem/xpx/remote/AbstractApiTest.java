package io.nem.xpx.remote;

import io.nem.ApiClient;
import io.nem.xpx.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.ed25519.Ed25519CryptoEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;



/**
 * The Class AbstractApiTest.
 */

public abstract class AbstractApiTest {

	/** The logger. */
	protected Logger LOGGER = Logger.getAnonymousLogger();

	/** The testnet secure nem txn hash. */
	protected String testnetSecureNemTxnHash = "f2df2e0def5bf86b2e5220c01ebafe30b84b4e78fc1476865e6b8f36851e097a";

	/** The testnet plain nem txn hash. */
	protected String testnetPlainNemTxnHash = "7c7b7f868f166e95b150654a306478bcfc139387fce3cfb7195a9499668bdf64";

	/** The engine. */
	protected Ed25519CryptoEngine engine = (Ed25519CryptoEngine) CryptoEngines.ed25519Engine();
	
	/** The local node base path. */
	protected String localNodeBasePath = "http://localhost:8881";
	
	/** The upload node base path. */
	protected String localRemote = "http://localhost:8881";
	
	/** The upload node base path. */
	protected String uploadNodeBasePath = "https://dev.gateway.proximax.io";

	/** The download node base path. */
	protected String downloadNodeBasePath = "http://p2ptest.smartproof.io:8881";
	/** The search node base path. */
	protected String searchNodeBasePath = "http://p2ptest.smartproof.io:8881";

	/** The api client. */
	protected ApiClient apiClient;
	
	/**
	 * Instantiates a new abstract api test.
	 */
	public AbstractApiTest() {
		this.apiClient = new ApiClient().setBasePath(uploadNodeBasePath);
	}

	/**
	 * Extract expected small txt file content.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String extractExpectedSmallTxtFileContent() throws IOException {
		return FileUtils.readFileToString(new File("src//test//resources//test_small_file.txt"));
	}
	
	/**sput
	 * Extract large file size.
	 *
	 * @return the long
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public long extractLargeFileSize() throws IOException {
		return FileUtils.sizeOf(new File("src//test//resources//test_large_file.zip"));
	}

	/**
	 * Extract small file size.
	 *
	 * @return the long
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public long extractSmallFileSize() throws IOException {
		return FileUtils.sizeOf(new File("src//test//resources//test_small_file.txt"));
	}
}
