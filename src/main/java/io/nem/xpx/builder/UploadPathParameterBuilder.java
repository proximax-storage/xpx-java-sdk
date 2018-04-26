package io.nem.xpx.builder;

import org.nem.core.model.mosaic.Mosaic;

import io.nem.ApiException;
import io.nem.xpx.service.model.UploadDataParameter;
import io.nem.xpx.service.model.UploadPathParameter;
import io.nem.xpx.service.model.XpxSdkGlobalConstants;
import io.nem.xpx.utils.KeyUtils;


/**
 * The Class UploadPathParameterBuilder.
 */
public class UploadPathParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadPathParameterBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param senderPrivateKey the sender private key
	 * @return the i sender
	 */
	public static ISender senderPrivateKey(String senderPrivateKey) {
		return new UploadPathParameterBuilder.Builder(senderPrivateKey);
	}

	/**
	 * The Interface ISender.
	 */
	public interface ISender {

		/**
		 * Recipient public key.
		 *
		 * @param recipientPublicKey the recipient public key
		 * @return the i build
		 */
		IBuild recipientPublicKey(String recipientPublicKey);

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
		 * Path.
		 *
		 * @param path the path
		 * @return the i build
		 */
		IBuild path(String path);

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
		 * @return the upload path parameter
		 * @throws ApiException the api exception
		 */
		UploadPathParameter build() throws ApiException;

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		/** The instance. */
		UploadPathParameter instance = new UploadPathParameter();

		/**
		 * Instantiates a new builder.
		 *
		 * @param senderPrivateKey the sender private key
		 */
		public Builder(String senderPrivateKey) {
			instance.setSenderPrivateKey(senderPrivateKey);
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.IBuild#messageType(int)
		 */
		@Override
		public IBuild messageType(int messageType) {
			instance.setMessageType(messageType);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.IBuild#path(java.lang.String)
		 */
		@Override
		public IBuild path(String path) {
			instance.setPath(path);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.IBuild#name(java.lang.String)
		 */
		@Override
		public IBuild name(String name) {
			instance.setName(name);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.IBuild#keywords(java.lang.String)
		 */
		@Override
		public IBuild keywords(String keywords) {
			instance.setKeywords(keywords);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.IBuild#metaData(java.lang.String)
		 */
		@Override
		public IBuild metaData(String metaData) {
			instance.setMetaData(metaData);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.IBuild#mosaics(org.nem.core.model.mosaic.Mosaic[])
		 */
		@Override
		public IBuild mosaics(Mosaic... mosaics) {
			instance.setMosaics(mosaics);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.IBuild#build()
		 */
		@Override
		public UploadPathParameter build() throws ApiException {
			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadPathParameterBuilder.ISender#recipientPublicKey(java.lang.String)
		 */
		@Override
		public IBuild recipientPublicKey(String recipientPublicKey) {
			instance.setRecipientPublicKey(recipientPublicKey);
			return this;
		}

	}
}
