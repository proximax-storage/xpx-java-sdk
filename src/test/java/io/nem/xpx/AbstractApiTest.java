package io.nem.xpx;

import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.ed25519.Ed25519CryptoEngine;

import io.nem.ApiClient;
import io.nem.Configuration;

public abstract class AbstractApiTest {

	protected Configuration configuration;
	//	testnet keys
	protected String xPvkey = "8e75544a9f90253fcd880ea73b78f3bc84e1fad032c0cd1062f5694c4fc28bcd";
	protected String xPubkey = "d24fcd87f3d1f661a0dc15f658cbbffb51b1a13cea3ad99acf73df9b896aed94";
	protected String testnetSecureNemTxnHash = "596facad4321e068de718d0cc25294e1d1b3bedc3b202c23a8e48e05601eed44";
	protected String testnetPlainNemTxnHash = "2ae359a75c9381f6ffe087826cb48f7a4720ff8fdc29738ce9e553bb6442936d";
	
	protected Ed25519CryptoEngine engine = (Ed25519CryptoEngine) CryptoEngines.ed25519Engine();
	
	public AbstractApiTest() {
		Configuration.setDefaultApiClient(new ApiClient().setBasePath("http://localhost:8881/areyes1"));
	}
	
}
