package io.nem.xpx.facade.account;

import io.nem.xpx.service.NemTransactionApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Address;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.JsonSerializer;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.service.intf.AccountApi;
import io.nem.xpx.service.local.LocalAccountApi;
import io.nem.xpx.service.remote.RemoteAccountApi;

/**
 * The Class Account.
 */
public class Account extends AbstractFacadeService {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;

	/** The account api. */
	private AccountApi accountApi;

	protected final NemTransactionApi nemTransactionApi;

	/** The is local peer connection. */
	private boolean isLocalPeerConnection = false;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 *             the peer connection not found exception
	 */
	public Account(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.accountApi = new RemoteAccountApi(((RemotePeerConnection) peerConnection).getApiClient());
		} else {
			this.isLocalPeerConnection = true;
			this.accountApi = new LocalAccountApi(new NemTransactionApi(peerConnection.getNodeEndpoint()));
		}

		this.peerConnection = peerConnection;
		this.nemTransactionApi = peerConnection.getNemTransactionApi();
		this.engine = CryptoEngines.ed25519Engine();
	}


	public List<TransactionMetaDataPair> getIncomingTransactions(String publicKey)
			throws ApiException, InterruptedException, ExecutionException {
		
		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getIncomingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
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
	 * @param privateKey
	 *            the private key
	 * @return the all transactions
	 * @throws ApiException
	 *             the api exception
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public List<TransactionMetaDataPair> getAllTransactions(String publicKey) throws ApiException, InterruptedException, ExecutionException {

		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getAllTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
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
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public List<TransactionMetaDataPair> getOutgoingTransactions(String publicKey, String privateKey) throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getOutgoingTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
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
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public List<TransactionMetaDataPair> getUnconfirmedTransactions(String publicKey, String privateKey) throws ApiException, InterruptedException, ExecutionException {
		List<TransactionMetaDataPair> returnListOfTxnMetaDataPair = new ArrayList<TransactionMetaDataPair>();
		List<TransactionMetaDataPair> listOfTxnMetaDataPair = nemTransactionApi
				.getUnconfirmedTransactions(Address.fromPublicKey(PublicKey.fromHexString(publicKey)).getEncoded());
		List<String> transactionString = new ArrayList<String>();
		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
			if (checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
				returnListOfTxnMetaDataPair.add(metaDataPair);
			}
		}

		return returnListOfTxnMetaDataPair;
	}

}
