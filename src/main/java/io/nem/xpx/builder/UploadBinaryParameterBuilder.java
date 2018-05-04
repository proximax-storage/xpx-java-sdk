package io.nem.xpx.builder;

import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;

import io.nem.xpx.adapters.cipher.CustomEncryption;
import io.nem.xpx.builder.UploadDataParameterBuilder.IInit;
import io.nem.xpx.builder.UploadDataParameterBuilder.IMessageType;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.UploadBinaryParameter;


/**
 * The Class UploadFileParameterBuilder.
 */
public class UploadBinaryParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadBinaryParameterBuilder() {
	}

	/**
	 * Inits the.
	 *
	 * @return the i init
	 */
	public static IInit init() {
		return new UploadBinaryParameterBuilder.Builder();
	}
	
	
	/**
	 * Message type.
	 *
	 * @param messageType the message type
	 * @return the i message type
	 */
	public static IMessageType messageType(int messageType) {
		return new UploadBinaryParameterBuilder.Builder(messageType);
	}
	
	/**
	 * The Interface IInit.
	 */
	public interface IInit {
		
		/**
		 * Plain content.
		 *
		 * @return the i message type
		 */
		IMessageType plainContent();
		
		/**
		 * Plain content.
		 *
		 * @param customEncryption the custom encryption
		 * @return the i message type
		 */
		IMessageType plainContent(CustomEncryption customEncryption);
		
		/**
		 * Secure content.
		 *
		 * @return the i message type
		 */
		IMessageType secureContent();
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
		 * @return the i receiver
		 */
		IReceiver receiverOrSenderPublicKey(String receiverOrSenderPublicKey);
	}

	/**
	 * The Interface IReceiver.
	 */
	public interface IReceiver {
		
		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the i name
		 */
		IName name(String name);
	}

	/**
	 * The Interface IName.
	 */
	public interface IName {
		
		/**
		 * Data.
		 *
		 * @param data the data
		 * @return the i data
		 */
		IData data(byte[] data);
	}

	/**
	 * The Interface IData.
	 */
	public interface IData {
		
		/**
		 * Content type.
		 *
		 * @param contentType the content type
		 * @return the i encoding
		 */
		IEncoding contentType(String contentType);
		
		/**
		 * Encoding.
		 *
		 * @param encoding the encoding
		 * @return the i encoding
		 */
		IEncoding encoding(String encoding);
	}


	/**
	 * The Interface IEncoding.
	 */
	public interface IEncoding {
		
		/**
		 * Keywords.
		 *
		 * @param keywords the keywords
		 * @return the i keywords
		 */
		IKeywords keywords(String keywords);
	}

	/**
	 * The Interface IKeywords.
	 */
	public interface IKeywords {
		
		/**
		 * Metadata.
		 *
		 * @param metadata the metadata
		 * @return the i build
		 */
		IBuild metadata(String metadata);
	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {
		
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
		 * @return the upload binary parameter
		 * @throws ApiException the api exception
		 */
		UploadBinaryParameter build() throws ApiException;
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder
			implements IInit,ISender, IReceiver, IMessageType, IData, IEncoding, IKeywords, IName, IBuild {

		/** The instance. */
		UploadBinaryParameter instance = null;
		
		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			instance = new UploadBinaryParameter();
		}

		/**
		 * Instantiates a new builder.
		 *
		 * @param messageType the message type
		 */
		public Builder(int messageType) {
			instance = new UploadBinaryParameter();
			instance.setMessageType(messageType);
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IInit#plainContent()
		 */
		@Override
		public IMessageType plainContent() {
			this.instance.setMessageType(MessageTypes.PLAIN);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IInit#plainContent(io.nem.xpx.adapters.cipher.CustomEncryption)
		 */
		@Override
		public IMessageType plainContent(CustomEncryption customEncryption) {
			this.instance.setMessageType(MessageTypes.PLAIN);
			this.instance.setCustomEncryption(customEncryption);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IInit#secureContent()
		 */
		@Override
		public IMessageType secureContent() {
			this.instance.setMessageType(MessageTypes.SECURE);
			return this;
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
		public UploadBinaryParameter build() throws ApiException {
			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IEncoding#keywords(java.lang.String)
		 */
		@Override
		public IKeywords keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IKeywords#metadata(java.lang.String)
		 */
		@Override
		public IBuild metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IData#encoding(java.lang.String)
		 */
		@Override
		public IEncoding encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IData#contentType(java.lang.String)
		 */
		@Override
		public IEncoding contentType(String contentType) {
			this.instance.setContentType(contentType);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IMessageType#senderOrReceiverPrivateKey(java.lang.String)
		 */
		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IName#data(byte[])
		 */
		@Override
		public IData data(byte[] data) {
			this.instance.setData(data);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.ISender#receiverOrSenderPublicKey(java.lang.String)
		 */
		@Override
		public IReceiver receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadBinaryParameterBuilder.IReceiver#name(java.lang.String)
		 */
		@Override
		public IName name(String name) {
			this.instance.setName(name);
			return this;
		}
	}
}
