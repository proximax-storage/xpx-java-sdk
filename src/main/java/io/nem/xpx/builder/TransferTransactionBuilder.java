package io.nem.xpx.builder;

import io.nem.xpx.factory.AttachmentFactory;
import io.nem.xpx.model.XpxSdkGlobalConstants;
import io.nem.xpx.service.TransactionFeeCalculators;
import org.nem.core.crypto.Signature;
import org.nem.core.model.*;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.mosaic.MosaicId;
import org.nem.core.model.namespace.NamespaceId;
import org.nem.core.model.primitive.Amount;
import org.nem.core.model.primitive.Quantity;
import org.nem.core.time.TimeInstant;

public class TransferTransactionBuilder {

	private final TransactionFeeCalculators transactionFeeCalculators;

	private int version;

	private TimeInstant timeStamp;

	private Account sender;

	private Account recipient;

	private Amount amount;

	private TransferTransactionAttachment attachment;

	private Signature signature;

	private TimeInstant deadline;

	private Amount fee;

	private TransactionFeeCalculator feeCalculator;

	private Account signBy;

	public TransferTransactionBuilder(TransactionFeeCalculators transactionFeeCalculators) {
	    this.transactionFeeCalculators = transactionFeeCalculators;
	}

	public TransferTransactionBuilder sender(Account sender) {
		this.sender = sender;
		return this;
	}

	public TransferTransactionBuilder recipient(Account recipient) {
		this.recipient = recipient;
		return this;
	}

	public TransferTransactionBuilder amount(Amount amount) {
		this.amount = amount;
		return this;
	}

	public TransferTransactionBuilder attachment(TransferTransactionAttachment attachment) {
		this.attachment = attachment;
		return this;
	}

	public TransferTransaction buildTransaction() {
		return buildTransaction(false);
	}

	public TransferTransactionBuilder fee(Amount amount) {
		this.fee = amount;
		return this;
	}

	public TransferTransactionBuilder deadline(TimeInstant deadline) {
		this.deadline = deadline;
		return this;
	}

	public TransferTransactionBuilder signature(Signature signature) {
		this.signature = signature;
		return this;
	}

	public TransferTransactionBuilder message(Message message) {
		if (this.attachment == null) {
			this.attachment = (AttachmentFactory.createTransferTransactionAttachmentMessage(message));
		} else {
			this.attachment.setMessage(message);
		}

		return this;
	}

	public TransferTransactionBuilder feeCalculator(TransactionFeeCalculator feeCalculator) {
		this.feeCalculator = feeCalculator;
		return this;
	}

	public TransferTransactionBuilder version(int version) {
		this.version = version;
		return this;
	}

	public TransferTransactionBuilder timeStamp(TimeInstant timeInstance) {
		this.timeStamp = timeInstance;
		return this;
	}

	public TransferTransactionBuilder signBy(Account account) {
		this.signBy = account;
		return this;
	}

	public TransferTransactionBuilder addMosaic(Mosaic mosaic) {
		if (this.attachment == null) {
			this.attachment = AttachmentFactory.createTransferTransactionAttachmentMosaic(mosaic);
		} else {
			this.attachment.addMosaic(mosaic);
		}
		return this;
	}

	public TransferTransactionBuilder addMosaic(MosaicId mosaic, Quantity quantity) {
		this.attachment.addMosaic(mosaic, quantity);
		return this;
	}

	public TransferTransactionBuilder addMosaics(Mosaic... mosaics) {
		for (Mosaic mosaic : mosaics) {
			this.attachment.addMosaic(mosaic);
		}

		return this;
	}


	public TransferTransaction buildTransaction(boolean isForMultisig) {

	    TransferTransaction instance;

		//	attach the xpx mosaic (constant)
		Mosaic xpxMosaic = new Mosaic(new MosaicId(new NamespaceId("prx"), "xpx"),
				Quantity.fromValue(10000));

		this.attachment.addMosaic(xpxMosaic);

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
			instance = new TransferTransaction(this.version, this.timeStamp, this.sender, this.recipient,
					this.amount, this.attachment);
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

	public TransferTransaction buildAndSignTransaction() {
		final TransferTransaction transferTransaction = buildTransaction();
		transferTransaction.sign();
		return transferTransaction;
	}
}
