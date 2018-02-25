# Official Proximax P2P Storage Java SDK

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

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

## Generating a Data Hash from a File or Free form data.

Initialize the Configuration Node  

```java
RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");


## Upload a File or Free Form Data
Attach a file as a plain message on a NEM Txn
```java
Upload upload = new Upload(remotePeerConnection);
try {
	String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, new File("src//test//resources//small_file_test.txt"), null, null);
} catch (ApiException | IOException e) {
	e.printStackTrace();
}
```
Attach a free form data (string) as a plain message on a NEM Txn
```java

Upload upload = new Upload(remotePeerConnection);
try {
	String nemhash = upload.uploadData(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, "This is a test data1", null, null, null);
} catch (ApiException e) {
	e.printStackTrace();
}
```
Attach a file as a secure message on a NEM Txn
```java
Upload upload = new Upload(remotePeerConnection);
try {
	String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey, new File("src//test//resources//small_file_test.txt"), null, null);
} catch (ApiException | IOException e) {
	e.printStackTrace();
}
```
Attach a free form data (string) as a secure message on a NEM Txn
```java

Upload upload = new Upload(remotePeerConnection);
try {
	String nemhash = upload.uploadData(MessageTypes.SECURE, this.xPvkey, this.xPubkey, "This is a test data1", null, null, null);
} catch (ApiException e) {
	e.printStackTrace();
}
```
## Download a File or Free Form Data
Download a file from a plain/secure message
```java
Download download = new Download(remotePeerConnection);
DownloadData message = download.downloadData(
		"199ce1da8b677556aa515d53b213f444c182efccd7240b053682ca7912342c7f",
		this.xPvkey, this.xPubkey);
		
String message = new String(message.getData(), "UTF-8");

FileUtils.writeByteArrayToFile(new File("src//test//resources//file_"
		+ message.getDataMessage().getName() + System.currentTimeMillis() + ".zip"), message.getData());	
```
Attach a free form data (string) as a plain/secure message on a NEM Txn
```java
Download download = new Download(remotePeerConnection);
DownloadData message = download.downloadData(
		"199ce1da8b677556aa515d53b213f444c182efccd7240b053682ca7912342c7f",
		this.xPvkey, this.xPubkey);
		
String message = new String(message.getData(), "UTF-8");
```
## Documentation for API Endpoints

All URIs are relative to *http://localhost:8881/areyes1*

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



