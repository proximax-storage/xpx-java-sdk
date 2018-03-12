# Official Proximax P2P Storage Java SDK

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation
To install the IPFS API

```shell
mvn install:install-file -DgroupId=io.ipfs -DartifactId=api -Dversion=1.2.0 -Dpackaging=jar -Dfile=lib/api-v1.2.0.jar
```
To install NEM Core API

```shell
mvn install:install-file -DgroupId=org.nem.core -DartifactId=nem-core -Dversion=0.6.93-BETA -Dpackaging=jar -Dfile=lib/nem-core-0.6.93-BETA.jar
```
To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.nem.xpx</groupId>
    <artifactId>xpx-java-sdk</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.nem.xpx:xpx-java-sdk:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/xpx-java-sdk-1.0.0.jar
* target/lib/*.jar



## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

## Connect as a Client Only or Peer Participant 

Initialize the Configuration Node 

```java
//	Connected to a remote live node endpoint api
RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://p2ptest.smartproof.io:8881");
```

For local connection, your local computer needs to run the proximax daemon.

```java
//	Connected to a local live node endpoint api
LocalPeerConnection localPeerConnection = new LocalPeerConnection(
new NodeEndpoint("http", "http://104.128.226.60", 7890)) // NEM Node.
,"/ip4/127.0.0.1/tcp/5001");
```


## Upload a File or Free Form Data
Attach a file as a plain message on a NEM Txn
```java
Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	String nemhash = upload.uploadFile(MessageTypes.PLAIN, "<sender private key>", "<receiver public key>", new File("src//test//resources//small_file.txt"), null, null);
} catch (ApiException | IOException e) {
	e.printStackTrace();
}
```
Attach a free form data (string) as a plain message on a NEM Txn
```java

Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	String nemhash = upload.uploadData(MessageTypes.PLAIN, "<sender private key>", "<receiver public key>", "This is a test data1", null, null, null).getNemHash();
} catch (ApiException e) {
	e.printStackTrace();
}
```
Attach a file as a secure message on a NEM Txn
```java
Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	String nemhash = upload.uploadFile(MessageTypes.SECURE, "<sender private key>", "<receiver public key>", new File("src//test//resources//small_file_test.txt"), null, null).getNemHash();
} catch (ApiException | IOException e) {
	e.printStackTrace();
}
```
Attach a free form data (string) as a secure message on a NEM Txn
```java

Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	String nemhash = upload.uploadData(MessageTypes.SECURE, "<sender private key>", "<receiver public key>", "This is a test data1", null, null, null).getNemHash();
} catch (ApiException e) {
	e.printStackTrace();
}
```
## Search using Keywords
```java
RemotePeerConnection remotePeerConnection = new RemotePeerConnection(remotePeerConnection);
Search search = new Search(remotePeerConnection); // or localPeerConnection
String jsonResult = search.searchAllTransactionWithRegexKeyword(this.xPvkey, "alvinreyes");
```		

## Download a File or Free Form Data
Download a file from a plain message
```java
Download download = new Download(remotePeerConnection); // or localPeerConnection
DownloadData message = download.downloadPublicData(
		"199ce1da8b677556aa515d53b213f444c182efccd7240b053682ca7912342c7f");
		
FileUtils.writeByteArrayToFile(new File(message.getDataMessage().getName(),
		HexEncoder.getBytes(new String(message.getData())));
```
Download a file from a secure message
```java
Download download = new Download(remotePeerConnection); // or localPeerConnection
DownloadData message = download.downloadData(
		"37098d2d5d36070ec9e9db94e3e7d07659866b0de53c2d3c30b8918cb5967de4", "<sender or receiver private key>", "<sender or receiver public key>");

FileUtils.writeByteArrayToFile(new File(message.getDataMessage().getName()), message.getData());
```
Download a free form data from a plain message
```java
Download download = new Download(remotePeerConnection); // or localPeerConnection
DownloadData message = download.downloadPublicData(
					"565e5eafe7902d856a5a2c05a9b5a15c5aa5f941cbff7c19369ecbe4367f0b9c");
String message = new String(message.getData(), "UTF-8");
```
Download a free form data from a secure message
```java
Download download = new Download(remotePeerConnection); // or localPeerConnection
DownloadData message = download.downloadData(
					"82dda8b1f2c5be931e1ada8ab41a1ce79be8b21c6b1a89eef0678b97783c4b2c", "<sender or receiver private key>", "<sender or receiver public key>");
String message = new String(message.getData(), "UTF-8");
```

## Listening to incoming Transactions (confirmed or unconfirmed).

The SDK comes with a built in monitoring tool to allow developers to monitor a specific address (https://github.com/NEMPH/nem-transaction-monitor)

## Documentation for API Endpoints

All URIs are relative to *http://p2ptest.smartproof.io:8881*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AccountApi* | [**getAllIncomingNemAddressTransactionsUsingGET**](docs/AccountApi.md#getAllIncomingNemAddressTransactionsUsingGET) | **GET** /account/get/all-incoming-transactions/{publicKey} | getAllIncomingNemAddressTransactions
*AccountApi* | [**getAllNemAddressTransactionsUsingGET**](docs/AccountApi.md#getAllNemAddressTransactionsUsingGET) | **GET** /account/get/all-transactions/{publicKey} | getAllNemAddressTransactions
*AccountApi* | [**getAllNemAddressTransactionsWithPageSizeUsingGET**](docs/AccountApi.md#getAllNemAddressTransactionsWithPageSizeUsingGET) | **GET** /account/get/all-transactions/{publicKey}/{pageSize} | getAllNemAddressTransactionsWithPageSize
*AccountApi* | [**getAllOutgoingNemAddressTransactionsUsingGET**](docs/AccountApi.md#getAllOutgoingNemAddressTransactionsUsingGET) | **GET** /account/get/all-outgoing-transactions/{publicKey} | getAllOutgoingNemAddressTransactions
*AccountApi* | [**getAllUnconfirmedNemAddressTransactionsUsingGET**](docs/AccountApi.md#getAllUnconfirmedNemAddressTransactionsUsingGET) | **GET** /account/get/all-unconfirmed-transactions/{publicKey} | getAllUnconfirmedNemAddressTransactions
*AccountApi* | [**getNemAddressDetailsUsingGET**](docs/AccountApi.md#getNemAddressDetailsUsingGET) | **GET** /account/get/info/{publicKey} | Get the NEM Address Details
*DataHashApi* | [**generateHashAndExposeFileToNetworkUsingPOST**](docs/DataHashApi.md#generateHashAndExposeFileToNetworkUsingPOST) | **POST** /datahash/upload/generate | Generates the encrypted datahash and uploads the file in the process.
*DataHashApi* | [**generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST**](docs/DataHashApi.md#generateHashExposeByteArrayToNetworkBuildAndSignUsingPOST) | **POST** /datahash/upload/data/generate-sign | This endpoint can be used to generate the transaction along with the data hash with the private key signature.
*DataHashApi* | [**generateHashExposeFileToNetworkBuildAndSignUsingPOST**](docs/DataHashApi.md#generateHashExposeFileToNetworkBuildAndSignUsingPOST) | **POST** /datahash/upload/generate-sign | This endpoint can be used to generate the transaction along with the data hash with the private key signature.
*DataHashApi* | [**uploadJsonDataAndGenerateHashUsingPOST**](docs/DataHashApi.md#uploadJsonDataAndGenerateHashUsingPOST) | **POST** /datahash/upload/data/generate | Generates the encrypted datahash and uploads the JSON Format String data to the P2P Storage Network.
*DownloadApi* | [**downloadPlainMessageFileUsingNemHashUsingGET**](docs/DownloadApi.md#downloadPlainMessageFileUsingNemHashUsingGET) | **GET** /download/data/plain/{nemhash} | Download resource/file using NEM Transaction Hash
*DownloadApi* | [**downloadRawBytesPlainMessageFileUsingNemHashUsingGET**](docs/DownloadApi.md#downloadRawBytesPlainMessageFileUsingNemHashUsingGET) | **GET** /download/data/plain/rawbytes/{nemhash} | Download plain resource/file using NEM Transaction Hash
*DownloadApi* | [**downloadRawBytesSecureMessageFileUsingNemHashUsingGET**](docs/DownloadApi.md#downloadRawBytesSecureMessageFileUsingNemHashUsingGET) | **GET** /download/data/secure/rawbytes/{nemhash} | Download secured resource/file using NEM Transaction Hash
*DownloadApi* | [**downloadRawBytesUsingHashUsingPOST**](docs/DownloadApi.md#downloadRawBytesUsingHashUsingPOST) | **POST** /download/data/rawbytes | Download secured encrypted resource/file using Data Hash
*DownloadApi* | [**downloadSecureMessageFileUsingNemHashUsingGET**](docs/DownloadApi.md#downloadSecureMessageFileUsingNemHashUsingGET) | **GET** /download/data/secure/{nemhash} | Download resource/file using NEM Transaction Hash
*DownloadApi* | [**downloadStreamPlainMessageFileUsingNemHashUsingGET**](docs/DownloadApi.md#downloadStreamPlainMessageFileUsingNemHashUsingGET) | **GET** /download/data/plain/stream/{nemhash} | Download plain resource/file using NEM Transaction Hash
*DownloadApi* | [**downloadStreamSecureMessageFileUsingNemHashUsingGET**](docs/DownloadApi.md#downloadStreamSecureMessageFileUsingNemHashUsingGET) | **GET** /download/data/secure/stream/{nemhash} | Download secured resource/file using NEM Transaction Hash
*DownloadApi* | [**downloadStreamUsingHashUsingPOST**](docs/DownloadApi.md#downloadStreamUsingHashUsingPOST) | **POST** /download/data/stream | Download secured encrypted resource/file using Data Hash
*NodeApi* | [**checkNodeUsingGET**](docs/NodeApi.md#checkNodeUsingGET) | **GET** /node/check | Check if the Storage Node is up and running.
*NodeApi* | [**getNodeInfoUsingGET**](docs/NodeApi.md#getNodeInfoUsingGET) | **GET** /node/info | Get Storage Node Information
*NodeApi* | [**setBlockchainNodeConnectionUsingPOST**](docs/NodeApi.md#setBlockchainNodeConnectionUsingPOST) | **POST** /node/set/blockchain/connection | Get Storage Node Information
*PublishAndAnnounceApi* | [**announceRequestPublishDataSignatureUsingPOST**](docs/PublishAndAnnounceApi.md#announceRequestPublishDataSignatureUsingPOST) | **POST** /publish/announce | Announce the DataHash to NEM/P2P Storage and P2P Database
*PublishAndAnnounceApi* | [**publishAndSendSingleFileToAddressUsingPOST**](docs/PublishAndAnnounceApi.md#publishAndSendSingleFileToAddressUsingPOST) | **POST** /publish/single/to/{address} | Store a single file that can only be access by the given address
*PublishAndAnnounceApi* | [**publishAndSendSingleFileToAddressesUsingPOST**](docs/PublishAndAnnounceApi.md#publishAndSendSingleFileToAddressesUsingPOST) | **POST** /publish/single/to/addresses | Store a single file that can only be access by the given addresses
*SearchApi* | [**searchContentUsingAllNemHashUsingGET**](docs/SearchApi.md#searchContentUsingAllNemHashUsingGET) | **GET** /search/all/content/hash/{nemHash} | Search through all the owner&#39;s documents to find a content that matches the text specified.
*SearchApi* | [**searchContentUsingPublicNemHashUsingGET**](docs/SearchApi.md#searchContentUsingPublicNemHashUsingGET) | **GET** /search/public/content/hash/{nemHash} | Search through all the owner&#39;s documents to find a content that matches the text specified.
*SearchApi* | [**searchContentUsingTextUsingGET**](docs/SearchApi.md#searchContentUsingTextUsingGET) | **GET** /search/public/content/{text} | Search through all the owner&#39;s documents to find a content that matches the text specified.
*SearchApi* | [**searchTransactionPvKeyWithKeywordUsingGET**](docs/SearchApi.md#searchTransactionPvKeyWithKeywordUsingGET) | **GET** /search/all/content/keyword/{keywords} | Search through all the owners documents to find a content that matches the text specified.
*SearchApi* | [**searchTransactionWithKeywordUsingGET**](docs/SearchApi.md#searchTransactionWithKeywordUsingGET) | **GET** /search/public/content/keyword/{keywords} | Search through all the owners documents to find a content that matches the text specified.
*SearchApi* | [**searchTransactionWithMetadataUsingGET**](docs/SearchApi.md#searchTransactionWithMetadataUsingGET) | **GET** /search/public/content/metadata/{text} | Search through all the owners documents to find a key that matches the specified parameter key


## Documentation for Models

 - [AccountInfo](docs/AccountInfo.md)
 - [AccountMetaData](docs/AccountMetaData.md)
 - [AccountMetaDataPair](docs/AccountMetaDataPair.md)
 - [Address](docs/Address.md)
 - [Amount](docs/Amount.md)
 - [BinaryTransactionEncryptedMessage](docs/BinaryTransactionEncryptedMessage.md)
 - [BlockAmount](docs/BlockAmount.md)
 - [GenericResponseMessage](docs/GenericResponseMessage.md)
 - [InputStream](docs/InputStream.md)
 - [KeyPair](docs/KeyPair.md)
 - [MultisigInfo](docs/MultisigInfo.md)
 - [NodeInfo](docs/NodeInfo.md)
 - [PrivateKey](docs/PrivateKey.md)
 - [PublicKey](docs/PublicKey.md)
 - [RequestAnnounceDataSignature](docs/RequestAnnounceDataSignature.md)
 - [Resource](docs/Resource.md)
 - [ResponseEntity](docs/ResponseEntity.md)
 - [StackTraceElement](docs/StackTraceElement.md)
 - [Throwable](docs/Throwable.md)
 - [URI](docs/URI.md)
 - [URL](docs/URL.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

Alvin P. Reyes

