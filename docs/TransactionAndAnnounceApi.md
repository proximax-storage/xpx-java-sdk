# TransactionAndAnnounceApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**announceRequestPublishDataSignatureUsingPOST**](TransactionAndAnnounceApi.md#announceRequestPublishDataSignatureUsingPOST) | **POST** /transaction/announce | Announce the DataHash to NEM/P2P Storage and P2P Database
[**getXPXTransactionUsingGET**](TransactionAndAnnounceApi.md#getXPXTransactionUsingGET) | **GET** /transaction/get/{nemHash} | Get the XPX Transaction Hash


<a name="announceRequestPublishDataSignatureUsingPOST"></a>
# **announceRequestPublishDataSignatureUsingPOST**
> String announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature)

Announce the DataHash to NEM/P2P Storage and P2P Database

Endpoint that can be use to announce the data hash transaction. This will grab the signed BinaryTransaferTransaction and create the P2P Database Entry for the specific data hash / transaction.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.TransactionAndAnnounceApi;


TransactionAndAnnounceApi apiInstance = new TransactionAndAnnounceApi();
RequestAnnounceDataSignature requestAnnounceDataSignature = new RequestAnnounceDataSignature(); // RequestAnnounceDataSignature | The Request Announce Data Signature Json Format
try {
    String result = apiInstance.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionAndAnnounceApi#announceRequestPublishDataSignatureUsingPOST");
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

<a name="getXPXTransactionUsingGET"></a>
# **getXPXTransactionUsingGET**
> String getXPXTransactionUsingGET(nemHash)

Get the XPX Transaction Hash

Endpoint can be used to get XPX Transaction.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.TransactionAndAnnounceApi;


TransactionAndAnnounceApi apiInstance = new TransactionAndAnnounceApi();
String nemHash = "nemHash_example"; // String | XPX Transaction Hash
try {
    String result = apiInstance.getXPXTransactionUsingGET(nemHash);
    ln(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TransactionAndAnnounceApi#getXPXTransactionUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **nemHash** | **String**| XPX Transaction Hash |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

