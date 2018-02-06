# DataHashApi

All URIs are relative to *http://localhost:8881/areyes1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**generateHashAndExposeFileToNetworkUsingPOST**](DataHashApi.md#generateHashAndExposeFileToNetworkUsingPOST) | **POST** /datahash/upload/generate | Generates the encrypted datahash and uploads the file in the process.
[**generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST**](DataHashApi.md#generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST) | **POST** /datahash/upload/data/generate-sign | This endpoint can be used to generate the transaction along with the data hash with the private key signature.
[**generateHashExposeFileToNetworkBuildAndSignUsingPOST**](DataHashApi.md#generateHashExposeFileToNetworkBuildAndSignUsingPOST) | **POST** /datahash/upload/generate-sign | This endpoint can be used to generate the transaction along with the data hash with the private key signature.
[**uploadJsonDataAndGenerateHashUsingPOST**](DataHashApi.md#uploadJsonDataAndGenerateHashUsingPOST) | **POST** /datahash/upload/data/generate | Generates the encrypted datahash and uploads the JSON Format String data to the P2P Storage Network.


<a name="generateHashAndExposeFileToNetworkUsingPOST"></a>
# **generateHashAndExposeFileToNetworkUsingPOST**
> BinaryTransactionEncryptedMessage generateHashAndExposeFileToNetworkUsingPOST(file, keywords, metadata)

Generates the encrypted datahash and uploads the file in the process.

This endpoint can be used to generates the encrypted datahash and uploads the file in the process.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DataHashApi;


DataHashApi apiInstance = new DataHashApi();
File file = new File("/path/to/file.txt"); // File | The Multipart File that will be stored on the P2P Storage Network
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | JSON Format MetaData stored on the NEM Txn Message
try {
    BinaryTransactionEncryptedMessage result = apiInstance.generateHashAndExposeFileToNetworkUsingPOST(file, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataHashApi#generateHashAndExposeFileToNetworkUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **file** | **File**| The Multipart File that will be stored on the P2P Storage Network |
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| JSON Format MetaData stored on the NEM Txn Message | [optional]

### Return type

[**BinaryTransactionEncryptedMessage**](BinaryTransactionEncryptedMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST"></a>
# **generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST**
> RequestAnnounceDataSignature generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST(xPvkey, xPubkey, messageType, data, keywords, metadata)

This endpoint can be used to generate the transaction along with the data hash with the private key signature.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DataHashApi;


DataHashApi apiInstance = new DataHashApi();
String xPvkey = "xPvkey_example"; // String | Sender Private Key
String xPubkey = "xPubkey_example"; // String | Receiver Public Key
String messageType = "messageType_example"; // String | Message Type ( PLAIN or SECURE )
String data = "data_example"; // String | Free form string data that will be stored on the P2P Network
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Json Format Data Structure
try {
    RequestAnnounceDataSignature result = apiInstance.generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST(xPvkey, xPubkey, messageType, data, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataHashApi#generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| Sender Private Key | [optional]
 **xPubkey** | **String**| Receiver Public Key | [optional]
 **messageType** | **String**| Message Type ( PLAIN or SECURE ) | [optional]
 **data** | **String**| Free form string data that will be stored on the P2P Network | [optional]
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| Json Format Data Structure | [optional]

### Return type

[**RequestAnnounceDataSignature**](RequestAnnounceDataSignature.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="generateHashExposeFileToNetworkBuildAndSignUsingPOST"></a>
# **generateHashExposeFileToNetworkBuildAndSignUsingPOST**
> RequestAnnounceDataSignature generateHashExposeFileToNetworkBuildAndSignUsingPOST(xPvkey, xPubkey, messageType, file, keywords, metadata)

This endpoint can be used to generate the transaction along with the data hash with the private key signature.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DataHashApi;


DataHashApi apiInstance = new DataHashApi();
String xPvkey = "xPvkey_example"; // String | Sender Private Key
String xPubkey = "xPubkey_example"; // String | Receiver Public Key
String messageType = "messageType_example"; // String | Message Type ( PLAIN or SECURE )
File file = new File("/path/to/file.txt"); // File | The Multipart File
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | Json Format Data Structure
try {
    RequestAnnounceDataSignature result = apiInstance.generateHashExposeFileToNetworkBuildAndSignUsingPOST(xPvkey, xPubkey, messageType, file, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataHashApi#generateHashExposeFileToNetworkBuildAndSignUsingPOST");
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

[**RequestAnnounceDataSignature**](RequestAnnounceDataSignature.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="uploadJsonDataAndGenerateHashUsingPOST"></a>
# **uploadJsonDataAndGenerateHashUsingPOST**
> BinaryTransactionEncryptedMessage uploadJsonDataAndGenerateHashUsingPOST(data, keywords, metadata)

Generates the encrypted datahash and uploads the JSON Format String data to the P2P Storage Network.

This endpoint can be used to generates the encrypted datahash and uploads the file in the process.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.DataHashApi;


DataHashApi apiInstance = new DataHashApi();
String data = "data_example"; // String | Free form string data that will be stored on the P2P Network
String keywords = "keywords_example"; // String | Comma delimited Keyword/Tags
String metadata = "metadata_example"; // String | JSON Format MetaData stored on the NEM Txn Message
try {
    BinaryTransactionEncryptedMessage result = apiInstance.uploadJsonDataAndGenerateHashUsingPOST(data, keywords, metadata);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DataHashApi#uploadJsonDataAndGenerateHashUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | **String**| Free form string data that will be stored on the P2P Network |
 **keywords** | **String**| Comma delimited Keyword/Tags | [optional]
 **metadata** | **String**| JSON Format MetaData stored on the NEM Txn Message | [optional]

### Return type

[**BinaryTransactionEncryptedMessage**](BinaryTransactionEncryptedMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

