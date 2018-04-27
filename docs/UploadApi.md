# UploadApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cleanupPinnedContentUsingPOST**](UploadApi.md#cleanupPinnedContentUsingPOST) | **POST** /upload/cleanup | Calls the garbage clean up and tries to unpin the given hash
[**directoryExtractUsingPOST**](UploadApi.md#directoryExtractUsingPOST) | **POST** /upload/dir/extract | Grabs a zip file with static content, extract and load directory to the IPFS/P2P Network
[**uploadBase64StringBinaryUsingPOST**](UploadApi.md#uploadBase64StringBinaryUsingPOST) | **POST** /upload/base64/binary | Uploads a Base64 encoded String binary file to the IPFS/P2P Storage Network
[**uploadBytesBinaryUsingPOST**](UploadApi.md#uploadBytesBinaryUsingPOST) | **POST** /upload/bytes/binary | Uploads a Base64 encoded byte[] binary file to the IPFS/P2P Storage Network
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

<a name="directoryExtractUsingPOST"></a>
# **directoryExtractUsingPOST**
> String directoryExtractUsingPOST(file, name, keywords, metadata)

Grabs a zip file with static content, extract and load directory to the IPFS/P2P Network

Generates the Root hash of your directory.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
File file = new File("/path/to/file.txt"); // File | Base64 byte[] representation of the data object to be uploaded
String name = "name_example"; // String | Custom Name of the data. If none is specified, timestamp will be used.
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Additional data in a JSON Format
try {
    String result = apiInstance.directoryExtractUsingPOST(file, name, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#directoryExtractUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**| Base64 byte[] representation of the data object to be uploaded |
 **name** | **String**| Custom Name of the data. If none is specified, timestamp will be used. | [optional]
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| Additional data in a JSON Format | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="uploadBase64StringBinaryUsingPOST"></a>
# **uploadBase64StringBinaryUsingPOST**
> Object uploadBase64StringBinaryUsingPOST(uploadBase64BinaryRequestParameter)

Uploads a Base64 encoded String binary file to the IPFS/P2P Storage Network

This endpoint can be used to generate the data that will be injected to the NEM Blockchain.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
UploadBase64BinaryRequestParameter uploadBase64BinaryRequestParameter = new UploadBase64BinaryRequestParameter(); // UploadBase64BinaryRequestParameter | Base64 String representation of the data object to be uploaded
try {
    Object result = apiInstance.uploadBase64StringBinaryUsingPOST(uploadBase64BinaryRequestParameter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadBase64StringBinaryUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadBase64BinaryRequestParameter** | [**UploadBase64BinaryRequestParameter**](UploadBase64BinaryRequestParameter.md)| Base64 String representation of the data object to be uploaded |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="uploadBytesBinaryUsingPOST"></a>
# **uploadBytesBinaryUsingPOST**
> Object uploadBytesBinaryUsingPOST(uploadBytesBinaryRequestParameter)

Uploads a Base64 encoded byte[] binary file to the IPFS/P2P Storage Network

This endpoint can be used to generate the data that will be injected to the NEM Blockchain.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
UploadBytesBinaryRequestParameter uploadBytesBinaryRequestParameter = new UploadBytesBinaryRequestParameter(); // UploadBytesBinaryRequestParameter | Base64 byte[] representation of the data object to be uploaded
try {
    Object result = apiInstance.uploadBytesBinaryUsingPOST(uploadBytesBinaryRequestParameter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadBytesBinaryUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadBytesBinaryRequestParameter** | [**UploadBytesBinaryRequestParameter**](UploadBytesBinaryRequestParameter.md)| Base64 byte[] representation of the data object to be uploaded |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
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
> Object uploadPlainTextUsingPOST(uploadTextParameter)

Upload the Text to the IPFS/P2P Storage Network

This endpoint can be used to generate the data that will be injected to the NEM Blockchain.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.UploadApi;


UploadApi apiInstance = new UploadApi();
UploadTextRequestParameter uploadTextParameter = new UploadTextRequestParameter(); // UploadTextRequestParameter | A Plain Text
try {
    Object result = apiInstance.uploadPlainTextUsingPOST(uploadTextParameter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UploadApi#uploadPlainTextUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uploadTextParameter** | [**UploadTextRequestParameter**](UploadTextRequestParameter.md)| A Plain Text |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

