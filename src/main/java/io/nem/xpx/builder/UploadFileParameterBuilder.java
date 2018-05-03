package io.nem.xpx.builder;

import java.io.File;

import org.nem.core.model.mosaic.Mosaic;
import io.nem.ApiException;
import io.nem.xpx.model.UploadFileParameter;



/**
 * The Class UploadFileParameterBuilder.
 */
public class UploadFileParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadFileParameterBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @return the i sender
	 */
	public static ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
		return new UploadFileParameterBuilder.Builder(senderOrReceiverPrivateKey);
	}

	/**
	 * The Interface ISender.
	 */
	public interface ISender {

		/**
		 * Recipient public key.
		 *
		 * @param receiverOrSenderPublicKey the receiver or sender public key
		 * @return the i build
		 */
		IBuild receiverOrSenderPublicKey(String receiverOrSenderPublicKey);

	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		/**
		 * Message type.
		 *
		 * @param messageType the message type
		 * @return the i build
		 */
		IBuild messageType(int messageType);

		/**
		 * Data.
		 *
		 * @param data the data
		 * @return the i build
		 */
		IBuild data(File data);

		/**
		 * Content type.
		 *
		 * @param contentType the content type
		 * @return the i build
		 */
		IBuild contentType(String contentType);
		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the i build
		 */
		IBuild name(String name);

		/**
		 * Keywords.
		 *
		 * @param keywords the keywords
		 * @return the i build
		 */
		IBuild keywords(String keywords);

		/**
		 * Meta data.
		 *
		 * @param metaData the meta data
		 * @return the i build
		 */
		IBuild metaData(String metaData);

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
		UploadFileParameter build() throws ApiException;

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		/** The instance. */
		UploadFileParameter instance = new UploadFileParameter();

		/**
		 * Instantiates a new builder.
		 *
		 * @param senderOrReceiverPrivateKey the sender or receiver private key
		 */
		public Builder(String senderOrReceiverPrivateKey) {
			instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#messageType(int)
		 */
		@Override
		public IBuild messageType(int messageType) {
			instance.setMessageType(messageType);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#data(java.io.File)
		 */
		@Override
		public IBuild data(File data) {
			instance.setData(data);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#name(java.lang.String)
		 */
		@Override
		public IBuild name(String name) {
			instance.setName(name);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#keywords(java.lang.String)
		 */
		@Override
		public IBuild keywords(String keywords) {
			instance.setKeywords(keywords);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#metaData(java.lang.String)
		 */
		@Override
		public IBuild metaData(String metaData) {
			instance.setMetaData(metaData);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#mosaics(org.nem.core.model.mosaic.Mosaic[])
		 */
		@Override
		public IBuild mosaics(Mosaic... mosaics) {
			instance.setMosaics(mosaics);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#build()
		 */
		@Override
		public UploadFileParameter build() throws ApiException {
			
			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.ISender#recipientPublicKey(java.lang.String)
		 */
		@Override
		public IBuild receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IBuild#contentType(java.lang.String)
		 */
		@Override
		public IBuild contentType(String contentType) {
			instance.setContentType(contentType);
			return this;
		}

	}
}
