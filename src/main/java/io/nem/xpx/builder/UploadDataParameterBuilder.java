package io.nem.xpx.builder;

import org.nem.core.model.mosaic.Mosaic;
import io.nem.ApiException;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.monitor.UploadTransactionMonitor;
import io.nem.xpx.utils.KeyUtils;

public class UploadDataParameterBuilder {
	/**
	 * Instantiates a new transaction builder.
	 */
	private UploadDataParameterBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public static ISender senderPrivateKey(String senderPrivateKey) {
		return new UploadDataParameterBuilder.Builder(senderPrivateKey);
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

		IBuild data(String data);

		IBuild name(String name);

		IBuild keywords(String keywords);

		IBuild metaData(String metaData);

		IBuild mosaics(Mosaic... mosaics);

		IBuild confirmedTransactionHandler(UploadTransactionMonitor handler);

		IBuild unconfirmedTransactionHandler(UploadTransactionMonitor handler);

		UploadDataParameter build() throws ApiException;

	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		UploadDataParameter instance = new UploadDataParameter();

		public Builder(String senderPrivateKey) {
			instance.setSenderPrivateKey(senderPrivateKey);
		}

		@Override
		public IBuild messageType(int messageType) {
			instance.setMessageType(messageType);
			return this;
		}

		@Override
		public IBuild data(String data) {
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
		public IBuild confirmedTransactionHandler(UploadTransactionMonitor handler) {
			instance.setConfirmedTransactionHandler(handler);
			return this;
		}

		@Override
		public IBuild unconfirmedTransactionHandler(UploadTransactionMonitor handler) {
			instance.setUnconfirmedTransactionHandler(handler);
			return this;
		}

		@Override
		public UploadDataParameter build() throws ApiException {
			TransactionMonitorBuilder.init()
					.addressesToMonitor(KeyUtils.getAddressFromPrivateKey(this.instance.getSenderPrivateKey())) // address to monitor
					.subscribe(XpxSdkGlobalConstants.URL_WS_TRANSACTIONS,
							this.instance.getConfirmedTransactionHandler()) // multiple subscription and a handler
					.subscribe(XpxSdkGlobalConstants.URL_WS_TRANSACTIONS, new UploadTransactionMonitor())
					.monitor(); // trigger the monitoring process
			
			return instance;
		}

		@Override
		public IBuild recipientPublicKey(String recipientPublicKey) {
			instance.setRecipientPublicKey(recipientPublicKey);
			return this;
		}

	}
}
