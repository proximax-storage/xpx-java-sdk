# PublishAndSubscribeApi

All URIs are relative to *http://localhost:8881*

Method | HTTP request | Description
------------- | ------------- | -------------
[**publishTopicUsingGET**](PublishAndSubscribeApi.md#publishTopicUsingGET) | **GET** /pubsub/init/{topic} | Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.
[**sendToTopicUsingGET**](PublishAndSubscribeApi.md#sendToTopicUsingGET) | **GET** /pubsub/send/to/{topic} | Send a message to a published topic


<a name="publishTopicUsingGET"></a>
# **publishTopicUsingGET**
> Object publishTopicUsingGET(topic, message)

Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.

Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.PublishAndSubscribeApi;


PublishAndSubscribeApi apiInstance = new PublishAndSubscribeApi();
String topic = "topic_example"; // String | Topic
String message = "message_example"; // String | Initial Message
try {
    Object result = apiInstance.publishTopicUsingGET(topic, message);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PublishAndSubscribeApi#publishTopicUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **topic** | **String**| Topic |
 **message** | **String**| Initial Message | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="sendToTopicUsingGET"></a>
# **sendToTopicUsingGET**
> Object sendToTopicUsingGET(topic, message)

Send a message to a published topic

Publish and Subscribe. Make sure that the IPFS daemon has pubsub enabled.

### Example
```java
// Import classes:
//import io.nem.ApiException;
//import io.nem.xpx.PublishAndSubscribeApi;


PublishAndSubscribeApi apiInstance = new PublishAndSubscribeApi();
String topic = "topic_example"; // String | Topic
String message = "message_example"; // String | Initial Message
try {
    Object result = apiInstance.sendToTopicUsingGET(topic, message);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PublishAndSubscribeApi#sendToTopicUsingGET");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **topic** | **String**| Topic |
 **message** | **String**| Initial Message | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

