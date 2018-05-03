# DataHashApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**generateHashForDataOnlyUsingPOST**](DataHashApi.md#generateHashForDataOnlyUsingPOST) | **POST** /datahash/hash-only | Generates the datahash but doesn&#39;t upload the file on the network


<a name="generateHashForDataOnlyUsingPOST"></a>
# **generateHashForDataOnlyUsingPOST**
> String generateHashForDataOnlyUsingPOST(data)

Generates the datahash but doesn&#39;t upload the file on the network

This endpoint can be used to generates the datahash and uploads the file in the process.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DataHashApi;


DataHashApi apiInstance = new DataHashApi();
byte[] data = data_example; // byte[] | Free form string data that will be stored on the P2P Network
try {
    String result = apiInstance.generateHashForDataOnlyUsingPOST(data);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataHashApi#generateHashForDataOnlyUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | **byte[]**| Free form string data that will be stored on the P2P Network |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

