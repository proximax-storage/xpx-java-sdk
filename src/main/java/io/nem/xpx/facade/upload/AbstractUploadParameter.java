package io.nem.xpx.facade.upload;

import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import io.nem.xpx.utils.JsonUtils;
import org.nem.core.model.mosaic.Mosaic;

import java.util.Map;


/**
 * The Class DataParameter.
 */
public abstract class AbstractUploadParameter {

	/** The sender private key. */
	private String senderPrivateKey;
	
	/** The recipient public key. */
	private String receiverPublicKey;
	
	/** The content type. */
	private String contentType;
	
	/** The name. */
	private String name;
	
	/** The keywords. */
	private String keywords;
	
	/** The meta data. */
	private String metaData;

	// TODO use List
	/** The mosaics. */
	private Mosaic[] mosaics;
	
	private PrivacyStrategy privacyStrategy = PrivacyStrategyFactory.plainPrivacy();

	/**
	 * Gets the sender or receiver private key.
	 *
	 * @return the sender or receiver private key
	 */
	public String getSenderPrivateKey() {
		return senderPrivateKey;
	}

	/**
	 * Sets the sender or receiver private key.
	 *
	 * @param senderPrivateKey the new sender or receiver private key
	 */
	public void setSenderPrivateKey(String senderPrivateKey) {
		this.senderPrivateKey = senderPrivateKey;
	}

	/**
	 * Gets the receiver or sender public key.
	 *
	 * @return the receiver or sender public key
	 */
	public String getReceiverPublicKey() {
		return receiverPublicKey;
	}

	/**
	 * Sets the receiver or sender public key.
	 *
	 * @param receiverPublicKey the new receiver or sender public key
	 */
	public void setReceiverPublicKey(String receiverPublicKey) {
		this.receiverPublicKey = receiverPublicKey;
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
	public void setMetaData(Map<String, String> metaData) {
		this.metaData = metaData == null ? null : JsonUtils.toJson(metaData);
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

	public PrivacyStrategy getPrivacyStrategy() {
		return privacyStrategy;
	}

	public void setPrivacyStrategy(PrivacyStrategy privacyStrategy) {
		this.privacyStrategy = privacyStrategy;
	}
}
