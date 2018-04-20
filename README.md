# Official Proximax P2P Storage Java SDK

![banner](https://proximax.io/wp-content/uploads/2018/03/ProximaX-logotype.png)

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation
To install the IPFS API

```shell
mvn install:install-file -DgroupId=io.ipfs -DartifactId=api -Dversion=1.2.0 -Dpackaging=jar -Dfile=lib/api-v1.2.0.jar
```
To install NEM Core API

```shell
mvn install:install-file -DgroupId=org.nem.core -DartifactId=nem-core -Dversion=0.6.95-BETA -Dpackaging=jar -Dfile=lib/nem-core-0.6.95-BETA.jar
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

Download the Light weight node

Windows - *https://sp1.ams3.digitaloceanspaces.com/P2PNODELW/p2pnodelw-v.0.0.1-BETA.zip*
Linux - To follow

Extract and run runp2p.bat(Windows) or runp2p.sh(Linux)

```java
//	Connected to a local live node endpoint api
LocalPeerConnection localPeerConnection = new LocalPeerConnection(new NodeEndpoint("http", "http://104.128.226.60", 7890)),"/ip4/127.0.0.1/tcp/5001");
```


## Upload a File or Free Form Data
Attach a file as a plain message on a NEM Txn

```java
Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	UploadFileParameter parameter = UploadFileParameterBuilder
					.senderPrivateKey(<sender private key>)
					.recipientPublicKey(<receiver public key>)
					.messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//small_file.txt"))
					.metaData(null).keywords(null)
					.build();
					
	String nemhash = upload.uploadFile(parameter).getNemHash();
	
} catch (ApiException | IOException e) {
	e.printStackTrace();
}
```
Attach a free form data (string) as a plain message on a NEM Txn

```java

Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	UploadDataParameter parameter = UploadDataParameterBuilder
					.senderPrivateKey(<sender private key>)
					.recipientPublicKey(<recipient public key>)
					.messageType(MessageTypes.PLAIN)
					.data("This is a test data")
					.metaData(null).keywords("keywords")
					.build();
										
	String nemhash = upload.uploadData(parameter).getNemHash();
} catch (ApiException e) {
	e.printStackTrace();
}
```
Attach a file as a secure message on a NEM Txn

```java
Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	UploadFileParameter parameter = UploadFileParameterBuilder
					.senderPrivateKey(<sender private key>)
					.recipientPublicKey(<receiver public key>)
					.messageType(MessageTypes.SECURE)
					.data(new File("src//test//resources//small_file.txt"))
					.metaData(null).keywords(null)
					.build();

	String nemhash = upload.uploadFile(parameter).getNemHash();
} catch (ApiException | IOException e) {
	e.printStackTrace();
}
```
Attach a free form data (string) as a secure message on a NEM Txn

```java

Upload upload = new Upload(remotePeerConnection); // or localPeerConnection
try {
	UploadDataParameter parameter = UploadDataParameterBuilder
					.senderPrivateKey(<sender private key>)
					.recipientPublicKey(<recipient public key>)
					.messageType(MessageTypes.SECURE)
					.data("This is a test data")
					.metaData(null).keywords("keywords")
					.build();

	String nemhash = upload.uploadData(parameter).getNemHash();
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
DownloadData message = download.downloadPublicDataOrFile(
		"199ce1da8b677556aa515d53b213f444c182efccd7240b053682ca7912342c7f");
		
FileUtils.writeByteArrayToFile(new File(message.getDataMessage().getName(),
		HexEncoder.getBytes(new String(message.getData())));
```
Download a file from a secure message

```java
Download download = new Download(remotePeerConnection); // or localPeerConnection
DownloadData message = download.downloadDataOrFile(
		"37098d2d5d36070ec9e9db94e3e7d07659866b0de53c2d3c30b8918cb5967de4", "<sender or receiver private key>", "<sender or receiver public key>");

FileUtils.writeByteArrayToFile(new File(message.getDataMessage().getName()), message.getData());
```
Download a free form data from a plain message

```java
Download download = new Download(remotePeerConnection); // or localPeerConnection
DownloadData message = download.downloadPublicDataOrFile(
					"565e5eafe7902d856a5a2c05a9b5a15c5aa5f941cbff7c19369ecbe4367f0b9c");
String message = new String(message.getData(), "UTF-8");
```
Download a free form data from a secure message

```java
Download download = new Download(remotePeerConnection); // or localPeerConnection
DownloadData message = download.downloadDataOrFile(
					"82dda8b1f2c5be931e1ada8ab41a1ce79be8b21c6b1a89eef0678b97783c4b2c", "<sender or receiver private key>", "<sender or receiver public key>");
String message = new String(message.getData(), "UTF-8");
```

## Listening to incoming Transactions after uploading (confirmed or unconfirmed).

Creating a custom transaction monitor handler

```java
public class TestMonitor implements UploadTransactionMonitor {
	@Override
	public Type getPayloadType(StompHeaders headers) {
		return String.class;
	}
	@Override
	public void handleFrame(StompHeaders headers, Object payload) {
		System.out.println(payload.toString()); // handle the payload.
	}
}
```

Monitor confirmed transaction

```java
	UploadDataParameter parameter = UploadDataParameterBuilder.senderPrivateKey(<sender private key>)
						.recipientPublicKey(<recipient public key>)
						.messageType(MessageTypes.SECURE).data("This is a test data")
						.metaData(null).keywords("keywords")
						.confirmedTransactionHandler(new TestMonitor())
						.build();
						
	UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(<sender private key>)
						.recipientPublicKey(<recipient public key>)
						.messageType(MessageTypes.SECURE).data(new File("src//test//resources//small_file.txt"))
						.metaData(null).keywords("keywords")
						.confirmedTransactionHandler(new TestMonitor())
						.build();
```
## Author

Alvin P. Reyes

