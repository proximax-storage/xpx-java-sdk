package io.nem.xpx.builder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.crypto.Signature;
import org.nem.core.messages.PlainMessage;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.HashUtils;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransactionFeeCalculator;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.TransferTransactionAttachment;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.ncc.RequestAnnounce;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.serialization.BinarySerializer;
import org.nem.core.serialization.Deserializer;
import org.nem.core.serialization.JsonDeserializer;
import org.nem.core.serialization.JsonSerializer;
import org.nem.core.time.SystemTimeProvider;
import org.nem.core.time.TimeInstant;
import io.nem.ApiException;
import io.nem.xpx.NemAccountApi;
import io.nem.xpx.factory.AttachmentFactory;
import io.nem.xpx.model.InsufficientAmountException;
import io.nem.xpx.model.RequestAnnounceDataSignature;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.utils.TransactionSenderUtil;


/**
 * The Class TransactionBuilder.
 */
public class BinaryTransferTransactionBuilder {

	/**
	 * Instantiates a new transaction builder.
	 */
	private BinaryTransferTransactionBuilder() {
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the i sender
	 */
	public static ISender sender(Account sender) {
		return new BinaryTransferTransactionBuilder.Builder(sender);
	}

	/**
	 * Sender.
	 *
	 * @param sender the sender
	 * @return the i sender
	 */
	public static ISender sender(String sender) {
		return new BinaryTransferTransactionBuilder.Builder(sender);
	}

	/**
	 * The Interface ISender.
	 */
	public interface ISender {

		/**
		 * Recipient.
		 *
		 * @param recipient
		 *            the recipient
		 * @return the i recipient
		 */
		IBuild recipient(String recipient);

		/**
		 * Recipient.
		 *
		 * @param recipient the recipient
		 * @return the i build
		 */
		IBuild recipient(Account recipient);

	}

	/**
	 * The Interface IBuild.
	 */
	public interface IBuild {

		/**
		 * Version.
		 *
		 * @param version
		 *            the version
		 * @return the i build
		 */
		IBuild version(int version);

		/**
		 * Sign by.
		 *
		 * @param account
		 *            the account
		 * @return the i build
		 */
		IBuild signBy(Account account);

		/**
		 * Time stamp.
		 *
		 * @param timeInstance
		 *            the time instance
		 * @return the i build
		 */
		IBuild timeStamp(TimeInstant timeInstance);

		/**
		 * Fee.
		 *
		 * @param amount
		 *            the amount
		 * @return the i build
		 */
		IBuild fee(Amount amount);

		/**
		 * Fee calculator.
		 *
		 * @param feeCalculator
		 *            the fee calculator
		 * @return the i build
		 */
		IBuild feeCalculator(TransactionFeeCalculator feeCalculator);

		/**
		 * Amount.
		 *
		 * @param amount
		 *            the amount
		 * @return the i build
		 */
		IBuild amount(Amount amount);

		/**
		 * Message.
		 *
		 * @param message
		 *            the message
		 * @param messageType
		 *            the message type
		 * @return the i build
		 */
		IBuild message(String message, int messageType);

		/**
		 * Message.
		 *
		 * @param message
		 *            the message
		 * @param messageType
		 *            the message type
		 * @return the i build
		 */
		IBuild message(byte[] message, int messageType);

		/**
		 * Adds the mosaic.
		 *
		 * @param mosaic
		 *            the mosaic
		 * @return the i build
		 */
		IBuild addMosaic(Mosaic mosaic);

		/**
		 * Adds the mosaic.
		 *
		 * @param mosaic
		 *            the mosaic
		 * @param quantity
		 *            the quantity
		 * @return the i build
		 */
		IBuild addMosaic(MosaicId mosaic, Quantity quantity);

		/**
		 * Adds the mosaics.
		 *
		 * @param mosaic the mosaic
		 * @return the i build
		 */
		IBuild addMosaics(Mosaic... mosaic);

		/**
		 * Encrypted message.
		 *
		 * @param encryptedMessage the encrypted message
		 * @return the i build
		 */
		IBuild encryptedMessage(String encryptedMessage);

		/**
		 * Attachment.
		 *
		 * @param attachment
		 *            the attachment
		 * @return the i build
		 */
		IBuild attachment(TransferTransactionAttachment attachment);

		/**
		 * Deadline.
		 *
		 * @param timeInstant
		 *            the time instant
		 * @return the i build
		 */
		IBuild deadline(TimeInstant timeInstant);

		/**
		 * Signature.
		 *
		 * @param signature
		 *            the signature
		 * @return the i build
		 */
		IBuild signature(Signature signature);

		/**
		 * Builds the and send transaction.
		 *
		 * @return the transaction
		 */
		BinaryTransferTransaction buildTransaction();

		/**
		 * Builds the transaction.
		 *
		 * @param isForMultisig the is for multisig
		 * @return the binary transfer transaction
		 */
		BinaryTransferTransaction buildTransaction(boolean isForMultisig);

		/**
		 * Builds the and sign transaction.
		 *
		 * @return the request announce data signature
		 */
		RequestAnnounceDataSignature buildAndSignTransaction();

		/**
		 * Builds the and send transaction.
		 *
		 * @return the nem announce result
		 * @throws ApiException the api exception
		 * @throws InterruptedException the interrupted exception
		 * @throws ExecutionException the execution exception
		 * @throws InsufficientAmountException the insufficient amount exception
		 */
		NemAnnounceResult buildAndSendTransaction()
				throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException;

		/**
		 * Builds the sign and send transaction.
		 *
		 * @return the nem announce result
		 * @throws ApiException the api exception
		 * @throws InterruptedException the interrupted exception
		 * @throws ExecutionException the execution exception
		 * @throws InsufficientAmountException the insufficient amount exception
		 */
		NemAnnounceResult buildSignAndSendTransaction()
				throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException;

		/**
		 * Builds the and send future transaction.
		 *
		 * @return the completable future
		 * @throws ApiException the api exception
		 * @throws InterruptedException the interrupted exception
		 * @throws ExecutionException the execution exception
		 * @throws InsufficientAmountException the insufficient amount exception
		 */
		CompletableFuture<Deserializer> buildAndSendFutureTransaction()
				throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException;
	}

	/**
	 * The Class Builder.
	 */
	private static class Builder implements ISender, IBuild {

		/** The instance. */
		private BinaryTransferTransaction instance;

		/** The version. */
		// constructor
		private int version;

		/** The time stamp. */
		private TimeInstant timeStamp;

		/** The sender. */
		private Account sender;

		/** The recipient. */
		private Account recipient;

		/** The amount. */
		private Amount amount;

		/** The attachment. */
		private TransferTransactionAttachment attachment;

		/** The signature. */
		private Signature signature;

		/** The deadline. */
		private TimeInstant deadline;

		/** The fee. */
		// secondary
		private Amount fee;

		/** The fee calculator. */
		private TransactionFeeCalculator feeCalculator;

		/** The sign by. */
		private Account signBy;

		/** The encrypted message. */
		private String encryptedMessage;

		/** The message. */
		private String message;

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender
		 *            the sender
		 */
		public Builder(Account sender) {
			this.sender = sender;
		}

		/**
		 * Instantiates a new builder.
		 *
		 * @param sender the sender
		 */
		public Builder(String sender) {
			this.sender = new Account(new KeyPair(PrivateKey.fromHexString(sender)));
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.ISender#recipient(java.lang.String)
		 */
		@Override
		public IBuild recipient(String recipient) {
			this.recipient = new Account(Address.fromPublicKey(PublicKey.fromHexString(recipient)));
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.GenericTransactionBuilder.ISender#recipient(
		 * org.nem.core.model.Account)
		 */
		@Override
		public IBuild recipient(Account recipient) {
			this.recipient = recipient;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IRecipient#amount(java.lang. Long)
		 */
		@Override
		public IBuild amount(Amount amount) {
			this.amount = amount;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#attachment(org.nem.
		 * core.model.TransferTransactionAttachment)
		 */
		@Override
		public IBuild attachment(TransferTransactionAttachment attachment) {
			this.attachment = attachment;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#buildTransaction()
		 */
		@Override
		public BinaryTransferTransaction buildTransaction() {
			if (this.timeStamp == null) {
				this.timeStamp = new SystemTimeProvider().getCurrentTime();
			}

			if (this.amount == null) {
				this.amount(Amount.fromNem(0));
			}

			if (this.version == 0) {
				instance = new BinaryTransferTransaction(this.timeStamp, this.sender, this.recipient, this.amount,
						this.attachment);
			} else {
				instance = new BinaryTransferTransaction(this.version, this.timeStamp, this.sender, this.recipient,
						this.amount, this.attachment);
			}

			Amount amountFee = null;
			TransactionFeeCalculator transactionFeeCalculator = null;
			transactionFeeCalculator = XpxSdkGlobalConstants.getGlobalTransactionFee();
			amountFee = XpxSdkGlobalConstants.getGlobalTransactionFee().calculateMinimumFee(instance);

			if (this.fee == null && this.feeCalculator == null) {
				instance.setFee(amountFee);
			} else {

				if (this.fee != null) {
					instance.setFee(this.fee);
				} else if (this.feeCalculator != null) {
					TransactionFeeCalculator feeCalculator;
					if (this.feeCalculator != null) {
						feeCalculator = this.feeCalculator;
					} else {
						feeCalculator = transactionFeeCalculator;
					}
					instance.setFee(feeCalculator.calculateMinimumFee(instance));
				}
			}

			if (this.deadline != null) {
				instance.setDeadline(this.deadline);
			} else {
				instance.setDeadline(this.timeStamp.addHours(23));
			}
			if (this.signature != null) {
				instance.setSignature(this.signature);
			}
			if (this.signBy != null) {
				instance.signBy(this.signBy);
			}
			if (this.encryptedMessage != null) {
				instance.setEncryptedMessage(this.encryptedMessage);
			}

			return instance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild# buildAndSendTransaction()
		 */
		@Override
		public NemAnnounceResult buildAndSendTransaction()
				throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException {
			this.buildTransaction();
			return TransactionSenderUtil.sendTransferTransaction(this.instance);
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.IBuild#buildSignAndSendTransaction()
		 */
		@Override
		public NemAnnounceResult buildSignAndSendTransaction()
				throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException {
			this.buildTransaction().sign();
			return TransactionSenderUtil.sendTransferTransaction(this.instance);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#fee(org.nem.core.model
		 * .primitive.Amount)
		 */
		@Override
		public IBuild fee(Amount amount) {
			this.fee = amount;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#deadline(org.nem.core.
		 * time.TimeInstant)
		 */
		@Override
		public IBuild deadline(TimeInstant deadline) {
			this.deadline = deadline;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.builders.TransactionBuilder.IBuild#signature(org.nem.core
		 * .crypto.Signature)
		 */
		@Override
		public IBuild signature(Signature signature) {
			this.signature = signature;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.TransactionBuilder.IBuild#message(java.lang.
		 * String, org.nem.core.model.MessageTypes)
		 */
		@Override
		public IBuild message(String message, int messageType) {
			this.message = message;
			this.encryptedMessage = message;
			Message transactionMessage = null;
			if (messageType == MessageTypes.SECURE) {
				transactionMessage = SecureMessage.fromDecodedPayload(this.sender, this.recipient, message.getBytes());
			} else {
				transactionMessage = new PlainMessage(message.getBytes());
			}

			if (this.attachment == null) {
				this.attachment = (AttachmentFactory.createTransferTransactionAttachmentMessage(transactionMessage));
			} else {
				this.attachment.setMessage(transactionMessage);
			}

			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.TransactionBuilder.IBuild#message(byte[],
		 * org.nem.core.model.MessageTypes)
		 */
		@Override
		public IBuild message(byte[] message, int messageType) {
			this.message = new String(message);
			Message transactionMessage = null;
			if (messageType == MessageTypes.SECURE) {
				transactionMessage = SecureMessage.fromDecodedPayload(this.sender, this.recipient, message);
			} else {
				transactionMessage = new PlainMessage(message);
			}

			if (this.attachment == null) {
				this.attachment = (AttachmentFactory.createTransferTransactionAttachmentMessage(transactionMessage));
			} else {
				this.attachment.setMessage(transactionMessage);
			}

			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.spectro.builders.GenericTransactionBuilder.IBuild#
		 * feeCalculator(org.nem.core.model.TransactionFeeCalculator)
		 */
		@Override
		public IBuild feeCalculator(TransactionFeeCalculator feeCalculator) {
			this.feeCalculator = feeCalculator;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.TransferTransactionBuilder.IBuild#version(int)
		 */
		@Override
		public IBuild version(int version) {
			this.version = version;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.TransferTransactionBuilder.IBuild#timeStamp(org.
		 * nem.core.time.TimeInstant)
		 */
		@Override
		public IBuild timeStamp(TimeInstant timeInstance) {
			this.timeStamp = timeInstance;
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.TransferTransactionBuilder.IBuild#signBy(org.nem
		 * .core.model.Account)
		 */
		@Override
		public IBuild signBy(Account account) {
			this.signBy = account;
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.IBuild#buildAndSendFutureTransaction()
		 */
		@Override
		public CompletableFuture<Deserializer> buildAndSendFutureTransaction()
				throws ApiException, InterruptedException, ExecutionException, InsufficientAmountException {
			this.buildTransaction();
			return TransactionSenderUtil.sendFutureTransferTransaction(this.instance);
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.IBuild#encryptedMessage(java.lang.String)
		 */
		@Override
		public IBuild encryptedMessage(String message) {
			this.encryptedMessage = message;
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.IBuild#buildAndSignTransaction()
		 */
		@Override
		public RequestAnnounceDataSignature buildAndSignTransaction() {

			this.buildTransaction().sign();
			final byte[] data = BinarySerializer.serializeToBytes(instance.asNonVerifiable());
			final RequestAnnounce request = new RequestAnnounce(data, instance.getSignature().getBytes());
			RequestAnnounceDataSignature requestAnnounceDataSignature = new RequestAnnounceDataSignature();
			requestAnnounceDataSignature.setData(
					new JsonDeserializer(JsonSerializer.serializeToJson(request), null).readString("data", 5000));
			requestAnnounceDataSignature.setSignature(
					new JsonDeserializer(JsonSerializer.serializeToJson(request), null).readString("signature", 5000));
			return requestAnnounceDataSignature;

		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.IBuild#addMosaic(org.nem.core.model.mosaic.Mosaic)
		 */
		@Override
		public IBuild addMosaic(Mosaic mosaic) {
			if (this.attachment == null) {
				this.attachment = new TransferTransactionAttachment();
			} else {
				this.attachment.addMosaic(mosaic);
			}
			return this;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see io.nem.apps.builders.TransferTransactionBuilder.IBuild#addMosaic(org.
		 * nem.core.model.mosaic.MosaicId, org.nem.core.model.primitive.Quantity)
		 */
		@Override
		public IBuild addMosaic(MosaicId mosaic, Quantity quantity) {
			this.attachment.addMosaic(mosaic, quantity);
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.IBuild#addMosaics(org.nem.core.model.mosaic.Mosaic[])
		 */
		@Override
		public IBuild addMosaics(Mosaic... mosaics) {
			if (mosaics != null) {
				for (Mosaic mosaic : mosaics) {
					this.attachment.addMosaic(mosaic);
				}
			}
			return this;
		}

		/* (non-Javadoc)
		 * @see io.nem.xpx.builder.BinaryTransferTransactionBuilder.IBuild#buildTransaction(boolean)
		 */
		@Override
		public BinaryTransferTransaction buildTransaction(boolean isForMultisig) {
			if (this.timeStamp == null) {
				this.timeStamp = XpxSdkGlobalConstants.TIME_PROVIDER.getCurrentTime();
			}

			if (this.amount == null) {
				this.amount(Amount.fromNem(0));
			}

			if (this.version == 0) {
				instance = new BinaryTransferTransaction(this.timeStamp, this.sender, this.recipient, this.amount,
						this.attachment);
			} else {
				instance = new BinaryTransferTransaction(this.version, this.timeStamp, this.sender, this.recipient,
						this.amount, this.attachment);
			}

			Amount amountFee = null;
			if (this.fee != null) {
				amountFee = this.fee;
			} else if (this.feeCalculator != null) {
				amountFee = this.feeCalculator.calculateMinimumFee(instance);
			} else {
				TransactionFeeCalculator globalFeeCalculator = isForMultisig
						? XpxSdkGlobalConstants.getGlobalMultisigTransactionFee()
						: XpxSdkGlobalConstants.getGlobalTransactionFee();
				amountFee = globalFeeCalculator.calculateMinimumFee(instance);
			}
			instance.setFee(amountFee);

			if (this.deadline != null) {
				instance.setDeadline(this.deadline);
			} else {
				instance.setDeadline(this.timeStamp.addHours(23));
			}
			if (this.signature != null) {
				instance.setSignature(this.signature);
			}
			if (this.signBy != null) {
				instance.signBy(this.signBy);
			}

			return instance;
		}

	}

}
