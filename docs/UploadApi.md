# UploadApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cleanupPinnedContentUsingPOST**](UploadApi.md#cleanupPinnedContentUsingPOST) | **POST** /upload/cleanup | Calls the garbage clean up and tries to unpin the given hash
[**uploadBase64StringBinaryUsingPOST**](UploadApi.md#uploadBase64StringBinaryUsingPOST) | **POST** /upload/base64/binary | Uploads a Base64 encoded String binary file to the IPFS/P2P Storage Network
[**uploadBytesBinaryUsingPOST**](UploadApi.md#uploadBytesBinaryUsingPOST) | **POST** /upload/bytes/binary | Uploads a Base64 encoded byte[] binary file to the IPFS/P2P Storage Network
[**uploadFileUsingPOST**](UploadApi.md#uploadFileUsingPOST) | **POST** /upload/file | Upload the File to the IPFS/P2P Storage Network
[**uploadGenerateAndSignUsingPOST**](UploadApi.md#uploadGenerateAndSignUsingPOST) | **POST** /upload/generate-sign | uploadGenerateAndSign
[**uploadPlainTextUsingPOST**](UploadApi.md#uploadPlainTextUsingPOST) | **POST** /upload/text | Upload the Text to the IPFS/P2P Storage Network


<a name="cleanupPinnedContentUsingPOST"></a>
# **cleanupPinnedContentUsingPOST**
> String cleanupPinnedContentUsingPOST(multihash)

Calls the garbage clean up and tries to unpin the given hash

This endpoint can be used to generates the datahash and uploads the file in the process.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
String multihash = "multihash_example"; // String | The pinned multihash
try {
    String result = apiInstance.cleanupPinnedContentUsingPOST(multihash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#cleanupPinnedContentUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **multihash** | **String**| The pinned multihash |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="uploadBase64StringBinaryUsingPOST"></a>
# **uploadBase64StringBinaryUsingPOST**
> Object uploadBase64StringBinaryUsingPOST(data, contentType, name, keywords, metadata)

Uploads a Base64 encoded String binary file to the IPFS/P2P Storage Network

This endpoint can be used to generate the data that will be injected to the NEM Blockchain.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
String data = "data_example"; // String | Base64 String representation of the data object to be uploaded
String contentType = "contentType_example"; // String | Content Type of the Data. If none is given, system will try to detect.
String name = "name_example"; // String | Custom Name of the data. If none is specified, timestamp will be used.
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Additional data in a JSON Format
try {
    Object result = apiInstance.uploadBase64StringBinaryUsingPOST(data, contentType, name, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadBase64StringBinaryUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | **String**| Base64 String representation of the data object to be uploaded |
 **contentType** | **String**| Content Type of the Data. If none is given, system will try to detect. | [optional]
 **name** | **String**| Custom Name of the data. If none is specified, timestamp will be used. | [optional]
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| Additional data in a JSON Format | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="uploadBytesBinaryUsingPOST"></a>
# **uploadBytesBinaryUsingPOST**
> Object uploadBytesBinaryUsingPOST(data, contentType, name, keywords, metadata)

Uploads a Base64 encoded byte[] binary file to the IPFS/P2P Storage Network

This endpoint can be used to generate the data that will be injected to the NEM Blockchain.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
byte[] data = data_example; // byte[] | Base64 byte[] representation of the data object to be uploaded
String contentType = "contentType_example"; // String | Content Type of the Data. If none is given, system will try to detect.
String name = "name_example"; // String | Custom Name of the data. If none is specified, timestamp will be used.
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Additional data in a JSON Format
try {
    Object result = apiInstance.uploadBytesBinaryUsingPOST(data, contentType, name, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadBytesBinaryUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | **byte[]**| Base64 byte[] representation of the data object to be uploaded |
 **contentType** | **String**| Content Type of the Data. If none is given, system will try to detect. | [optional]
 **name** | **String**| Custom Name of the data. If none is specified, timestamp will be used. | [optional]
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| Additional data in a JSON Format | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="uploadFileUsingPOST"></a>
# **uploadFileUsingPOST**
> Object uploadFileUsingPOST(file, name, keywords, metadata)

Upload the File to the IPFS/P2P Storage Network

This endpoint can be used to generate the data that will be injected to the NEM Blockchain.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
File file = new File("/path/to/file.txt"); // File | The Multipart File that will be stored on the P2P Storage Network
String name = "name_example"; // String | Custom Name of the data. If none is specified, timestamp will be used.
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Additional data in a JSON Format
try {
    Object result = apiInstance.uploadFileUsingPOST(file, name, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadFileUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**| The Multipart File that will be stored on the P2P Storage Network |
 **name** | **String**| Custom Name of the data. If none is specified, timestamp will be used. | [optional]
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| Additional data in a JSON Format | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*

<a name="uploadGenerateAndSignUsingPOST"></a>
# **uploadGenerateAndSignUsingPOST**
> String uploadGenerateAndSignUsingPOST(xPvkey, xPubkey, messageType, file, keywords, metadata)

uploadGenerateAndSign

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
String xPvkey = "xPvkey_example"; // String | Sender Private Key
String xPubkey = "xPubkey_example"; // String | Receiver Public Key
String messageType = "messageType_example"; // String | Message Type ( PLAIN or SECURE )
File file = new File("/path/to/file.txt"); // File | The Multipart File
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Json Format Data Structure
try {
    String result = apiInstance.uploadGenerateAndSignUsingPOST(xPvkey, xPubkey, messageType, file, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadGenerateAndSignUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| Sender Private Key | [optional]
 **xPubkey** | **String**| Receiver Public Key | [optional]
 **messageType** | **String**| Message Type ( PLAIN or SECURE ) | [optional]
 **file** | **File**| The Multipart File | [optional]
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| Json Format Data Structure | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="uploadPlainTextUsingPOST"></a>
# **uploadPlainTextUsingPOST**
> Object uploadPlainTextUsingPOST(text, name, encoding, keywords, metadata)

Upload the Text to the IPFS/P2P Storage Network

This endpoint can be used to generate the data that will be injected to the NEM Blockchain.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
String text = "text_example"; // String | A Free Form Text based data
String name = "name_example"; // String | Custom Name of the data. If none is specified, timestamp will be used.
String encoding = "encoding_example"; // String | Text Data Encoding. Default: UTF-8
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Additional data in a JSON Format
try {
    Object result = apiInstance.uploadPlainTextUsingPOST(text, name, encoding, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadPlainTextUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **text** | **String**| A Free Form Text based data |
 **name** | **String**| Custom Name of the data. If none is specified, timestamp will be used. | [optional]
 **encoding** | **String**| Text Data Encoding. Default: UTF-8 | [optional]
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| Additional data in a JSON Format | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: */*

