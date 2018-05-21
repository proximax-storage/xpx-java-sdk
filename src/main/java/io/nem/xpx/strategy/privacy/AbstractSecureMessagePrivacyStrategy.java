package io.nem.xpx.strategy.privacy;

import io.nem.xpx.exceptions.DecodeNemMessageFailureException;
import io.nem.xpx.model.NemMessageType;
import org.nem.core.crypto.KeyPair;
import org.nem.core.crypto.PrivateKey;
import org.nem.core.crypto.PublicKey;
import org.nem.core.messages.SecureMessage;
import org.nem.core.model.Account;
import org.nem.core.model.Message;
import org.nem.core.model.MessageTypes;
import org.nem.core.model.TransferTransaction;

import static io.nem.xpx.utils.ParameterValidationUtils.checkParameter;

public abstract class AbstractSecureMessagePrivacyStrategy extends PrivacyStrategy {

    public final KeyPair keyPairOfPrivateKey;
    public final KeyPair keyPairOfPublicKey;
    public final Account accountWithPrivateKey;
    public final Account accountWithPublicKey;

    public AbstractSecureMessagePrivacyStrategy(String privateKey, String publicKey) {
        checkParameter(privateKey != null, "private key is required");
        checkParameter(publicKey != null, "public key is required");

        this.keyPairOfPrivateKey = new KeyPair(PrivateKey.fromHexString(privateKey));
        this.keyPairOfPublicKey = new KeyPair(PublicKey.fromHexString(publicKey));
        this.accountWithPrivateKey = new Account(keyPairOfPrivateKey);
        this.accountWithPublicKey = new Account(keyPairOfPublicKey);
    }

    @Override
    public NemMessageType getNemMessageType() {
        return NemMessageType.SECURE;
    }

    @Override
    public Message encodeToMessage(byte[] data) {
        return SecureMessage.fromDecodedPayload(accountWithPrivateKey, accountWithPublicKey, data);
    }

    @Override
    public byte[] decodeTransaction(TransferTransaction transaction) {
        if (transaction.getMessage().getType() == MessageTypes.PLAIN)
            return transaction.getMessage().getDecodedPayload();

        if (transaction.getSigner().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded())) {
            return SecureMessage.fromEncodedPayload(accountWithPrivateKey, accountWithPublicKey,
                    transaction.getMessage().getEncodedPayload()).getDecodedPayload();

        } else if (transaction.getRecipient().getAddress().getEncoded().equals(accountWithPrivateKey.getAddress().getEncoded())) {
            return SecureMessage.fromEncodedPayload(accountWithPublicKey, accountWithPrivateKey,
                    transaction.getMessage().getEncodedPayload()).getDecodedPayload();

        } else {
            throw new DecodeNemMessageFailureException("Private key cannot be used to decode the Nem secured message.");
        }
    }
}
