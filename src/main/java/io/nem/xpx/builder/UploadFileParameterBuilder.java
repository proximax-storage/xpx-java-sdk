package io.nem.xpx.builder;

import io.nem.xpx.adapters.cipher.CustomEncryption;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.utils.ContentTypeUtils;
import org.apache.commons.io.FileUtils;
import org.apache.maven.surefire.shade.org.codehaus.plexus.util.StringUtils;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;

import java.io.File;
import java.io.IOException;




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
	 * Inits the.
	 *
	 * @return the i init
	 */
	public static IInit init() {
		return new UploadFileParameterBuilder.Builder();
	}

	/**
	 * Message type.
	 *
	 * @param messageType the message type
	 * @return the i message type
	 */
	public static IMessageType messageType(int messageType) {
		return new UploadFileParameterBuilder.Builder(messageType);
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
		 * @return the i name
		 */
		IName receiverOrSenderPublicKey(String receiverOrSenderPublicKey);
	}

	/**
	 * The Interface IName.
	 */
	public interface IName {
		
		/**
		 * Data.
		 *
		 * @param data the data
		 * @return the i build
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		IBuild data(File data) throws IOException;
		
		/**
		 * Name.
		 *
		 * @param name the name
		 * @return the i name
		 */
		IName name(String name);
		
		/**
		 * Content type.
		 *
		 * @param contentType the content type
		 * @return the i name
		 */
		IName contentType(String contentType);
		
		/**
		 * Encoding.
		 *
		 * @param encoding the encoding
		 * @return the i name
		 */
		IName encoding(String encoding);
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
		 * @return the upload file parameter
		 * @throws ApiException the api exception
		 */
		UploadFileParameter build() throws ApiException;
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder
			implements IInit, ISender, IMessageType , IName, IBuild {

		/** The instance. */
		UploadFileParameter instance = null;
		
		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			instance = new UploadFileParameter();
		}
		
		/**
		 * Instantiates a new builder.
		 *
		 * @param messageType the message type
		 */
		public Builder(int messageType) {
			instance = new UploadFileParameter();
			instance.setMessageType(messageType);
		}
		
		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IInit#plainContent()
		 */
		@Override
		public IMessageType plainContent() {
			this.instance.setMessageType(MessageTypes.PLAIN);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IInit#plainContent(io.nem.xpx.adapters.cipher.CustomEncryption)
		 */
		@Override
		public IMessageType plainContent(CustomEncryption customEncryption) {
			this.instance.setMessageType(MessageTypes.PLAIN);
			this.instance.setCustomEncryption(customEncryption);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IInit#secureContent()
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
		public UploadFileParameter build() throws ApiException {
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
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IName#encoding(java.lang.String)
		 */
		@Override
		public IName encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.UploadFileParameterBuilder.IName#contentType(java.lang.String)
		 */
		@Override
		public IName contentType(String contentType) {
			if (StringUtils.isNotEmpty(contentType))
				this.instance.setContentType(contentType);
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
		public IBuild data(File data) throws IOException {
			this.instance.setData(data);
			this.instance.setName(data.getName());
			this.instance.setContentType(ContentTypeUtils.detectContentType(FileUtils.readFileToByteArray(data)));
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
		public IName name(String name) {
			this.instance.setName(name);
			return this;
		}
	}
}
