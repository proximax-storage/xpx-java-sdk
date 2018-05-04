package io.nem.xpx.builder;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.mosaic.Mosaic;
import io.nem.xpx.adapters.cipher.CustomEncryption;
import io.nem.xpx.exceptions.ApiException;
import io.nem.xpx.model.UploadFileParameter;



/**
 * The Class UploadFileParameterBuilder.
 */
public class UploadFileParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadFileParameterBuilder() {
	}
	
	public static IInit init() {
		return new UploadFileParameterBuilder.Builder();
	}

	public static IMessageType messageType(int messageType) {
		return new UploadFileParameterBuilder.Builder(messageType);
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
		IName receiverOrSenderPublicKey(String receiverOrSenderPublicKey);
	}

	public interface IName {
		IBuild data(File data) throws IOException;
		IName name(String name);
		IName contentType(String contentType);
		IName encoding(String encoding);
	}

	public interface IBuild {
		IBuild keywords(String keywords);
		IBuild metadata(String metadata);
		IBuild mosaics(Mosaic... mosaics);

		UploadFileParameter build() throws ApiException;
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder
			implements IInit, ISender, IMessageType , IName, IBuild {

		/** The instance. */
		UploadFileParameter instance = null;
		Tika tika = new Tika();

		public Builder() {
			instance = new UploadFileParameter();
		}
		public Builder(int messageType) {
			instance = new UploadFileParameter();
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
		public UploadFileParameter build() throws ApiException {
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
		public IName encoding(String encoding) {
			this.instance.setEncoding(encoding);
			return this;
		}

		@Override
		public IName contentType(String contentType) {
			if(contentType == null || contentType.equals("")) {
				Tika tika = new Tika();
				contentType = tika.detect(contentType);
			}
			this.instance.setContentType(contentType);
			return this;
		}

		@Override
		public ISender senderOrReceiverPrivateKey(String senderOrReceiverPrivateKey) {
			this.instance.setSenderOrReceiverPrivateKey(senderOrReceiverPrivateKey);
			return this;
		}

		@Override
		public IBuild data(File data) throws IOException {
			this.instance.setData(data);
			this.instance.setName(data.getName());
			this.instance.setContentType(tika.detect(FileUtils.readFileToByteArray(data)));
			return this;
		}

		@Override
		public IName receiverOrSenderPublicKey(String receiverOrSenderPublicKey) {
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
