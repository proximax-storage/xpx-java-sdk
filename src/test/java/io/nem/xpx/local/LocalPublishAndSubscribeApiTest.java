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
import io.nem.xpx.service.remote.RemotePublishAndSubscribeApi;
import org.junit.Ignore;
import org.junit.Test;


/**
 * API tests for PublishAndSubscribeApi.
 */
@Ignore
public class LocalPublishAndSubscribeApiTest extends AbstractApiTest{

    /** The api. */
    private final RemotePublishAndSubscribeApi api = new RemotePublishAndSubscribeApi(apiClient);

    
    /**
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     *
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void publishTopicUsingGETTest() throws ApiException {
        String topic = null;
        String message = null;
        Object response = api.publishTopicUsingGET(topic, message);

        // TODO: test validations
    }
    
}
