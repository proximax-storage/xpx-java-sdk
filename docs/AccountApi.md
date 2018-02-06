# AccountApi

All URIs are relative to *http://localhost:8881/areyes1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAllIncomingNemAddressTransactionsUsingGET**](AccountApi.md#getAllIncomingNemAddressTransactionsUsingGET) | **GET** /account/get/all-incoming-transactions/{publicKey} | getAllIncomingNemAddressTransactions
[**getAllNemAddressTransactionsUsingGET**](AccountApi.md#getAllNemAddressTransactionsUsingGET) | **GET** /account/get/all-transactions/{publicKey} | getAllNemAddressTransactions
[**getAllNemAddressTransactionsWithPageSizeUsingGET**](AccountApi.md#getAllNemAddressTransactionsWithPageSizeUsingGET) | **GET** /account/get/all-transactions/{publicKey}/{pageSize} | getAllNemAddressTransactionsWithPageSize
[**getAllOutgoingNemAddressTransactionsUsingGET**](AccountApi.md#getAllOutgoingNemAddressTransactionsUsingGET) | **GET** /account/get/all-outgoing-transactions/{publicKey} | getAllOutgoingNemAddressTransactions
[**getAllUnconfirmedNemAddressTransactionsUsingGET**](AccountApi.md#getAllUnconfirmedNemAddressTransactionsUsingGET) | **GET** /account/get/all-unconfirmed-transactions/{publicKey} | getAllUnconfirmedNemAddressTransactions
[**getNemAddressDetailsUsingGET**](AccountApi.md#getNemAddressDetailsUsingGET) | **GET** /account/get/info/{publicKey} | Get the NEM Address Details


<a name="getAllIncomingNemAddressTransactionsUsingGET"></a>
# **getAllIncomingNemAddressTransactionsUsingGET**
> String getAllIncomingNemAddressTransactionsUsingGET(publicKey)

getAllIncomingNemAddressTransactions

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.AccountApi;


AccountApi apiInstance = new AccountApi();
String publicKey = "publicKey_example"; // String | The NEM Account Public Key
try {
    String result = apiInstance.getAllIncomingNemAddressTransactionsUsingGET(publicKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#getAllIncomingNemAddressTransactionsUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **publicKey** | **String**| The NEM Account Public Key |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllNemAddressTransactionsUsingGET"></a>
# **getAllNemAddressTransactionsUsingGET**
> String getAllNemAddressTransactionsUsingGET(publicKey)

getAllNemAddressTransactions

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.AccountApi;


AccountApi apiInstance = new AccountApi();
String publicKey = "publicKey_example"; // String | The NEM Account Public Key
try {
    String result = apiInstance.getAllNemAddressTransactionsUsingGET(publicKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#getAllNemAddressTransactionsUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **publicKey** | **String**| The NEM Account Public Key |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllNemAddressTransactionsWithPageSizeUsingGET"></a>
# **getAllNemAddressTransactionsWithPageSizeUsingGET**
> String getAllNemAddressTransactionsWithPageSizeUsingGET(publicKey, pageSize)

getAllNemAddressTransactionsWithPageSize

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.AccountApi;


AccountApi apiInstance = new AccountApi();
String publicKey = "publicKey_example"; // String | The NEM Account Public Key
String pageSize = "pageSize_example"; // String | Page Size
try {
    String result = apiInstance.getAllNemAddressTransactionsWithPageSizeUsingGET(publicKey, pageSize);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#getAllNemAddressTransactionsWithPageSizeUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **publicKey** | **String**| The NEM Account Public Key |
 **pageSize** | **String**| Page Size |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllOutgoingNemAddressTransactionsUsingGET"></a>
# **getAllOutgoingNemAddressTransactionsUsingGET**
> String getAllOutgoingNemAddressTransactionsUsingGET(publicKey)

getAllOutgoingNemAddressTransactions

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.AccountApi;


AccountApi apiInstance = new AccountApi();
String publicKey = "publicKey_example"; // String | The NEM Account Public Key
try {
    String result = apiInstance.getAllOutgoingNemAddressTransactionsUsingGET(publicKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#getAllOutgoingNemAddressTransactionsUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **publicKey** | **String**| The NEM Account Public Key |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getAllUnconfirmedNemAddressTransactionsUsingGET"></a>
# **getAllUnconfirmedNemAddressTransactionsUsingGET**
> String getAllUnconfirmedNemAddressTransactionsUsingGET(publicKey)

getAllUnconfirmedNemAddressTransactions

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.AccountApi;


AccountApi apiInstance = new AccountApi();
String publicKey = "publicKey_example"; // String | The NEM Account Public Key
try {
    String result = apiInstance.getAllUnconfirmedNemAddressTransactionsUsingGET(publicKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#getAllUnconfirmedNemAddressTransactionsUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **publicKey** | **String**| The NEM Account Public Key |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getNemAddressDetailsUsingGET"></a>
# **getNemAddressDetailsUsingGET**
> AccountMetaDataPair getNemAddressDetailsUsingGET(publicKey)

Get the NEM Address Details

This endpoint returns the NEM Address/Account Information of a given address

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.AccountApi;


AccountApi apiInstance = new AccountApi();
String publicKey = "publicKey_example"; // String | The NEM Account Public Key
try {
    AccountMetaDataPair result = apiInstance.getNemAddressDetailsUsingGET(publicKey);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AccountApi#getNemAddressDetailsUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **publicKey** | **String**| The NEM Account Public Key |

### Return type

[**AccountMetaDataPair**](AccountMetaDataPair.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

