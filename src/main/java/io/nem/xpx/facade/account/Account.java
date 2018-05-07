package io.nem.xpx.facade.account;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.exceptions.PeerConnectionNotFoundException;
import io.nem.xpx.facade.AbstractFacadeService;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.service.NemTransactionApi;
import io.nem.xpx.service.intf.AccountApi;
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

	/** The peer connection. */
	
	private final PeerConnection peerConnection;

	/** The engine. */
	private final CryptoEngine engine;

	/** The account api. */
	private final AccountApi accountApi;

	/** The nem transaction api. */
	protected final NemTransactionApi nemTransactionApi;

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
	public Account(PeerConnection peerConnection) {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.accountApi = peerConnection.getAccountApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.peerConnection = peerConnection;
		this.nemTransactionApi = peerConnection.getNemTransactionApi();
		this.engine = CryptoEngines.ed25519Engine();
	}


	/**
	 * Gets the incoming transactions.
	 *
	 * @param publicKey the public key
	 * @return the incoming transactions
	 * @throws ApiException the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<TransactionMetaDataPair> getIncomingTransactions(String publicKey)
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
	 * @param publicKey            the public key
	 * @return the all transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<TransactionMetaDataPair> getAllTransactions(String publicKey) throws ApiException, InterruptedException, ExecutionException {

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
	 * @param publicKey            the public key
	 * @param privateKey            the private key
	 * @return the outgoing transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<TransactionMetaDataPair> getOutgoingTransactions(String publicKey) throws ApiException, InterruptedException, ExecutionException {
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
	 * @param publicKey            the public key
	 * @param privateKey            the private key
	 * @return the unconfirmed transactions
	 * @throws ApiException             the api exception
	 * @throws InterruptedException the interrupted exception
	 * @throws ExecutionException the execution exception
	 */
	public List<TransactionMetaDataPair> getUnconfirmedTransactions(String publicKey) throws ApiException, InterruptedException, ExecutionException {
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

}
