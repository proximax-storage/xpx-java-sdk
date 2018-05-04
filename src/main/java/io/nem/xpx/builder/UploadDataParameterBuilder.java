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
	
	public static IInit init() {
		return new UploadDataParameterBuilder.Builder();
	}
	
	public static IMessageType messageType(int messageType) {
		return new UploadDataParameterBuilder.Builder(messageType);
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
		IBuild defineData(String name, String data, DataTextContentType contentType, String encoding);
		IStartData startDefineData();
		IName name(String name);
	}

	public interface IName {
		IData data(String data);
	}
	
	public interface IData {
		IContentType contentType(DataTextContentType contentType);
	}

	public interface IContentType {
		IBuild encoding(String encoding);
	}
	
	public interface IStartData {
		IDataName setName(String name);
	}
	
	public interface IDataName {
		IDataContent setData(String data);
	}
	
	public interface IDataContent {
		IDataContentType setContentType(DataTextContentType contentType);
	}
	
	public interface IDataContentType {
		IDataEncoding setEncoding(String data);
	}
	
	public interface IDataEncoding {
		IBuild endDefineData();
	}
	
	
	public interface IBuild {
		IBuild keywords(String keywords);
		IBuild metadata(String metadata);
		IBuild mosaics(Mosaic... mosaics);
		UploadDataParameter build() throws ApiException;
	}
	
	
	

	/**
	 * The Class Builder.
	 */
	private static class Builder implements IInit,ISender,IReceiver,IMessageType,IData,IContentType,IName,IStartData,IDataName,IDataContent,IDataContentType,IDataEncoding, IBuild {

		/** The instance. */
		UploadDataParameter instance = new UploadDataParameter();

		public Builder() {
			instance = new UploadDataParameter();
		}
		public Builder(int messageType) {
			instance = new UploadDataParameter();
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


		@Override
		public IBuild keywords(String keywords) {
			this.instance.setKeywords(keywords);
			return this;
		}


		@Override
		public IBuild metadata(String metadata) {
			this.instance.setMetaData(metadata);
			return this;
		}


		@Override
		public IBuild encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}


		@Override
		public IContentType contentType(DataTextContentType contentType) {
			this.instance.setContentType(contentType.name());
			return this;
		}


		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}


		@Override
		public IData data(String data) {
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
		@Override
		public IBuild endDefineData() {
			return this;
		}
		@Override
		public IDataEncoding setEncoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}
		@Override
		public IDataContentType setContentType(DataTextContentType contentType) {
			this.instance.setContentType(contentType.name());
			return this;
		}
		@Override
		public IDataContent setData(String data) {
			this.instance.setData(data);
			return this;
		}
		@Override
		public IBuild defineData(String name, String data, DataTextContentType contentType, String encoding) {
			this.instance.setName(name);
			this.instance.setData(data);
			this.instance.setEncoding(encoding);
			this.instance.setContentType(contentType.name());
			return this;
		}
		@Override
		public IStartData startDefineData() {
			return this;
		}
		@Override
		public IDataName setName(String name) {
			this.instance.setName(name);
			return this;
		}


	}
}
