package io.nem.xpx.facade.upload;

import io.nem.xpx.exceptions.KeywordsAboveMaxLengthLimitException;
import io.nem.xpx.exceptions.MetadataAboveMaxLengthLimitException;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import io.nem.xpx.utils.JsonUtils;
import org.nem.core.model.mosaic.Mosaic;

import java.util.Map;

import static java.lang.String.format;

public abstract class AbstractUploadParameter {

	public static final int MAX_LENGTH_ALLOWED_FOR_METADATA = 400;
	public static final int MAX_LENGTH_ALLOWED_FOR_KEYWORDS = 80;

	private String senderPrivateKey;
	
	private String receiverPublicKey;
	
	private String contentType;
	
	private String name;
	
	private String keywords;
	
	private String metaData;

	// TODO consider using List
	private Mosaic[] mosaics;
	
	private PrivacyStrategy privacyStrategy = PrivacyStrategyFactory.plainPrivacy();

	public String getSenderPrivateKey() {
		return senderPrivateKey;
	}

	public void setSenderPrivateKey(String senderPrivateKey) {
		this.senderPrivateKey = senderPrivateKey;
	}

	public String getReceiverPublicKey() {
		return receiverPublicKey;
	}

	public void setReceiverPublicKey(String receiverPublicKey) {
		this.receiverPublicKey = receiverPublicKey;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
 	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		if (keywords != null && keywords.length() > MAX_LENGTH_ALLOWED_FOR_KEYWORDS)
			throw new KeywordsAboveMaxLengthLimitException(
					format("The provided keywords exceeds the maximum %d characters allowed: %s",
							MAX_LENGTH_ALLOWED_FOR_KEYWORDS, keywords));
		this.keywords = keywords;
	}

	public String getMetaDataAsString() {
		return metaData;
	}

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

	public Mosaic[] getMosaics() {
		return mosaics == null ? new Mosaic[0] : mosaics;
	}
	
	public void setMosaics(Mosaic[] mosaics) {
		this.mosaics = mosaics;
	}

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
