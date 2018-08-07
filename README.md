# Official Proximax P2P Storage Java SDK (NIS1 Compatible Only)
![banner](https://proximax.io/wp-content/uploads/2018/03/ProximaX-logotype.png)

ProximaX is a project that utilizes the NEM blockchain technology with the IPFS P2P storage technology to form a very powerful proofing solution for documents or files which are stored in an immutable and irreversible manner, similar to the blockchain technology solutions. 

## Adding Jitpack Repository
There are dependencies hosted from Jitpack. Add the JitPack repository to your build file

**Maven**
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Gradle**
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

## Getting started with ProximaX Java SDK (NIS1)
Import the following dependency to your Java Project. Replace with the latest version. 

**Maven**
```xml
<dependency>
    <groupId>io.proximax</groupId>
    <artifactId>xpx-java-sdk</artifactId>
    <version>0.1.0-beta.9</version>
</dependency>
```
**Gradle**
```xml
compile 'io.proximax:xpx-java-sdk:0.1.0-beta.9'
```

**Groovy**
```xml
@Grapes( 
    @Grab(group='io.proximax', module='xpx-java-sdk', version='0.1.0-beta.9')
)
```

### Get your XPX Test Tokens

Visit the XEM/XPX Testnet faucet at [https://proximaxfaucet20180730014353.azurewebsites.net/](https://proximaxfaucet20180730014353.azurewebsites.net/ "Testnet Faucet")


### Establishing a connection
We support 2 types of connection. 

***Remote Connection***

`RemotePeerConnection` are for clients who wants wants to use a remote storage and doesn't want or prefer to its own device to participate on storage network.

```java
RemotePeerConnection remotePeerConnection = new RemotePeerConnection("https://testnet.gateway.proximax.io");
```
Aside from the testnet connection above, one can also use the following testnet gateways.

+ [https://testnet1.gateway.proximax.io](https://testnet1.gateway.proximax.io/node/info "Testnet 1")
+ [https://testnet2.gateway.proximax.io](https://testnet2.gateway.proximax.io/node/info "Testnet 2")
+ [https://testnet3.gateway.proximax.io](https://testnet3.gateway.proximax.io/node/info "Testnet 3")

These testnet gateways are privately hosted storage contributor nodes on our ProximaX servers. All of them are connected to NEM Testnet and Public IPFS network. 

We also have mainnet and mijinnet (NIS Mijin)

+ [https://mainnet.gateway.proximax.io](https://mainnet.gateway.proximax.io/node/info "Mainnet")
+ [https://mijin.gateway.proximax.io](https://mijin.gateway.proximax.io/node/info "Mijin")

***Local Connection***

`LocalHttpPeerConnection` are for local client who is participating as part of the Storage network. The prequisite for this is that the host client should run the `proximax daemon`.

```java
LocalHttpPeerConnection remotePeerConnection = new LocalHttpPeerConnection(
                    ConnectionFactory.createNemNodeConnection("testnet","http", "104.128.226.60", 7890),
                    ConnectionFactory.createIPFSNodeConnection("/ip4/127.0.0.1/tcp/5001")
            );
```

Download and extract the proximax daemon here.

+ [Windows](https://testnet1.gateway.proximax.io/xpxfs/61d9360b862e20bc26ab80566e2c7c4190a4ba90324d108f2318dee582ce6607)
+ [Linux](https://testnet1.gateway.proximax.io/xpxfs/7f27ddb0c33f657237db083739c2ec26d36f67f793fe6b7b04df960d8261bf3b)

Run the daemon like so

```bash
proximax.exe init 
proximax.exe daemon
```

Now that we've establish the connection, let's get into the coding.

To easily access each feature/function, we need to initialze the object passing the `peerConnection` variable.

```java
//  To Upload
final Upload upload = new Upload(peerConnection); // blocking
final UploadAsync uploadAsync = new UploadAsync(peerConnection); // non-blocking (async)

//  To Download
final Download download = new Download(peerConnection);
final DownloadAsync downloadAsync = new DownloadAsync(peerConnection);

//  More functions that can be used
final Search search = new Search(peerConnection);
final SearchAsync searchAsync = new SearchAsync(peerConnection);

//  To look up Account
final Account account = new Account(peerConnection);
final AccountAsync accountAsync = new AccountAsync(peerConnection);

//  To look up XPX / Proximax Transactions
final Transactions transactions = new Transactions(peerConnection);
final TransactionsAsync transactionsAsync = new TransactionsAsync(peerConnection);
```

### Create / Generate new NEM Account

To generate a new account, use the `peerConnection` to call the `NemAccountApi()`

```java
peerConnection.getNemAccountApi().generateAccount()
```

***Grab the Address and get XEMs/XPXs.***  

Visit the XEM/XPX Testnet faucet at [https://proximaxfaucet20180730014353.azurewebsites.net/](https://proximaxfaucet20180730014353.azurewebsites.net/ "Testnet Faucet")

### Upload / Upload Async

***Uploading a file***

When uploading a binary file, the `Upload` requires a `UploadFileParameter` object specifying the necessary information. This object is then pass to the Upload API that will in turn return an `UploadResult` object which has the NEM Hash.
```java
final UploadFileParameter parameter = UploadFileParameter.create()
        .senderPrivateKey(PRIVATE_KEY)
        .receiverPublicKey(PUBLIC_KEY)
        .file(new File("/path/to/file.pdf"))
        .keywords("keywords")
        .metadata(singletonMap("someMetadataKey", "someMetadataValue"))
        .build();

final UploadResult uploadResult = upload.uploadFile(parameter);
uploadResult.getNemHash(); // prints the Nem Hash to download file
```

***Uploading binary data (bytes)***

When uploading a binary file, the `Upload` requires a `UploadBinaryParameter` object.
```java
final UploadBinaryParameter parameter = UploadBinaryParameter.create()
        .senderPrivateKey(TEST_PRIVATE_KEY)
        .receiverPublicKey(TEST_PUBLIC_KEY)
        .data(SOME_BYTE_ARRAY)
        .keywords("keywords")
        .metadata(singletonMap("someMetadataKey", "someMetadataValue"))
        .build();

final UploadResult uploadResult = upload.uploadBinary(parameter);
uploadResult.getNemHash(); // prints the Nem Hash to download file
```

***Uploading a text***

When uploading a text file, the `Upload` requires a `UploadTextDataParameter `object.
```java
final UploadTextDataParameter parameter = UploadTextDataParameter.create()
        .senderPrivateKey(PRIVATE_KEY)
        .receiverPublicKey(PUBLIC_KEY)
        .data("plain - the quick brown fox jumps over the lazy dog UTF-8")
        .name("SomeName1")
        .contentType("text/plain")
        .encoding("UTF-8")
        .keywords("keywords")
        .metadata(singletonMap("someMetadataKey", "someMetadataValue"))
        .build();

final UploadResult uploadResult = upload.uploadTextData(parameter);
uploadResult.getNemHash(); // prints the Nem Hash to download text
```

***Uploading multiple files***

When uploading multiple files at the same time, the `Upload` requires `UploadMultipleFilesParameter` object. This would return an `UploadResult` for each file uploaded.

```java
final UploadMultipleFilesParameter parameter = UploadMultipleFilesParameter.create()
        .senderPrivateKey(PRIVATE_KEY)
        .receiverPublicKey(PUBLIC_KEY)
        .addFile(new File("/path/to/file 1"))
        .addFile(new File("/path/to/file 2"))
        .keywords("keywords")
        .metadata(singletonMap("someMetadataKey", "someMetadataValue"))
        .build();

final MultiFileUploadResult multiFileUploadResult = upload.uploadMultipleFiles(parameter);
multiFileUploadResult.getFileUploadResults().get(0).getUploadResult().getNemHash(); // prints the Nem Hash to download first file

```

***Uploading multiple files as zip***

When uploading multiple files compressed as a zip file, the `Upload` requires `UploadFilesAsZipParameter` object.


```java
final UploadFilesAsZipParameter parameter = UploadFilesAsZipParameter.create()
        .senderPrivateKey(TEST_PRIVATE_KEY)
        .receiverPublicKey(TEST_PUBLIC_KEY)
        .zipFileName(ZIP_FILE_NAME)
        .addFile("/path/to/file 1")
        .addFile("/path/to/file 2")
        .keywords(KEYWORDS_PLAIN_AND_ZIP_FILE)
        .metadata(METADATA_AS_MAP)
        .build();

final UploadResult uploadResult = upload.uploadFilesAsZip(parameter);
uploadResult.getNemHash(); // prints the Nem Hash to download zip file
```

***Upload files with mosaics***

One can also attach a mosaic as part of the upload.

```java
Mosaic blueNumberAsset = new Mosaic(new MosaicId(new NamespaceId("bluenumber1"), "product"),
                Quantity.fromValue(10000));

UploadTextDataParameter parameter = UploadTextDataParameter.create()
                .senderPrivateKey(TEST_PRIVATE_KEY)
                .receiverPublicKey(TEST_PUBLIC_KEY)
                .data(new String("plain - the quick brown fox jumps over the lazy dog UTF-8".getBytes(),ENCODING_UTF_8))
                .name(TEST_NAME_1)
                .contentType(TEXT_PLAIN.toString())
                .encoding(ENCODING_UTF_8)
                .keywords(KEYWORDS_PLAIN_AND_DATA)
                .metadata(METADATA_AS_MAP)
                .mosaics(MOSAIC_LAND_REGISTRY)
                .build();

final UploadResult uploadResult = unitUnderTest.uploadTextData(parameter);
```

***Upload Path***

Note that this feature works on local peer connection and linux/mac machine only

```java
UploadPathParameter parameter = UploadPathParameter.create()
				.senderPrivateKey(TEST_PRIVATE_KEY)
				.receiverPublicKey(TEST_PUBLIC_KEY)
				.path("src/test/resources/")
				.metadata(METADATA_AS_MAP)
				.keywords(KEYWORDS_PLAIN_AND_PATH)
				.mosaics(MOSAIC_LAND_REGISTRY)
				.build();

final UploadResult uploadResult = unitUnderTest.uploadPath(parameter);
```

You will now be able to visit the path via our proximax or ipfs gateways

*Note: Path is by default, public. Please be careful when loading a directory as it exposes it the open public gateways*

### Securing uploaded content with Privacy Strategies 
By default, any upload will use a plain privacy strategy. This basically means that the file is not in anyway encrypted and can be accessed publicly. 
In order to secure the content being uploaded, other privacy strategies can be use to protect and secure your files. 

***Secured with NEM keys privacy strategy***

This will utilise the NEM private and public key provided to encrypt the content.
In addition to the content, this strategy will create the NEM transaction as a secure/encrypted message.

```java
final UploadFileParameter parameter = UploadFileParameter.create()
        .senderPrivateKey(PRIVATE_KEY)
        .receiverPublicKey(PUBLIC_KEY)
        .file(new File("/path/to/file"))
        .securedWithNemKeysPrivacyStrategy() // sets the privacy strategy
        .build();

final UploadResult uploadResult = upload.uploadFile(parameter);
```

The above code will encrypt the file using the senders private key and receivers public key.
 
***Secured with password privacy strategy***

This will use a password to encrypt the content. This strategy requires a minimum length of 50 characters for the password.

```java
final UploadFileParameter parameter = UploadFileParameter.create()
        .senderPrivateKey(PRIVATE_KEY)
        .receiverPublicKey(PUBLIC_KEY)
        .file(new File("/path/to/file"))
        .securedWithPasswordPrivacyStrategy(PASSWORD) // sets the privacy strategy
        .build();

final UploadResult uploadResult = upload.uploadFile(parameter);
```

 
***Secured with Shamir Shared Secret Key***

This will use a password to encrypt the content. This strategy requires a minimum length of 50 characters for the password.

```java
final Map<Integer, byte[]> minimumSecretParts = new HashMap<>();
minimumSecretParts.put(2, SHAMIR_SECRET_PARTS.get(2));
minimumSecretParts.put(5, SHAMIR_SECRET_PARTS.get(5));
minimumSecretParts.put(9, SHAMIR_SECRET_PARTS.get(9));

UploadBinaryParameter parameter = UploadBinaryParameter.create()
        .senderPrivateKey(TEST_PRIVATE_KEY)
        .receiverPublicKey(TEST_PUBLIC_KEY)
        .data(FileUtils.readFileToByteArray("/path/to/file"))
        .securedWithShamirSecretSharingPrivacyStrategy(SHAMIR_SECRET_TOTAL_PART_COUNT, SHAMIR_SECRET_MINIMUM_PART_COUNT_TO_BUILD,
                minimumSecretParts)
        .build();
```

***Custom privacy strategy***

Custom privacy strategies to encrypt the content also possible. The developer must implement any of the following classes.

`AbstractPlainMessagePrivacyStrategy` if NEM transactions should be in plain or unencrypted message.
`AbstractSecureMessagePrivacyStrategy` if NEM transactions should be in secure or encrypted message.

Both of the abstract class requires the following to be overriden.
```java
    @Override
    public byte[] encrypt(byte[] data) {
        // do encryption
        return encrytedData;
    }

    @Override
    public byte[] decrypt(byte[] data, TransferTransaction transaction, ResourceHashMessage hashMessage) {
        // do decryption
        return decryptedData;
    }
```

Pass an instance of your custom strategy when building the upload parameter.
```java
final UploadFileParameter parameter = UploadFileParameter.create()
        .senderPrivateKey(PRIVATE_KEY)
        .receiverPublicKey(PUBLIC_KEY)
        .file(new File("/path/to/file.pdf"))
        .privacyStrategy(MY_CUSTOM_STRATEGY) // sets the privacy strategy
        .build();

final UploadResult uploadResult = upload.uploadFile(parameter);
```

### Download / Download Async
Download function allows developers to download a content (binary,text) that are stored on the P2P Storage that is rooted to a NEM Transaction. All downloads requires the NEM Hash as this is the main reference of the Platform to get content. 

To initialize the `Download` service, this can be done by passing a `PeerConnection`
```java
final Download download = new Download(peerConnection);
```

***Download Plain Text File/Content***

Use the following code to download a text content that was uploaded using the plain/public privacy strategy.

```java
final DownloadTextDataResult result = download.downloadTextData(DownloadParameter.create().nemHash("7f2d1944016f1259e552b34cb5029d7473074856229094acc5ba479549e59411").build());

```

***Download Secure Text File/Content***

Use the following code to download a text content that was uploaded using the secure with nem keys privacy strategy.

```java

final DownloadTextDataResult result = download.downloadTextData(DownloadParameter.create()
.nemHash("47ef7e2a12ea7413a69ef215e33b1d32f21ccbf743b9358efc9909b869ab7e70")
.securedWithNemKeysPrivacyStrategy("<private key">, "<public key">)
.build());

```

***Download Plain Binary/File***

Use the following code to download a binary that was uploaded using the plain/public privacy strategy.

```java
final DownloadBinaryResult result = download.downloadBinary(DownloadParameter.create().nemHash("7f2d1944016f1259e552b34cb5029d7473074856229094acc5ba479549e59411").build());

```

***Download Secure Binary/File***

Use the following code to download a binary that was uploaded using the secure with nem keys privacy strategy.

```java

final DownloadBinaryResult result = download.downloadBinary(DownloadParameter.create()
.nemHash("47ef7e2a12ea7413a69ef215e33b1d32f21ccbf743b9358efc9909b869ab7e70")
.securedWithNemKeysPrivacyStrategy("<private key">, "<public key">)
.build());

```

***Download Secure with Password Text File***

Use the following code to download a binary that was uploaded using the plain/public privacy strategy.
```java

final DownloadTextDataResult result = download.downloadTextData(DownloadParameter.create()
.nemHash("47ef7e2a12ea7413a69ef215e33b1d32f21ccbf743b9358efc9909b869ab7e70")
.securedWithNemKeysPrivacyStrategy("<private key">, "<public key">)
.build());

```

***Download Secure with Password Binary/File***

Use the following code to download a binary that was uploaded using the secure with nem keys privacy strategy.
```java

final DownloadBinaryResult result = download.downloadBinary(DownloadParameter.create()
.nemHash("47ef7e2a12ea7413a69ef215e33b1d32f21ccbf743b9358efc9909b869ab7e70")
.securedWithNemKeysPrivacyStrategy("<private key">, "<public key">)
.build());

```

### Search / Search Async

The SDK also has a service to do basic searching on the blockchain. This is only an additional feature and can be resource extensive as it traverses the entire message and compare.

To initialize the `Search` service, this can be done by passing a `PeerConnection`
```java
final Search search = new Search(peerConnection);
```

***Search by keyword***

Search by keyword or keywords
```java
List<ResourceHashMessageJsonEntity> result = search.searchByKeyword(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "plain,secure,file");
byte[] data = peerConnection.getDownloadApi().downloadUsingDataHashUsingGET(result.get(0).hash())
```

***Search by name***

Search by name field
```java
List<ResourceHashMessageJsonEntity> result = search.searchByName(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "index.jpg");
byte[] data =  peerConnection.getDownloadApi().downloadUsingDataHashUsingGET(result.get(0).hash())
```

***Search by metadata***

Search by metadata
```java
List<ResourceHashMessageJsonEntity> result = search.searchByMetaDataKeyValue(TEST_PRIVATE_KEY, TEST_PUBLIC_KEY, "key1","value1");
byte[] data =  peerConnection.getDownloadApi().downloadUsingDataHashUsingGET(result.get(0).hash())
```

### Monitoring the Transaction

NEM has a few websockets exposed that can be used to monitor transactions. One of the tools we have is the nem transaction monitor that can easily be configured to monitor incoming, outgoing, confirm and unconfirmed transactions.

Download it [__here__](https://testnet.gateway.proximax.io/xpxfs/4b04b20335de3845bf11d148733cd56bd0090292c6403de20514e9dd87473896)

```xml
<dependency>
    <groupId>io.proximax.xpx</groupId>
    <artifactId>proximax-nem-txn-monitor</artifactId>
    <version>0.1.0-beta.x</version>
</dependency>
```

Call code to Monitor

```java

WsNemTransactionMonitor.networkName("testnet").host("23.228.67.85").port("7890").wsPort("7778")
	.addressToMonitor("MDYSYWVWGC6JDD7BGE4JBZMUEM5KXDZ7J77U4X2Y") // address to monitor
	.subscribe(io.proximax.utils.Constants.URL_WS_TRANSACTIONS, new CustomTransactionMonitorHandler1()) // multiple subscription and a handler
	.subscribe(io.proximax.utils.Constants.URL_WS_UNCONFIRMED, new CustomTransactionMonitorHandler2())
	.monitor(); // trigger the monitoring process
			
```


## Contribution
We'd love to get more people involve in the project. We're looking for enthusiastic conitrbutors that can help us improve the library. Contributing is simple, you can start by 
+ Test the SDK and raise an issue.
+ Pick up a task, code and raise a PR

Copyright (c) 2018 ProximaX Limited
