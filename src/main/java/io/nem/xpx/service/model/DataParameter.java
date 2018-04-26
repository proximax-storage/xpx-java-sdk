package io.nem.xpx.service.model;

import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Quantity;

public class DataParameter {

	/** The message type. */
	private int messageType;
	
	/** The sender private key. */
	private String senderPrivateKey;
	
	/** The recipient public key. */
	private String recipientPublicKey;
	
	
	private String contentType;
	
	private String encoding;
	
	/** The name. */
	private String name;
	
	/** The keywords. */
	private String keywords;
	
	/** The meta data. */
	private String metaData;
	
	/** The mosaics. */
	private Mosaic[] mosaics;


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
	 * Gets the sender private key.
	 *
	 * @return the sender private key
	 */
	public String getSenderPrivateKey() {
		return senderPrivateKey;
	}
	
	/**
	 * Sets the sender private key.
	 *
	 * @param senderPrivateKey the new sender private key
	 */
	public void setSenderPrivateKey(String senderPrivateKey) {
		this.senderPrivateKey = senderPrivateKey;
	}
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Gets the recipient public key.
	 *
	 * @return the recipient public key
	 */
	public String getRecipientPublicKey() {
		return recipientPublicKey;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Sets the recipient public key.
	 *
	 * @param recipientPublicKey the new recipient public key
	 */
	public void setRecipientPublicKey(String recipientPublicKey) {
		this.recipientPublicKey = recipientPublicKey;
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
		return mosaics;
	}
	
	/**
	 * Sets the mosaics.
	 *
	 * @param mosaics the new mosaics
	 */
	public void setMosaics(Mosaic[] mosaics) {
		this.mosaics = mosaics;
	}
	
	public void addMosaic(Mosaic mosaic) {
		this.mosaics[this.mosaics.length - 1] = mosaic;
	}
}
