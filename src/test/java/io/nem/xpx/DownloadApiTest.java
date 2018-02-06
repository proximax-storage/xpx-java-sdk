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


package io.nem.xpx;

import io.nem.ApiException;
import io.nem.xpx.model.ResponseEntity;
import org.junit.Test;
import org.junit.Ignore;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for DownloadApi
 */
@Ignore
public class DownloadApiTest {

    private final DownloadApi api = new DownloadApi();

    
    /**
     * Download resource/file using NEM Transaction Hash
     *
     * This endpoint returns either a byte array format of the actual file OR a JSON format GenericMessageResponse.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadPlainMessageFileUsingNemHashUsingGETTest() throws ApiException {
        String nemhash = null;
        ResponseEntity response = api.downloadPlainMessageFileUsingNemHashUsingGET(nemhash);

        // TODO: test validations
    }
    
    /**
     * Download plain resource/file using NEM Transaction Hash
     *
     * This endpoint returns a byte array format of the actual file
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadRawBytesPlainMessageFileUsingNemHashUsingGETTest() throws ApiException {
        String nemhash = null;
        byte[] response = api.downloadRawBytesPlainMessageFileUsingNemHashUsingGET(nemhash);

        // TODO: test validations
    }
    
    /**
     * Download secured resource/file using NEM Transaction Hash
     *
     * This endpoint returns a byte array format of the actual file
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadRawBytesSecureMessageFileUsingNemHashUsingGETTest() throws ApiException {
        String xPvkey = "8e75544a9f90253fcd880ea73b78f3bc84e1fad032c0cd1062f5694c4fc28bcd";
        String nemhash = "46ba9d7b141c6bed8e91493fa94ae7bf027a15fb1c4e591f48b8e776ce6d259c";
        byte[] response = api.downloadRawBytesSecureMessageFileUsingNemHashUsingGET(nemhash, xPvkey);
    }
    
    /**
     * Download resource/file using NEM Transaction Hash
     *
     * This endpoint returns either a byte array format of the actual file OR a JSON format GenericMessageResponse.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadSecureMessageFileUsingNemHashUsingGETTest() throws ApiException {
        String xPvkey = null;
        String nemhash = null;
        ResponseEntity response = api.downloadSecureMessageFileUsingNemHashUsingGET(xPvkey, nemhash);

        // TODO: test validations
    }
    
    /**
     * Download plain resource/file using NEM Transaction Hash
     *
     * This endpoint returns a byte array format of the actual file
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadStreamPlainMessageFileUsingNemHashUsingGETTest() throws ApiException {
        String nemhash = null;
        byte[] response = api.downloadStreamPlainMessageFileUsingNemHashUsingGET(nemhash);

        // TODO: test validations
    }
    
    /**
     * Download secured resource/file using NEM Transaction Hash
     *
     * This endpoint returns a byte array format of the actual file
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void downloadStreamSecureMessageFileUsingNemHashUsingGETTest() throws ApiException {
        String nemhash = null;
        String xPvkey = null;
        byte[] response = api.downloadStreamSecureMessageFileUsingNemHashUsingGET(nemhash, xPvkey);

        // TODO: test validations
    }
    
}
