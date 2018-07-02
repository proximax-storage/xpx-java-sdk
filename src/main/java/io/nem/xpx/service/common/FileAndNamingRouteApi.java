package io.nem.xpx.service.common;


import io.nem.ApiCallback;
import io.nem.ApiClient;
import io.nem.ApiResponse;
import io.nem.Pair;
import io.nem.ProgressRequestBody;
import io.nem.ProgressResponseBody;
import io.nem.xpx.exceptions.ApiException;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileAndNamingRouteApi {
    private ApiClient apiClient;


    public FileAndNamingRouteApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for directXipnsGetUsingGET
     * @param ipfsHash ipfsHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directXipnsGetUsingGETCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xipns/{ipfsHash}"
            .replaceAll("\\{" + "ipfsHash" + "\\}", apiClient.escapeString(ipfsHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directXipnsGetUsingGETValidateBeforeCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'ipfsHash' is set
        if (ipfsHash == null) {
            throw new ApiException("Missing the required parameter 'ipfsHash' when calling directXipnsGetUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directXipnsGetUsingGETCall(ipfsHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directXipnsGetUsingGET(String ipfsHash) throws ApiException {
        ApiResponse<byte[]> resp = directXipnsGetUsingGETWithHttpInfo(ipfsHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directXipnsGetUsingGETWithHttpInfo(String ipfsHash) throws ApiException {
        com.squareup.okhttp.Call call = directXipnsGetUsingGETValidateBeforeCall(ipfsHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directXipnsGetUsingGETAsync(String ipfsHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directXipnsGetUsingGETValidateBeforeCall(ipfsHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for directXipnsHeadUsingHEAD
     * @param ipfsHash ipfsHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directXipnsHeadUsingHEADCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xipns/{ipfsHash}"
            .replaceAll("\\{" + "ipfsHash" + "\\}", apiClient.escapeString(ipfsHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "HEAD", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directXipnsHeadUsingHEADValidateBeforeCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'ipfsHash' is set
        if (ipfsHash == null) {
            throw new ApiException("Missing the required parameter 'ipfsHash' when calling directXipnsHeadUsingHEAD(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directXipnsHeadUsingHEADCall(ipfsHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directXipnsHeadUsingHEAD(String ipfsHash) throws ApiException {
        ApiResponse<byte[]> resp = directXipnsHeadUsingHEADWithHttpInfo(ipfsHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directXipnsHeadUsingHEADWithHttpInfo(String ipfsHash) throws ApiException {
        com.squareup.okhttp.Call call = directXipnsHeadUsingHEADValidateBeforeCall(ipfsHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directXipnsHeadUsingHEADAsync(String ipfsHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directXipnsHeadUsingHEADValidateBeforeCall(ipfsHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for directXpxfsGetUsingGET
     * @param nemHash nemHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directXpxfsGetUsingGETCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxfs/{nemHash}"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directXpxfsGetUsingGETValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling directXpxfsGetUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directXpxfsGetUsingGETCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directXpxfsGetUsingGET(String nemHash) throws ApiException {
        ApiResponse<byte[]> resp = directXpxfsGetUsingGETWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directXpxfsGetUsingGETWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = directXpxfsGetUsingGETValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directXpxfsGetUsingGETAsync(String nemHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directXpxfsGetUsingGETValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for directXpxfsHeadUsingHEAD
     * @param nemHash nemHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directXpxfsHeadUsingHEADCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxfs/{nemHash}"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "HEAD", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directXpxfsHeadUsingHEADValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling directXpxfsHeadUsingHEAD(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directXpxfsHeadUsingHEADCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directXpxfsHeadUsingHEAD(String nemHash) throws ApiException {
        ApiResponse<byte[]> resp = directXpxfsHeadUsingHEADWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directXpxfsHeadUsingHEADWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = directXpxfsHeadUsingHEADValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directXpxfsHeadUsingHEADAsync(String nemHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directXpxfsHeadUsingHEADValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for directXpxnsGetUsingGET
     * @param nemHash nemHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directXpxnsGetUsingGETCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxns/{nemHash}"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directXpxnsGetUsingGETValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling directXpxnsGetUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directXpxnsGetUsingGETCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directXpxnsGetUsingGET(String nemHash) throws ApiException {
        ApiResponse<byte[]> resp = directXpxnsGetUsingGETWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directXpxnsGetUsingGETWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = directXpxnsGetUsingGETValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directXpxnsGetUsingGETAsync(String nemHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directXpxnsGetUsingGETValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for directXpxnsHeadUsingHEAD
     * @param nemHash nemHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directXpxnsHeadUsingHEADCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxns/{nemHash}"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "HEAD", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directXpxnsHeadUsingHEADValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling directXpxnsHeadUsingHEAD(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directXpxnsHeadUsingHEADCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directXpxnsHeadUsingHEAD(String nemHash) throws ApiException {
        ApiResponse<byte[]> resp = directXpxnsHeadUsingHEADWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directXpxnsHeadUsingHEADWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = directXpxnsHeadUsingHEADValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param nemHash nemHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directXpxnsHeadUsingHEADAsync(String nemHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directXpxnsHeadUsingHEADValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for directxIpfsGetUsingGET
     * @param ipfsHash ipfsHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directxIpfsGetUsingGETCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xipfs/{ipfsHash}"
            .replaceAll("\\{" + "ipfsHash" + "\\}", apiClient.escapeString(ipfsHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directxIpfsGetUsingGETValidateBeforeCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'ipfsHash' is set
        if (ipfsHash == null) {
            throw new ApiException("Missing the required parameter 'ipfsHash' when calling directxIpfsGetUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directxIpfsGetUsingGETCall(ipfsHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directxIpfsGetUsingGET(String ipfsHash) throws ApiException {
        ApiResponse<byte[]> resp = directxIpfsGetUsingGETWithHttpInfo(ipfsHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directxIpfsGetUsingGETWithHttpInfo(String ipfsHash) throws ApiException {
        com.squareup.okhttp.Call call = directxIpfsGetUsingGETValidateBeforeCall(ipfsHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directxIpfsGetUsingGETAsync(String ipfsHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directxIpfsGetUsingGETValidateBeforeCall(ipfsHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for directxIpfsHeadUsingHEAD
     * @param ipfsHash ipfsHash (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call directxIpfsHeadUsingHEADCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xipfs/{ipfsHash}"
            .replaceAll("\\{" + "ipfsHash" + "\\}", apiClient.escapeString(ipfsHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "HEAD", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call directxIpfsHeadUsingHEADValidateBeforeCall(String ipfsHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'ipfsHash' is set
        if (ipfsHash == null) {
            throw new ApiException("Missing the required parameter 'ipfsHash' when calling directxIpfsHeadUsingHEAD(Async)");
        }
        
        
        com.squareup.okhttp.Call call = directxIpfsHeadUsingHEADCall(ipfsHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return byte[]
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public byte[] directxIpfsHeadUsingHEAD(String ipfsHash) throws ApiException {
        ApiResponse<byte[]> resp = directxIpfsHeadUsingHEADWithHttpInfo(ipfsHash);
        return resp.getData();
    }

    /**
     * Download associated file of the given NEM Hash
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @return ApiResponse&lt;byte[]&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<byte[]> directxIpfsHeadUsingHEADWithHttpInfo(String ipfsHash) throws ApiException {
        com.squareup.okhttp.Call call = directxIpfsHeadUsingHEADValidateBeforeCall(ipfsHash, null, null);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Download associated file of the given NEM Hash (asynchronously)
     * Download associated file of the given NEM Hash (This is only applicable to public files only)
     * @param ipfsHash ipfsHash (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call directxIpfsHeadUsingHEADAsync(String ipfsHash, final ApiCallback<byte[]> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = directxIpfsHeadUsingHEADValidateBeforeCall(ipfsHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<byte[]>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for loadXpxfsDirectoryGetUsingGET
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call loadXpxfsDirectoryGetUsingGETCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxfs/{nemHash}/**"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call loadXpxfsDirectoryGetUsingGETValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling loadXpxfsDirectoryGetUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = loadXpxfsDirectoryGetUsingGETCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object loadXpxfsDirectoryGetUsingGET(String nemHash) throws ApiException {
        ApiResponse<Object> resp = loadXpxfsDirectoryGetUsingGETWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> loadXpxfsDirectoryGetUsingGETWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = loadXpxfsDirectoryGetUsingGETValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain (asynchronously)
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call loadXpxfsDirectoryGetUsingGETAsync(String nemHash, final ApiCallback<Object> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = loadXpxfsDirectoryGetUsingGETValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for loadXpxfsDirectoryHeadUsingHEAD
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call loadXpxfsDirectoryHeadUsingHEADCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxfs/{nemHash}/**"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "HEAD", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call loadXpxfsDirectoryHeadUsingHEADValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling loadXpxfsDirectoryHeadUsingHEAD(Async)");
        }
        
        
        com.squareup.okhttp.Call call = loadXpxfsDirectoryHeadUsingHEADCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object loadXpxfsDirectoryHeadUsingHEAD(String nemHash) throws ApiException {
        ApiResponse<Object> resp = loadXpxfsDirectoryHeadUsingHEADWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> loadXpxfsDirectoryHeadUsingHEADWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = loadXpxfsDirectoryHeadUsingHEADValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain (asynchronously)
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call loadXpxfsDirectoryHeadUsingHEADAsync(String nemHash, final ApiCallback<Object> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = loadXpxfsDirectoryHeadUsingHEADValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for loadXpxnsDirectoryGetUsingGET
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call loadXpxnsDirectoryGetUsingGETCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxns/{nemHash}/**"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call loadXpxnsDirectoryGetUsingGETValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling loadXpxnsDirectoryGetUsingGET(Async)");
        }
        
        
        com.squareup.okhttp.Call call = loadXpxnsDirectoryGetUsingGETCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object loadXpxnsDirectoryGetUsingGET(String nemHash) throws ApiException {
        ApiResponse<Object> resp = loadXpxnsDirectoryGetUsingGETWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> loadXpxnsDirectoryGetUsingGETWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = loadXpxnsDirectoryGetUsingGETValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain (asynchronously)
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call loadXpxnsDirectoryGetUsingGETAsync(String nemHash, final ApiCallback<Object> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = loadXpxnsDirectoryGetUsingGETValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for loadXpxnsDirectoryHeadUsingHEAD
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call loadXpxnsDirectoryHeadUsingHEADCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // create path and map variables
        String localVarPath = "/xpxns/{nemHash}/**"
            .replaceAll("\\{" + "nemHash" + "\\}", apiClient.escapeString(nemHash.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

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
        return apiClient.buildCall(localVarPath, "HEAD", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call loadXpxnsDirectoryHeadUsingHEADValidateBeforeCall(String nemHash, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'nemHash' is set
        if (nemHash == null) {
            throw new ApiException("Missing the required parameter 'nemHash' when calling loadXpxnsDirectoryHeadUsingHEAD(Async)");
        }
        
        
        com.squareup.okhttp.Call call = loadXpxnsDirectoryHeadUsingHEADCall(nemHash, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return Object
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Object loadXpxnsDirectoryHeadUsingHEAD(String nemHash) throws ApiException {
        ApiResponse<Object> resp = loadXpxnsDirectoryHeadUsingHEADWithHttpInfo(nemHash);
        return resp.getData();
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @return ApiResponse&lt;Object&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Object> loadXpxnsDirectoryHeadUsingHEADWithHttpInfo(String nemHash) throws ApiException {
        com.squareup.okhttp.Call call = loadXpxnsDirectoryHeadUsingHEADValidateBeforeCall(nemHash, null, null);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Loads a directory that&#39;s rooted from the NEM Blockchain (asynchronously)
     * Loads a Static Content.
     * @param nemHash NEM Txn (Public) linked to the directory (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call loadXpxnsDirectoryHeadUsingHEADAsync(String nemHash, final ApiCallback<Object> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = loadXpxnsDirectoryHeadUsingHEADValidateBeforeCall(nemHash, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Object>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
