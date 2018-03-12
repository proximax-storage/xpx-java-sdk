# PublishAndAnnounceApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**announceRequestPublishDataSignatureUsingPOST**](PublishAndAnnounceApi.md#announceRequestPublishDataSignatureUsingPOST) | **POST** /publish/announce | Announce the DataHash to NEM/P2P Storage and P2P Database
[**publishAndSendSingleFileToAddressUsingPOST**](PublishAndAnnounceApi.md#publishAndSendSingleFileToAddressUsingPOST) | **POST** /publish/single/to/{address} | Store a single file that can only be access by the given address
[**publishAndSendSingleFileToAddressesUsingPOST**](PublishAndAnnounceApi.md#publishAndSendSingleFileToAddressesUsingPOST) | **POST** /publish/single/to/addresses | Store a single file that can only be access by the given addresses


<a name="announceRequestPublishDataSignatureUsingPOST"></a>
# **announceRequestPublishDataSignatureUsingPOST**
> String announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature)

Announce the DataHash to NEM/P2P Storage and P2P Database

Endpoint that can be use to announce the data hash transaction. This will grab the signed BinaryTransaferTransaction and create the P2P Database Entry for the specific data hash / transaction.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.PublishAndAnnounceApi;


PublishAndAnnounceApi apiInstance = new PublishAndAnnounceApi();
RequestAnnounceDataSignature requestAnnounceDataSignature = new RequestAnnounceDataSignature(); // RequestAnnounceDataSignature | The Request Announce Data Signature Json Format
try {
    String result = apiInstance.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PublishAndAnnounceApi#announceRequestPublishDataSignatureUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestAnnounceDataSignature** | [**RequestAnnounceDataSignature**](RequestAnnounceDataSignature.md)| The Request Announce Data Signature Json Format | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="publishAndSendSingleFileToAddressUsingPOST"></a>
# **publishAndSendSingleFileToAddressUsingPOST**
> String publishAndSendSingleFileToAddressUsingPOST(xPvkey, address, messageType, file)

Store a single file that can only be access by the given address

This endpoint can be used to share a file to a specific address only.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.PublishAndAnnounceApi;


PublishAndAnnounceApi apiInstance = new PublishAndAnnounceApi();
String xPvkey = "xPvkey_example"; // String | The Sender's Private Key
String address = "address_example"; // String | The Receiver's Address without dash ('-')
String messageType = "messageType_example"; // String | Message Type ( PLAIN or SECURE )
File file = new File("/path/to/file.txt"); // File | The Multipart File
try {
    String result = apiInstance.publishAndSendSingleFileToAddressUsingPOST(xPvkey, address, messageType, file);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PublishAndAnnounceApi#publishAndSendSingleFileToAddressUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| The Sender&#39;s Private Key |
 **address** | **String**| The Receiver&#39;s Address without dash (&#39;-&#39;) |
 **messageType** | **String**| Message Type ( PLAIN or SECURE ) |
 **file** | **File**| The Multipart File |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="publishAndSendSingleFileToAddressesUsingPOST"></a>
# **publishAndSendSingleFileToAddressesUsingPOST**
> String publishAndSendSingleFileToAddressesUsingPOST(xPvkey, addresses, messageType, file)

Store a single file that can only be access by the given addresses

This endpoint can be used to exclusively share files across a set of given addresses. This means that the file that&#39;s published here can only be viewed or downloaded by the given addresses including the uploader.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.PublishAndAnnounceApi;


PublishAndAnnounceApi apiInstance = new PublishAndAnnounceApi();
String xPvkey = "xPvkey_example"; // String | The Sender's Private Key
List<String> addresses = Arrays.asList("addresses_example"); // List<String> | The List of receiving Addresses without dash ('-')
String messageType = "messageType_example"; // String | Message Type ( PLAIN or SECURE )
File file = new File("/path/to/file.txt"); // File | The Multipart File
try {
    String result = apiInstance.publishAndSendSingleFileToAddressesUsingPOST(xPvkey, addresses, messageType, file);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PublishAndAnnounceApi#publishAndSendSingleFileToAddressesUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| The Sender&#39;s Private Key | [optional]
 **addresses** | [**List&lt;String&gt;**](String.md)| The List of receiving Addresses without dash (&#39;-&#39;) | [optional]
 **messageType** | **String**| Message Type ( PLAIN or SECURE ) | [optional]
 **file** | **File**| The Multipart File | [optional]

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

