package io.nem.xpx.builder;

import java.util.List;

import org.nem.core.model.mosaic.Mosaic;

import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.facade.model.DataTextContentType;
import io.nem.xpx.model.UploadDataParameter;


/**
 * The Class UploadDataParameterBuilder.
 */
public class UploadDataParameterBuilderV2 {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadDataParameterBuilderV2() {
	}

	/**
	 * Message type.
	 *
	 * @param messageType the message type
	 * @return the i message type
	 */
	public static IMessageType messageType(int messageType) {
		return new UploadDataParameterBuilderV2.Builder(messageType);
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
		 * @return the i start data
		 */
		IStartData receiverOrSenderPublicKey(String receiverOrSenderPublicKey);
	}

	/**
	 * The Interface IStartData.
	 */
	public interface IStartData {
		
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
		 * @return the i data
		 */
		IData startDefineData();
	}

	/**
	 * The Interface IData.
	 */
	public interface IData {
		
		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the i data name
		 */
		IDataName name(String name);
	}

	/**
	 * The Interface IDataName.
	 */
	public interface IDataName {
		
		/**
		 * Data.
		 *
		 * @param data the data
		 * @return the i data content type
		 */
		IDataContentType data(String data);
	}

	/**
	 * The Interface IDataContentType.
	 */
	public interface IDataContentType {
		
		/**
		 * Content type.
		 *
		 * @param contentType the content type
		 * @return the i data encoding
		 */
		IDataEncoding contentType(DataTextContentType contentType);
	}

	/**
	 * The Interface IDataEncoding.
	 */
	public interface IDataEncoding {
		
		/**
		 * Encoding.
		 *
		 * @param encoding the encoding
		 * @return the i end data
		 */
		IEndData encoding(String encoding);
	}

	/**
	 * The Interface IEndData.
	 */
	public interface IEndData {
		
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
	 * The Interface IBuildMultipleData.
	 */
	public interface IBuildMultipleData {
		
		/**
		 * Builds the.
		 *
		 * @return the list
		 * @throws ApiException the api exception
		 */
		List<UploadDataParameter> build() throws ApiException;
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IStartData, IEndData, IMessageType, IData, IDataContentType,
			IDataEncoding, IDataName, IBuild {

		/** The instance. */
		UploadDataParameter instance = null;

		/**
		 * Instantiates a new builder.
		 *
		 * @param messageType the message type
		 */
		public Builder(int messageType) {
			instance = new UploadDataParameter();
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
		public UploadDataParameter build() throws ApiException {
			if (instance.getEncoding() == null) {
				instance.setEncoding("UTF-8");
			}
			if (instance.getContentType() == null) {
				instance.setContentType("text/plain");
			}

			return instance;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IBuild#keywords(java.lang.String)
		 */
		@Override
		public IBuild keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IBuild#metadata(java.lang.String)
		 */
		@Override
		public IBuild metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IDataEncoding#encoding(java.lang.String)
		 */
		@Override
		public IEndData encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IDataContentType#contentType(io.nem.xpx.facade.model.DataTextContentType)
		 */
		@Override
		public IDataEncoding contentType(DataTextContentType contentType) {
			this.instance.setContentType(contentType.name());
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IMessageType#senderOrReceiverPrivateKey(java.lang.String)
		 */
		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IDataName#data(java.lang.String)
		 */
		@Override
		public IDataContentType data(String data) {
			this.instance.setData(data);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.ISender#receiverOrSenderPublicKey(java.lang.String)
		 */
		@Override
		public IStartData receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IData#name(java.lang.String)
		 */
		@Override
		public IDataName name(String name) {
			this.instance.setName(name);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IEndData#endDefineData()
		 */
		@Override
		public IBuild endDefineData() {
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IStartData#defineData(java.lang.String, java.lang.String, io.nem.xpx.facade.model.DataTextContentType, java.lang.String)
		 */
		@Override
		public IBuild defineData(String name, String data, DataTextContentType contentType, String encoding) {
			this.instance.setName(name);
			this.instance.setData(data);
			this.instance.setContentType(contentType.name());
			this.instance.setEncoding(encoding);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadDataParameterBuilderV2.IStartData#startDefineData()
		 */
		@Override
		public IData startDefineData() {
			return this;
		}

	}
}
