/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.facade.multisigupload;

import io.proximax.xpx.builder.MultisigTransactionBuilder;
import io.proximax.xpx.builder.TransferTransactionBuilder;
import io.proximax.xpx.exceptions.ApiException;
import io.proximax.xpx.exceptions.PeerConnectionNotFoundException;
import io.proximax.xpx.facade.AbstractFacadeService;
import io.proximax.xpx.facade.connection.PeerConnection;
import io.proximax.xpx.facade.upload.UploadException;
import io.proximax.xpx.facade.upload.UploadResult;
import io.proximax.xpx.model.NemMessageType;
import io.proximax.xpx.model.RequestAnnounceDataSignature;
import io.proximax.xpx.service.IpfsGatewaySyncService;
import io.proximax.xpx.service.UploadDelegate;
import io.proximax.xpx.service.intf.TransactionAndAnnounceApi;
import io.proximax.xpx.utils.CryptoUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.nem.core.crypto.*;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;




/**
 * The Class MultisigUpload.
 */
public class MultisigUpload  extends AbstractFacadeService {
	/** The peer connection. */
	private final PeerConnection peerConnection;

	/** The engine. */
	private final CryptoEngine engine;

	private final UploadDelegate uploadDelegate;

	/** The publish and announce api. */
	private final TransactionAndAnnounceApi transactionAndAnnounceApi;

	/** The is local peer connection. */
	private final boolean isLocalPeerConnection;

	private final IpfsGatewaySyncService ipfsGatewaySyncService;

	/**
	 * Instantiates a new upload.
	 *
	 * @param peerConnection            the peer connection
	 */
	public MultisigUpload(PeerConnection peerConnection) {
		if (peerConnection == null) {
			throw new PeerConnectionNotFoundException("PeerConnection can't be null");
		}

		this.peerConnection = peerConnection;
		this.transactionAndAnnounceApi = peerConnection.getTransactionAndAnnounceApi();
		this.isLocalPeerConnection = peerConnection.isLocal();
		this.engine = CryptoEngines.ed25519Engine();
		this.ipfsGatewaySyncService = new IpfsGatewaySyncService(peerConnection.getSyncGateways());
		this.uploadDelegate = new UploadDelegate(peerConnection.getUploadApi());
	}

	
	/**
	 * Upload data on multisig transaction.
	 *
	 * @param parameters the parameters
	 * @return the multisig upload data
	 * @throws ApiException the api exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadResult uploadDataOnMultisigTransaction(MultisigUploadTextDataParameter parameters) throws ApiException, NoSuchAlgorithmException, InvalidKeyException,
			InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, UploadException {

		MultisigUploadResult multisigUploadData = handleMultisigDataUpload(parameters);
		return multisigUploadData;
	}

	/**
	 * Upload file on multisig transaction.
	 *
	 * @param uploadParameter the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadResult uploadFileOnMultisigTransaction(MultisigUploadFileParameter uploadParameter)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadResult multisigUploadData = handleMultisigFileUpload(uploadParameter);
		return multisigUploadData;

	}
	
	/**
	 * Upload binary on multisig transaction.
	 *
	 * @param uploadParameter the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws InvalidKeyException the invalid key exception
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadResult uploadBinaryOnMultisigTransaction(MultisigUploadBinaryParameter uploadParameter)
			throws IOException, ApiException, InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UploadException {

		MultisigUploadResult multisigUploadData = handleMultisigBinaryUpload(uploadParameter);
		return multisigUploadData;

	}


	/**
	 * Handle multisig data upload.
	 *
	 * @param param the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadResult handleMultisigDataUpload(MultisigUploadTextDataParameter param) throws IOException, ApiException, UploadException {
		byte[] encrypted = null;
		byte[] response = null;
		UploadDelegate.ResourceHashMessageWrapper hashMessageWrapper = null;
		String publishedData = "";
		String secretKey = null;

		try {
			if (param.getPrivacyStrategy().getNemMessageType() == NemMessageType.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(param.getData().getBytes(), keyRandom.toCharArray());
				hashMessageWrapper =  uploadDelegate.uploadTextToIpfs(encrypted, param.getName(), param.getContentType(), param.getEncoding(),
                        param.getKeywords(), param.getMetaDataAsString());
				secretKey = keyRandom;
			} else { // PLAIN
				hashMessageWrapper = uploadDelegate.uploadTextToIpfs(param.getData().getBytes(), param.getName(), param.getContentType(),
                        param.getEncoding(), param.getKeywords(), param.getMetaDataAsString());
			}
			final Message nemMessage = param.getPrivacyStrategy().encodeToMessage(hashMessageWrapper.getData());
			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(param.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(param.getReceiverPublicKey()))))
						.amount(Amount.fromNem(1l))
						.version(2)
						.message(nemMessage)
						.buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(param.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(param.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(param.getReceiverPublicKey()))))
						.addMosaics(param.getMosaics())
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(param.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

		} catch (Exception e) {

			uploadDelegate.deletePinnedContent(hashMessageWrapper.getResourceHashMessage().hash());
			throw new UploadException(e);
		} finally {
			ipfsGatewaySyncService.syncToGatewaysAsynchronously(hashMessageWrapper.getResourceHashMessage().hash());
		}

		return new MultisigUploadResult(new UploadResult(hashMessageWrapper.getResourceHashMessage(), publishedData), secretKey);
	}

	/**
	 * Handle multisig file upload.
	 *
	 * @param param the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadResult handleMultisigFileUpload(MultisigUploadFileParameter param) throws IOException, ApiException, UploadException {
		byte[] encrypted = null;
		byte[] response = null;
		UploadDelegate.ResourceHashMessageWrapper hashMessageWrapper = null;
		String publishedData = "";
		String secretKey = null;

		try {
			if (param.getPrivacyStrategy().getNemMessageType() == NemMessageType.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = org.apache.commons.codec.binary.Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(FileUtils.readFileToByteArray(param.getFile()), keyRandom.toCharArray());
				hashMessageWrapper = uploadDelegate.uploadBinaryToIpfs(encrypted, param.getName(), param.getContentType(),
                        param.getKeywords(), param.getMetaDataAsString());
				secretKey = keyRandom;
			} else { // PLAIN
				hashMessageWrapper = uploadDelegate.uploadBinaryToIpfs(FileUtils.readFileToByteArray(param.getFile()), param.getName(),
                        param.getContentType(), param.getKeywords(), param.getMetaDataAsString());
			}

			final Message nemMessage = param.getPrivacyStrategy().encodeToMessage(response);

			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(param.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(param.getReceiverPublicKey()))))
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(param.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(param.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(param.getReceiverPublicKey()))))
						.addMosaics(param.getMosaics())
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(param.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

		} catch (Exception e) {

			uploadDelegate.deletePinnedContent(hashMessageWrapper.getResourceHashMessage().hash());
			throw new UploadException(e);
		}

		return new MultisigUploadResult(new UploadResult(hashMessageWrapper.getResourceHashMessage(), publishedData), secretKey);
	}
	
	/**
	 * Handle multisig binary upload.
	 *
	 * @param param the upload parameter
	 * @return the multisig upload data
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ApiException the api exception
	 * @throws UploadException the upload exception
	 */
	public MultisigUploadResult handleMultisigBinaryUpload(MultisigUploadBinaryParameter param) throws IOException, ApiException, UploadException {
		byte[] encrypted = null;
		byte[] response = null;
		UploadDelegate.ResourceHashMessageWrapper hashMessageWrapper = null;
		String publishedData = "";
		String secretKey = null;

		try {
			if (param.getPrivacyStrategy().getNemMessageType() == NemMessageType.SECURE) {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				String keyRandom = org.apache.commons.codec.binary.Base64.encodeBase64String(keyGen.generateKey().getEncoded());

				encrypted = CryptoUtils.encrypt(param.getData(), keyRandom.toCharArray());
				hashMessageWrapper = uploadDelegate.uploadBinaryToIpfs(encrypted, param.getName(), param.getContentType(),
                        param.getKeywords(), param.getMetaDataAsString());
				secretKey = keyRandom;
			} else { // PLAIN
				hashMessageWrapper = uploadDelegate.uploadBinaryToIpfs(param.getData(), param.getName(), param.getContentType(),
                        param.getKeywords(), param.getMetaDataAsString());
			}

			final Message nemMessage = param.getPrivacyStrategy().encodeToMessage(response);

			if (this.isLocalPeerConnection) {

				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(param.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(param.getReceiverPublicKey()))))
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				NemAnnounceResult announceResult = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(param.getSenderPrivateKey()))))
						.otherTransaction(transaction)
						.buildAndSendMultisigTransaction();

				publishedData = announceResult.getTransactionHash().toString();

			} else {
				// Announce The Signature
				TransferTransaction transaction = new TransferTransactionBuilder(peerConnection.getTransactionFeeCalculators())
						.sender(new Account(new KeyPair(PublicKey.fromHexString(param.getMultisigPublicKey()))))
						.recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(param.getReceiverPublicKey()))))
						.addMosaics(param.getMosaics())
						.amount(Amount.fromNem(1l))
						.message(nemMessage)
						.buildTransaction();

				RequestAnnounceDataSignature requestAnnounceDataSignature = MultisigTransactionBuilder
						.peerConnection(peerConnection)
						.sender(new Account(new KeyPair(PrivateKey.fromHexString(param.getSenderPrivateKey()))))
						.otherTransaction(transaction).buildAndSignMultisigTransaction();

				// Return the NEM Txn Hash
				publishedData = transactionAndAnnounceApi
						.announceRequestPublishDataSignatureUsingPOST(requestAnnounceDataSignature);
			}

		} catch (Exception e) {

			uploadDelegate.deletePinnedContent(hashMessageWrapper.getResourceHashMessage().hash());
			throw new UploadException(e);
		}

		return new MultisigUploadResult(new UploadResult(hashMessageWrapper.getResourceHashMessage(), publishedData), secretKey);
	}

}
