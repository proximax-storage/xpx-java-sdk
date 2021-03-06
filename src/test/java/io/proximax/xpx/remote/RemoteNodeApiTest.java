/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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


package io.proximax.xpx.remote;

import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.model.GenericResponseMessage;
import io.proximax.xpx.model.NodeInfo;
import io.proximax.xpx.service.remote.RemoteNodeApi;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Ignore;



/**
 * API tests for NodeApi.
 */
@Ignore
public class RemoteNodeApiTest extends AbstractApiTest {

    /** The api. */
    private final RemoteNodeApi api = new RemoteNodeApi(apiClient);

    
    /**
     * Check if the Storage Node is up and running.
     *
     * This endpoint is used to check if the P2P Storage Node instance is either alive or down.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void checkNodeUsingGETTest() throws ApiException {
    	GenericResponseMessage response = api.checkNodeUsingGET();
    	
    	Assert.assertNotNull(response);
    }
    
    /**
     * Get Storage Node Information
     * 
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @throws ApiException          if the Api call fails
     */
    @Test
    public void getNodeInfoUsingGETTest() throws ApiException {
        NodeInfo response = api.getNodeInfoUsingGET();

        Assert.assertNotNull(response);
    }
    
    /**
     * Get Storage Node Information
     * 
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @throws ApiException          if the Api call fails
     */
    @Test
    @Ignore("This test can only be ran if you're running the node locally. e.i: set the api client base url to localhost")
    public void setBlockchainNodeConnectionUsingPOSTTest() throws ApiException {
        String network = null;
        String domain = null;
        String port = null;
        String response = api.setBlockchainNodeConnectionUsingPOST(network, domain, port);

        Assert.assertNotNull(response);
    }
    
}
