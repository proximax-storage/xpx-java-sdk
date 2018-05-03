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
	private UploadDataParameterBuilder() {}
	
	public static IMessageType messageType(int messageType) {
		return new UploadDataParameterBuilder.Builder(messageType);
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
		IData data(String data);
	}
	
	public interface IData {
		IContentType contentType(DataTextContentType contentType);
	}

	public interface IContentType {
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
		UploadDataParameter build() throws ApiException;
	}
	

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IReceiver, IMessageType,IData,IEncoding,IContentType,IKeywords,IName,IBuild {

		/** The instance. */
		UploadDataParameter instance = null;

		
		public Builder(int messageType) {
			instance = new UploadDataParameter();
			instance.setMessageType(messageType);
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


	}
}
