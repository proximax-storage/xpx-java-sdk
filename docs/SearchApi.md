# SearchApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**searchTransactionWithKeywordUsingGET**](SearchApi.md#searchTransactionWithKeywordUsingGET) | **GET** /search/by/keywords/{keywords} | Search through all the owners documents to find a content that matches the text specified.
[**searchTransactionWithMetadataUsingGET**](SearchApi.md#searchTransactionWithMetadataUsingGET) | **GET** /search/by/metadata/{text} | Search through all the owners documents to find a key that matches the specified parameter key


<a name="searchTransactionWithKeywordUsingGET"></a>
# **searchTransactionWithKeywordUsingGET**
> List&lt;ResourceHashMessageJsonEntity&gt; searchTransactionWithKeywordUsingGET(xPubkey, keywords)

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
    List<ResourceHashMessageJsonEntity> result = apiInstance.searchTransactionWithKeywordUsingGET(xPubkey, keywords);
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

[**List&lt;ResourceHashMessageJsonEntity&gt;**](ResourceHashMessageJsonEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="searchTransactionWithMetadataUsingGET"></a>
# **searchTransactionWithMetadataUsingGET**
> List&lt;ResourceHashMessageJsonEntity&gt; searchTransactionWithMetadataUsingGET(xPubkey, text)

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
    List<ResourceHashMessageJsonEntity> result = apiInstance.searchTransactionWithMetadataUsingGET(xPubkey, text);
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

[**List&lt;ResourceHashMessageJsonEntity&gt;**](ResourceHashMessageJsonEntity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

