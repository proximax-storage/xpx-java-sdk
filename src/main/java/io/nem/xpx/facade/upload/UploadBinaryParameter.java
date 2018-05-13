package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.model.DataParameter;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import io.nem.xpx.utils.ContentTypeUtils;
import org.apache.maven.surefire.shade.org.codehaus.plexus.util.StringUtils;
import org.nem.core.model.mosaic.Mosaic;

import java.io.Serializable;




/**
 * The Class 
 */
public class UploadBinaryParameter extends DataParameter implements Serializable {

	
	/** The data. */
	private byte[] data;
	
	
	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	public static SenderOrReceiverPrivateKeyStep<ReceiverOrSenderPublicKeyStep<BinaryDataStep<BuildStep>>> create() {
		return new Builder();
	}

	public interface BuildStep extends
			ContentTypeStep<BuildStep>,
			NameStep<BuildStep>,
			KeywordsStep<BuildStep>,
			MetadataStep<BuildStep>,
			MosaicsStep<BuildStep>,
			PrivacyStrategyStep<BuildStep> {

		UploadBinaryParameter build();
	}

	private static class Builder
			implements SenderOrReceiverPrivateKeyStep,
			ReceiverOrSenderPublicKeyStep,
			BinaryDataStep,
			BuildStep {

		UploadBinaryParameter instance;

		private Builder() {
			instance = new UploadBinaryParameter();
		}

		@Override
		public BuildStep mosaics(Mosaic... mosaics) {
			instance.setMosaics(mosaics);
			return this;
		}

		@Override
		public BuildStep keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}

		@Override
		public BuildStep metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}

		@Override
		public BuildStep privacyStrategy(PrivacyStrategy privacyStrategy) {
			this.instance.setPrivacyStrategy(privacyStrategy);
			return this;
		}

		@Override
		public BuildStep plainPrivacy() {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.plainPrivacy());
			return this;
		}

		@Override
		public BuildStep securedWithNemKeysPrivacyStrategy() {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithNemKeysPrivacyStrategy(
					this.instance.getSenderOrReceiverPrivateKey(),
					this.instance.getReceiverOrSenderPublicKey()));
			return this;
		}

		@Override
		public BuildStep securedWithPasswordPrivacyStrategy(String password) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithPasswordPrivacyStrategy(password));
			return this;
		}


		@Override
		public ReceiverOrSenderPublicKeyStep senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		@Override
		public BinaryDataStep receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public BuildStep data(byte[] data) {
			this.instance.setData(data);
			return this;
		}

		@Override
		public BuildStep contentType(String contentType) {
			this.instance.setContentType(contentType);
			return this;
		}

		@Override
		public BuildStep name(String name) {
			this.instance.setName(name);
			return this;
		}

		@Override
		public UploadBinaryParameter build() {
			if (StringUtils.isEmpty(this.instance.getContentType()))
				this.instance.setContentType(ContentTypeUtils.detectContentType(this.instance.data));
			return instance;
		}

	}


}
