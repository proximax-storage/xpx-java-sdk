package io.nem.xpx.builder;

import io.nem.xpx.builder.steps.*;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.UploadFilesAsZipParameter;
import io.nem.xpx.strategy.privacy.PrivacyStrategy;
import org.nem.core.model.mosaic.Mosaic;

import java.io.File;
import java.util.List;

import static io.nem.xpx.facade.DataTextContentType.APPLICATION_ZIP;


/**
 * The Class UploadFileParameterBuilder.
 */
public class UploadFilesAsZipParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadFilesAsZipParameterBuilder() {
	}

	/**
	 * Message type.
	 *
	 * @param messageType the message type
	 * @return the i message type
	 */
	public static SenderOrReceiverPrivateKeyStep<ReceiverOrSenderPublicKeyStep<ZipFileNameStep<BuildStep>>> messageType(int messageType) {
		return new UploadFilesAsZipParameterBuilder.Builder(messageType);
	}
	

	/**
	 * The Interface IBuild.
	 */
	public interface BuildStep extends FilesStep<BuildStep>,
			KeywordsStep<BuildStep>,
			MetadataStep<BuildStep>,
			MosaicsStep<BuildStep>,
			PrivacyStrategyStep<BuildStep> {

		/**
		 * Builds the.
		 *
		 * @return the upload file parameter
		 * @throws ApiException the api exception
		 */
		UploadFilesAsZipParameter build();
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder
			implements SenderOrReceiverPrivateKeyStep,
			ReceiverOrSenderPublicKeyStep,
			ZipFileNameStep,
			BuildStep {

		/** The instance. */
		UploadFilesAsZipParameter instance;
		
		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			instance = new UploadFilesAsZipParameter();
			instance.setContentType(APPLICATION_ZIP.toString());
		}
		
		/**
		 * Instantiates a new builder.
		 *
		 * @param messageType the message type
		 */
		public Builder(int messageType) {
			this();
			instance.setMessageType(messageType);
		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#mosaics(org.nem.core.
		 * model.mosaic.Mosaic[])
		 */
		@Override
		public BuildStep mosaics(Mosaic... mosaics) {
			instance.setMosaics(mosaics);
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#build()
		 */
		@Override
		public UploadFilesAsZipParameter build() {
			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#keywords(java.lang.String)
		 */
		@Override
		public BuildStep keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#metadata(java.lang.String)
		 */
		@Override
		public BuildStep metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IMessageType#senderOrReceiverPrivateKey(java.lang.String)
		 */
		@Override
		public ReceiverOrSenderPublicKeyStep senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.ISender#receiverOrSenderPublicKey(java.lang.String)
		 */
		@Override
		public ZipFileNameStep receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public String getSenderOrReceiverPrivateKey() {
			return this.instance.getSenderOrReceiverPrivateKey();
		}

		@Override
		public String getReceiverOrSenderPublicKey() {
			return this.instance.getReceiverOrSenderPublicKey();
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IName#name(java.lang.String)
		 */
		@Override
		public BuildStep zipFileName(String name) {
			this.instance.setName(name);
			return this;
		}

		@Override
		public void setPrivacyStrategy(PrivacyStrategy privacyStrategy) {
			// TODO
		}

		@Override
		public List<File> getFiles() {
			return instance.getFiles();
		}
	}
}
