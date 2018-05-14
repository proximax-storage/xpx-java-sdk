package io.nem.xpx.facade.download;

import io.nem.xpx.builder.steps.NemHashStep;
import io.nem.xpx.builder.steps.PrivacyStrategyDownloadStep;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;

import java.io.Serializable;

public class DownloadParameter implements Serializable {

	private String nemHash;

	private String senderOrReceiverPrivateKey;

	private String receiverOrSenderPublicKey;

	private PrivacyStrategy privacyStrategy = PrivacyStrategyFactory.plainPrivacy();

	public String getNemHash() {
		return nemHash;
	}

	public void setNemHash(String nemHash) {
		this.nemHash = nemHash;
	}

	public String getSenderOrReceiverPrivateKey() {
		return senderOrReceiverPrivateKey;
	}

	public void setSenderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
		this.senderOrReceiverPrivateKey = senderOrReceiverPrivateKey;
	}

	public String getReceiverOrSenderPublicKey() {
		return receiverOrSenderPublicKey;
	}

	public void setReceiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
		this.receiverOrSenderPublicKey = receiverOrSenderPublicKey;
	}

	public PrivacyStrategy getPrivacyStrategy() {
		return privacyStrategy;
	}

	public void setPrivacyStrategy(PrivacyStrategy privacyStrategy) {
		this.privacyStrategy = privacyStrategy;
	}

	public static PrivacyStrategyDownloadStep<BuildStep> create() {
		return new Builder();
	}

	public interface BuildStep extends PrivacyStrategyDownloadStep<BuildStep> {

		DownloadParameter build();
	}

	private static class Builder
			implements NemHashStep,
			BuildStep {

		DownloadParameter instance;

		private Builder() {
			instance = new DownloadParameter();
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
		public BuildStep securedWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithNemKeysPrivacyStrategy(
					senderOrReceiverPrivateKey, receiverOrSenderPublicKey));
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public BuildStep securedWithPasswordPrivacyStrategy(String password) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithPasswordPrivacyStrategy(password));
			return this;
		}

		@Override
		public BuildStep nemHash(String nemHash) {
			this.instance.setNemHash(nemHash);
			return this;
		}

		@Override
		public DownloadParameter build() {
			return instance;
		}

	}


}
