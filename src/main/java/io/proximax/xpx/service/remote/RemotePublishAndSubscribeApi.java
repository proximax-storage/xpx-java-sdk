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


package io.proximax.xpx.service.remote;

import io.proximax.ApiCallback;
import io.proximax.ApiClient;
import io.proximax.ApiResponse;
import io.proximax.Pair;
import io.proximax.ProgressRequestBody;
import io.proximax.ProgressResponseBody;
import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.service.intf.PublishAndSubscribeApi;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * The Class RemotePublishAndSubscribeApi.
 */
public class RemotePublishAndSubscribeApi implements PublishAndSubscribeApi {
    
    /** The api client. */
    private final ApiClient apiClient;

    /**
     * Instantiates a new remote publish and subscribe api.
     *
     * @param apiClient the api client
     */
    public RemotePublishAndSubscribeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Gets the api client.
     *
     * @return the api client
     */
    public ApiClient getApiClient() {
        return apiClient;
    }

    /**
     * Build call for publishTopicUsingGET.
     *
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call publishTopicUsingGETCall(String topic, String message, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/pubsub/init/{topic}"
            .replaceAll("\\{" + "topic" + "\\}", apiClient.escapeString(topic.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (message != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "message", message));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    /**
     * Publish topic using GET validate before call.
     *
     * @param topic the topic
     * @param message the message
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the com.squareup.okhttp. call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call publishTopicUsingGETValidateBeforeCall(String topic, String message, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'topic' is set
        if (topic == null) {
            throw new ApiException("Missing the required parameter 'topic' when calling publishTopicUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = publishTopicUsingGETCall(topic, message, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object publishTopicUsingGET(String topic, String message) throws ApiException {
        ApiResponse<Object> resp = publishTopicUsingGETWithHttpInfo(topic, message);
        return resp.getData();
    }

    /**
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> publishTopicUsingGETWithHttpInfo(String topic, String message) throws ApiException {
        com.squareup.okhttp.Call call = publishTopicUsingGETValidateBeforeCall(topic, message, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled. (asynchronously)
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call publishTopicUsingGETAsync(String topic, String message, final ApiCallback<Object> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = publishTopicUsingGETValidateBeforeCall(topic, message, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for sendToTopicUsingGET.
     *
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call sendToTopicUsingGETCall(String topic, String message, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/pubsub/send/to/{topic}"
            .replaceAll("\\{" + "topic" + "\\}", apiClient.escapeString(topic.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (message != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "message", message));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    /**
     * Send to topic using GET validate before call.
     *
     * @param topic the topic
     * @param message the message
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the com.squareup.okhttp. call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call sendToTopicUsingGETValidateBeforeCall(String topic, String message, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'topic' is set
        if (topic == null) {
            throw new ApiException("Missing the required parameter 'topic' when calling sendToTopicUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = sendToTopicUsingGETCall(topic, message, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Send a message to a published topic
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object sendToTopicUsingGET(String topic, String message) throws ApiException {
        ApiResponse<Object> resp = sendToTopicUsingGETWithHttpInfo(topic, message);
        return resp.getData();
    }

    /**
     * Send a message to a published topic
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> sendToTopicUsingGETWithHttpInfo(String topic, String message) throws ApiException {
        com.squareup.okhttp.Call call = sendToTopicUsingGETValidateBeforeCall(topic, message, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Send a message to a published topic (asynchronously)
     * Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
     * @param topic Topic (required)
     * @param message Initial Message (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call sendToTopicUsingGETAsync(String topic, String message, final ApiCallback<Object> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = sendToTopicUsingGETValidateBeforeCall(topic, message, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
