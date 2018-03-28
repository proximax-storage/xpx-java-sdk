/*
 * Proximax P2P Storage REST API
 * Proximax P2P Storage REST API
 *
 * OpenAPI spec version: v0.0.1
 * Contact: alvin.reyes@botmill.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.nem.xpx;

import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import io.nem.ApiCallback;
import io.nem.ApiClient;
import io.nem.ApiException;
import io.nem.ApiResponse;
import io.nem.Configuration;
import io.nem.Pair;
import io.nem.ProgressRequestBody;
import io.nem.ProgressResponseBody;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import io.nem.xpx.model.BinaryTransactionEncryptedMessage;
import io.nem.xpx.model.DataHashByteArrayEntity;
import io.nem.xpx.model.DataHashPathEntity;
import io.nem.xpx.model.PublishResult;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.utils.JsonUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;


public class LocalDataHashApi implements DataHashApiInterface {

	@SuppressWarnings("unchecked")
	@Override
	public BinaryTransactionEncryptedMessage generateHashAndExposeDataToNetworkUsingPOST(String data, String name,
			String keywords, String metadata) throws ApiException, IOException, NoSuchAlgorithmException {

		DataHashByteArrayEntity dataHashByteArrayEntity = new DataHashByteArrayEntity();
		dataHashByteArrayEntity.setFile(data.getBytes());
		if (name == null || (name != null && name.equals(""))) {
			dataHashByteArrayEntity.setName(Math.abs(System.currentTimeMillis()) + "");
		} else {
			dataHashByteArrayEntity.setName(name);
		}
		dataHashByteArrayEntity.setKeywords(keywords);
		dataHashByteArrayEntity.setMetadata((metadata == null) ? null : JsonUtils.fromJson(metadata, Map.class));

		BinaryTransactionEncryptedMessage binaryEncryptedMessage = new BinaryTransactionEncryptedMessage();

		PublishResult spfsBlockResult = exposeAndPinBinary(dataHashByteArrayEntity.getName(),
				dataHashByteArrayEntity.getFile());

		String multiHashString = spfsBlockResult.getMerkleNode().get(0).hash.toBase58();

		binaryEncryptedMessage.setTimestamp(System.currentTimeMillis());
		binaryEncryptedMessage.setHash(multiHashString);	
		binaryEncryptedMessage.setName(spfsBlockResult.getMerkleNode().get(0).name.get());
		binaryEncryptedMessage.setType(spfsBlockResult.getMerkleNode().get(0).type.toString());
		binaryEncryptedMessage.setKeywords(dataHashByteArrayEntity.getKeywords());
		binaryEncryptedMessage.setMetaData(JsonUtils.toJson(dataHashByteArrayEntity.getMetadata()));
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		binaryEncryptedMessage.setDigest(new String(
				org.bouncycastle.util.encoders.Hex.encode(messageDigest.digest(multiHashString.getBytes()))));

		return binaryEncryptedMessage;
	}

	public BinaryTransactionEncryptedMessage generateHashAndExposePath(String path, String name,
			String keywords, String metadata) throws Exception {

		DataHashPathEntity dataHashPathEntity = new DataHashPathEntity();
		dataHashPathEntity.setPath(path);
		if (name == null || (name != null && name.equals(""))) {
			dataHashPathEntity.setName(Math.abs(System.currentTimeMillis()) + "");
		} else {
			dataHashPathEntity.setName(name);
		}
		dataHashPathEntity.setKeywords(keywords);
		dataHashPathEntity.setMetadata((metadata == null) ? null : JsonUtils.fromJson(metadata, Map.class));

		BinaryTransactionEncryptedMessage binaryEncryptedMessage = new BinaryTransactionEncryptedMessage();

		PublishResult spfsBlockResult = exposeAndPinPath(dataHashPathEntity.getPath());

		String multiHashString = spfsBlockResult.getMerkleNode().get(0).hash.toBase58();

		binaryEncryptedMessage.setTimestamp(System.currentTimeMillis());
		binaryEncryptedMessage.setHash(multiHashString);	
		binaryEncryptedMessage.setName(spfsBlockResult.getMerkleNode().get(0).name.get());
		binaryEncryptedMessage.setType(spfsBlockResult.getMerkleNode().get(0).type.toString());
		binaryEncryptedMessage.setKeywords(dataHashPathEntity.getKeywords());
		binaryEncryptedMessage.setMetaData(JsonUtils.toJson(dataHashPathEntity.getMetadata()));
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		binaryEncryptedMessage.setDigest(new String(
				org.bouncycastle.util.encoders.Hex.encode(messageDigest.digest(multiHashString.getBytes()))));

		return binaryEncryptedMessage;
	}

	@Override
	public String cleanupPinnedContentUsingPOST(String multiHash) throws IOException, ApiException {
		XpxSdkGlobalConstants.getProximaxConnection().pin.rm(Multihash.fromBase58(multiHash));
		XpxSdkGlobalConstants.getProximaxConnection().repo.gc();

		return "Clean up success";

	}

	private PublishResult exposeAndPinBinary(String name, byte[] binary) throws IOException, ApiException {
		PublishResult result = new PublishResult();
		NamedStreamable.ByteArrayWrapper byteArrayWrapper = new NamedStreamable.ByteArrayWrapper(name, binary);
		List<MerkleNode> node = XpxSdkGlobalConstants.getProximaxConnection().add(byteArrayWrapper);
		List<Multihash> pinned = XpxSdkGlobalConstants.getProximaxConnection().pin.add(node.get(0).hash);
		result.setMerkleNode(node);
		result.setMultiHash(pinned);
		return result;
	}
	
	public PublishResult exposeAndPinPath(String path) throws Exception {

		PublishResult result = new PublishResult();
		NamedStreamable.FileWrapper fileWrapper = new NamedStreamable.FileWrapper(Paths.get(path).toFile());
		List<MerkleNode> node = XpxSdkGlobalConstants.getProximaxConnection().add(fileWrapper);
		List<Multihash> pinned = XpxSdkGlobalConstants.getProximaxConnection().pin.add(node.get(0).hash);
		result.setMerkleNode(node);
		result.setMultiHash(pinned);
		return result;

	}
	
	private PublishResult exposeBinary(String name, byte[] binary) throws IOException, ApiException {
		PublishResult result = new PublishResult();
		NamedStreamable.ByteArrayWrapper byteArrayWrapper = new NamedStreamable.ByteArrayWrapper(name, binary);
		List<MerkleNode> node = XpxSdkGlobalConstants.getProximaxConnection().add(byteArrayWrapper);
		result.setMerkleNode(node);
		return result;
	}

	private PublishResult getBinaryHashOnly(String name, byte[] binary) throws IOException, ApiException {
		PublishResult result = null;

		// store it in ipfs
		result = new PublishResult();
		NamedStreamable.ByteArrayWrapper byteArrayWrapper = new NamedStreamable.ByteArrayWrapper(name, binary);
		List<MerkleNode> node = XpxSdkGlobalConstants.getProximaxConnection().add(byteArrayWrapper);
		result.setMerkleNode(node);

		return result;
	}
}
