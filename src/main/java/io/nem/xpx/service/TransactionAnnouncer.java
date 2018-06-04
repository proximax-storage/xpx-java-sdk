package io.nem.xpx.service;

import io.nem.xpx.builder.TransferTransactionBuilder;
import io.nem.xpx.facade.upload.UploadException;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.model.Account;
import org.nem.core.model.Address;
import org.nem.core.model.Message;
import org.nem.core.model.TransferTransaction;
import org.nem.core.model.mosaic.Mosaic;
import org.nem.core.model.ncc.NemAnnounceResult;
import org.nem.core.model.primitive.Amount;

import static java.lang.String.format;

public class TransactionAnnouncer {

    private final TransactionFeeCalculators transactionFeeCalculators;
    private final TransactionSender transactionSender;

    public TransactionAnnouncer(TransactionFeeCalculators transactionFeeCalculators, TransactionSender transactionSender) {
        this.transactionFeeCalculators = transactionFeeCalculators;
        this.transactionSender = transactionSender;
    }

    public String announceTransactionForUploadedContent(Message nemMessage, String senderPrivateKey, String receiverPublicKey,
                                                        Mosaic[] mosaics) throws Exception {

        final TransferTransaction transferTransaction = new TransferTransactionBuilder(transactionFeeCalculators)
                .sender(new Account(new KeyPair(PrivateKey.fromHexString(senderPrivateKey))))
                .recipient(new Account(Address.fromPublicKey(PublicKey.fromHexString(receiverPublicKey))))
                .version(2)
                .amount(Amount.fromNem(1l))
                .message(nemMessage)
                .addMosaics(mosaics).buildAndSignTransaction();

        final NemAnnounceResult announceResult = transactionSender.sendTransferTransaction(transferTransaction);

        if (announceResult.getCode() == NemAnnounceResult.CODE_SUCCESS)
            return announceResult.getTransactionHash().toString();
        else
            throw new UploadException(
                    format("Announcement of Nem transaction failed: %s", announceResult.getMessage()));
    }
}
