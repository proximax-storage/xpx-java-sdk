/*
 * 
 */
package io.nem.xpx.facade;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import io.nem.api.ApiException;
import io.nem.xpx.LocalSearchApi;
import io.nem.xpx.LocalUploadApi;
import io.nem.xpx.RemoteSearchApi;
import io.nem.xpx.RemoteUploadApi;
import io.nem.xpx.SearchApi;
import io.nem.xpx.TransactionAndAnnounceApi;
import io.nem.xpx.TransactionApi;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.utils.JsonUtils;


/**
 * The Class Search.
 */
public class Search {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;
	
	private SearchApi searchApi;
	private boolean isLocalPeerConnection = false;
	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException 
	 */
	public Search(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.searchApi = new RemoteSearchApi();
		} else {
			this.isLocalPeerConnection = true;
			this.searchApi = new LocalSearchApi();
		}

		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
	}


	public String searchByKeyword(String xPvkey, String xPubkey,String keywords) throws ApiException, InterruptedException, ExecutionException {
		return JsonUtils.toJson(searchApi.searchTransactionWithKeywordUsingGET(xPvkey, xPubkey, keywords));
	}

	public String searchByKeyword(String xPubkey, String keywords) throws io.nem.api.ApiException, InterruptedException, ExecutionException {
		return JsonUtils.toJson(searchApi.searchTransactionWithKeywordUsingGET(xPubkey, keywords));
	}

	
	public String searchByMetadata(String xPubkey, String text){
		// TODO Auto-generated method stub
		return null;
	}
	
//	public String searchByKeywordRegex(String privateKey, String regex)
//			throws InterruptedException, ExecutionException, UnsupportedEncodingException, ApiException {
//
//		PrivateKey pvKey = PrivateKey.fromHexString(privateKey);
//		KeyPair keyPair = new KeyPair(pvKey);
//		String privateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();
//
//		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
//				.getAllTransactionsWithPageSize(privateKeyAddress, "100");
//
//		List<BinaryTransactionEncryptedMessage> encryptedMessage = new ArrayList<BinaryTransactionEncryptedMessage>();
//		// loop thru and search for any keyword.
//
//		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {
//			// we only process plain. We don't have access to the secure
//			// messages at this point.
//			if (tmp.getEntity() instanceof TransferTransaction) {
//				TransferTransaction binaryTransaferTransaction = (TransferTransaction) tmp.getEntity();
//
//				boolean found = false;
//				try {
//					BinaryTransactionEncryptedMessage message = null;
//					if (binaryTransaferTransaction.getMessage().getType() == 1) {
//
//						message = JsonUtils
//								.fromJson(
//										new String(binaryTransaferTransaction.getAttachment().getMessage()
//												.getDecodedPayload(), "UTF-8"),
//										BinaryTransactionEncryptedMessage.class);
//
//						// String[] commaSeparatedkeywordsSplit =
//						// commaSeparatedkeywords.split(",");
//						// for (String keyword : commaSeparatedkeywordsSplit) {
//						// boolean a = Pattern.matches(regex,
//						// message.getKeywords());
//						boolean a = Pattern.compile(regex).matcher(message.getKeywords()).matches();
//						if (a) {
//							found = true;
//						}
//						// }
//
//					} else if (binaryTransaferTransaction.getMessage().getType() == 2) {
//
//						SecureMessage secureMessage = null;
//						if (binaryTransaferTransaction.getSigner().getAddress().getEncoded()
//								.equals(privateKeyAddress)) {
//							secureMessage = SecureMessage.fromEncodedPayload(
//									new Account(new org.nem.core.crypto.KeyPair(PrivateKey.fromHexString(privateKey))),
//									new Account(Address.fromPublicKey(
//											binaryTransaferTransaction.getRecipient().getAddress().getPublicKey())),
//									binaryTransaferTransaction.getMessage().getEncodedPayload());
//						} else if (binaryTransaferTransaction.getRecipient().getAddress().getEncoded()
//								.equals(privateKeyAddress)) {
//							secureMessage = SecureMessage.fromEncodedPayload(
//									new Account(Address.fromPublicKey(
//											binaryTransaferTransaction.getRecipient().getAddress().getPublicKey())),
//									new Account(new org.nem.core.crypto.KeyPair(PrivateKey.fromHexString(privateKey))),
//									binaryTransaferTransaction.getMessage().getEncodedPayload());
//						}
//
//						message = JsonUtils.fromJson(new String(secureMessage.getDecodedPayload(), "UTF-8"),
//								BinaryTransactionEncryptedMessage.class);
//
//						if (Pattern.matches(regex, message.getKeywords())) {
//							found = true;
//						}
//
//					}
//
//					if (found) {
//						encryptedMessage.add(message);
//					}
//
//				} catch (Exception e) {
//					continue;
//				}
//			}
//		}
//		return JsonUtils.toJson(encryptedMessage);
//	}
//
//	/**
//	 * Search all transaction with keyword.
//	 *
//	 * @param privateKey the private key
//	 * @param commaSeparatedkeywords the comma separatedkeywords
//	 * @return the string
//	 * @throws InterruptedException the interrupted exception
//	 * @throws ExecutionException the execution exception
//	 * @throws UnsupportedEncodingException the unsupported encoding exception
//	 * @throws ApiException the api exception
//	 */
//	public String searchAllTransactionWithKeyword(String privateKey, String commaSeparatedkeywords)
//			throws InterruptedException, ExecutionException, UnsupportedEncodingException, ApiException {
//
//		PrivateKey pvKey = PrivateKey.fromHexString(privateKey);
//		KeyPair keyPair = new KeyPair(pvKey);
//		String privateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();
//
//		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
//				.getAllTransactionsWithPageSize(privateKeyAddress, "100");
//
//		List<BinaryTransactionEncryptedMessage> encryptedMessage = new ArrayList<BinaryTransactionEncryptedMessage>();
//		// loop thru and search for any keyword.
//
//		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {
//			// we only process plain. We don't have access to the secure
//			// messages at this point.
//			if (tmp.getEntity() instanceof TransferTransaction) {
//				TransferTransaction binaryTransaferTransaction = (TransferTransaction) tmp.getEntity();
//
//				boolean found = false;
//				try {
//					BinaryTransactionEncryptedMessage message = null;
//					if (binaryTransaferTransaction.getMessage().getType() == 1) {
//
//						message = JsonUtils
//								.fromJson(
//										new String(binaryTransaferTransaction.getAttachment().getMessage()
//												.getDecodedPayload(), "UTF-8"),
//										BinaryTransactionEncryptedMessage.class);
//
//						String[] commaSeparatedkeywordsSplit = commaSeparatedkeywords.split(",");
//						for (String keyword : commaSeparatedkeywordsSplit) {
//							if (message.getKeywords().contains(keyword)) {
//								found = true;
//								break;
//							}
//						}
//
//					} else if (binaryTransaferTransaction.getMessage().getType() == 2) {
//
//						SecureMessage secureMessage = null;
//						if (binaryTransaferTransaction.getSigner().getAddress().getEncoded()
//								.equals(privateKeyAddress)) {
//							secureMessage = SecureMessage.fromEncodedPayload(
//									new Account(new org.nem.core.crypto.KeyPair(PrivateKey.fromHexString(privateKey))),
//									new Account(Address.fromPublicKey(
//											binaryTransaferTransaction.getRecipient().getAddress().getPublicKey())),
//									binaryTransaferTransaction.getMessage().getEncodedPayload());
//						} else if (binaryTransaferTransaction.getRecipient().getAddress().getEncoded()
//								.equals(privateKeyAddress)) {
//							secureMessage = SecureMessage.fromEncodedPayload(
//									new Account(Address.fromPublicKey(
//											binaryTransaferTransaction.getRecipient().getAddress().getPublicKey())),
//									new Account(new org.nem.core.crypto.KeyPair(PrivateKey.fromHexString(privateKey))),
//									binaryTransaferTransaction.getMessage().getEncodedPayload());
//						}
//
//						message = JsonUtils.fromJson(new String(secureMessage.getDecodedPayload(), "UTF-8"),
//								BinaryTransactionEncryptedMessage.class);
//
//						String[] commaSeparatedkeywordsSplit = commaSeparatedkeywords.split(",");
//						for (String keyword : commaSeparatedkeywordsSplit) {
//							if (message.getKeywords().contains(keyword)) {
//								found = true;
//								break;
//							}
//						}
//
//					}
//
//					if (found) {
//						encryptedMessage.add(message);
//					}
//
//				} catch (Exception e) {
//					continue;
//				}
//			}
//		}
//		return JsonUtils.toJson(encryptedMessage);
//	}
//
//	/**
//	 * Search all public transaction with keyword.
//	 *
//	 * @param publicKey the public key
//	 * @param commaSeparatedkeywords the comma separatedkeywords
//	 * @return the string
//	 * @throws InterruptedException the interrupted exception
//	 * @throws ExecutionException the execution exception
//	 * @throws UnsupportedEncodingException the unsupported encoding exception
//	 * @throws ApiException the api exception
//	 */
//	public String searchAllPublicTransactionWithKeyword(String publicKey, String commaSeparatedkeywords)
//			throws InterruptedException, ExecutionException, UnsupportedEncodingException, ApiException {
//
//		PublicKey pbKey = PublicKey.fromHexString(publicKey);
//		Address address = Address.fromPublicKey(pbKey);
//		String publicKeyAddress = address.toString();
//
//		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
//				.getAllTransactionsWithPageSize(publicKeyAddress, "100");
//
//		List<BinaryTransactionEncryptedMessage> encryptedMessage = new ArrayList<BinaryTransactionEncryptedMessage>();
//		// loop thru and search for any keyword.
//
//		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {
//			// we only process plain. We don't have access to the secure
//			// messages at this point.
//			if (tmp.getEntity() instanceof TransferTransaction) {
//
//				TransferTransaction binaryTransaferTransaction = (TransferTransaction) tmp.getEntity();
//				try {
//					if (binaryTransaferTransaction.getMessage().getType() == 1) {
//
//						boolean found = false;
//						BinaryTransactionEncryptedMessage message = JsonUtils
//								.fromJson(
//										new String(binaryTransaferTransaction.getAttachment().getMessage()
//												.getDecodedPayload(), "UTF-8"),
//										BinaryTransactionEncryptedMessage.class);
//
//						String[] commaSeparatedkeywordsSplit = commaSeparatedkeywords.split(",");
//						for (String keyword : commaSeparatedkeywordsSplit) {
//							if (message.getKeywords().contains(keyword)) {
//								found = true;
//								break;
//							}
//						}
//
//						if (found) {
//							encryptedMessage.add(message);
//						}
//
//					}
//				} catch (Exception e) {
//					continue;
//				}
//			}
//		}
//		return JsonUtils.toJson(encryptedMessage);
//	}
//
//	/**
//	 * Search all public transaction with regex keyword.
//	 *
//	 * @param publicKey the public key
//	 * @param regex the regex
//	 * @return the string
//	 * @throws InterruptedException the interrupted exception
//	 * @throws ExecutionException the execution exception
//	 * @throws UnsupportedEncodingException the unsupported encoding exception
//	 * @throws ApiException the api exception
//	 */
//	public String searchAllPublicTransactionWithRegexKeyword(String publicKey, String regex)
//			throws InterruptedException, ExecutionException, UnsupportedEncodingException, ApiException {
//
//		PublicKey pbKey = PublicKey.fromHexString(publicKey);
//		Address address = Address.fromPublicKey(pbKey);
//		String publicKeyAddress = address.toString();
//
//		List<TransactionMetaDataPair> listOfTransactionMetadataPair = TransactionApi
//				.getAllTransactionsWithPageSize(publicKeyAddress, "100");
//
//		List<BinaryTransactionEncryptedMessage> encryptedMessage = new ArrayList<BinaryTransactionEncryptedMessage>();
//		// loop thru and search for any keyword.
//
//		for (TransactionMetaDataPair tmp : listOfTransactionMetadataPair) {
//			// we only process plain. We don't have access to the secure
//			// messages at this point.
//			if (tmp.getEntity() instanceof TransferTransaction) {
//
//				TransferTransaction binaryTransaferTransaction = (TransferTransaction) tmp.getEntity();
//				try {
//					if (binaryTransaferTransaction.getMessage().getType() == 1) {
//
//						BinaryTransactionEncryptedMessage message = JsonUtils
//								.fromJson(
//										new String(binaryTransaferTransaction.getAttachment().getMessage()
//												.getDecodedPayload(), "UTF-8"),
//										BinaryTransactionEncryptedMessage.class);
//
//						boolean a = Pattern.compile(regex).matcher(message.getKeywords()).matches();
//						if (a) {
//							encryptedMessage.add(message);
//						}
//
//					}
//				} catch (Exception e) {
//					continue;
//				}
//			}
//		}
//		return JsonUtils.toJson(encryptedMessage);
//	}

}
