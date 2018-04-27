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
import io.nem.ApiException;
import io.nem.ApiResponse;
import io.nem.Configuration;
import io.nem.Pair;
import io.nem.ProgressRequestBody;
import io.nem.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.service.intf.TransactionAndAnnounceApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Class RemoteTransactionAndAnnounceApi.
 */
public class RemoteTransactionAndAnnounceApi implements TransactionAndAnnounceApi {
    
    /** The api client. */
    private ApiClient apiClient;

    /**
     * Instantiates a new remote transaction and announce api.
     */
    public RemoteTransactionAndAnnounceApi() {
        this(Configuration.getDefaultApiClient());
    }

    /**
     * Instantiates a new remote transaction and announce api.
     *
     * @param apiClient the api client
     */
    public RemoteTransactionAndAnnounceApi(ApiClient apiClient) {
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
     * Sets the api client.
     *
     * @param apiClient the new api client
     */
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for announceRequestPublishDataSignatureUsingPOST.
     *
     * @param requestAnnounceDataSignature The Request Announce Data Signature Json Format (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call announceRequestPublishDataSignatureUsingPOSTCall(RequestAnnounceDataSignature requestAnnounceDataSignature, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = requestAnnounceDataSignature;
        
        // create path and map variables
        String localVarPath = "/transaction/announce";

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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    /**
     * Announce request publish data signature using POST validate before call.
     *
     * @param requestAnnounceDataSignature the request announce data signature
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the com.squareup.okhttp. call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call announceRequestPublishDataSignatureUsingPOSTValidateBeforeCall(RequestAnnounceDataSignature requestAnnounceDataSignature, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        
        com.squareup.okhttp.Call call = announceRequestPublishDataSignatureUsingPOSTCall(requestAnnounceDataSignature, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Announce the DataHash to NEM/P2P Storage and P2P Database
     * Endpoint that can be use to announce the data hash transaction. This will grab the signed BinaryTransaferTransaction and create the P2P Database Entry for the specific data hash / transaction.
     * @param requestAnnounceDataSignature The Request Announce Data Signature Json Format (optional)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String announceRequestPublishDataSignatureUsingPOST(RequestAnnounceDataSignature requestAnnounceDataSignature) throws ApiException {
        ApiResponse<String> resp = announceRequestPublishDataSignatureUsingPOSTWithHttpInfo(requestAnnounceDataSignature);
        return resp.getData();
    }

    /**
     * Announce the DataHash to NEM/P2P Storage and P2P Database
     * Endpoint that can be use to announce the data hash transaction. This will grab the signed BinaryTransaferTransaction and create the P2P Database Entry for the specific data hash / transaction.
     * @param requestAnnounceDataSignature The Request Announce Data Signature Json Format (optional)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> announceRequestPublishDataSignatureUsingPOSTWithHttpInfo(RequestAnnounceDataSignature requestAnnounceDataSignature) throws ApiException {
        com.squareup.okhttp.Call call = announceRequestPublishDataSignatureUsingPOSTValidateBeforeCall(requestAnnounceDataSignature, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Announce the DataHash to NEM/P2P Storage and P2P Database (asynchronously)
     * Endpoint that can be use to announce the data hash transaction. This will grab the signed BinaryTransaferTransaction and create the P2P Database Entry for the specific data hash / transaction.
     * @param requestAnnounceDataSignature The Request Announce Data Signature Json Format (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call announceRequestPublishDataSignatureUsingPOSTAsync(RequestAnnounceDataSignature requestAnnounceDataSignature, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = announceRequestPublishDataSignatureUsingPOSTValidateBeforeCall(requestAnnounceDataSignature, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getXPXTransactionUsingGET.
     *
     * @param nemHash XPX Transaction Hash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getXPXTransactionUsingGETCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/transaction/get/{nemHash}"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

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
     * Gets the XPX transaction using GET validate before call.
     *
     * @param nemHash the nem hash
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the XPX transaction using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getXPXTransactionUsingGETValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling getXPXTransactionUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = getXPXTransactionUsingGETCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Get the XPX Transaction Hash
     * Endpoint can be used to get XPX Transaction.
     * @param nemHash XPX Transaction Hash (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String getXPXTransactionUsingGET(String nemHash) throws ApiException {
        ApiResponse<String> resp = getXPXTransactionUsingGETWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Get the XPX Transaction Hash
     * Endpoint can be used to get XPX Transaction.
     * @param nemHash XPX Transaction Hash (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> getXPXTransactionUsingGETWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = getXPXTransactionUsingGETValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get the XPX Transaction Hash (asynchronously)
     * Endpoint can be used to get XPX Transaction.
     * @param nemHash XPX Transaction Hash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getXPXTransactionUsingGETAsync(String nemHash, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getXPXTransactionUsingGETValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
