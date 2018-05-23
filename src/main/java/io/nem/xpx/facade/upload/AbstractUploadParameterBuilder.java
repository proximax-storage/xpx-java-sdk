package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.CommonUploadBuildSteps;
import io.nem.xpx.builder.steps.ReceiverOrSenderPublicKeyStep;
import io.nem.xpx.builder.steps.SenderOrReceiverPrivateKeyStep;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import org.nem.core.model.mosaic.Mosaic;

import java.util.Map;


public abstract class AbstractUploadParameterBuilder<NextBuildStepAfterPublicKey, FinalBuildSteps>
		implements
		CommonUploadBuildSteps<FinalBuildSteps>,
		SenderOrReceiverPrivateKeyStep,
		ReceiverOrSenderPublicKeyStep {

	protected AbstractUploadParameter instance;

	protected AbstractUploadParameterBuilder(AbstractUploadParameter instance) {
		this.instance = instance;
	}

	@Override
	public FinalBuildSteps mosaics(Mosaic... mosaics) {
		instance.setMosaics(mosaics);
		return  (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps keywords(String keywords) {
		this.instance.setKeywords(keywords);
		return  (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps metadata(Map<String, String> metadata) {
		this.instance.setMetaData(metadata);
		return  (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps privacyStrategy(PrivacyStrategy privacyStrategy) {
		this.instance.setPrivacyStrategy(privacyStrategy);
		return  (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps plainPrivacy() {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.plainPrivacy());
		return  (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps securedWithNemKeysPrivacyStrategy() {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithNemKeysPrivacyStrategy(
				this.instance.getSenderOrReceiverPrivateKey(),
				this.instance.getReceiverOrSenderPublicKey()));
		return  (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps securedWithPasswordPrivacyStrategy(String password) {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithPasswordPrivacyStrategy(password));
		return (FinalBuildSteps) this;
	}


	@Override
	public ReceiverOrSenderPublicKeyStep senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
		this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
		return this;
	}

	@Override
	public NextBuildStepAfterPublicKey receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
		this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
		return (NextBuildStepAfterPublicKey) this;
	}

}
