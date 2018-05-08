package io.nem.xpx.facade.account;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.model.ResourceHashMessageJsonEntity;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.AccountApi;
import io.nem.xpx.service.intf.SearchApi;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.TransactionMetaDataPair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * The Class Account.
 */
@SuppressWarnings("unused")
public class Account extends AbstractFacadeService {

	protected final String publicKey;

	/** The peer connection. */

	private final PeerConnection peerConnection;

	/** The engine. */
	private final CryptoEngine engine;

	/** The account api. */
	private final AccountApi accountApi;

	/** The nem transaction api. */
	protected final NemTransactionApi nemTransactionApi;

	protected final SearchApi searchApi;

	/** The is local peer connection. */
	private final boolean isLocalPeerConnection;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public Account(PeerConnection peerConnection, String publicKey) {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.accountApi = peerConnection.getAccountApi();
		this.searchApi = peerConnection.getSearchApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.peerConnection = peerConnection;
		this.nemTransactionApi = peerConnection.getNemTransactionApi();
		this.engine = CryptoEngines.ed25519Engine();
		this.publicKey = publicKey;
	}

	/**
	 * Gets the incoming transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the incoming transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<TransactionMetaDataPair> getIncomingTransactions()
			throws ApiException, InterruptedException, ExecutionException {

		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getIncomingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

	/**
	 * Gets the all transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @return the all transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<TransactionMetaDataPair> getAllTransactions()
			throws ApiException, InterruptedException, ExecutionException {

		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getAllTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

	/**
	 * Gets the outgoing transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @param privateKey
	 *            the private key
	 * @return the outgoing transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<TransactionMetaDataPair> getOutgoingTransactions()
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getOutgoingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

	/**
	 * Gets the unconfirmed transactions.
	 *
	 * @param publicKey
	 *            the public key
	 * @param privateKey
	 *            the private key
	 * @return the unconfirmed transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<TransactionMetaDataPair> getUnconfirmedTransactions()
			throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getUnconfirmedTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

	public List<ResourceHashMessageJsonEntity> searchByName(String xPvkey, String name)
			throws ApiException, InterruptedException, ExecutionException {
		return searchApi.searchTransactionWithKeywordUsingGET(xPvkey, this.publicKey, name);
	}

	/**
	 * Search by keyword.
	 *
	 * @param xPvkey
	 *            the x pvkey
	 * @param xPubkey
	 *            the x pubkey
	 * @param keywords
	 *            the keywords
	 * @return the list
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<ResourceHashMessageJsonEntity> searchByKeyword(String xPvkey, String keywords)
			throws ApiException, InterruptedException, ExecutionException {
		return searchApi.searchTransactionWithKeywordUsingGET(xPvkey, this.publicKey, keywords);
	}

	/**
	 * Search by meta data key value.
	 *
	 * @param xPvkey
	 *            the x pvkey
	 * @param xPubkey
	 *            the x pubkey
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 * @return the string
	 * @throws ApiException
	 *             the api exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 * @throws ExecutionException
	 *             the execution exception
	 */
	public List<ResourceHashMessageJsonEntity> searchByMetaDataKeyValue(String xPvkey, String key, String value)
			throws ApiException, InterruptedException, ExecutionException {
		return searchApi.searchTransactionWithMetadataKeyValuePair(xPvkey, this.publicKey, key, value);
	}

}
