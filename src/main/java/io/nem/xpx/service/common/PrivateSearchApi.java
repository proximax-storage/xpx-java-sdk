package io.nem.xpx.service.common;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.service.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.model.buffers.ResourceHashMessage;
import io.nem.xpx.utils.JsonUtils;

public abstract class PrivateSearchApi {

	/** The nem transaction api. */
	private final NemTransactionApi nemTransactionApi;

	/**
	 * Instantiates a new local search api.
	 *
	 * @param nemTransactionApi
	 * 
	 *            the nem transaction api
	 */
	public PrivateSearchApi(NemTransactionApi nemTransactionApi) {
		this.nemTransactionApi = nemTransactionApi;
	}

	public List<ResourceHashMessageJsonEntity> searchTransactionWithName(String xPvKey, String xPubkey, String name)
			throws ApiException, InterruptedException, ExecutionException {

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		encryptedMessage.addAll(getNextForNameSearch(xPvKey, xPubkey, name, ""));
		return encryptedMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.nem.xpx.service.intf.SearchApi#searchTransactionWithMetadataKeyValuePair(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<ResourceHashMessageJsonEntity> searchTransactionWithMetadataKeyValuePair(String xPvKey, String xPubkey,
			String key, String value) throws ApiException, InterruptedException, ExecutionException {

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		encryptedMessage.addAll(getNextForMetadataSearch(xPvKey, xPubkey, key, value, ""));
		return encryptedMessage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.nem.xpx.service.intf.SearchApi#searchTransactionWithKeywordUsingGET(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public List<ResourceHashMessageJsonEntity> searchTransactionWithKeyword(String xPvKey, String xPubkey,
			String keywords) throws ApiException, InterruptedException, ExecutionException {
		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		encryptedMessage.addAll(getNextForKeywordSearch(xPvKey, xPubkey, keywords, ""));
		return encryptedMessage;
	}

	private List<ResourceHashMessageJsonEntity> getNextForNameSearch(String xPvKey, String xPubkey, String name,
			String hash) throws ApiException, InterruptedException, ExecutionException {

		PrivateKey pvKey = PrivateKey.fromHexString(xPvKey);
		KeyPair keyPair = new KeyPair(pvKey);
		String privateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		List<TransactionMetaDataPair> listOfTransactionMetadataPair = null;
		if (hash.equals("")) {
			listOfTransactionMetadataPair = nemTransactionApi.getAllTransactions(privateKeyAddress);
		} else {
			listOfTransactionMetadataPair = nemTransactionApi.getAllTransactions(privateKeyAddress, hash);
		}

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		// loop thru and search for any keyword.
		String currentHash = "";
		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {

			if (tmp.getEntity() instanceof TransferTransaction) {
				currentHash = tmp.getMetaData().getHash().toString();
				TransferTransaction transferTransaction = (TransferTransaction) tmp.getEntity();

				if (checkIfTxnHaveXPXMosaic(transferTransaction)) {

					boolean found = false;
					try {

						if (transferTransaction.getMessage().getType() == 1) {

							ResourceHashMessage resourceMessage = ResourceHashMessage
									.getRootAsResourceHashMessage(ByteBuffer.wrap(
											Base64.decodeBase64(transferTransaction.getMessage().getDecodedPayload())));

							if (resourceMessage.name().toLowerCase().contains(name.toLowerCase())) {
								encryptedMessage.add(toEntity(currentHash,resourceMessage));
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

							if (resourceMessage.name().toLowerCase().contains(name.toLowerCase())) {
								encryptedMessage.add(toEntity(currentHash,resourceMessage));
							}


						}
						
						

					} catch (Exception e) {
						//Logger.info("Error on decoding NEM Transaction Message." + e.getMessage());
						continue;
					}
				}
			}

		}
		if (!currentHash.equals("")) {
			encryptedMessage.addAll(getNextForNameSearch(xPvKey, xPubkey, name, currentHash));
		}
		return encryptedMessage;
	}

	private List<ResourceHashMessageJsonEntity> getNextForKeywordSearch(String xPvKey, String xPubkey, String keywords,
			String hash) throws ApiException, InterruptedException, ExecutionException {

		PrivateKey pvKey = PrivateKey.fromHexString(xPvKey);
		KeyPair keyPair = new KeyPair(pvKey);
		String privateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		List<TransactionMetaDataPair> listOfTransactionMetadataPair = null;
		if (hash.equals("")) {
			listOfTransactionMetadataPair = nemTransactionApi.getAllTransactions(privateKeyAddress);
		} else {
			listOfTransactionMetadataPair = nemTransactionApi.getAllTransactions(privateKeyAddress, hash);
		}

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		// loop thru and search for any keyword.
		String currentHash = "";
		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {

			if (tmp.getEntity() instanceof TransferTransaction) {
				currentHash = tmp.getMetaData().getHash().toString();
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
								encryptedMessage.add(toEntity(currentHash,resourceMessage));
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
								encryptedMessage.add(toEntity(currentHash,resourceMessage));
							}

						}

					} catch (Exception e) {
						//Logger.info("Error on decoding NEM Transaction Message." + e.getMessage());
						continue;
					}
				}
			}

		}
		if (!currentHash.equals("")) {
			encryptedMessage.addAll(getNextForKeywordSearch(xPvKey, xPubkey, keywords, currentHash));
		}
		return encryptedMessage;
	}

	private List<ResourceHashMessageJsonEntity> getNextForMetadataSearch(String xPvKey, String xPubkey, String key,
			String value, String hash) throws ApiException, InterruptedException, ExecutionException {

		PrivateKey pvKey = PrivateKey.fromHexString(xPvKey);
		KeyPair keyPair = new KeyPair(pvKey);
		String privateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

		List<TransactionMetaDataPair> listOfTransactionMetadataPair = null;
		if (hash.equals("")) {
			listOfTransactionMetadataPair = nemTransactionApi.getAllTransactions(privateKeyAddress);
		} else {
			listOfTransactionMetadataPair = nemTransactionApi.getAllTransactions(privateKeyAddress, hash);
		}

		List<ResourceHashMessageJsonEntity> encryptedMessage = new ArrayList<ResourceHashMessageJsonEntity>();
		// loop thru and search for any keyword.
		String currentHash = "";
		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {

			if (tmp.getEntity() instanceof TransferTransaction) {
				currentHash = tmp.getMetaData().getHash().toString();
				TransferTransaction transferTransaction = (TransferTransaction) tmp.getEntity();
				if (checkIfTxnHaveXPXMosaic(transferTransaction)) {

					boolean found = false;
					try {

						if (transferTransaction.getMessage().getType() == 1) {

							ResourceHashMessage resourceMessage = ResourceHashMessage
									.getRootAsResourceHashMessage(ByteBuffer.wrap(
											Base64.decodeBase64(transferTransaction.getMessage().getDecodedPayload())));

							if (resourceMessage.metaData() != null) {
								@SuppressWarnings("unchecked")
								Map<String, String> jsonToMap = JsonUtils.fromJson(resourceMessage.metaData(),
										Map.class);
								if (jsonToMap.containsKey(key) && jsonToMap.get(key).equals(value)) {
									encryptedMessage.add(toEntity(currentHash,resourceMessage));
								}
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
								@SuppressWarnings("unchecked")
								Map<String, String> jsonToMap = JsonUtils.fromJson(resourceMessage.metaData(),
										Map.class);
								if (jsonToMap.containsKey(key) && jsonToMap.get(key).equals(value)) {
									encryptedMessage.add(toEntity(currentHash,resourceMessage));
								}
							}

						}

					} catch (Exception e) {
						//Logger.info("Error on decoding NEM Transaction Message." + e.getMessage());
						continue;
					}
				}
			}
		}
		if (!currentHash.equals("")) {
			encryptedMessage.addAll(getNextForMetadataSearch(xPvKey, xPubkey, key, value, currentHash));
		}
		return encryptedMessage;
	}

	/**
	 * To entity.
	 *
	 * @param resourceMessage
	 *            the resource message
	 * @return the resource hash message json entity
	 */
	protected ResourceHashMessageJsonEntity toEntity(String nemHash,ResourceHashMessage resourceMessage) {
		
		ResourceHashMessageJsonEntity resourceHashMessageJsonEntity = new ResourceHashMessageJsonEntity();
		resourceHashMessageJsonEntity.setDigest(resourceMessage.digest());
		resourceHashMessageJsonEntity.setHash(resourceMessage.hash());
		resourceHashMessageJsonEntity.setKeywords(resourceMessage.keywords());
		resourceHashMessageJsonEntity.setMetaData(resourceMessage.metaData());
		resourceHashMessageJsonEntity.setName(resourceMessage.name());
		resourceHashMessageJsonEntity.setTimestamp(resourceMessage.timestamp());
		resourceHashMessageJsonEntity.setType(resourceMessage.type());
		resourceHashMessageJsonEntity.setNemHash(nemHash);
		return resourceHashMessageJsonEntity;
	}

	/**
	 * Check if txn have XPX mosaic.
	 *
	 * @param transaction
	 *            the transaction
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
