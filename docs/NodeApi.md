# NodeApi

All URIs are relative to *http://localhost:8881/areyes1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**checkNodeUsingGET**](NodeApi.md#checkNodeUsingGET) | **GET** /node/check | Check if the Storage Node is up and running.
[**getNodeInfoUsingGET**](NodeApi.md#getNodeInfoUsingGET) | **GET** /node/info | Get Storage Node Information
[**setBlockchainNodeConnectionUsingPOST**](NodeApi.md#setBlockchainNodeConnectionUsingPOST) | **POST** /node/set/blockchain/connection | Get Storage Node Information


<a name="checkNodeUsingGET"></a>
# **checkNodeUsingGET**
> GenericResponseMessage checkNodeUsingGET()

Check if the Storage Node is up and running.

This endpoint is used to check if the P2P Storage Node instance is either alive or down.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.NodeApi;


NodeApi apiInstance = new NodeApi();
try {
    GenericResponseMessage result = apiInstance.checkNodeUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NodeApi#checkNodeUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**GenericResponseMessage**](GenericResponseMessage.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getNodeInfoUsingGET"></a>
# **getNodeInfoUsingGET**
> NodeInfo getNodeInfoUsingGET()

Get Storage Node Information

This endpoint returns the information of the P2P Storage Node

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.NodeApi;


NodeApi apiInstance = new NodeApi();
try {
    NodeInfo result = apiInstance.getNodeInfoUsingGET();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NodeApi#getNodeInfoUsingGET");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**NodeInfo**](NodeInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="setBlockchainNodeConnectionUsingPOST"></a>
# **setBlockchainNodeConnectionUsingPOST**
> String setBlockchainNodeConnectionUsingPOST(network, domain, port)

Get Storage Node Information

This endpoint returns the information of the P2P Storage Node

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.NodeApi;


NodeApi apiInstance = new NodeApi();
String network = "network_example"; // String | Blockchain Network
String domain = "domain_example"; // String | Blockchain Network Domain (xxx.xxx.xxx)
String port = "port_example"; // String | Blockchain Network Port (xxx.xxx.xxx)
try {
    String result = apiInstance.setBlockchainNodeConnectionUsingPOST(network, domain, port);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling NodeApi#setBlockchainNodeConnectionUsingPOST");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **network** | **String**| Blockchain Network |
 **domain** | **String**| Blockchain Network Domain (xxx.xxx.xxx) |
 **port** | **String**| Blockchain Network Port (xxx.xxx.xxx) |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

