package io.nem.xpx.facade.multisigupload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.facade.upload.UploadTextDataParameter;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import io.nem.xpx.utils.ContentTypeUtils;
import org.apache.maven.surefire.shade.org.codehaus.plexus.util.StringUtils;
import org.nem.core.model.mosaic.Mosaic;

import java.io.Serializable;



/**
 * The Class MultisigUploadDataParameter.
 */
public class MultisigUploadTextDataParameter extends UploadTextDataParameter implements Serializable {
	
	/** The multisig public key. */
	private String multisigPublicKey;

	/**
	 * Gets the multisig public key.
	 *
	 * @return the multisig public key
	 */
	public String getMultisigPublicKey() {
		return multisigPublicKey;
	}

	
	/**
	 * Sets the multisig public key.
	 *
	 * @param multisigPublicKey the new multisig public key
	 */
	public void setMultisigPublicKey(String multisigPublicKey) {
		this.multisigPublicKey = multisigPublicKey;
	}

	public static MultisigPublicKeyStep<SenderOrReceiverPrivateKeyStep<ReceiverOrSenderPublicKeyStep<TextDataStep<BuildStep>>>> createMultisigParam() {
		return new Builder();
	}

	public interface BuildStep extends
			NameStep<BuildStep>,
			EncodingStep<BuildStep>,
			ContentTypeStep<BuildStep>,
			KeywordsStep<BuildStep>,
			MetadataStep<BuildStep>,
			MosaicsStep<BuildStep>,
            PrivacyStrategyUploadStep<BuildStep> {

		MultisigUploadTextDataParameter build();
	}

	private static class Builder
			implements MultisigPublicKeyStep,
			SenderOrReceiverPrivateKeyStep,
			ReceiverOrSenderPublicKeyStep,
			TextDataStep,
			BuildStep {

		MultisigUploadTextDataParameter instance;

		private Builder() {
			instance = new MultisigUploadTextDataParameter();
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
		public SenderOrReceiverPrivateKeyStep multisigPublicKeyStep(String multisigPublicKeyStep) {
			this.instance.setMultisigPublicKey(multisigPublicKeyStep);
			return this;
		}

		@Override
		public ReceiverOrSenderPublicKeyStep senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		@Override
		public TextDataStep receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public BuildStep data(String data) {
			this.instance.setData(data);
			return this;
		}

		@Override
		public BuildStep contentType(String contentType) {
			this.instance.setContentType(contentType);
			return this;
		}

		@Override
		public BuildStep encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}

		@Override
		public BuildStep name(String name) {
			this.instance.setName(name);
			return this;
		}

		@Override
		public MultisigUploadTextDataParameter build() {
			if (StringUtils.isEmpty(this.instance.getContentType()))
				this.instance.setContentType(ContentTypeUtils.detectContentType(this.instance.getData()));
			return instance;
		}
	}

}
