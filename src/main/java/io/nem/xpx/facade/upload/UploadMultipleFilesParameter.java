package io.nem.xpx.facade.upload;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import io.nem.xpx.strategy.privacy.PrivacyStrategyFactory;
import org.nem.core.model.mosaic.Mosaic;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;
import static java.util.Arrays.asList;


public class UploadMultipleFilesParameter extends AbstractUploadParameter implements Serializable {

	private List<File> files = new ArrayList<>();

	public List<File> getFiles() {
		return files;
	}

	public void addFiles(List<File> files) {
		this.files.addAll(files);
	}

	public void addFiles(File... files) {
		this.files.addAll(asList(files));
	}

	public void addFile(File file) {
		this.files.add(file);
	}

	public static SenderOrReceiverPrivateKeyStep
			<ReceiverOrSenderPublicKeyStep
					<BuildStep>> create() {
		return new Builder();
	}

	public interface BuildStep extends FilesStep<BuildStep>,
			KeywordsStep<BuildStep>,
			MetadataStep<BuildStep>,
			MosaicsStep<BuildStep>,
			PrivacyStrategyUploadStep<BuildStep> {

		UploadMultipleFilesParameter build();
	}

	private static class Builder
			implements SenderOrReceiverPrivateKeyStep,
			ReceiverOrSenderPublicKeyStep,
			BuildStep {

		private UploadMultipleFilesParameter instance;

		private Builder() {
			instance = new UploadMultipleFilesParameter();
			instance.setContentType(APPLICATION_ZIP.toString());
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
		public BuildStep receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public BuildStep addFiles(File... files) {
			this.instance.addFiles(files);
			return this;
		}

		@Override
		public BuildStep addFiles(List<File> files) {
			this.instance.addFiles(files);
			return this;
		}

		@Override
		public BuildStep addFile(File file) {
			this.instance.addFile(file);
			return this;
		}

		@Override
		public UploadMultipleFilesParameter build() {
			return instance;
		}

	}

}
