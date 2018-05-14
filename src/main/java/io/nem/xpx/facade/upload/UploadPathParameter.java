package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import org.nem.core.model.mosaic.Mosaic;

import java.io.Serializable;




/**
 * The Class UploadPathParameter.
 */
public class UploadPathParameter extends AbstractUploadParameter implements Serializable {


	/** The path. */
	private String path;
	
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public static SenderOrReceiverPrivateKeyStep<ReceiverOrSenderPublicKeyStep<PathStep<BuildStep>>> create() {
		return new Builder();
	}

	public interface BuildStep extends
			KeywordsStep<BuildStep>,
			MetadataStep<BuildStep>,
			MosaicsStep<BuildStep>,
			PrivacyStrategyUploadStep<BuildStep> {

		UploadPathParameter build();
	}

	private static class Builder
			implements SenderOrReceiverPrivateKeyStep,
			ReceiverOrSenderPublicKeyStep,
			PathStep,
			BuildStep {

		UploadPathParameter instance;

		private Builder() {
			instance = new UploadPathParameter();
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
		public PathStep receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public BuildStep path(String path) {
			this.instance.setPath(path);
			return this;
		}

		@Override
		public UploadPathParameter build() {
			return instance;
		}
	}

}
