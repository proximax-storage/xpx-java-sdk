# DownloadApi

All URIs are relative to *http://localhost:8881/areyes1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**downloadPlainMessageFileUsingNemHashUsingGET**](DownloadApi.md#downloadPlainMessageFileUsingNemHashUsingGET) | **GET** /download/data/plain/{nemhash} | Download resource/file using NEM Transaction Hash
[**downloadRawBytesPlainMessageFileUsingNemHashUsingGET**](DownloadApi.md#downloadRawBytesPlainMessageFileUsingNemHashUsingGET) | **GET** /download/data/plain/rawbytes/{nemhash} | Download plain resource/file using NEM Transaction Hash
[**downloadRawBytesSecureMessageFileUsingNemHashUsingGET**](DownloadApi.md#downloadRawBytesSecureMessageFileUsingNemHashUsingGET) | **GET** /download/data/secure/rawbytes/{nemhash} | Download secured resource/file using NEM Transaction Hash
[**downloadRawBytesUsingHashUsingPOST**](DownloadApi.md#downloadRawBytesUsingHashUsingPOST) | **POST** /download/data/rawbytes | Download secured encrypted resource/file using Data Hash
[**downloadSecureMessageFileUsingNemHashUsingGET**](DownloadApi.md#downloadSecureMessageFileUsingNemHashUsingGET) | **GET** /download/data/secure/{nemhash} | Download resource/file using NEM Transaction Hash
[**downloadStreamPlainMessageFileUsingNemHashUsingGET**](DownloadApi.md#downloadStreamPlainMessageFileUsingNemHashUsingGET) | **GET** /download/data/plain/stream/{nemhash} | Download plain resource/file using NEM Transaction Hash
[**downloadStreamSecureMessageFileUsingNemHashUsingGET**](DownloadApi.md#downloadStreamSecureMessageFileUsingNemHashUsingGET) | **GET** /download/data/secure/stream/{nemhash} | Download secured resource/file using NEM Transaction Hash
[**downloadStreamUsingHashUsingPOST**](DownloadApi.md#downloadStreamUsingHashUsingPOST) | **POST** /download/data/stream | Download secured encrypted resource/file using Data Hash


<a name="downloadPlainMessageFileUsingNemHashUsingGET"></a>
# **downloadPlainMessageFileUsingNemHashUsingGET**
> ResponseEntity downloadPlainMessageFileUsingNemHashUsingGET(nemhash)

Download resource/file using NEM Transaction Hash

This endpoint returns either a byte array format of the actual file OR a JSON format GenericMessageResponse.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String nemhash = "nemhash_example"; // String | The NEM Transaction Hash
try {
    ResponseEntity result = apiInstance.downloadPlainMessageFileUsingNemHashUsingGET(nemhash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadPlainMessageFileUsingNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemhash** | **String**| The NEM Transaction Hash |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadRawBytesPlainMessageFileUsingNemHashUsingGET"></a>
# **downloadRawBytesPlainMessageFileUsingNemHashUsingGET**
> byte[] downloadRawBytesPlainMessageFileUsingNemHashUsingGET(nemhash)

Download plain resource/file using NEM Transaction Hash

This endpoint returns a byte array format of the actual file

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String nemhash = "nemhash_example"; // String | The NEM Transaction Hash
try {
    byte[] result = apiInstance.downloadRawBytesPlainMessageFileUsingNemHashUsingGET(nemhash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadRawBytesPlainMessageFileUsingNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemhash** | **String**| The NEM Transaction Hash |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadRawBytesSecureMessageFileUsingNemHashUsingGET"></a>
# **downloadRawBytesSecureMessageFileUsingNemHashUsingGET**
> byte[] downloadRawBytesSecureMessageFileUsingNemHashUsingGET(nemhash, xPvkey)

Download secured resource/file using NEM Transaction Hash

This endpoint returns a byte array format of the actual file

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String nemhash = "nemhash_example"; // String | The NEM Transaction Hash
String xPvkey = "xPvkey_example"; // String | The Sender or Receiver's Private Key
try {
    byte[] result = apiInstance.downloadRawBytesSecureMessageFileUsingNemHashUsingGET(nemhash, xPvkey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadRawBytesSecureMessageFileUsingNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemhash** | **String**| The NEM Transaction Hash |
 **xPvkey** | **String**| The Sender or Receiver&#39;s Private Key | [optional]

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadRawBytesUsingHashUsingPOST"></a>
# **downloadRawBytesUsingHashUsingPOST**
> byte[] downloadRawBytesUsingHashUsingPOST(hash)

Download secured encrypted resource/file using Data Hash

This endpoint returns a byte array format of the actual encrypted file

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String hash = "hash_example"; // String | The Data Hash
try {
    byte[] result = apiInstance.downloadRawBytesUsingHashUsingPOST(hash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadRawBytesUsingHashUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **hash** | **String**| The Data Hash |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadSecureMessageFileUsingNemHashUsingGET"></a>
# **downloadSecureMessageFileUsingNemHashUsingGET**
> ResponseEntity downloadSecureMessageFileUsingNemHashUsingGET(xPvkey, nemhash)

Download resource/file using NEM Transaction Hash

This endpoint returns either a byte array format of the actual file OR a JSON format GenericMessageResponse.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String xPvkey = "xPvkey_example"; // String | The Sender or Receiver's Private Key
String nemhash = "nemhash_example"; // String | The NEM Transaction Hash
try {
    ResponseEntity result = apiInstance.downloadSecureMessageFileUsingNemHashUsingGET(xPvkey, nemhash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadSecureMessageFileUsingNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| The Sender or Receiver&#39;s Private Key |
 **nemhash** | **String**| The NEM Transaction Hash |

### Return type

[**ResponseEntity**](ResponseEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadStreamPlainMessageFileUsingNemHashUsingGET"></a>
# **downloadStreamPlainMessageFileUsingNemHashUsingGET**
> byte[] downloadStreamPlainMessageFileUsingNemHashUsingGET(nemhash)

Download plain resource/file using NEM Transaction Hash

This endpoint returns a byte array format of the actual file

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String nemhash = "nemhash_example"; // String | The NEM Transaction Hash
try {
    byte[] result = apiInstance.downloadStreamPlainMessageFileUsingNemHashUsingGET(nemhash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadStreamPlainMessageFileUsingNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemhash** | **String**| The NEM Transaction Hash |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadStreamSecureMessageFileUsingNemHashUsingGET"></a>
# **downloadStreamSecureMessageFileUsingNemHashUsingGET**
> byte[] downloadStreamSecureMessageFileUsingNemHashUsingGET(nemhash, xPvkey)

Download secured resource/file using NEM Transaction Hash

This endpoint returns a byte array format of the actual file

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String nemhash = "nemhash_example"; // String | The NEM Transaction Hash
String xPvkey = "xPvkey_example"; // String | The Sender or Receiver's Private Key
try {
    byte[] result = apiInstance.downloadStreamSecureMessageFileUsingNemHashUsingGET(nemhash, xPvkey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadStreamSecureMessageFileUsingNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemhash** | **String**| The NEM Transaction Hash |
 **xPvkey** | **String**| The Sender or Receiver&#39;s Private Key | [optional]

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="downloadStreamUsingHashUsingPOST"></a>
# **downloadStreamUsingHashUsingPOST**
> byte[] downloadStreamUsingHashUsingPOST(hash)

Download secured encrypted resource/file using Data Hash

This endpoint returns a byte array format of the actual encrypted file

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DownloadApi;


DownloadApi apiInstance = new DownloadApi();
String hash = "hash_example"; // String | The Data Hash
try {
    byte[] result = apiInstance.downloadStreamUsingHashUsingPOST(hash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DownloadApi#downloadStreamUsingHashUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **hash** | **String**| The Data Hash |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

