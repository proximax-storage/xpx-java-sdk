# SearchApi

All URIs are relative to *http://localhost:8881/areyes1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**searchContentUsingAllNemHashUsingGET**](SearchApi.md#searchContentUsingAllNemHashUsingGET) | **GET** /search/all/content/hash/{nemHash} | Search through all the owner&#39;s documents to find a content that matches the text specified.
[**searchContentUsingPublicNemHashUsingGET**](SearchApi.md#searchContentUsingPublicNemHashUsingGET) | **GET** /search/public/content/hash/{nemHash} | Search through all the owner&#39;s documents to find a content that matches the text specified.
[**searchContentUsingTextUsingGET**](SearchApi.md#searchContentUsingTextUsingGET) | **GET** /search/public/content/{text} | Search through all the owner&#39;s documents to find a content that matches the text specified.
[**searchTransactionPvKeyWithKeywordUsingGET**](SearchApi.md#searchTransactionPvKeyWithKeywordUsingGET) | **GET** /search/all/content/keyword/{keywords} | Search through all the owners documents to find a content that matches the text specified.
[**searchTransactionWithKeywordUsingGET**](SearchApi.md#searchTransactionWithKeywordUsingGET) | **GET** /search/public/content/keyword/{keywords} | Search through all the owners documents to find a content that matches the text specified.
[**searchTransactionWithMetadataUsingGET**](SearchApi.md#searchTransactionWithMetadataUsingGET) | **GET** /search/public/content/metadata/{text} | Search through all the owners documents to find a key that matches the specified parameter key


<a name="searchContentUsingAllNemHashUsingGET"></a>
# **searchContentUsingAllNemHashUsingGET**
> String searchContentUsingAllNemHashUsingGET(xPvkey, nemHash)

Search through all the owner&#39;s documents to find a content that matches the text specified.

This endpoint can only be used to look up publicly available resources (PLAIN and SECURE Message Types).

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.SearchApi;


SearchApi apiInstance = new SearchApi();
String xPvkey = "xPvkey_example"; // String | The Sender or Receiver's Public Key
String nemHash = "nemHash_example"; // String | NEM Hash that will be matched to the files available
try {
    String result = apiInstance.searchContentUsingAllNemHashUsingGET(xPvkey, nemHash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#searchContentUsingAllNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| The Sender or Receiver&#39;s Public Key |
 **nemHash** | **String**| NEM Hash that will be matched to the files available |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="searchContentUsingPublicNemHashUsingGET"></a>
# **searchContentUsingPublicNemHashUsingGET**
> String searchContentUsingPublicNemHashUsingGET(xPubkey, nemHash)

Search through all the owner&#39;s documents to find a content that matches the text specified.

This endpoint can only be used to look up publicly available resources (PLAIN Message Types).

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.SearchApi;


SearchApi apiInstance = new SearchApi();
String xPubkey = "xPubkey_example"; // String | The Sender or Receiver's Public Key
String nemHash = "nemHash_example"; // String | NEM Hash that will be matched to the files available
try {
    String result = apiInstance.searchContentUsingPublicNemHashUsingGET(xPubkey, nemHash);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#searchContentUsingPublicNemHashUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPubkey** | **String**| The Sender or Receiver&#39;s Public Key |
 **nemHash** | **String**| NEM Hash that will be matched to the files available |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="searchContentUsingTextUsingGET"></a>
# **searchContentUsingTextUsingGET**
> String searchContentUsingTextUsingGET(xPubkey, text)

Search through all the owner&#39;s documents to find a content that matches the text specified.

This endpoint can only be used to look up publicly available resources (PLAIN Message Types).

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.SearchApi;


SearchApi apiInstance = new SearchApi();
String xPubkey = "xPubkey_example"; // String | The Sender or Receiver's Public Key
String text = "text_example"; // String | Text or Keyword that will be match to the files available
try {
    String result = apiInstance.searchContentUsingTextUsingGET(xPubkey, text);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#searchContentUsingTextUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPubkey** | **String**| The Sender or Receiver&#39;s Public Key |
 **text** | **String**| Text or Keyword that will be match to the files available |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="searchTransactionPvKeyWithKeywordUsingGET"></a>
# **searchTransactionPvKeyWithKeywordUsingGET**
> List&lt;BinaryTransactionEncryptedMessage&gt; searchTransactionPvKeyWithKeywordUsingGET(xPvkey, keywords)

Search through all the owners documents to find a content that matches the text specified.

This endpoint can only be used to look up publicly available resources (PLAIN and SECURE Message Types).

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.SearchApi;


SearchApi apiInstance = new SearchApi();
String xPvkey = "xPvkey_example"; // String | The Sender or Receiver's Private Key
String keywords = "keywords_example"; // String | Comma delimited Keyword that will be match to the files available
try {
    List<BinaryTransactionEncryptedMessage> result = apiInstance.searchTransactionPvKeyWithKeywordUsingGET(xPvkey, keywords);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#searchTransactionPvKeyWithKeywordUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPvkey** | **String**| The Sender or Receiver&#39;s Private Key |
 **keywords** | **String**| Comma delimited Keyword that will be match to the files available |

### Return type

[**List&lt;BinaryTransactionEncryptedMessage&gt;**](BinaryTransactionEncryptedMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="searchTransactionWithKeywordUsingGET"></a>
# **searchTransactionWithKeywordUsingGET**
> List&lt;BinaryTransactionEncryptedMessage&gt; searchTransactionWithKeywordUsingGET(xPubkey, keywords)

Search through all the owners documents to find a content that matches the text specified.

This endpoint can only be used to look up publicly available resources (PLAIN Message Types).

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.SearchApi;


SearchApi apiInstance = new SearchApi();
String xPubkey = "xPubkey_example"; // String | The Sender or Receiver's Public Key
String keywords = "keywords_example"; // String | Comma delimited Keyword that will be match to the files available
try {
    List<BinaryTransactionEncryptedMessage> result = apiInstance.searchTransactionWithKeywordUsingGET(xPubkey, keywords);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#searchTransactionWithKeywordUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPubkey** | **String**| The Sender or Receiver&#39;s Public Key |
 **keywords** | **String**| Comma delimited Keyword that will be match to the files available |

### Return type

[**List&lt;BinaryTransactionEncryptedMessage&gt;**](BinaryTransactionEncryptedMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="searchTransactionWithMetadataUsingGET"></a>
# **searchTransactionWithMetadataUsingGET**
> List&lt;BinaryTransactionEncryptedMessage&gt; searchTransactionWithMetadataUsingGET(xPubkey, text)

Search through all the owners documents to find a key that matches the specified parameter key

This endpoint can only be used to look up publicly available resources (PLAIN Message Types).

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.SearchApi;


SearchApi apiInstance = new SearchApi();
String xPubkey = "xPubkey_example"; // String | The Sender or Receiver's Public Key
String text = "text_example"; // String | Index-based searching on metadata
try {
    List<BinaryTransactionEncryptedMessage> result = apiInstance.searchTransactionWithMetadataUsingGET(xPubkey, text);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SearchApi#searchTransactionWithMetadataUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xPubkey** | **String**| The Sender or Receiver&#39;s Public Key |
 **text** | **String**| Index-based searching on metadata |

### Return type

[**List&lt;BinaryTransactionEncryptedMessage&gt;**](BinaryTransactionEncryptedMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

