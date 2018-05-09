package io.nem.xpx.builder;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.UploadFilesAsZipParameter;
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
	public static IMessageType messageType(int messageType) {
		return new UploadFilesAsZipParameterBuilder.Builder(messageType);
	}
	
	/**
	 * The Interface IMessageType.
	 */
	public interface IMessageType {
		
		/**
		 * Sender or receiver private key.
		 *
		 * @param senderOrReceiverPrivateKey the sender or receiver private key
		 * @return the i sender
		 */
		ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey);
	}

	/**
	 * The Interface ISender.
	 */
	public interface ISender {
		
		/**
		 * Receiver or sender public key.
		 *
		 * @param receiverOrSenderPublicKey the receiver or sender public key
		 * @return the i name
		 */
		IName receiverOrSenderPublicKey(String receiverOrSenderPublicKey);
	}

	/**
	 * The Interface IName.
	 */
	public interface IName {

		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the i name
		 */
		IBuild zipFileName(String name);

	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		IBuild addFiles(File... files);

		IBuild addFiles(List<File> files);

		IBuild addFile(File file);

		/**
		 * Keywords.
		 *
		 * @param keywords the keywords
		 * @return the i build
		 */
		IBuild keywords(String keywords);
		
		/**
		 * Metadata.
		 *
		 * @param metadata the metadata
		 * @return the i build
		 */
		IBuild metadata(String metadata);
		
		/**
		 * Mosaics.
		 *
		 * @param mosaics the mosaics
		 * @return the i build
		 */
		IBuild mosaics(Mosaic... mosaics);

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
			implements ISender, IMessageType , IName, IBuild {

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
		public IBuild mosaics(Mosaic... mosaics) {
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
		public IBuild keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#metadata(java.lang.String)
		 */
		@Override
		public IBuild metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IMessageType#senderOrReceiverPrivateKey(java.lang.String)
		 */
		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IName#data(java.io.File)
		 */
		@Override
		public IBuild addFiles(File... files) {
			this.instance.addFiles(files);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IName#data(java.io.File)
		 */
		@Override
		public IBuild addFiles(List<File> files) {
			this.instance.addFiles(files);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IName#data(java.io.File)
		 */
		@Override
		public IBuild addFile(File file) {
			this.instance.addFiles(file);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.ISender#receiverOrSenderPublicKey(java.lang.String)
		 */
		@Override
		public IName receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IName#name(java.lang.String)
		 */
		@Override
		public IBuild zipFileName(String name) {
			this.instance.setName(name);
			return this;
		}
	}
}
