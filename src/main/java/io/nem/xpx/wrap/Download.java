package io.nem.xpx.wrap;

import org.nem.core.crypto.CryptoEngine;
import org.nem.core.crypto.CryptoEngines;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;
import org.nem.core.utils.HexEncoder;
import io.nem.builder.api.TransactionApi;
import io.nem.utils.JsonUtils;
import io.nem.xpx.DownloadApi;
import io.nem.xpx.model.BinaryTransactionEncryptedMessage;

public class Download {

	private PeerConnection peerConnection;
	private DownloadApi downloadApi = new DownloadApi();
	private CryptoEngine engine;

	public Download(PeerConnection peerConnection) {
		this.peerConnection = peerConnection;
		this.engine = CryptoEngines.ed25519Engine();
		this.downloadApi = new DownloadApi();
	}

	public DownloadData downloadData(String nemHash, String senderOrReceiverPrivateKey,
			String senderOrReceiverPublicKey) {
		DownloadData downloadData = new DownloadData();
		byte[] securedResponse = null;
		SecureMessage message;
		BinaryTransactionEncryptedMessage binaryEncryptedData = new BinaryTransactionEncryptedMessage();
		try {

			// get the addresses
			// private key address
			PrivateKey privateKey = PrivateKey.fromHexString(senderOrReceiverPrivateKey);
			KeyPair keyPair = new KeyPair(privateKey);
			String senderOrReceiverPrivateKeyAddress = Address.fromPublicKey(keyPair.getPublicKey()).toString();

			// Evauate the transaction.
			TransferTransaction transaction = (TransferTransaction) TransactionApi.getTransaction(nemHash).getEntity();

			if (transaction.getMessage().getType() == 2) {
				if (transaction.getSigner().getAddress().getEncoded().equals(senderOrReceiverPrivateKeyAddress)) {

					message = SecureMessage.fromEncodedPayload(
							new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
							new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
							transaction.getMessage().getEncodedPayload());

					binaryEncryptedData = JsonUtils.fromJson(new String(message.getDecodedPayload()),
							BinaryTransactionEncryptedMessage.class);
					
					securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

					byte[] decrypted = engine
							.createBlockCipher(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine),
									new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine))
							.decrypt(HexEncoder.getBytes(new String(securedResponse)));

					downloadData.setData(decrypted);
					downloadData.setDataMessage(binaryEncryptedData);
					downloadData.setMessageType(MessageTypes.SECURE);
					return downloadData;
				} else if (transaction.getRecipient().getAddress().getEncoded()
						.equals(senderOrReceiverPrivateKeyAddress)) {

					message = SecureMessage.fromEncodedPayload(
							new Account(new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine)),
							new Account(new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine)),
							transaction.getMessage().getEncodedPayload());

					binaryEncryptedData = JsonUtils.fromJson(new String(message.getDecodedPayload()),
							BinaryTransactionEncryptedMessage.class);

					securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

					byte[] decrypted = engine
							.createBlockCipher(
									new KeyPair(PrivateKey.fromHexString(senderOrReceiverPrivateKey), engine),
									new KeyPair(PublicKey.fromHexString(senderOrReceiverPublicKey), engine))
							.decrypt(HexEncoder.getBytes(new String(securedResponse)));

					downloadData.setData(decrypted);
					downloadData.setDataMessage(binaryEncryptedData);
					downloadData.setMessageType(MessageTypes.SECURE);
					return downloadData;

				}

			} else if (transaction.getMessage().getType() == 1) {

				binaryEncryptedData = JsonUtils.fromJson(new String(transaction.getMessage().getDecodedPayload()),
						BinaryTransactionEncryptedMessage.class);

				securedResponse = downloadApi.downloadStreamUsingHashUsingPOST(binaryEncryptedData.getHash());

				downloadData.setData(securedResponse);
				downloadData.setDataMessage(binaryEncryptedData);
				downloadData.setMessageType(MessageTypes.PLAIN);
				return downloadData;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return downloadData;
	}

}
