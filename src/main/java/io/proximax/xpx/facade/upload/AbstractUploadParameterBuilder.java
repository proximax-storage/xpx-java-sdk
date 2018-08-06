/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.facade.upload;

import io.proximax.xpx.builder.steps.*;
import io.proximax.xpx.strategy.privacy.PrivacyStrategy;
import io.proximax.xpx.strategy.privacy.PrivacyStrategyFactory;
import io.proximax.xpx.strategy.privacy.SecuredWithShamirSecretSharingPrivacyStrategy.SecretPart;
import org.nem.core.model.Account;
import org.nem.core.model.mosaic.Mosaic;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
	 * @see io.proximax.xpx.builder.steps.MosaicsStep#mosaics(org.proximax.core.model.mosaic.Mosaic[])
	 */
	@Override
	public FinalBuildSteps mosaics(Mosaic... mosaics) {
		instance.setMosaics(mosaics);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.KeywordsStep#keywords(java.lang.String)
	 */
	@Override
	public FinalBuildSteps keywords(String keywords) {
		this.instance.setKeywords(keywords);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.MetadataStep#metadata(java.util.Map)
	 */
	@Override
	public FinalBuildSteps metadata(Map<String, String> metadata) {
		this.instance.setMetaData(metadata);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.PrivacyStrategyUploadStep#privacyStrategy(io.proximax.xpx.strategy.privacy.PrivacyStrategy)
	 */
	@Override
	public FinalBuildSteps privacyStrategy(PrivacyStrategy privacyStrategy) {
		this.instance.setPrivacyStrategy(privacyStrategy);
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.PrivacyStrategyUploadStep#plainPrivacy()
	 */
	@Override
	public FinalBuildSteps plainPrivacy() {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.plainPrivacy());
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.PrivacyStrategyUploadStep#securedWithNemKeysPrivacyStrategy()
	 */
	@Override
	public FinalBuildSteps securedWithNemKeysPrivacyStrategy() {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithNemKeysPrivacyStrategy(
				this.instance.getSenderPrivateKey(),
				this.instance.getReceiverPublicKey()));
		return  (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.PrivacyStrategyUploadStep#securedWithPasswordPrivacyStrategy(java.lang.String)
	 */
	@Override
	public FinalBuildSteps securedWithPasswordPrivacyStrategy(String password) {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithPasswordPrivacyStrategy(password));
		return (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount,
																		 int secretMinimumPartCountToBuild,
																		 SecretPart... secretParts) {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithShamirSecretSharingPrivacyStrategy(
				secretTotalPartCount, secretMinimumPartCountToBuild,
				Stream.of(secretParts).collect(Collectors.toMap(parts -> parts.index, parts -> parts.secretPart))));
		return (FinalBuildSteps) this;
	}

	@Override
	public FinalBuildSteps securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount,
																		 int secretMinimumPartCountToBuild,
																		 List<SecretPart> secretParts) {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithShamirSecretSharingPrivacyStrategy(
				secretTotalPartCount, secretMinimumPartCountToBuild, secretParts == null ? Collections.emptyMap() :
				secretParts.stream().collect(Collectors.toMap(parts -> parts.index, parts -> parts.secretPart))));
		return (FinalBuildSteps) this;

	}

	@Override
	public FinalBuildSteps securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount,
																		 int secretMinimumPartCountToBuild,
																		 Map<Integer, byte[]> secretParts) {
		this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithShamirSecretSharingPrivacyStrategy(
				secretTotalPartCount, secretMinimumPartCountToBuild, secretParts == null ? Collections.emptyMap() : secretParts));
		return (FinalBuildSteps) this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.SenderPrivateKeyStep#senderPrivateKey(java.lang.String)
	 */
	@Override
	public ReceiverPublicKeyStep senderPrivateKey(String senderPrivateKeyStep) {
		this.instance.setSenderPrivateKey(senderPrivateKeyStep);
		return this;
	}

	/* (non-Javadoc)
	 * @see io.proximax.xpx.builder.steps.ReceiverPublicKeyStep#receiverPublicKey(java.lang.String)
	 */
	@Override
	public NextBuildStepAfterPublicKey receiverPublicKey(String receiverPublicKey) {
		this.instance.setReceiverPublicKey(receiverPublicKey);
		return (NextBuildStepAfterPublicKey) this;
	}


	@Override
	public NextBuildStepAfterPublicKey receiverPublicKey(Account receiverPublicKey) {
		this.instance.setReceiverPublicKeyAccount(receiverPublicKey);
		return (NextBuildStepAfterPublicKey) this;
	}

	@Override
	public ReceiverPublicKeyStep senderPrivateKey(Account senderPrivateKey) {
		this.instance.setSenderPrivateKeyAccount(senderPrivateKey);
		return this;
	}
}
