package io.nem.xpx.builder;

import org.nem.core.model.mosaic.Mosaic;

import io.nem.ApiException;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.model.UploadDataParameter;



/**
 * The Class UploadDataParameterBuilder.
 */
public class UploadDataParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadDataParameterBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param senderOrReceiverPrivateKey the sender or receiver private key
	 * @return the i sender
	 */
	public static ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
		return new UploadDataParameterBuilder.Builder(senderOrReceiverPrivateKey);
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
		IBuild data(String data);
		
		/**
		 * Content type.
		 *
		 * @param contentType the content type
		 * @return the i build
		 */
		IBuild contentType(DataTextContentType contentType);
		
		/**
		 * Encoding.
		 *
		 * @param encoding the encoding
		 * @return the i build
		 */
		IBuild encoding(String encoding);

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
		 * @return the upload data parameter
		 * @throws ApiException the api exception
		 */
		UploadDataParameter build() throws ApiException;

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		/** The instance. */
		UploadDataParameter instance = null;

		/**
		 * Instantiates a new builder.
		 *
		 * @param senderOrReceiverPrivateKey the sender or receiver private key
		 */
		public Builder(String senderOrReceiverPrivateKey) {
			instance = new UploadDataParameter();
			instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#messageType(int)
		 */
		@Override
		public IBuild messageType(int messageType) {
			instance.setMessageType(messageType);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#data(java.lang.String)
		 */
		@Override
		public IBuild data(String data) {
			instance.setData(data);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#name(java.lang.String)
		 */
		@Override
		public IBuild name(String name) {
			instance.setName(name);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#keywords(java.lang.String)
		 */
		@Override
		public IBuild keywords(String keywords) {
			instance.setKeywords(keywords);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#metaData(java.lang.String)
		 */
		@Override
		public IBuild metaData(String metaData) {
			instance.setMetaData(metaData);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#mosaics(org.nem.core.model.mosaic.Mosaic[])
		 */
		@Override
		public IBuild mosaics(Mosaic... mosaics) {
			instance.setMosaics(mosaics);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#build()
		 */
		@Override
		public UploadDataParameter build() throws ApiException {
			if(instance.getEncoding() == null) {
				instance.setEncoding("UTF-8");
			}
			if(instance.getContentType() == null) {
				instance.setContentType("text/plain");
			}
			
			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.ISender#recipientPublicKey(java.lang.String)
		 */
		@Override
		public IBuild receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#contentType(io.nem.xpx.facade.model.DataTextContentType)
		 */
		@Override
		public IBuild contentType(DataTextContentType contentType) {
			instance.setContentType(contentType.toString());
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#encoding(java.lang.String)
		 */
		@Override
		public IBuild encoding(String encoding) {
			instance.setEncoding(encoding);
			return this;
		}

	}
}
