package io.nem.xpx.builder;

import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;

import io.nem.xpx.adapters.cipher.CustomEncryption;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.model.UploadDataParameter;




/**
 * The Class UploadDataParameterBuilder.
 */
public class UploadDataParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadDataParameterBuilder() {}
	
	/**
	 * Inits the.
	 *
	 * @return the i init
	 */
	public static IInit init() {
		return new UploadDataParameterBuilder.Builder();
	}
	
	/**
	 * Message type.
	 *
	 * @param messageType the message type
	 * @return the i message type
	 */
	public static IMessageType messageType(int messageType) {
		return new UploadDataParameterBuilder.Builder(messageType);
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
		 * Define data.
		 *
		 * @param name the name
		 * @param data the data
		 * @param contentType the content type
		 * @param encoding the encoding
		 * @return the i build
		 */
		IBuild defineData(String name, String data, DataTextContentType contentType, String encoding);
		
		/**
		 * Start define data.
		 *
		 * @return the i start data
		 */
		IStartData startDefineData();
		
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
		IData data(String data);
	}
	
	/**
	 * The Interface IData.
	 */
	public interface IData {
		
		/**
		 * Content type.
		 *
		 * @param contentType the content type
		 * @return the i content type
		 */
		IContentType contentType(DataTextContentType contentType);
	}

	/**
	 * The Interface IContentType.
	 */
	public interface IContentType {
		
		/**
		 * Encoding.
		 *
		 * @param encoding the encoding
		 * @return the i build
		 */
		IBuild encoding(String encoding);
	}
	
	/**
	 * The Interface IStartData.
	 */
	public interface IStartData {
		
		/**
		 * Sets the name.
		 *
		 * @param name the name
		 * @return the i data name
		 */
		IDataName setName(String name);
	}
	
	/**
	 * The Interface IDataName.
	 */
	public interface IDataName {
		
		/**
		 * Sets the data.
		 *
		 * @param data the data
		 * @return the i data content
		 */
		IDataContent setData(String data);
	}
	
	/**
	 * The Interface IDataContent.
	 */
	public interface IDataContent {
		
		/**
		 * Sets the content type.
		 *
		 * @param contentType the content type
		 * @return the i data content type
		 */
		IDataContentType setContentType(DataTextContentType contentType);
	}
	
	/**
	 * The Interface IDataContentType.
	 */
	public interface IDataContentType {
		
		/**
		 * Sets the encoding.
		 *
		 * @param data the data
		 * @return the i data encoding
		 */
		IDataEncoding setEncoding(String data);
	}
	
	/**
	 * The Interface IDataEncoding.
	 */
	public interface IDataEncoding {
		
		/**
		 * End define data.
		 *
		 * @return the i build
		 */
		IBuild endDefineData();
	}
	
	
	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {
		
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
		 * @return the upload data parameter
		 * @throws ApiException the api exception
		 */
		UploadDataParameter build() throws ApiException;
	}
	
	
	

	/**
	 * The Class Builder.
	 */
	private static class Builder implements IInit,ISender,IReceiver,IMessageType,IData,IContentType,IName,IStartData,IDataName,IDataContent,IDataContentType,IDataEncoding, IBuild {

		/** The instance. */
		UploadDataParameter instance = new UploadDataParameter();

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			instance = new UploadDataParameter();
		}
		
		/**
		 * Instantiates a new builder.
		 *
		 * @param messageType the message type
		 */
		public Builder(int messageType) {
			instance = new UploadDataParameter();
			instance.setMessageType(messageType);
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IInit#plainContent()
		 */
		@Override
		public IMessageType plainContent() {
			this.instance.setMessageType(MessageTypes.PLAIN);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IInit#plainContent(io.nem.xpx.adapters.cipher.CustomEncryption)
		 */
		@Override
		public IMessageType plainContent(CustomEncryption customEncryption) {
			this.instance.setMessageType(MessageTypes.PLAIN);
			this.instance.setCustomEncryption(customEncryption);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IInit#secureContent()
		 */
		@Override
		public IMessageType secureContent() {
			this.instance.setMessageType(MessageTypes.SECURE);
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
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#keywords(java.lang.String)
		 */
		@Override
		public IBuild keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IBuild#metadata(java.lang.String)
		 */
		@Override
		public IBuild metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IContentType#encoding(java.lang.String)
		 */
		@Override
		public IBuild encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IData#contentType(io.nem.xpx.facade.model.DataTextContentType)
		 */
		@Override
		public IContentType contentType(DataTextContentType contentType) {
			this.instance.setContentType(contentType.toString());
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IMessageType#senderOrReceiverPrivateKey(java.lang.String)
		 */
		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IName#data(java.lang.String)
		 */
		@Override
		public IData data(String data) {
			this.instance.setData(data);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.ISender#receiverOrSenderPublicKey(java.lang.String)
		 */
		@Override
		public IReceiver receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}


		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IReceiver#name(java.lang.String)
		 */
		@Override
		public IName name(String name) {
			this.instance.setName(name);
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IDataEncoding#endDefineData()
		 */
		@Override
		public IBuild endDefineData() {
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IDataContentType#setEncoding(java.lang.String)
		 */
		@Override
		public IDataEncoding setEncoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IDataContent#setContentType(io.nem.xpx.facade.model.DataTextContentType)
		 */
		@Override
		public IDataContentType setContentType(DataTextContentType contentType) {
			this.instance.setContentType(contentType.name());
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IDataName#setData(java.lang.String)
		 */
		@Override
		public IDataContent setData(String data) {
			this.instance.setData(data);
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IReceiver#defineData(java.lang.String, java.lang.String, io.nem.xpx.facade.model.DataTextContentType, java.lang.String)
		 */
		@Override
		public IBuild defineData(String name, String data, DataTextContentType contentType, String encoding) {
			this.instance.setName(name);
			this.instance.setData(data);
			this.instance.setEncoding(encoding);
			this.instance.setContentType(contentType.name());
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IReceiver#startDefineData()
		 */
		@Override
		public IStartData startDefineData() {
			return this;
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilder.IStartData#setName(java.lang.String)
		 */
		@Override
		public IDataName setName(String name) {
			this.instance.setName(name);
			return this;
		}


	}
}
