package io.nem.xpx.builder;

import java.io.File;

import org.nem.core.model.mosaic.Mosaic;
import io.nem.ApiException;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadFileParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.monitor.UploadTransactionMonitor;
import io.nem.xpx.utils.KeyUtils;

public class UploadFileParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadFileParameterBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public static ISender senderPrivateKey(String senderPrivateKey) {
		return new UploadFileParameterBuilder.Builder(senderPrivateKey);
	}

	/**
	 * The Interface ISender.
	 */
	public interface ISender {

		IBuild recipientPublicKey(String recipientPublicKey);

	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		IBuild messageType(int messageType);

		IBuild data(File data);

		IBuild name(String name);

		IBuild keywords(String keywords);

		IBuild metaData(String metaData);

		IBuild mosaics(Mosaic... mosaics);

		UploadFileParameter build() throws ApiException;

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		UploadFileParameter instance = new UploadFileParameter();

		public Builder(String senderPrivateKey) {
			instance.setSenderPrivateKey(senderPrivateKey);
		}

		@Override
		public IBuild messageType(int messageType) {
			instance.setMessageType(messageType);
			return this;
		}

		@Override
		public IBuild data(File data) {
			instance.setData(data);
			return this;
		}

		@Override
		public IBuild name(String name) {
			instance.setName(name);
			return this;
		}

		@Override
		public IBuild keywords(String keywords) {
			instance.setKeywords(keywords);
			return this;
		}

		@Override
		public IBuild metaData(String metaData) {
			instance.setMetaData(metaData);
			return this;
		}

		@Override
		public IBuild mosaics(Mosaic... mosaics) {
			instance.setMosaics(mosaics);
			return this;
		}

		@Override
		public UploadFileParameter build() throws ApiException {
			
			return instance;
		}

		@Override
		public IBuild recipientPublicKey(String recipientPublicKey) {
			instance.setRecipientPublicKey(recipientPublicKey);
			return this;
		}

	}
}
