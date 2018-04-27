/*
 * Proximax P2P Storage REST API
 * Proximax P2P Storage REST API
 *
 * OpenAPI spec version: v0.0.1
 * Contact: proximax.storage@proximax.io
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.nem.xpx.service.local;

import io.nem.ApiCallback;
import io.nem.ApiClient;
import io.nem.ApiException;
import io.nem.ApiResponse;
import io.nem.Configuration;
import io.nem.Pair;
import io.nem.ProgressRequestBody;
import io.nem.ProgressResponseBody;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.service.TransactionApi;
import io.nem.xpx.service.intf.SearchApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.JsonUtils;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Transaction;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.TransactionMetaDataPair;


/**
 * The Class LocalSearchApi.
 */
public class LocalSearchApi implements SearchApi {

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.SearchApi#searchTransactionWithKeywordUsingGET(java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResourceHashMessageJsonEntity> searchTransactionWithKeywordUsingGET(String xPubkey, String keywords)
			throws ApiException, InterruptedException, ExecutionException {

		PublicKey pbKey = PublicKey.fromHexString(xPubkey);
		Address address = Address.fromPublicKey(pbKey);
		String publicKeyAddress = address.toString();

		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
				.getAllTransactionsWithPageSize(publicKeyAddress, "100");

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		// loop thru and search for any keyword.

		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {

			// we only process plain. We don't have access to the secure
			// messages at this point.
			if (tmp.getEntity() instanceof TransferTransaction) {

				TransferTransaction transferTransaction = (TransferTransaction) tmp.getEntity();
				if (checkIfTxnHaveXPXMosaic(transferTransaction)) {
					try {
						if (transferTransaction.getMessage().getType() == 1) {

							boolean found = false;
							ResourceHashMessage resourceMessage = ResourceHashMessage
									.getRootAsResourceHashMessage(ByteBuffer.wrap(
											Base64.decodeBase64(transferTransaction.getMessage().getDecodedPayload())));

							String[] commaSeparatedkeywordsSplit = keywords.split(",");
							for (String keyword : commaSeparatedkeywordsSplit) {
								if (resourceMessage.keywords().contains(keyword)) {
									found = true;
									break;
								}
							}

							if (found) {
								encryptedMessage.add(toEntity(resourceMessage));
							}

						}
					} catch (Exception e) {
						continue;
					}
				}
			}
		}
		return encryptedMessage;
	}
	
	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.SearchApi#searchAllPublicTransactionWithMetadataKeyValuePair(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResourceHashMessageJsonEntity>  searchAllPublicTransactionWithMetadataKeyValuePair(String xPubkey, String key, String value)
			throws InterruptedException, ExecutionException, ApiException {
		PublicKey pbKey = PublicKey.fromHexString(xPubkey);
		Address address = Address.fromPublicKey(pbKey);
		String publicKeyAddress = address.toString();
		
		
		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
				.getAllTransactionsWithPageSize(publicKeyAddress, "100");

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		// loop thru and search for any keyword.

		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {
			// we only process plain. We don't have access to the secure
			// messages at this point.
			if (tmp.getEntity() instanceof TransferTransaction) {
				TransferTransaction transferTransaction = (TransferTransaction) tmp.getEntity();
				if (checkIfTxnHaveXPXMosaic(transferTransaction)) {
					try {
						System.out.println(transferTransaction.getMessage().getType());
						if (transferTransaction.getMessage().getType() == 1) {

							boolean found = false;
							ResourceHashMessage resourceMessage = ResourceHashMessage
									.getRootAsResourceHashMessage(ByteBuffer.wrap(
											Base64.decodeBase64(transferTransaction.getMessage().getDecodedPayload())));
							if (resourceMessage.metaData() != null) {
								Map<String, String> jsonToMap = JsonUtils.fromJson(resourceMessage.metaData(), Map.class);
								if (jsonToMap.containsKey(key) && jsonToMap.get(key).equals(value)) {
									found = true;
								}
							}

							if (found) {
								encryptedMessage.add(toEntity(resourceMessage));
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		return encryptedMessage;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.SearchApi#searchTransactionWithMetadataKeyValuePair(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResourceHashMessageJsonEntity> searchTransactionWithMetadataKeyValuePair(String xPvKey, String xPubkey,
			String key, String value) throws ApiException, InterruptedException, ExecutionException {
		
		PrivateKey pvKey = PrivateKey.fromHexString(xPvKey);
		KeyPair keyPair = new KeyPair(pvKey);
		String privateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
				.getAllTransactionsWithPageSize(privateKeyAddress, "100");

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		// loop thru and search for any keyword.

		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {
			// we only process plain. We don't have access to the secure
			// messages at this point.
			if (tmp.getEntity() instanceof TransferTransaction) {
				TransferTransaction transferTransaction = (TransferTransaction) tmp.getEntity();
				if (checkIfTxnHaveXPXMosaic(transferTransaction)) {

					boolean found = false;
					try {

						if (transferTransaction.getMessage().getType() == 1) {

							ResourceHashMessage resourceMessage = ResourceHashMessage
									.getRootAsResourceHashMessage(ByteBuffer.wrap(
											Base64.decodeBase64(transferTransaction.getMessage().getDecodedPayload())));

							if (resourceMessage.metaData() != null) {
								Map<String, String> jsonToMap = JsonUtils.fromJson(resourceMessage.metaData(), Map.class);
								if (jsonToMap.containsKey(key) && jsonToMap.get(key).equals(value)) {
									found = true;
								}
							}

							if (found) {
								encryptedMessage.add(toEntity(resourceMessage));
							}

						} else if (transferTransaction.getMessage().getType() == 2) {

							SecureMessage secureMessage = null;
							if (transferTransaction.getSigner().getAddress().getEncoded().equals(privateKeyAddress)) {
								secureMessage = SecureMessage.fromEncodedPayload(
										new Account(new KeyPair(PrivateKey.fromHexString(xPvKey))),
										new Account(new KeyPair(PublicKey.fromHexString(xPubkey))),
										transferTransaction.getMessage().getEncodedPayload());

							} else if (transferTransaction.getRecipient().getAddress().getEncoded()
									.equals(privateKeyAddress)) {
								secureMessage = SecureMessage.fromEncodedPayload(
										new Account(new KeyPair(PublicKey.fromHexString(xPubkey))),
										new Account(new KeyPair(PrivateKey.fromHexString(xPvKey))),
										transferTransaction.getMessage().getEncodedPayload());
							}

							ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
									ByteBuffer.wrap(Base64.decodeBase64(secureMessage.getDecodedPayload())));

							if (resourceMessage.metaData() != null) {
								Map<String, String> jsonToMap = JsonUtils.fromJson(resourceMessage.metaData(), Map.class);
								if (jsonToMap.containsKey(key) && jsonToMap.get(key).equals(value)) {
									found = true;
								}
							}

							if (found) {
								encryptedMessage.add(toEntity(resourceMessage));
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		return encryptedMessage;
	}
	
	/* (non-Javadoc)
	 * @see io.nem.xpx.service.intf.SearchApi#searchTransactionWithKeywordUsingGET(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<ResourceHashMessageJsonEntity> searchTransactionWithKeywordUsingGET(String xPvKey, String xPubkey,
			String keywords) throws ApiException, InterruptedException, ExecutionException {

		PrivateKey pvKey = PrivateKey.fromHexString(xPvKey);
		KeyPair keyPair = new KeyPair(pvKey);
		String privateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
				.getAllTransactionsWithPageSize(privateKeyAddress, "100");

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		// loop thru and search for any keyword.

		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {
			// we only process plain. We don't have access to the secure
			// messages at this point.
			if (tmp.getEntity() instanceof TransferTransaction) {
				TransferTransaction transferTransaction = (TransferTransaction) tmp.getEntity();
				if (checkIfTxnHaveXPXMosaic(transferTransaction)) {

					boolean found = false;
					try {

						if (transferTransaction.getMessage().getType() == 1) {

							ResourceHashMessage resourceMessage = ResourceHashMessage
									.getRootAsResourceHashMessage(ByteBuffer.wrap(
											Base64.decodeBase64(transferTransaction.getMessage().getDecodedPayload())));

							String[] commaSeparatedkeywordsSplit = keywords.split(",");
							for (String keyword : commaSeparatedkeywordsSplit) {
								if (resourceMessage.keywords().contains(keyword)) {
									found = true;
									break;
								}
							}

							if (found) {
								encryptedMessage.add(toEntity(resourceMessage));
							}

						} else if (transferTransaction.getMessage().getType() == 2) {

							SecureMessage secureMessage = null;
							if (transferTransaction.getSigner().getAddress().getEncoded().equals(privateKeyAddress)) {
								secureMessage = SecureMessage.fromEncodedPayload(
										new Account(new KeyPair(PrivateKey.fromHexString(xPvKey))),
										new Account(new KeyPair(PublicKey.fromHexString(xPubkey))),
										transferTransaction.getMessage().getEncodedPayload());

							} else if (transferTransaction.getRecipient().getAddress().getEncoded()
									.equals(privateKeyAddress)) {
								secureMessage = SecureMessage.fromEncodedPayload(
										new Account(new KeyPair(PublicKey.fromHexString(xPubkey))),
										new Account(new KeyPair(PrivateKey.fromHexString(xPvKey))),
										transferTransaction.getMessage().getEncodedPayload());
							}

							ResourceHashMessage resourceMessage = ResourceHashMessage.getRootAsResourceHashMessage(
									ByteBuffer.wrap(Base64.decodeBase64(secureMessage.getDecodedPayload())));

							String[] commaSeparatedkeywordsSplit = keywords.split(",");
							for (String keyword : commaSeparatedkeywordsSplit) {
								if (resourceMessage.keywords().contains(keyword)) {
									found = true;
									break;
								}
							}

							if (found) {
								encryptedMessage.add(toEntity(resourceMessage));
							}

						}

					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		return encryptedMessage;
	}


	/**
	 * To entity.
	 *
	 * @param resourceMessage the resource message
	 * @return the resource hash message json entity
	 */
	private ResourceHashMessageJsonEntity toEntity(ResourceHashMessage resourceMessage) {

		ResourceHashMessageJsonEntity resourceHashMessageJsonEntity = new ResourceHashMessageJsonEntity();
		resourceHashMessageJsonEntity.setDigest(resourceMessage.digest());
		resourceHashMessageJsonEntity.setHash(resourceMessage.hash());
		resourceHashMessageJsonEntity.setKeywords(resourceMessage.keywords());
		resourceHashMessageJsonEntity.setMetaData(resourceMessage.metaData());
		resourceHashMessageJsonEntity.setName(resourceMessage.name());
		resourceHashMessageJsonEntity.setTimestamp(resourceMessage.timestamp());
		resourceHashMessageJsonEntity.setType(resourceMessage.type());
		return resourceHashMessageJsonEntity;
	}

	/**
	 * Check if txn have XPX mosaic.
	 *
	 * @param transaction the transaction
	 * @return true, if successful
	 */
	protected boolean checkIfTxnHaveXPXMosaic(Transaction transaction) {

		if (transaction instanceof TransferTransaction) {
			Iterator<Mosaic> mosaicIter = ((TransferTransaction) transaction).getMosaics().iterator();
			while (mosaicIter.hasNext()) {
				Mosaic mosaic = mosaicIter.next();
				if (mosaic.getMosaicId().getNamespaceId().getRoot().toString().equals("prx")
						&& mosaic.getMosaicId().getName().equals("xpx")) {
					return true;
				}
			}

		}
		return false;
	}



}