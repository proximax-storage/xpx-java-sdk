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

	public static final File SAMPLE_PDF_FILE1 = new File("src//test//resources//test_pdf_file_v1.pdf");
	public static final File SAMPLE_PDF_FILE2 = new File("src//test//resources//test_pdf_file_v2.pdf");
	public static final File SAMPLE_NON_EXISTENT_FILE = new File("src//test//resources//pdf_non_existent.pdf");
	public static final String SAMPLE_ZIP_FILE_NAME = "test.zip";
	public static final String SAMPLE_KEYWORDS = "plain,file";
	public static final String SAMPLE_METADATA = aSampleMetadata();

	/** The logger. */
	protected Logger LOGGER = Logger.getAnonymousLogger();

	/** The x pvkey. */
	// testnet keys
	protected String xPvkey = "deaae199f8e511ec51eb0046cf8d78dc481e20a340d003bbfcc3a66623d09763";

	/** The x pubkey. */
	//protected String xPubkey = "092f13a06496c002510a6afc03f5db522664716aaeefdded450106df1624dd3d";
	protected String xPubkey = "36e6fbc1cc5c3ef49d313721650b98d7d7d126a4f731d70071f4f3b4798cdc85";

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
	protected String uploadNodeBasePath = "https://demo-gateway.proximax.io";
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

	private static String aSampleMetadata() {
		Map<String,String> metaData = new HashMap<String,String>();
		metaData.put("key1", "value1");
		return JsonUtils.toJson(metaData);
	}
}
