package io.nem.xpx.facade.upload;

import io.nem.xpx.exceptions.KeywordsAboveMaxLengthLimitException;
import io.nem.xpx.exceptions.MetadataAboveMaxLengthLimitException;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import io.nem.xpx.utils.JsonUtils;
import org.nem.core.model.mosaic.Mosaic;

import java.util.Map;

import static java.lang.String.format;


/**
 * The Class AbstractUploadParameter.
 */
public abstract class AbstractUploadParameter {

	/** The Constant MAX_LENGTH_ALLOWED_FOR_METADATA. */
	public static final int MAX_LENGTH_ALLOWED_FOR_METADATA = 400;
	
	/** The Constant MAX_LENGTH_ALLOWED_FOR_KEYWORDS. */
	public static final int MAX_LENGTH_ALLOWED_FOR_KEYWORDS = 80;

	/** The sender private key. */
	private String senderPrivateKey;
	
	/** The receiver public key. */
	private String receiverPublicKey;
	
	/** The content type. */
	private String contentType;
	
	/** The name. */
	private String name;
	
	/** The keywords. */
	private String keywords;
	
	/** The meta data. */
	private String metaData;

	/** The mosaics. */
	// TODO consider using List
	private Mosaic[] mosaics;
	
	/** The privacy strategy. */
	private PrivacyStrategy privacyStrategy = PrivacyStrategyFactory.plainPrivacy();

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

	/**
	 * Gets the receiver public key.
	 *
	 * @return the receiver public key
	 */
	public String getReceiverPublicKey() {
		return receiverPublicKey;
	}

	/**
	 * Sets the receiver public key.
	 *
	 * @param receiverPublicKey the new receiver public key
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
		if (keywords != null && keywords.length() > MAX_LENGTH_ALLOWED_FOR_KEYWORDS)
			throw new KeywordsAboveMaxLengthLimitException(
					format("The provided keywords exceeds the maximum %d characters allowed: %s",
							MAX_LENGTH_ALLOWED_FOR_KEYWORDS, keywords));
		this.keywords = keywords;
	}

	/**
	 * Gets the meta data as string.
	 *
	 * @return the meta data as string
	 */
	public String getMetaDataAsString() {
		return metaData;
	}

	/**
	 * Sets the meta data.
	 *
	 * @param metaData the meta data
	 */
	public void setMetaData(Map<String, String> metaData) {
		if (metaData != null) {
			final String metadataAsString = JsonUtils.toJson(metaData);
			if (metadataAsString.length() > MAX_LENGTH_ALLOWED_FOR_METADATA)
				throw new MetadataAboveMaxLengthLimitException(
						format("The provided metadata exceeds the maximum %d characters allowed: %s",
								MAX_LENGTH_ALLOWED_FOR_METADATA, metadataAsString));
			this.metaData = metadataAsString;
		} else {
			this.metaData = null;
		}
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

	/**
	 * Gets the privacy strategy.
	 *
	 * @return the privacy strategy
	 */
	public PrivacyStrategy getPrivacyStrategy() {
		return privacyStrategy;
	}

	/**
	 * Sets the privacy strategy.
	 *
	 * @param privacyStrategy the new privacy strategy
	 */
	public void setPrivacyStrategy(PrivacyStrategy privacyStrategy) {
		this.privacyStrategy = privacyStrategy;
	}
}
