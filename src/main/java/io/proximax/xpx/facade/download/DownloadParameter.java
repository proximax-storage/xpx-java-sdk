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

package io.proximax.xpx.facade.download;

import io.proximax.xpx.builder.steps.HashStep;
import io.proximax.xpx.builder.steps.PrivacyStrategyDownloadStep;
import io.proximax.xpx.strategy.privacy.PrivacyStrategy;
import io.proximax.xpx.strategy.privacy.PrivacyStrategyFactory;
import io.proximax.xpx.strategy.privacy.SecuredWithShamirSecretSharingPrivacyStrategy;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The Class DownloadParameter.
 */
public class DownloadParameter implements Serializable {

	
	private String ipfsHash;
	
	/** The proximax hash. */
	private String nemHash;

	/** The sender or receiver private key. */
	private String senderOrReceiverPrivateKey;

	/** The receiver or sender public key. */
	private String receiverOrSenderPublicKey;

	/** The privacy strategy. */
	private PrivacyStrategy privacyStrategy = PrivacyStrategyFactory.plainPrivacy();

	/**
	 * Gets the proximax hash.
	 *
	 * @return the proximax hash
	 */
	public String getNemHash() {
		return nemHash;
	}

	/**
	 * Sets the proximax hash.
	 *
	 * @param nemHash the new proximax hash
	 */
	public void setNemHash(String nemHash) {
		this.nemHash = nemHash;
	}
	
	

	public String getIpfsHash() {
		return ipfsHash;
	}

	public void setIpfsHash(String ipfsHash) {
		this.ipfsHash = ipfsHash;
	}

	/**
	 * Gets the sender or receiver private key.
	 *
	 * @return the sender or receiver private key
	 */
	public String getSenderOrReceiverPrivateKey() {
		return senderOrReceiverPrivateKey;
	}

	/**
	 * Sets the sender or receiver private key.
	 *
	 * @param senderOrReceiverPrivateKey the new sender or receiver private key
	 */
	public void setSenderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
		this.senderOrReceiverPrivateKey = senderOrReceiverPrivateKey;
	}

	/**
	 * Gets the receiver or sender public key.
	 *
	 * @return the receiver or sender public key
	 */
	public String getReceiverOrSenderPublicKey() {
		return receiverOrSenderPublicKey;
	}

	/**
	 * Sets the receiver or sender public key.
	 *
	 * @param receiverOrSenderPublicKey the new receiver or sender public key
	 */
	public void setReceiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
		this.receiverOrSenderPublicKey = receiverOrSenderPublicKey;
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

	/**
	 * Creates the.
	 *
	 * @return the proximax hash step
	 */
	public static HashStep<BuildStep> create() {
		return new Builder();
	}

	/**
	 * The Interface BuildStep.
	 */
	public interface BuildStep extends PrivacyStrategyDownloadStep<BuildStep> {

		/**
		 * Builds the.
		 *
		 * @return the download parameter
		 */
		DownloadParameter build();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder
			implements HashStep,
			BuildStep {

		/** The instance. */
		DownloadParameter instance;

		/**
		 * Instantiates a new builder.
		 */
		private Builder() {
			instance = new DownloadParameter();
		}


		/* (non-Javadoc)
		 * @see io.proximax.xpx.builder.steps.PrivacyStrategyDownloadStep#privacyStrategy(io.proximax.xpx.strategy.privacy.PrivacyStrategy)
		 */
		@Override
		public BuildStep privacyStrategy(PrivacyStrategy privacyStrategy) {
			this.instance.setPrivacyStrategy(privacyStrategy);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.proximax.xpx.builder.steps.PrivacyStrategyDownloadStep#plainPrivacy()
		 */
		@Override
		public BuildStep plainPrivacy() {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.plainPrivacy());
			return this;
		}

		/* (non-Javadoc)
		 * @see io.proximax.xpx.builder.steps.PrivacyStrategyDownloadStep#securedWithNemKeysPrivacyStrategy(java.lang.String, java.lang.String)
		 */
		@Override
		public BuildStep securedWithNemKeysPrivacyStrategy(String senderOrReceiverPrivateKey, String receiverOrSenderPublicKey) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithNemKeysPrivacyStrategy(
					senderOrReceiverPrivateKey, receiverOrSenderPublicKey));
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.proximax.xpx.builder.steps.PrivacyStrategyDownloadStep#securedWithPasswordPrivacyStrategy(java.lang.String)
		 */
		@Override
		public BuildStep securedWithPasswordPrivacyStrategy(String password) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithPasswordPrivacyStrategy(password));
			return this;
		}

		@Override
		public BuildStep securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount,
																			 int secretMinimumPartCountToBuild,
																			 SecuredWithShamirSecretSharingPrivacyStrategy.SecretPart... secretParts) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithShamirSecretSharingPrivacyStrategy(
					secretTotalPartCount, secretMinimumPartCountToBuild,
					Stream.of(secretParts).collect(Collectors.toMap(parts -> parts.index, parts -> parts.secretPart))));
			return (BuildStep) this;
		}

		@Override
		public BuildStep securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount,
																			 int secretMinimumPartCountToBuild,
																			 List<SecuredWithShamirSecretSharingPrivacyStrategy.SecretPart> secretParts) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithShamirSecretSharingPrivacyStrategy(
					secretTotalPartCount, secretMinimumPartCountToBuild, secretParts == null ? Collections.emptyMap() :
							secretParts.stream().collect(Collectors.toMap(parts -> parts.index, parts -> parts.secretPart))));
			return (BuildStep) this;

		}

		@Override
		public BuildStep securedWithShamirSecretSharingPrivacyStrategy(int secretTotalPartCount,
																			 int secretMinimumPartCountToBuild,
																			 Map<Integer, byte[]> secretParts) {
			this.instance.setPrivacyStrategy(PrivacyStrategyFactory.securedWithShamirSecretSharingPrivacyStrategy(
					secretTotalPartCount, secretMinimumPartCountToBuild, secretParts == null ? Collections.emptyMap() : secretParts));
			return (BuildStep) this;
		}


		/* (non-Javadoc)
		 * @see io.proximax.xpx.builder.steps.NemHashStep#nemHash(java.lang.String)
		 */
		@Override
		public BuildStep nemHash(String nemHash) {
			this.instance.setNemHash(nemHash);
			return this;
		}

		@Override
		public BuildStep ipfsHash(String ipfsHash) {
			this.instance.setIpfsHash(ipfsHash);
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.proximax.xpx.facade.download.DownloadParameter.BuildStep#build()
		 */
		@Override
		public DownloadParameter build() {
			return instance;
		}

	}


}
