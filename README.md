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
Configuration.setDefaultApiClient(new ApiClient().setBaseUrl("<node endpoint url>");
DataHashApi dataHashApi = new DataHashApi();
DownloadApi downloadApi = new DownloadApi();
PublishAndAnnounceApi publishAndAnnounceApi = new PublishAndAnnounceApi();
```
Generating a Data Hash for a File (note that this process uploads the file to the P2P Nodes permanently)
```java
File file = new File("src\\test\\resources\\small_file_test.txt");
//	keywords
String keywords = "small,file,test";

//	string,string map
Map<String, String> smallMetadataTest = new HashMap<String, String>();
smallMetadataTest.put("type", "small");
smallMetadataTest.put("value", "file");
String metadata = JsonUtils.toJson(smallMetadataTest);
BinaryTransactionEncryptedMessage response = dataHashApi.generateHashForFileOnlyUsingPOST(file, keywords,
		metadata);

```
Generating a Data Hash for a Free form data (note that this process uploads the file to the P2P Nodes permanently)
```java
String data = "this is a free form string data";
//	keywords
String keywords = "small,file,test";

//	string,string map
Map<String, String> smallMetadataTest = new HashMap<String, String>();
smallMetadataTest.put("type", "small");
smallMetadataTest.put("value", "file");
String metadata = JsonUtils.toJson(smallMetadataTest);
BinaryTransactionEncryptedMessage response = dataHashApi.generateHashForDataOnlyUsingPOST(data, keywords,
		metadata);
```

## Announcing a Plain Data Hash to NEM Blockchain
Attach the message as a plain message transaction
```java
BinaryTransactionEncryptedMessage response = dataHashApi.generateHashForDataOnlyUsingPOST(data, keywords,
		metadata);
		
RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
			.sender(new Account(new KeyPair(PrivateKey.fromHexString(this.xPvkey))))
			.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(this.xPubkey))))
			.message(JsonUtils.toJson(response), MessageTypes.PLAIN).buildAndSignTransaction();

String publishedData = publishAndAnnounceApi.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
```
## Announcing a Secure Data Hash to NEM Blockchain
Attach the message as a secure message transaction. 
```java
BinaryTransactionEncryptedMessage response = dataHashApi.generateHashForDataOnlyUsingPOST(data, keywords,
		metadata);
		
RequestAnnounceDataSignature requestAnnounceDataSignature = BinaryTransferTransactionBuilder
			.sender(new Account(new KeyPair(PrivateKey.fromHexString(this.xPvkey))))
			.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(this.xPubkey))))
			.message(JsonUtils.toJson(response), MessageTypes.SECURE).buildAndSignTransaction();

String publishedData = publishAndAnnounceApi.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
```
## Searching for Messages in NEM Blockchain

```java
TransactionApi.getTransaction(this.testnetSecureNemTxnHash);
TransactionApi.getAllTransaction("<address>");
```

## Decoding Plain Data Hash
```java
DownloadApi.downloadRawBytesPlainMessageFileUsingNemHashUsingGET("<nem txn hash>")
```

## Decoding Secured Data Hash

Once you have the Txn ID, you can decrypt the Transaction, pull the data hash and decrypt the uploaded file or data.
```java
TransferTransaction transaction  = (TransferTransaction) TransactionApi.getTransaction(this.testnetSecureNemTxnHash).getEntity();

SecureMessage message = SecureMessage.fromEncodedPayload(
					new Account(new KeyPair(PrivateKey.fromHexString(this.xPvkey))), 
					new Account(new KeyPair(PublicKey.fromHexString(this.xPubkey))),
					transaction.getMessage().getEncodedPayload()
					);
			
//	 We now have the decrypted secure message.
BinaryTransactionEncryptedMessage binaryEncryptedData = JsonUtils.fromJson(new String(message.getDecodedPayload(),"UTF-8"), BinaryTransactionEncryptedMessage.class);

// 	We need to pull down the file in a form of a byte array.
byte[] securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

//	We then decrypt with NEM Keys. This will return the decrypted file / data.
byte[] decrypted = engine
		.createBlockCipher(new KeyPair(PublicKey.fromHexString(this.xPubkey), engine),
				new KeyPair(PrivateKey.fromHexString(this.xPvkey), engine))
		.decrypt(HexEncoder.getBytes(new String(securedResponse, "UTF-8")));

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



