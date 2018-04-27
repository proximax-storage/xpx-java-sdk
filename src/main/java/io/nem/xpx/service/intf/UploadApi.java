/*
 * Proximax P2P Storage REST API
 * Proximax P2P Storage REST API
 *
 * OpenAPI spec version: v0.0.1
 * Contact: alvin.reyes@botmill.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.nem.xpx.service.intf;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.nem.ApiException;
import io.nem.xpx.model.UploadBase64BinaryRequestParameter;
import io.nem.xpx.model.UploadBytesBinaryRequestParameter;
import io.nem.xpx.model.UploadTextRequestParameter;


/**
 * The Interface UploadApi.
 */
public interface UploadApi {
	
	/**
	 * Cleanup pinned content using POST.
	 *
	 * @param multihash the multihash
	 * @return the string
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String cleanupPinnedContentUsingPOST(String multihash) throws ApiException, IOException;

	/**
	 * Upload base 64 string binary using POST.
	 *
	 * @param parameter the parameter
	 * @return the object
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public Object uploadBase64StringBinaryUsingPOST(UploadBase64BinaryRequestParameter parameter)
			throws ApiException, IOException, NoSuchAlgorithmException;
	
	/**
	 * Upload binary using POST.
	 *
	 * @param parameter the parameter
	 * @return the object
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public Object uploadBytesBinaryUsingPOST(UploadBytesBinaryRequestParameter parameter)
			throws ApiException, IOException, NoSuchAlgorithmException;

	/**
	 * Upload file using POST.
	 *
	 * @param parameter the parameter
	 * @return the object
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	//public Object uploadFileUsingPOST(File file, String name, String keywords, String metadata) throws ApiException, IOException, NoSuchAlgorithmException;

	/**
	 * Upload plain text using POST.
	 *
	 * @param text the text
	 * @param name the name
	 * @param encoding the encoding
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @return the object
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public Object uploadPlainTextUsingPOST(UploadTextRequestParameter parameter)
			throws ApiException, IOException, NoSuchAlgorithmException;
	
}
