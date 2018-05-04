package io.nem.xpx.local;
/*
 * Proximax REST API
 * Proximax REST API
 *
 * OpenAPI spec version: v0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


import io.nem.ApiClient;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.ResponseEntity;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.service.remote.RemoteDownloadApi;
import io.nem.xpx.utils.JsonUtils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.TransferTransaction;
import org.nem.core.utils.HexEncoder;
import java.io.UnsupportedEncodingException;


/**
 * API tests for DownloadApi.
 */
@Ignore
public class LocalDownloadApiTest extends AbstractApiTest {

	/** The api. */
	private final RemoteDownloadApi api = new RemoteDownloadApi(apiClient);

	/**
	 * Download resource/file using NEM Transaction Hash
	 *
	 * This endpoint returns either a byte array format of the actual file OR a
	 * JSON format GenericMessageResponse.
	 *
	 * @throws ApiException
	 *             if the Api call fails
	 */
	@Test
	public void downloadPlainMessageFileUsingNemHashUsingGETTest() throws ApiException {
		String nemhash = this.testnetPlainNemTxnHash;
		byte[] response = api.downloadTextUsingGET(nemhash,"bytes");
		Assert.assertNotNull(response);
	}

	/**
	 * Download plain resource/file using NEM Transaction Hash
	 * 
	 * This endpoint returns a byte array format of the actual file.
	 *
	 * @throws ApiException
	 *             if the Api call fails
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	@Test
	@Ignore("This test can only be ran if you're running the node locally. e.i: set the api client base url to localhost")
	public void downloadRawBytesPlainMessageFileUsingNemHashUsingGETTest()
			throws ApiException, UnsupportedEncodingException {
		String nemhash = this.testnetPlainNemTxnHash;
		byte[] response = api.downloadTextUsingGET(nemhash,"bytes");

		Assert.assertNotNull(new String(response, "UTF-8"));
	}


}
