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

import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.service.intf.SearchApi;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * The Class RemoteSearchApi.
 */
public class RemoteSearchApi implements SearchApi {
	
	/** The api client. */
	private ApiClient apiClient;

    /**
     * Instantiates a new remote search api.
     */
    public RemoteSearchApi() {
        this(Configuration.getDefaultApiClient());
    }

    /**
     * Instantiates a new remote search api.
     *
     * @param apiClient the api client
     */
    public RemoteSearchApi(ApiClient apiClient) {
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
     * Build call for searchTransactionWithKeywordUsingGET.
     *
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param keywords Comma delimited Keyword that will be match to the files available (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call searchTransactionWithKeywordUsingGETCall(String xPubkey, String keywords, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/search/by/keywords/{keywords}"
            .replaceAll("\\{" + "keywords" + "\\}", apiClient.escapeString(keywords.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xPubkey != null)
        localVarHeaderParams.put("x-pubkey", apiClient.parameterToString(xPubkey));

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
     * Search transaction with keyword using GET validate before call.
     *
     * @param xPubkey the x pubkey
     * @param keywords the keywords
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the com.squareup.okhttp. call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call searchTransactionWithKeywordUsingGETValidateBeforeCall(String xPubkey, String keywords, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'xPubkey' is set
        if (xPubkey == null) {
            throw new ApiException("Missing the required parameter 'xPubkey' when calling searchTransactionWithKeywordUsingGET(Async)");
        }
        
        // verify the required parameter 'keywords' is set
        if (keywords == null) {
            throw new ApiException("Missing the required parameter 'keywords' when calling searchTransactionWithKeywordUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = searchTransactionWithKeywordUsingGETCall(xPubkey, keywords, progressListener, progressRequestListener);
        return call;
        
    }

    /**
     * Search through all the owners documents to find a content that matches the text specified.
     * This endpoint can only be used to look up publicly available resources (PLAIN Message Types).
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param keywords Comma delimited Keyword that will be match to the files available (required)
     * @return List&lt;ResourceHashMessageJsonEntity&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<ResourceHashMessageJsonEntity> searchTransactionWithKeywordUsingGET(String xPubkey, String keywords) throws ApiException {
        ApiResponse<List<ResourceHashMessageJsonEntity>> resp = searchTransactionWithKeywordUsingGETWithHttpInfo(xPubkey, keywords);
        return resp.getData();
    }

    /**
     * Search through all the owners documents to find a content that matches the text specified.
     * This endpoint can only be used to look up publicly available resources (PLAIN Message Types).
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param keywords Comma delimited Keyword that will be match to the files available (required)
     * @return ApiResponse&lt;List&lt;ResourceHashMessageJsonEntity&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<ResourceHashMessageJsonEntity>> searchTransactionWithKeywordUsingGETWithHttpInfo(String xPubkey, String keywords) throws ApiException {
        com.squareup.okhttp.Call call = searchTransactionWithKeywordUsingGETValidateBeforeCall(xPubkey, keywords, null, null);
        Type localVarReturnType = new TypeToken<List<ResourceHashMessageJsonEntity>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Search through all the owners documents to find a content that matches the text specified. (asynchronously)
     * This endpoint can only be used to look up publicly available resources (PLAIN Message Types).
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param keywords Comma delimited Keyword that will be match to the files available (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call searchTransactionWithKeywordUsingGETAsync(String xPubkey, String keywords, final ApiCallback<List<ResourceHashMessageJsonEntity>> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = searchTransactionWithKeywordUsingGETValidateBeforeCall(xPubkey, keywords, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<ResourceHashMessageJsonEntity>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    
    /**
     * Build call for searchTransactionWithMetadataUsingGET.
     *
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param key Meta key (optional)
     * @param value Meta value (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call searchTransactionWithMetadataUsingGETCall(String xPubkey, String key, String value, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/search/by/metadata";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (key != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "key", key));
        if (value != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "value", value));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (xPubkey != null)
        localVarHeaderParams.put("x-pubkey", apiClient.parameterToString(xPubkey));

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
     * Search transaction with metadata using GET validate before call.
     *
     * @param xPubkey the x pubkey
     * @param key the key
     * @param value the value
     * @param progressListener the progress listener
     * @param progressRequestListener the progress request listener
     * @return the com.squareup.okhttp. call
     * @throws ApiException the api exception
     */
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call searchTransactionWithMetadataUsingGETValidateBeforeCall(String xPubkey, String key, String value, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'xPubkey' is set
        if (xPubkey == null) {
            throw new ApiException("Missing the required parameter 'xPubkey' when calling searchTransactionWithMetadataUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = searchTransactionWithMetadataUsingGETCall(xPubkey, key, value, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Search through all the owners documents to find a key that matches the specified parameter key
     * This endpoint can only be used to look up publicly available resources (PLAIN Message Types).
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param key Meta key (optional)
     * @param value Meta value (optional)
     * @return List&lt;ResourceHashMessageJsonEntity&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<ResourceHashMessageJsonEntity> searchTransactionWithMetadataUsingGET(String xPubkey, String key, String value) throws ApiException {
        ApiResponse<List<ResourceHashMessageJsonEntity>> resp = searchTransactionWithMetadataUsingGETWithHttpInfo(xPubkey, key, value);
        return resp.getData();
    }

    /**
     * Search through all the owners documents to find a key that matches the specified parameter key
     * This endpoint can only be used to look up publicly available resources (PLAIN Message Types).
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param key Meta key (optional)
     * @param value Meta value (optional)
     * @return ApiResponse&lt;List&lt;ResourceHashMessageJsonEntity&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<ResourceHashMessageJsonEntity>> searchTransactionWithMetadataUsingGETWithHttpInfo(String xPubkey, String key, String value) throws ApiException {
        com.squareup.okhttp.Call call = searchTransactionWithMetadataUsingGETValidateBeforeCall(xPubkey, key, value, null, null);
        Type localVarReturnType = new TypeToken<List<ResourceHashMessageJsonEntity>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Search through all the owners documents to find a key that matches the specified parameter key (asynchronously)
     * This endpoint can only be used to look up publicly available resources (PLAIN Message Types).
     * @param xPubkey The Sender or Receiver&#39;s Public Key (required)
     * @param key Meta key (optional)
     * @param value Meta value (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call searchTransactionWithMetadataUsingGETAsync(String xPubkey, String key, String value, final ApiCallback<List<ResourceHashMessageJsonEntity>> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = searchTransactionWithMetadataUsingGETValidateBeforeCall(xPubkey, key, value, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<ResourceHashMessageJsonEntity>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.SearchApi#searchTransactionWithKeywordUsingGET(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResourceHashMessageJsonEntity> searchTransactionWithKeywordUsingGET(String xPvKey, String xPubkey,
			String keywords) throws ApiException, InterruptedException, ExecutionException {
		throw new ApiException("Method can't be accessed thru remote connection");
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.SearchApi#searchAllPublicTransactionWithMetadataKeyValuePair(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResourceHashMessageJsonEntity> searchAllPublicTransactionWithMetadataKeyValuePair(String xPubkey,
			String key, String value) throws ApiException, InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.SearchApi#searchTransactionWithMetadataKeyValuePair(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResourceHashMessageJsonEntity> searchTransactionWithMetadataKeyValuePair(String xPvKey, String xPubkey,
			String key, String value) throws ApiException, InterruptedException, ExecutionException {
		throw new ApiException("Method can't be accessed thru remote connection");
	}
}
