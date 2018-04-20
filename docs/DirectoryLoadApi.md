# DirectoryLoadApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**loadDirectoryUsingGET**](DirectoryLoadApi.md#loadDirectoryUsingGET) | **GET** /directory/load/{nemHash}/** | Loads a Static Content.


<a name="loadDirectoryUsingGET"></a>
# **loadDirectoryUsingGET**
> Object loadDirectoryUsingGET(nemHash)

Loads a Static Content.

Loads a Static Content.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DirectoryLoadApi;


DirectoryLoadApi apiInstance = new DirectoryLoadApi();
String nemHash = "nemHash_example"; // String | NEM Txn (Public) linked to the directory
try {
    Object result = apiInstance.loadDirectoryUsingGET(nemHash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DirectoryLoadApi#loadDirectoryUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemHash** | **String**| NEM Txn (Public) linked to the directory |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

