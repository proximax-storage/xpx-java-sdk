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

	public static IMessageType messageType(int messageType) {
		return new UploadDataParameterBuilderV2.Builder(messageType);
	}

	public interface IMessageType {
		ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey);
	}

	public interface ISender {
		IStartData receiverOrSenderPublicKey(String receiverOrSenderPublicKey);
	}

	public interface IStartData {
		IBuild defineData(String name, String data, DataTextContentType contentType, String encoding);
		IData startDefineData();
	}

	public interface IData {
		IDataName name(String name);
	}

	public interface IDataName {
		IDataContentType data(String data);
	}

	public interface IDataContentType {
		IDataEncoding contentType(DataTextContentType contentType);
	}

	public interface IDataEncoding {
		IEndData encoding(String encoding);
	}

	public interface IEndData {
		IBuild endDefineData();
	}

	public interface IBuild {
		IBuild keywords(String keywords);
		IBuild metadata(String metadata);
		IBuild mosaics(Mosaic... mosaics);
		UploadDataParameter build() throws ApiException;
	}
	
	public interface IBuildMultipleData {
		List<UploadDataParameter> build() throws ApiException;
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IStartData, IEndData, IMessageType, IData, IDataContentType,
			IDataEncoding, IDataName, IBuild {

		/** The instance. */
		UploadDataParameter instance = null;

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
		public IEndData encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}

		@Override
		public IDataEncoding contentType(DataTextContentType contentType) {
			this.instance.setContentType(contentType.name());
			return this;
		}

		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		@Override
		public IDataContentType data(String data) {
			this.instance.setData(data);
			return this;
		}

		@Override
		public IStartData receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
			this.instance.setReceiverOrSenderPublicKey(receiverOrSenderPublicKey);
			return this;
		}

		@Override
		public IDataName name(String name) {
			this.instance.setName(name);
			return this;
		}

		@Override
		public IBuild endDefineData() {
			return this;
		}

		@Override
		public IBuild defineData(String name, String data, DataTextContentType contentType, String encoding) {
			this.instance.setName(name);
			this.instance.setData(data);
			this.instance.setContentType(contentType.name());
			this.instance.setEncoding(encoding);
			return this;
		}

		@Override
		public IData startDefineData() {
			return this;
		}

	}
}
