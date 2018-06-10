package io.nem.xpx.model;

import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

import io.nem.xpx.adapters.cipher.CustomEncryption;



/**
 * The Class DataParameter.
 */
public class DataParameter {

	/** The message type. */
	private int messageType;
	
	/** The sender private key. */
	private String senderOrReceiverPrivateKey;
	
	/** The recipient public key. */
	private String receiverOrSenderPublicKey;
	
	/** The content type. */
	private String contentType;
	
	/** The encoding. */
	private String encoding;
	
	/** The name. */
	private String name;
	
	/** The keywords. */
	private String keywords;
	
	/** The meta data. */
	private String metaData;

	// TODO use List
	/** The mosaics. */
	private Mosaic[] mosaics;
	
	/** The custom encryption. */
	private CustomEncryption customEncryption;
	

	

	/**
	 * Gets the custom encryption.
	 *
	 * @return the custom encryption
	 */
	public CustomEncryption getCustomEncryption() {
		return customEncryption;
	}

	/**
	 * Sets the custom encryption.
	 *
	 * @param customEncryption the new custom encryption
	 */
	public void setCustomEncryption(CustomEncryption customEncryption) {
		this.customEncryption = customEncryption;
	}

	/**
	 * Gets the message type.
	 *
	 * @return the message type
	 */
	public int getMessageType() {
		return messageType;
	}
	
	/**
	 * Sets the message type.
	 *
	 * @param messageType the new message type
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	

	
	/**
	 * Gets the sender or receiver private key.
	 *
	 * @return the sender or receiver private key
	 */
	public String getSenderOrReceiverPrivateKey() {
		return senderOrReceiverPrivateKey;
	}

	/**
	 * Sets the sender or receiver private key.
	 *
	 * @param senderOrReceiverPrivateKey the new sender or receiver private key
	 */
	public void setSenderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
		this.senderOrReceiverPrivateKey = senderOrReceiverPrivateKey;
	}

	/**
	 * Gets the receiver or sender public key.
	 *
	 * @return the receiver or sender public key
	 */
	public String getReceiverOrSenderPublicKey() {
		return receiverOrSenderPublicKey;
	}

	/**
	 * Sets the receiver or sender public key.
	 *
	 * @param receiverOrSenderPublicKey the new receiver or sender public key
	 */
	public void setReceiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
		this.receiverOrSenderPublicKey = receiverOrSenderPublicKey;
	}

	/**
	 * Gets the encoding.
	 *
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Sets the encoding.
	 *
	 * @param encoding the new encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}


	/**
	 * Gets the content type.
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the keywords.
	 *
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	
	/**
	 * Sets the keywords.
	 *
	 * @param keywords the new keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * Gets the meta data.
	 *
	 * @return the meta data
	 */
	public String getMetaData() {
		return metaData;
	}
	
	/**
	 * Sets the meta data.
	 *
	 * @param metaData the new meta data
	 */
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}
	
	/**
	 * Gets the mosaics.
	 *
	 * @return the mosaics
	 */
	public Mosaic[] getMosaics() {
		return mosaics == null ? new Mosaic[0] : mosaics;
	}
	
	/**
	 * Sets the mosaics.
	 *
	 * @param mosaics the new mosaics
	 */
	public void setMosaics(Mosaic[] mosaics) {
		this.mosaics = mosaics;
	}
	
	/**
	 * Adds the mosaic.
	 *
	 * @param mosaic the mosaic
	 */
	public void addMosaic(Mosaic mosaic) {
		this.mosaics[this.mosaics.length - 1] = mosaic;
	}
}
