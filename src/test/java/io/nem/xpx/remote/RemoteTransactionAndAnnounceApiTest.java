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


package io.nem.xpx.remote;

import java.io.File;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.service.remote.RemoteTransactionAndAnnounceApi;
import org.junit.Test;
import org.junit.Ignore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for TransactionAndAnnounceApi.
 */
@Ignore
public class RemoteTransactionAndAnnounceApiTest extends AbstractApiTest {

    /** The api. */
    private final RemoteTransactionAndAnnounceApi api = new RemoteTransactionAndAnnounceApi(apiClient);

    
    /**
     * Announce the DataHash to NEM/P2P Storage and P2P Database
     *
     * Endpoint that can be use to announce the data hash transaction. This will grab the signed BinaryTransaferTransaction and create the P2P Database Entry for the specific data hash / transaction.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void announceRequestPublishDataSignatureUsingPOSTTest() throws ApiException {
        RequestAnnounceDataSignature requestAnnounceDataSignature = null;
        String response = api.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);

        // TODO: test validations
    }
   
    
}
