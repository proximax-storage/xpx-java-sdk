# DownloadApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**downloadBinaryUsingGET**](DownloadApi.md#downloadBinaryUsingGET) | **GET** /download/binary | Download a binary using NEM Transaction Hash
[**downloadSecureBinaryUsingGET**](DownloadApi.md#downloadSecureBinaryUsingGET) | **GET** /download/secure/binary | Download a secure resource/blob using NEM Private Key and Transaction Hash
[**downloadSecureFileUsingGET**](DownloadApi.md#downloadSecureFileUsingGET) | **GET** /download/secure/file | Download a secure resource/file using NEM Private Key and Transaction Hash
[**downloadTextUsingGET**](DownloadApi.md#downloadTextUsingGET) | **GET** /download/text | Download a base64 encoded plain text data using NEM Transaction Hash
[**downloadUsingDataHashUsingGET**](DownloadApi.md#downloadUsingDataHashUsingGET) | **GET** /download/direct/datahash | Download IPFS file associated to the datahash


<a name="downloadBinaryUsingGET"></a>
# **downloadBinaryUsingGET**
> byte[] downloadBinaryUsingGET(nemHash, transferMode)

Download a binary using NEM Transaction Hash

Download the binary file associated to a NEM Hash. If NEM Hash uses SECURE Message, it returns the NEM TXN Payload Instead

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String nemHash = "nemHash_example"; // String | The NEM Transaction Hash
String transferMode = "transferMode_example"; // String | Transfer Mode default: bytes (bytes,stream,base64)
try {
    byte[] result = apiInstance.downloadBinaryUsingGET(nemHash, transferMode);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadBinaryUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemHash** | **String**| The NEM Transaction Hash |
 **transferMode** | **String**| Transfer Mode default: bytes (bytes,stream,base64) | [enum: bytes, stream, base64]

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadSecureBinaryUsingGET"></a>
# **downloadSecureBinaryUsingGET**
> byte[] downloadSecureBinaryUsingGET(xPvkey, nemHash, transferType)

Download a secure resource/blob using NEM Private Key and Transaction Hash

Download a blob associated to a NEM Hash. If NEM Hash uses SECURE Message, it returns the NEM TXN Payload Instead

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String xPvkey = "xPvkey_example"; // String | The Sender or Receiver's Private Key
String nemHash = "nemHash_example"; // String | The NEM Transaction Hash
String transferType = "transferType_example"; // String | Transfer Type default: bytes (bytes,stream,base64)
try {
    byte[] result = apiInstance.downloadSecureBinaryUsingGET(xPvkey, nemHash, transferType);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadSecureBinaryUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| The Sender or Receiver&#39;s Private Key |
 **nemHash** | **String**| The NEM Transaction Hash |
 **transferType** | **String**| Transfer Type default: bytes (bytes,stream,base64) | [enum: bytes, stream, base64]

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadSecureFileUsingGET"></a>
# **downloadSecureFileUsingGET**
> byte[] downloadSecureFileUsingGET(xPvkey, nemHash, transferType)

Download a secure resource/file using NEM Private Key and Transaction Hash

Download a file associated to a NEM Hash. If NEM Hash uses SECURE Message, it returns the NEM TXN Payload Instead

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String xPvkey = "xPvkey_example"; // String | The Sender or Receiver's Private Key
String nemHash = "nemHash_example"; // String | The NEM Transaction Hash
String transferType = "transferType_example"; // String | Transfer Type default: bytes (bytes,stream,base64)
try {
    byte[] result = apiInstance.downloadSecureFileUsingGET(xPvkey, nemHash, transferType);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadSecureFileUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| The Sender or Receiver&#39;s Private Key |
 **nemHash** | **String**| The NEM Transaction Hash |
 **transferType** | **String**| Transfer Type default: bytes (bytes,stream,base64) | [enum: bytes, stream, base64]

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadTextUsingGET"></a>
# **downloadTextUsingGET**
> byte[] downloadTextUsingGET(nemHash, transferMode)

Download a base64 encoded plain text data using NEM Transaction Hash

Download a plain text data associated to a NEM Hash. If NEM Hash uses SECURE Message, it returns the NEM TXN Payload Instead

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String nemHash = "nemHash_example"; // String | The NEM Transaction Hash
String transferMode = "transferMode_example"; // String | Transfer Mode default: bytes (bytes,stream)
try {
    byte[] result = apiInstance.downloadTextUsingGET(nemHash, transferMode);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadTextUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemHash** | **String**| The NEM Transaction Hash |
 **transferMode** | **String**| Transfer Mode default: bytes (bytes,stream) | [enum: bytes, stream]

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadUsingDataHashUsingGET"></a>
# **downloadUsingDataHashUsingGET**
> byte[] downloadUsingDataHashUsingGET(dataHash)

Download IPFS file associated to the datahash

Download IPFS file associated to the datahash

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String dataHash = "dataHash_example"; // String | The NEM Transaction Hash
try {
    byte[] result = apiInstance.downloadUsingDataHashUsingGET(dataHash);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadUsingDataHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dataHash** | **String**| The NEM Transaction Hash |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

