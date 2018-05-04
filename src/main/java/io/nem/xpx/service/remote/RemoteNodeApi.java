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


package io.nem.xpx.service.remote;

import io.nem.ApiCallback;
import io.nem.ApiClient;
import io.nem.ApiResponse;
import io.nem.Configuration;
import io.nem.Pair;
import io.nem.ProgressRequestBody;
import io.nem.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.GenericResponseMessage;
import io.nem.xpx.model.NodeInfo;
import io.nem.xpx.service.intf.NodeApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * The Class RemoteNodeApi.
 */
public class RemoteNodeApi implements NodeApi {
    
    /** The api client. */
    private final ApiClient apiClient;

    /**
     * Instantiates a new remote node api.
     *
     * @param apiClient the api client
     */
    public RemoteNodeApi(ApiClient apiClient) {
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
     * Build call for checkNodeUsingGET.
     *
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call checkNodeUsingGETCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/node/check";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
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
     * Check node using GET validate before call.
     *
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the com.squareup.okhttp. call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call checkNodeUsingGETValidateBeforeCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        
        com.squareup.okhttp.Call call = checkNodeUsingGETCall(progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Check if the Storage Node is up and running.
     * This endpoint is used to check if the P2P Storage Node instance is either alive or down.
     * @return GenericResponseMessage
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public GenericResponseMessage checkNodeUsingGET() throws ApiException {
        ApiResponse<GenericResponseMessage> resp = checkNodeUsingGETWithHttpInfo();
        return resp.getData();
    }

    /**
     * Check if the Storage Node is up and running.
     * This endpoint is used to check if the P2P Storage Node instance is either alive or down.
     * @return ApiResponse&lt;GenericResponseMessage&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<GenericResponseMessage> checkNodeUsingGETWithHttpInfo() throws ApiException {
        com.squareup.okhttp.Call call = checkNodeUsingGETValidateBeforeCall(null, null);
        Type localVarReturnType = new TypeToken<GenericResponseMessage>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Check if the Storage Node is up and running. (asynchronously)
     * This endpoint is used to check if the P2P Storage Node instance is either alive or down.
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call checkNodeUsingGETAsync(final ApiCallback<GenericResponseMessage> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = checkNodeUsingGETValidateBeforeCall(progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<GenericResponseMessage>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getNodeInfoPeersUsingGET.
     *
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getNodeInfoPeersUsingGETCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/node/info/peers";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
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
     * Gets the node info peers using GET validate before call.
     *
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the node info peers using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getNodeInfoPeersUsingGETValidateBeforeCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        
        com.squareup.okhttp.Call call = getNodeInfoPeersUsingGETCall(progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Get Storage Node Information
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @return NodeInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public NodeInfo getNodeInfoPeersUsingGET() throws ApiException {
        ApiResponse<NodeInfo> resp = getNodeInfoPeersUsingGETWithHttpInfo();
        return resp.getData();
    }

    /**
     * Get Storage Node Information
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @return ApiResponse&lt;NodeInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<NodeInfo> getNodeInfoPeersUsingGETWithHttpInfo() throws ApiException {
        com.squareup.okhttp.Call call = getNodeInfoPeersUsingGETValidateBeforeCall(null, null);
        Type localVarReturnType = new TypeToken<NodeInfo>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Storage Node Information (asynchronously)
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getNodeInfoPeersUsingGETAsync(final ApiCallback<NodeInfo> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getNodeInfoPeersUsingGETValidateBeforeCall(progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<NodeInfo>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getNodeInfoUsingGET.
     *
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getNodeInfoUsingGETCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/node/info";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
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
     * Gets the node info using GET validate before call.
     *
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the node info using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getNodeInfoUsingGETValidateBeforeCall(final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        
        com.squareup.okhttp.Call call = getNodeInfoUsingGETCall(progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Get Storage Node Information
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @return NodeInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public NodeInfo getNodeInfoUsingGET() throws ApiException {
        ApiResponse<NodeInfo> resp = getNodeInfoUsingGETWithHttpInfo();
        return resp.getData();
    }

    /**
     * Get Storage Node Information
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @return ApiResponse&lt;NodeInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<NodeInfo> getNodeInfoUsingGETWithHttpInfo() throws ApiException {
        com.squareup.okhttp.Call call = getNodeInfoUsingGETValidateBeforeCall(null, null);
        Type localVarReturnType = new TypeToken<NodeInfo>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Storage Node Information (asynchronously)
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getNodeInfoUsingGETAsync(final ApiCallback<NodeInfo> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getNodeInfoUsingGETValidateBeforeCall(progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<NodeInfo>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for setBlockchainNodeConnectionUsingPOST.
     *
     * @param network Blockchain Network (required)
     * @param domain Blockchain Network Domain (xxx.xxx.xxx) (required)
     * @param port Blockchain Network Port (xxx.xxx.xxx) (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call setBlockchainNodeConnectionUsingPOSTCall(String network, String domain, String port, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/node/set/blockchain/connection";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (network != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "network", network));
        if (domain != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "domain", domain));
        if (port != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "port", port));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    /**
     * Sets the blockchain node connection using POST validate before call.
     *
     * @param network the network
     * @param domain the domain
     * @param port the port
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the com.squareup.okhttp. call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call setBlockchainNodeConnectionUsingPOSTValidateBeforeCall(String network, String domain, String port, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'network' is set
        if (network == null) {
            throw new ApiException("Missing the required parameter 'network' when calling setBlockchainNodeConnectionUsingPOST(Async)");
        }
        
        // verify the required parameter 'domain' is set
        if (domain == null) {
            throw new ApiException("Missing the required parameter 'domain' when calling setBlockchainNodeConnectionUsingPOST(Async)");
        }
        
        // verify the required parameter 'port' is set
        if (port == null) {
            throw new ApiException("Missing the required parameter 'port' when calling setBlockchainNodeConnectionUsingPOST(Async)");
        }
        
        
        com.squareup.okhttp.Call call = setBlockchainNodeConnectionUsingPOSTCall(network, domain, port, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Get Storage Node Information
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @param network Blockchain Network (required)
     * @param domain Blockchain Network Domain (xxx.xxx.xxx) (required)
     * @param port Blockchain Network Port (xxx.xxx.xxx) (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String setBlockchainNodeConnectionUsingPOST(String network, String domain, String port) throws ApiException {
        ApiResponse<String> resp = setBlockchainNodeConnectionUsingPOSTWithHttpInfo(network, domain, port);
        return resp.getData();
    }

    /**
     * Get Storage Node Information
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @param network Blockchain Network (required)
     * @param domain Blockchain Network Domain (xxx.xxx.xxx) (required)
     * @param port Blockchain Network Port (xxx.xxx.xxx) (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> setBlockchainNodeConnectionUsingPOSTWithHttpInfo(String network, String domain, String port) throws ApiException {
        com.squareup.okhttp.Call call = setBlockchainNodeConnectionUsingPOSTValidateBeforeCall(network, domain, port, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Storage Node Information (asynchronously)
     * This endpoint returns the information of the P2P Storage Node.
     *
     * @param network Blockchain Network (required)
     * @param domain Blockchain Network Domain (xxx.xxx.xxx) (required)
     * @param port Blockchain Network Port (xxx.xxx.xxx) (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call setBlockchainNodeConnectionUsingPOSTAsync(String network, String domain, String port, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = setBlockchainNodeConnectionUsingPOSTValidateBeforeCall(network, domain, port, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
