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


import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.service.remote.RemoteDataHashApi;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.utils.HexEncoder;



/**
 * API tests for DataHashApi.
 */
@Ignore
public class LocalDataHashApiTest extends AbstractApiTest {

	/** The api. */
	private final RemoteDataHashApi api = new RemoteDataHashApi(apiClient);

	/**
	 * Upload json data and generate hash using POST test.
	 *
	 * @throws ApiException the api exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void uploadJsonDataAndGenerateHashUsingPOSTTest() throws ApiException, InvalidKeyException,
			InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {


		byte[] encrypted = engine
				.createBlockCipher(new KeyPair(PrivateKey.fromHexString(this.xPvkey), engine),
						new KeyPair(PublicKey.fromHexString(this.xPubkey), engine))
				.encrypt(FileUtils.readFileToByteArray(new File("src\\test\\resources\\small_file.txt")));

		// pass the hex encoded string of the data.
		String data = HexEncoder.getString(encrypted);
		String response = api.generateHashForDataOnlyUsingPOST(encrypted);

		Assert.assertNotNull(response);

	}

}
