/*
 * Proximax P2P Storage REST API
 * Proximax P2P Storage REST API
 *
 * OpenAPI spec version: v0.0.1
 * Contact: proximax.storage@proximax.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.nem.xpx.local;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.remote.AbstractApiTest;
import io.nem.xpx.service.remote.RemoteDirectoryLoadApi;
import org.junit.Test;
import org.junit.Ignore;
/**
 * API tests for DirectoryLoadApi
 */
@Ignore
public class LocalDirectoryLoadApiTest extends AbstractApiTest {

    private final RemoteDirectoryLoadApi api = new RemoteDirectoryLoadApi(apiClient);

    
    /**
     * Loads a Static Content.
     *
     * Loads a Static Content.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void loadDirectoryUsingGETTest() throws io.nem.xpx.exceptions.ApiException {
        String nemHash = null;
        Object response = api.loadDirectoryUsingGET(nemHash);

    }
    
}
