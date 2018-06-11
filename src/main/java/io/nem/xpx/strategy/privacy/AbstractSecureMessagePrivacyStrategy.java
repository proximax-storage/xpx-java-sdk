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


/**
 * The Class AbstractSecureMessagePrivacyStrategy.
 */
public abstract class AbstractSecureMessagePrivacyStrategy extends PrivacyStrategy {

    /** The key pair of private key. */
    public final KeyPair keyPairOfPrivateKey;
    
    /** The key pair of public key. */
    public final KeyPair keyPairOfPublicKey;
    
    /** The account with private key. */
    public final Account accountWithPrivateKey;
    
    /** The account with public key. */
    public final Account accountWithPublicKey;

    /**
     * Instantiates a new abstract secure message privacy strategy.
     *
     * @param privateKey the private key
     * @param publicKey the public key
     */
    public AbstractSecureMessagePrivacyStrategy(String privateKey, String publicKey) {
        checkParameter(privateKey != null, "private key is required");
        checkParameter(publicKey != null, "public key is required");

        this.keyPairOfPrivateKey = new KeyPair(PrivateKey.fromHexString(privateKey));
        this.keyPairOfPublicKey = new KeyPair(PublicKey.fromHexString(publicKey));
        this.accountWithPrivateKey = new Account(keyPairOfPrivateKey);
        this.accountWithPublicKey = new Account(keyPairOfPublicKey);
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#getNemMessageType()
     */
    @Override
    public NemMessageType getNemMessageType() {
        return NemMessageType.SECURE;
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#encodeToMessage(byte[])
     */
    @Override
    public Message encodeToMessage(byte[] data) {
        return SecureMessage.fromDecodedPayload(accountWithPrivateKey, accountWithPublicKey, data);
    }

    /* (non-Javadoc)
     * @see io.nem.xpx.strategy.privacy.PrivacyStrategy#decodeTransaction(org.nem.core.model.TransferTransaction)
     */
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
