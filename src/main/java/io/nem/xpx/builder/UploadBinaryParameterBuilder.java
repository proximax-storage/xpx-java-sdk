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

	public static IInit init() {
		return new UploadBinaryParameterBuilder.Builder();
	}
	
	
	public static IMessageType messageType(int messageType) {
		return new UploadBinaryParameterBuilder.Builder(messageType);
	}
	
	public interface IInit {
		IMessageType plainContent();
		IMessageType plainContent(CustomEncryption customEncryption);
		IMessageType secureContent();
	}

	public interface IMessageType {
		ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey);
	}

	public interface ISender {
		IReceiver receiverOrSenderPublicKey(String receiverOrSenderPublicKey);
	}

	public interface IReceiver {
		IName name(String name);
	}

	public interface IName {
		IData data(byte[] data);
	}

	public interface IData {
		IEncoding contentType(String contentType);
		IEncoding encoding(String encoding);
	}


	public interface IEncoding {
		IKeywords keywords(String keywords);
	}

	public interface IKeywords {
		IBuild metadata(String metadata);
	}

	public interface IBuild {
		IBuild mosaics(Mosaic... mosaics);

		UploadBinaryParameter build() throws ApiException;
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder
			implements IInit,ISender, IReceiver, IMessageType, IData, IEncoding, IKeywords, IName, IBuild {

		/** The instance. */
		UploadBinaryParameter instance = null;
		
		public Builder() {
			instance = new UploadBinaryParameter();
		}

		public Builder(int messageType) {
			instance = new UploadBinaryParameter();
			instance.setMessageType(messageType);
		}

		@Override
		public IMessageType plainContent() {
			this.instance.setMessageType(MessageTypes.PLAIN);
			return this;
		}

		@Override
		public IMessageType plainContent(CustomEncryption customEncryption) {
			this.instance.setMessageType(MessageTypes.PLAIN);
			this.instance.setCustomEncryption(customEncryption);
			return this;
		}

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

		@Override
		public IKeywords keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}

		@Override
		public IBuild metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}

		@Override
		public IEncoding encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}

		@Override
		public IEncoding contentType(String contentType) {
			this.instance.setContentType(contentType);
			return this;
		}

		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		@Override
		public IData data(byte[] data) {
			this.instance.setData(data);
			return this;
		}

		@Override
		public IReceiver receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public IName name(String name) {
			this.instance.setName(name);
			return this;
		}
	}
}
