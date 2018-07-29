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


package io.proximax.xpx.service.intf;
import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.model.ResourceHashMessageJsonEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;




/**
 * The Interface SearchApi.
 */
public interface SearchApi {
  
    /**
     * Search transaction with keyword using GET.
     *
     * @param xPubkey the x pubkey
     * @param keywords the keywords
     * @return the list
     * @throws ApiException the api exception
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    public List<ResourceHashMessageJsonEntity> searchTransactionWithKeywordUsingGET(String xPubkey, String keywords) throws ApiException, InterruptedException, ExecutionException;

    /**
     * Search transaction with keyword using GET.
     *
     * @param xPvKey the x pv key
     * @param xPubkey the x pubkey
     * @param keywords the keywords
     * @return the list
     * @throws ApiException the api exception
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    public List<ResourceHashMessageJsonEntity> searchTransactionWithKeywordUsingGET(String xPvKey,String xPubkey,String keywords)
			throws ApiException, InterruptedException, ExecutionException;
    
    /**
     * Search transaction with name using GET.
     *
     * @param xPvKey the x pv key
     * @param xPubkey the x pubkey
     * @param name the name
     * @return the list
     * @throws ApiException the api exception
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    public List<ResourceHashMessageJsonEntity> searchTransactionWithNameUsingGET(String xPvKey,String xPubkey,String name)
			throws ApiException, InterruptedException, ExecutionException;
    
    /**
     * Search all public transaction with metadata key value pair.
     *
     * @param xPubkey the x pubkey
     * @param key the key
     * @param value the value
     * @return the list
     * @throws ApiException the api exception
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    public List<ResourceHashMessageJsonEntity> searchTransactionWithMetadataUsingGET(String xPubkey, String key, String value) throws ApiException, InterruptedException, ExecutionException;
    
    /**
     * Search transaction with metadata key value pair.
     *
     * @param xPvKey the x pv key
     * @param xPubkey the x pubkey
     * @param key the key
     * @param value the value
     * @return the list
     * @throws ApiException the api exception
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    public List<ResourceHashMessageJsonEntity> searchTransactionWithMetadataKeyValuePair(String xPvKey,String xPubkey, String key, String value) throws ApiException, InterruptedException, ExecutionException;

}