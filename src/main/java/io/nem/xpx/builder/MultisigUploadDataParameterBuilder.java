package io.nem.xpx.builder;

import org.nem.core.model.mosaic.Mosaic;

import io.nem.ApiException;
import io.nem.xpx.builder.UploadDataParameterBuilder.IBuild;
import io.nem.xpx.builder.UploadDataParameterBuilder.ISender;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.service.model.MultisigUploadDataParameter;
import io.nem.xpx.service.model.UploadDataParameter;

public class MultisigUploadDataParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private MultisigUploadDataParameterBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param senderPrivateKey the sender private key
	 * @return the i sender
	 */
	public static ISender senderPrivateKey(String senderPrivateKey) {
		return new MultisigUploadDataParameterBuilder.Builder(senderPrivateKey);
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
		IMultiSig recipientPublicKey(String recipientPublicKey);

	}
	
	/**
	 * The Interface ISender.
	 */
	public interface IMultiSig {

		/**
		 * Recipient public key.
		 *
		 * @param recipientPublicKey the recipient public key
		 * @return the i build
		 */
		IBuild multisigPublicKey(String multisigPublicKey);

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
		
		IBuild contentType(DataTextContentType contentType);
		
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
		MultisigUploadDataParameter build() throws ApiException;

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IMultiSig, IBuild {

		/** The instance. */
		MultisigUploadDataParameter instance = new MultisigUploadDataParameter();

		/**
		 * Instantiates a new builder.
		 *
		 * @param senderPrivateKey the sender private key
		 */
		public Builder(String senderPrivateKey) {
			instance.setSenderPrivateKey(senderPrivateKey);
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
		public MultisigUploadDataParameter build() throws ApiException {
			if(instance.getEncoding() == null) {
				instance.setEncoding("UTF-8");
			}
			if(instance.getContentType() == null) {
				instance.setEncoding("text/plain");
			}
			
			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.ISender#recipientPublicKey(java.lang.String)
		 */
		@Override
		public IMultiSig recipientPublicKey(String recipientPublicKey) {
			instance.setRecipientPublicKey(recipientPublicKey);
			return this;
		}

		@Override
		public IBuild contentType(DataTextContentType contentType) {
			instance.setContentType(contentType.toString());
			return this;
		}

		@Override
		public IBuild encoding(String encoding) {
			instance.setEncoding(encoding);
			return this;
		}

		@Override
		public IBuild multisigPublicKey(String multisigPublicKey) {
			instance.setMultisigPublicKey(multisigPublicKey);
			return this;
		}
	}
}
