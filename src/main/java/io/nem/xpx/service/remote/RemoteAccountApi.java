package io.nem.xpx.service.remote;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import io.nem.Pair;
import io.nem.ProgressRequestBody;
import io.nem.ProgressResponseBody;
import io.nem.ApiCallback;
import io.nem.ApiClient;
import io.nem.ApiResponse;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.AccountMetaDataPair;
import io.nem.xpx.service.intf.AccountApi;



/**
 * The Class RemoteAccountApi.
 */
public class RemoteAccountApi implements AccountApi {
	
	/** The api client. */
	private final ApiClient apiClient;

    /**
     * Instantiates a new remote account api.
     *
     * @param apiClient the api client
     */
    public RemoteAccountApi(ApiClient apiClient) {
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
     * Build call for getAllIncomingNemAddressTransactionsUsingGET.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAllIncomingNemAddressTransactionsUsingGETCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/account/get/incoming/transactions/{publicKey}"
            .replaceAll("\\{" + "publicKey" + "\\}", apiClient.escapeString(publicKey.toString()));

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
     * Gets the all incoming nem address transactions using GET validate before call.
     *
     * @param publicKey the public key
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the all incoming nem address transactions using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAllIncomingNemAddressTransactionsUsingGETValidateBeforeCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'publicKey' is set
        if (publicKey == null) {
            throw new ApiException("Missing the required parameter 'publicKey' when calling getAllIncomingNemAddressTransactionsUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = getAllIncomingNemAddressTransactionsUsingGETCall(publicKey, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * getAllIncomingNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String getAllIncomingNemAddressTransactionsUsingGET(String publicKey) throws ApiException {
        ApiResponse<String> resp = getAllIncomingNemAddressTransactionsUsingGETWithHttpInfo(publicKey);
        return resp.getData();
    }

    /**
     * getAllIncomingNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> getAllIncomingNemAddressTransactionsUsingGETWithHttpInfo(String publicKey) throws ApiException {
        com.squareup.okhttp.Call call = getAllIncomingNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * getAllIncomingNemAddressTransactions (asynchronously).
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAllIncomingNemAddressTransactionsUsingGETAsync(String publicKey, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getAllIncomingNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getAllNemAddressTransactionsUsingGET.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAllNemAddressTransactionsUsingGETCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/account/get/transactions/{publicKey}"
            .replaceAll("\\{" + "publicKey" + "\\}", apiClient.escapeString(publicKey.toString()));

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
     * Gets the all nem address transactions using GET validate before call.
     *
     * @param publicKey the public key
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the all nem address transactions using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAllNemAddressTransactionsUsingGETValidateBeforeCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'publicKey' is set
        if (publicKey == null) {
            throw new ApiException("Missing the required parameter 'publicKey' when calling getAllNemAddressTransactionsUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = getAllNemAddressTransactionsUsingGETCall(publicKey, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * getAllNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String getAllNemAddressTransactionsUsingGET(String publicKey) throws ApiException {
        ApiResponse<String> resp = getAllNemAddressTransactionsUsingGETWithHttpInfo(publicKey);
        return resp.getData();
    }

    /**
     * getAllNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> getAllNemAddressTransactionsUsingGETWithHttpInfo(String publicKey) throws ApiException {
        com.squareup.okhttp.Call call = getAllNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * getAllNemAddressTransactions (asynchronously).
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAllNemAddressTransactionsUsingGETAsync(String publicKey, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getAllNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getAllNemAddressTransactionsWithPageSizeUsingGET.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param pageSize Page Size (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAllNemAddressTransactionsWithPageSizeUsingGETCall(String publicKey, String pageSize, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/account/get/transactions/{publicKey}/{pageSize}"
            .replaceAll("\\{" + "publicKey" + "\\}", apiClient.escapeString(publicKey.toString()))
            .replaceAll("\\{" + "pageSize" + "\\}", apiClient.escapeString(pageSize.toString()));

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
     * Gets the all nem address transactions with page size using GET validate before call.
     *
     * @param publicKey the public key
     * @param pageSize the page size
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the all nem address transactions with page size using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAllNemAddressTransactionsWithPageSizeUsingGETValidateBeforeCall(String publicKey, String pageSize, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'publicKey' is set
        if (publicKey == null) {
            throw new ApiException("Missing the required parameter 'publicKey' when calling getAllNemAddressTransactionsWithPageSizeUsingGET(Async)");
        }
        
        // verify the required parameter 'pageSize' is set
        if (pageSize == null) {
            throw new ApiException("Missing the required parameter 'pageSize' when calling getAllNemAddressTransactionsWithPageSizeUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = getAllNemAddressTransactionsWithPageSizeUsingGETCall(publicKey, pageSize, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * getAllNemAddressTransactionsWithPageSize.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param pageSize Page Size (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String getAllNemAddressTransactionsWithPageSizeUsingGET(String publicKey, String pageSize) throws ApiException {
        ApiResponse<String> resp = getAllNemAddressTransactionsWithPageSizeUsingGETWithHttpInfo(publicKey, pageSize);
        return resp.getData();
    }

    /**
     * getAllNemAddressTransactionsWithPageSize.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param pageSize Page Size (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> getAllNemAddressTransactionsWithPageSizeUsingGETWithHttpInfo(String publicKey, String pageSize) throws ApiException {
        com.squareup.okhttp.Call call = getAllNemAddressTransactionsWithPageSizeUsingGETValidateBeforeCall(publicKey, pageSize, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * getAllNemAddressTransactionsWithPageSize (asynchronously).
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param pageSize Page Size (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAllNemAddressTransactionsWithPageSizeUsingGETAsync(String publicKey, String pageSize, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getAllNemAddressTransactionsWithPageSizeUsingGETValidateBeforeCall(publicKey, pageSize, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getAllOutgoingNemAddressTransactionsUsingGET.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAllOutgoingNemAddressTransactionsUsingGETCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/account/get/outgoing/transactions/{publicKey}"
            .replaceAll("\\{" + "publicKey" + "\\}", apiClient.escapeString(publicKey.toString()));

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
     * Gets the all outgoing nem address transactions using GET validate before call.
     *
     * @param publicKey the public key
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the all outgoing nem address transactions using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAllOutgoingNemAddressTransactionsUsingGETValidateBeforeCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'publicKey' is set
        if (publicKey == null) {
            throw new ApiException("Missing the required parameter 'publicKey' when calling getAllOutgoingNemAddressTransactionsUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = getAllOutgoingNemAddressTransactionsUsingGETCall(publicKey, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * getAllOutgoingNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String getAllOutgoingNemAddressTransactionsUsingGET(String publicKey) throws ApiException {
        ApiResponse<String> resp = getAllOutgoingNemAddressTransactionsUsingGETWithHttpInfo(publicKey);
        return resp.getData();
    }

    /**
     * getAllOutgoingNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> getAllOutgoingNemAddressTransactionsUsingGETWithHttpInfo(String publicKey) throws ApiException {
        com.squareup.okhttp.Call call = getAllOutgoingNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * getAllOutgoingNemAddressTransactions (asynchronously).
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAllOutgoingNemAddressTransactionsUsingGETAsync(String publicKey, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getAllOutgoingNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getAllUnconfirmedNemAddressTransactionsUsingGET.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getAllUnconfirmedNemAddressTransactionsUsingGETCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/account/get/unconfirmed/transactions/{publicKey}"
            .replaceAll("\\{" + "publicKey" + "\\}", apiClient.escapeString(publicKey.toString()));

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
     * Gets the all unconfirmed nem address transactions using GET validate before call.
     *
     * @param publicKey the public key
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the all unconfirmed nem address transactions using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getAllUnconfirmedNemAddressTransactionsUsingGETValidateBeforeCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'publicKey' is set
        if (publicKey == null) {
            throw new ApiException("Missing the required parameter 'publicKey' when calling getAllUnconfirmedNemAddressTransactionsUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = getAllUnconfirmedNemAddressTransactionsUsingGETCall(publicKey, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * getAllUnconfirmedNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String getAllUnconfirmedNemAddressTransactionsUsingGET(String publicKey) throws ApiException {
        ApiResponse<String> resp = getAllUnconfirmedNemAddressTransactionsUsingGETWithHttpInfo(publicKey);
        return resp.getData();
    }

    /**
     * getAllUnconfirmedNemAddressTransactions.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> getAllUnconfirmedNemAddressTransactionsUsingGETWithHttpInfo(String publicKey) throws ApiException {
        com.squareup.okhttp.Call call = getAllUnconfirmedNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * getAllUnconfirmedNemAddressTransactions (asynchronously).
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getAllUnconfirmedNemAddressTransactionsUsingGETAsync(String publicKey, final ApiCallback<String> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getAllUnconfirmedNemAddressTransactionsUsingGETValidateBeforeCall(publicKey, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for getNemAddressDetailsUsingGET.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getNemAddressDetailsUsingGETCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/account/get/info/{publicKey}"
            .replaceAll("\\{" + "publicKey" + "\\}", apiClient.escapeString(publicKey.toString()));

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
     * Gets the nem address details using GET validate before call.
     *
     * @param publicKey the public key
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the nem address details using GET validate before call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getNemAddressDetailsUsingGETValidateBeforeCall(String publicKey, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'publicKey' is set
        if (publicKey == null) {
            throw new ApiException("Missing the required parameter 'publicKey' when calling getNemAddressDetailsUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = getNemAddressDetailsUsingGETCall(publicKey, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Get the NEM Address Details
     * This endpoint returns the NEM Address/Account Information of a given address.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return AccountMetaDataPair
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public AccountMetaDataPair getNemAddressDetailsUsingGET(String publicKey) throws ApiException {
        ApiResponse<AccountMetaDataPair> resp = getNemAddressDetailsUsingGETWithHttpInfo(publicKey);
        return resp.getData();
    }

    /**
     * Get the NEM Address Details
     * This endpoint returns the NEM Address/Account Information of a given address.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @return ApiResponse&lt;AccountMetaDataPair&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<AccountMetaDataPair> getNemAddressDetailsUsingGETWithHttpInfo(String publicKey) throws ApiException {
        com.squareup.okhttp.Call call = getNemAddressDetailsUsingGETValidateBeforeCall(publicKey, null, null);
        Type localVarReturnType = new TypeToken<AccountMetaDataPair>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get the NEM Address Details (asynchronously)
     * This endpoint returns the NEM Address/Account Information of a given address.
     *
     * @param publicKey The NEM Account Public Key (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getNemAddressDetailsUsingGETAsync(String publicKey, final ApiCallback<AccountMetaDataPair> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = getNemAddressDetailsUsingGETValidateBeforeCall(publicKey, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<AccountMetaDataPair>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
