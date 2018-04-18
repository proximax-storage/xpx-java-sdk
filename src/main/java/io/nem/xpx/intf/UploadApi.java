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

package io.nem.xpx.intf;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import io.nem.ApiException;

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

	public Object uploadBase64StringBinaryUsingPOST(String data, String contentType, String name, String keywords, String metadata)
			throws ApiException, IOException, NoSuchAlgorithmException;
	
	/**
	 * Upload binary using POST.
	 *
	 * @param data the data
	 * @param contentType the content type
	 * @param name the name
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @return the object
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public Object uploadBytesBinaryUsingPOST(byte[] data, String contentType, String name, String keywords, String metadata)
			throws ApiException, IOException, NoSuchAlgorithmException;

	/**
	 * Upload file using POST.
	 *
	 * @param file the file
	 * @param name the name
	 * @param keywords the keywords
	 * @param metadata the metadata
	 * @return the object
	 * @throws ApiException the api exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public Object uploadFileUsingPOST(File file, String name, String keywords, String metadata) throws ApiException, IOException, NoSuchAlgorithmException;

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
	public Object uploadPlainTextUsingPOST(String text, String name, String encoding, String keywords, String metadata)
			throws ApiException, IOException, NoSuchAlgorithmException;
	
}
