package io.nem.xpx.builder;

import org.nem.core.model.mosaic.Mosaic;
import io.nem.ApiException;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadPathParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.monitor.UploadTransactionMonitor;
import io.nem.xpx.utils.KeyUtils;

public class UploadPathParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadPathParameterBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public static ISender senderPrivateKey(String senderPrivateKey) {
		return new UploadPathParameterBuilder.Builder(senderPrivateKey);
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

		IBuild path(String path);

		IBuild name(String name);

		IBuild keywords(String keywords);

		IBuild metaData(String metaData);

		IBuild mosaics(Mosaic... mosaics);

		UploadPathParameter build() throws ApiException;

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		UploadPathParameter instance = new UploadPathParameter();

		public Builder(String senderPrivateKey) {
			instance.setSenderPrivateKey(senderPrivateKey);
		}

		@Override
		public IBuild messageType(int messageType) {
			instance.setMessageType(messageType);
			return this;
		}

		@Override
		public IBuild path(String path) {
			instance.setPath(path);
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
		public UploadPathParameter build() throws ApiException {
			return instance;
		}

		@Override
		public IBuild recipientPublicKey(String recipientPublicKey) {
			instance.setRecipientPublicKey(recipientPublicKey);
			return this;
		}

	}
}
