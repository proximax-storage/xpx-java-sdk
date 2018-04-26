package io.nem.xpx.facade;

import java.util.ArrayList;
import java.util.List;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.model.ncc.TransactionMetaDataPair;
import org.nem.core.serialization.JsonSerializer;

import io.nem.ApiException;
import io.nem.xpx.facade.connection.PeerConnection;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.service.intf.AccountApi;
import io.nem.xpx.service.local.LocalAccountApi;
import io.nem.xpx.service.model.PeerConnectionNotFoundException;
import io.nem.xpx.service.remote.RemoteAccountApi;

public class Account {

	/** The peer connection. */
	private PeerConnection peerConnection;

	/** The engine. */
	private CryptoEngine engine;

	private AccountApi accountApi;
	
	private boolean isLocalPeerConnection = false;

	/**
	 * Instantiates a new search.
	 *
	 * @param peerConnection
	 *            the peer connection
	 * @throws PeerConnectionNotFoundException
	 */
	public Account(PeerConnection peerConnection) throws PeerConnectionNotFoundException {

		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		if (peerConnection instanceof RemotePeerConnection) {
			this.accountApi = new RemoteAccountApi();
		} else {
			this.isLocalPeerConnection = true;
			this.accountApi = new LocalAccountApi();
		}

		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
	}
	

	public String getIncomingTransactions(String publicKey, String privateKey) throws ApiException {
		return null;
	}


	public String getAllTransactions(String publicKey, String privateKey) throws ApiException {
//		List<TransactionMetaDataPair> listOfTxnMetaDataPair = io.nem.xpx.service.TransactionApi
//				.getAllTransactions(KeyConvertor.getAddressFromPublicKey(publicKey));
//		List<String> transactionString = new ArrayList<String>();
//		for (TransactionMetaDataPair metaDataPair : listOfTxnMetaDataPair) {
//			if(checkIfTxnHaveXPXMosaic(metaDataPair.getEntity())) {
//				transactionString.add(JsonSerializer.serializeToJson(metaDataPair.getEntity()).toJSONString());
//			}
//		}
		return null;
	}



	public String getOutgoingTransactions(String publicKey,String privateKey) throws ApiException {
		return null;
	}


	public String getUnconfirmedTransactions(String publicKey, String privateKey) throws ApiException {
		return null;
	}
}

	
