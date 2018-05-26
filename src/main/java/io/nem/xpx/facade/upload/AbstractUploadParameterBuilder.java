package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import org.nem.core.model.mosaic.Mosaic;

import java.util.Map;


public abstract class AbstractUploadParameterBuilder<NextBuildStepAfterPublicKey, FinalBuildSteps>
		implements
		CommonUploadBuildSteps<FinalBuildSteps>,
		SenderPrivateKeyStep,
		ReceiverPublicKeyStep {

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
				this.instance.getSenderPrivateKey(),
				this.instance.getReceiverPublicKey()));
		return  (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps securedWithPasswordPrivacyStrategy(String password) {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithPasswordPrivacyStrategy(password));
		return (FinalBuildSteps) this;
	}


	@Override
	public ReceiverPublicKeyStep senderPrivateKey(String senderPrivateKeyStep) {
		this.instance.setSenderPrivateKey(senderPrivateKeyStep);
		return this;
	}

	@Override
	public NextBuildStepAfterPublicKey receiverPublicKey(String receiverPublicKey) {
		this.instance.setReceiverPublicKey(receiverPublicKey);
		return (NextBuildStepAfterPublicKey) this;
	}

}
