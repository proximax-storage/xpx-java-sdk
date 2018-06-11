package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import org.nem.core.model.mosaic.Mosaic;

import java.util.Map;



/**
 * The Class AbstractUploadParameterBuilder.
 *
 * @param <NextBuildStepAfterPublicKey> the generic type
 * @param <FinalBuildSteps> the generic type
 */
public abstract class AbstractUploadParameterBuilder<NextBuildStepAfterPublicKey, FinalBuildSteps>
		implements
		CommonUploadBuildSteps<FinalBuildSteps>,
		SenderPrivateKeyStep,
		ReceiverPublicKeyStep {

	/** The instance. */
	protected AbstractUploadParameter instance;

	/**
	 * Instantiates a new abstract upload parameter builder.
	 *
	 * @param instance the instance
	 */
	protected AbstractUploadParameterBuilder(AbstractUploadParameter instance) {
		this.instance = instance;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.MosaicsStep#mosaics(org.nem.core.model.mosaic.Mosaic[])
	 */
	@Override
	public FinalBuildSteps mosaics(Mosaic... mosaics) {
		instance.setMosaics(mosaics);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.KeywordsStep#keywords(java.lang.String)
	 */
	@Override
	public FinalBuildSteps keywords(String keywords) {
		this.instance.setKeywords(keywords);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.MetadataStep#metadata(java.util.Map)
	 */
	@Override
	public FinalBuildSteps metadata(Map<String, String> metadata) {
		this.instance.setMetaData(metadata);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.PrivacyStrategyUploadStep#privacyStrategy(io.nem.xpx.strategy.privacy.PrivacyStrategy)
	 */
	@Override
	public FinalBuildSteps privacyStrategy(PrivacyStrategy privacyStrategy) {
		this.instance.setPrivacyStrategy(privacyStrategy);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.PrivacyStrategyUploadStep#plainPrivacy()
	 */
	@Override
	public FinalBuildSteps plainPrivacy() {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.plainPrivacy());
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.PrivacyStrategyUploadStep#securedWithNemKeysPrivacyStrategy()
	 */
	@Override
	public FinalBuildSteps securedWithNemKeysPrivacyStrategy() {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithNemKeysPrivacyStrategy(
				this.instance.getSenderPrivateKey(),
				this.instance.getReceiverPublicKey()));
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.PrivacyStrategyUploadStep#securedWithPasswordPrivacyStrategy(java.lang.String)
	 */
	@Override
	public FinalBuildSteps securedWithPasswordPrivacyStrategy(String password) {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithPasswordPrivacyStrategy(password));
		return (FinalBuildSteps) this;
	}


	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.SenderPrivateKeyStep#senderPrivateKey(java.lang.String)
	 */
	@Override
	public ReceiverPublicKeyStep senderPrivateKey(String senderPrivateKeyStep) {
		this.instance.setSenderPrivateKey(senderPrivateKeyStep);
		return this;
	}

	/* (non-Javadoc)
	 * @see io.nem.xpx.builder.steps.ReceiverPublicKeyStep#receiverPublicKey(java.lang.String)
	 */
	@Override
	public NextBuildStepAfterPublicKey receiverPublicKey(String receiverPublicKey) {
		this.instance.setReceiverPublicKey(receiverPublicKey);
		return (NextBuildStepAfterPublicKey) this;
	}

}
