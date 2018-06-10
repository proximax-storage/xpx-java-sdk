# Official Proximax P2P Storage Java SDK
![banner](https://proximax.io/wp-content/uploads/2018/03/ProximaX-logotype.png)
[![Build Status](https://travis-ci.com/proximax-storage/xpx-java-sdk.svg?token=eGkpLEqVyC41fTyC53bq&branch=master)](https://travis-ci.com/proximax-storage/xpx-java-sdk)

## Requirements
Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

### Maven users
Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.nem.xpx</groupId>
    <artifactId>xpx-java-sdk</artifactId>
    <version>0.0.1-BETA</version>
</dependency>
```

### Gradle users
Add this dependency to your project's build file:

```groovy
compile "io.nem.xpx:xpx-java-sdk:0.0.1-BETA"
```

## Getting Started
Make sure to read thru the ProximaX Development Flow to understand the difference between remote and local connection.

## Remote Connection
Remote connection allows developers to build application that can solve their business case while not subjecting their environment to participate on the P2P/IFPS Storage Network. They will have to delegate those to a remote box and access the capability to store, download and search for files via the REST APIs of the Web Service Gateways.

Initialize the Configuration Node 

```java
//	Connected to a remote live node endpoint api
RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://demo-gateway.proximax.io");
```

## Local Connection
Local connection allows developers to build an application that can solve their business case and participate on the P2P/IPFS Storage network. It comes with a benefit of directly accessing the files from network therefore, is much more efficient and performs better than Remote.

For local connection, your local computer needs to run the proximax P2P (IPFS) daemon node.
Download the Light weight node
Windows - *https://sp1.ams3.digitaloceanspaces.com/P2PNODELW/p2pnodelw-v.0.0.1-BETA.zip*
Linux - To follow
Extract and run runp2p.bat(Windows) or runp2p.sh(Linux)
```java
//	Connect to NEM node (the local p2p connection is implicitely set)
LocalHttpPeerConnection localPeerConnection = new LocalHttpPeerConnection(new NodeEndpoint("http", "104.128.226.60", 7890));
```
## Upload a Binary/File 
```java
Upload upload = new Upload(remotePeerConnection); // or localPeerConnection

Map<String, String> metaData = new HashMap<String, String>();
metaData.put("key1", "value1");

UploadBinaryParameter parameter = UploadBinaryParameterBuilder
        .messageType(MessageTypes.PLAIN)  // or SECURE
		.senderPrivateKey(xPvkey).receiverPublicKey(xPubkey)
		.name("test_pdf_file_v1")
		.data(FileUtils.readFileToByteArray(new File("src//test//resources//test_pdf_file_v1.pdf")))
		.contentType("application/pdf").keywords("test_pdf_file_v1")
		.metadata(JsonUtils.toJson(metaData))
		.build();

//  Return the NEM Hash
String nemhash = upload.uploadFile(parameter).getNemHash();
```
## Upload a Text Data
Attach a file as a plain message on a NEM Txn
```java
Upload upload = new Upload(remotePeerConnection); // or localPeerConnection

Map<String, String> metaData = new HashMap<String, String>();
metaData.put("key1", "value1");

UploadDataParameter parameter = UploadDataParameterBuilder
				.messageType(MessageTypes.PLAIN) // or SECURE
				.senderPrivateKey(xPvkey)
				.receiverPublicKey(xPubkey)
				.name("Custom Name")
				.data(new String("test plain - new 1".getBytes("UTF-8")))
				.contentType(DataTextContentType.TEXT_PLAIN)
				.encoding("UTF-8")
				.keywords("plain,data")
				.metadata(JsonUtils.toJson(metaData)) // one level map to json
				.build();
				
//  Return the NEM Hash
String nemhash = upload.uploadTextData(parameter).getNemHash();

```

## Search using Keywords
```java
Search search = new Search(remotePeerConnection); // or localPeerConnection
String result = search.searchByKeyword(xPubkey, "plain");
```		

## Download a Binary/File
```java
Download download = new Download(localPeerConnection);
String timeStamp = System.currentTimeMillis() + "";
DownloadResult message = download.downloadBinaryOrFile("e0ca6d958ba01592ddeaa40e9d810a4314707f6673c2271e5d0eeb018a4be997");

//  Get the byte[] data
byte[] data =  message.getData();
```

## Download a Text Data
Download a file from a plain message
```java
Download download = new Download(localPeerConnection);
DownloadResult message = download.downloadTextData("627e3b70b2e902c8ca33447216535c5f0cc90da408a3db9b5b7ded95873bb47c");

//  Get the Text data
String data = new String(message.getData(), "UTF-8");
```

## Contribution
We'd love to get more people involve in the project. We're looking for enthusiastic conitrbutors that can help us improve the library. Contributing is simple, you can start by 
+ Test the SDK and raise an issue.
+ Pick up a task, code and raise a PR

Copyright (c) 2018 ProximaX Limited

