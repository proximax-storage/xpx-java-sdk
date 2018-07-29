/*
 * Copyright 2018 ProximaX Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.proximax.xpx.builder;

import io.proximax.xpx.factory.AttachmentFactory;
import io.proximax.xpx.model.XpxSdkGlobalConstants;
import io.proximax.xpx.service.TransactionFeeCalculators;
import org.nem.core.crypto.Signature;
import org.nem.core.model.*;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.time.TimeInstant;

/**
 * The Class TransferTransactionBuilder.
 */
public class TransferTransactionBuilder {

	/** The transaction fee calculators. */
	private final TransactionFeeCalculators transactionFeeCalculators;

	/** The version. */
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
	private Amount fee;

	/** The fee calculator. */
	private TransactionFeeCalculator feeCalculator;

	/** The sign by. */
	private Account signBy;

	/**
	 * Instantiates a new transfer transaction builder.
	 *
	 * @param transactionFeeCalculators
	 *            the transaction fee calculators
	 */
	public TransferTransactionBuilder(TransactionFeeCalculators transactionFeeCalculators) {
		this.transactionFeeCalculators = transactionFeeCalculators;
	}

	/**
	 * Sender.
	 *
	 * @param sender
	 *            the sender
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder sender(Account sender) {
		this.sender = sender;
		return this;
	}

	/**
	 * Recipient.
	 *
	 * @param recipient
	 *            the recipient
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder recipient(Account recipient) {
		this.recipient = recipient;
		return this;
	}

	/**
	 * Amount.
	 *
	 * @param amount
	 *            the amount
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder amount(Amount amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Attachment.
	 *
	 * @param attachment
	 *            the attachment
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder attachment(TransferTransactionAttachment attachment) {
		this.attachment = attachment;
		return this;
	}

	/**
	 * Builds the transaction.
	 *
	 * @return the transfer transaction
	 */
	public TransferTransaction buildTransaction() {
		return buildTransaction(false);
	}

	/**
	 * Fee.
	 *
	 * @param amount
	 *            the amount
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder fee(Amount amount) {
		this.fee = amount;
		return this;
	}

	/**
	 * Deadline.
	 *
	 * @param deadline
	 *            the deadline
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder deadline(TimeInstant deadline) {
		this.deadline = deadline;
		return this;
	}

	/**
	 * Signature.
	 *
	 * @param signature
	 *            the signature
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder signature(Signature signature) {
		this.signature = signature;
		return this;
	}

	/**
	 * Message.
	 *
	 * @param message
	 *            the message
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder message(Message message) {
		if (this.attachment == null) {
			this.attachment = (AttachmentFactory.createTransferTransactionAttachmentMessage(message));
		} else {
			this.attachment.setMessage(message);
		}

		return this;
	}

	/**
	 * Fee calculator.
	 *
	 * @param feeCalculator
	 *            the fee calculator
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder feeCalculator(TransactionFeeCalculator feeCalculator) {
		this.feeCalculator = feeCalculator;
		return this;
	}

	/**
	 * Version.
	 *
	 * @param version
	 *            the version
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder version(int version) {
		this.version = version;
		return this;
	}

	/**
	 * Time stamp.
	 *
	 * @param timeInstance
	 *            the time instance
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder timeStamp(TimeInstant timeInstance) {
		this.timeStamp = timeInstance;
		return this;
	}

	/**
	 * Sign by.
	 *
	 * @param account
	 *            the account
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder signBy(Account account) {
		this.signBy = account;
		return this;
	}

	/**
	 * Adds the mosaic.
	 *
	 * @param mosaic
	 *            the mosaic
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder addMosaic(Mosaic mosaic) {
		if (this.attachment == null) {
			this.attachment = AttachmentFactory.createTransferTransactionAttachmentMosaic(mosaic);
		} else {
			this.attachment.addMosaic(mosaic);
		}
		return this;
	}

	/**
	 * Adds the mosaic.
	 *
	 * @param mosaic
	 *            the mosaic
	 * @param quantity
	 *            the quantity
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder addMosaic(MosaicId mosaic, Quantity quantity) {
		if (this.attachment == null) {
			this.attachment = (AttachmentFactory.createTransferTransactionAttachment());
		}
		this.attachment.addMosaic(mosaic, quantity);
		return this;
	}

	/**
	 * Adds the mosaics.
	 *
	 * @param mosaics
	 *            the mosaics
	 * @return the transfer transaction builder
	 */
	public TransferTransactionBuilder addMosaics(Mosaic... mosaics) {
		if (this.attachment == null) {
			this.attachment = (AttachmentFactory.createTransferTransactionAttachment());
		}

		for (Mosaic mosaic : mosaics) {
			this.attachment.addMosaic(mosaic);
		}
		return this;
	}

	/**
	 * Builds the transaction.
	 *
	 * @param isForMultisig
	 *            the is for multisig
	 * @return the transfer transaction
	 */
	public TransferTransaction buildTransaction(boolean isForMultisig) {

		TransferTransaction instance;

		if (this.timeStamp == null) {
			this.timeStamp = XpxSdkGlobalConstants.TIME_PROVIDER.getCurrentTime();
		}

		if (this.amount == null) {
			this.amount(Amount.fromNem(0));
		}

		if (this.version == 0) {
			instance = new TransferTransaction(this.timeStamp, this.sender, this.recipient, this.amount,
					this.attachment);
		} else {
			instance = new TransferTransaction(this.version, this.timeStamp, this.sender, this.recipient, this.amount,
					this.attachment);
		}

		// fee
		Amount amountFee;
		if (this.fee != null) {
			amountFee = this.fee;
		} else if (this.feeCalculator != null) {
			amountFee = this.feeCalculator.calculateMinimumFee(instance);
		} else {
			TransactionFeeCalculator globalFeeCalculator = isForMultisig
					? transactionFeeCalculators.getFeeCalculatorMultiSig(this.sender.getAddress().getEncoded())
					: transactionFeeCalculators.getFeeCalculator(this.sender.getAddress().getEncoded());
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

	/**
	 * Builds the and sign transaction.
	 *
	 * @return the transfer transaction
	 */
	public TransferTransaction buildAndSignTransaction() {
		final TransferTransaction transferTransaction = buildTransaction();
		transferTransaction.sign();
		return transferTransaction;
	}
}
